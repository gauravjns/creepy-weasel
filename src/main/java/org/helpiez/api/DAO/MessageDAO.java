package org.helpiez.api.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.helpiez.api.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class MessageDAO {
	
	@Autowired
    protected JdbcTemplate jdbc;
	
	public Message getMsgbyID(int id) {
		Message msg= new Message();
		msg =jdbc.queryForObject("SELECT * FROM message WHERE msgid=?", new msgMapper(), id);
		return msg;
	}
	
	// sending all the updates so that client remain updated
	public List<Message> getMsgslist(int id, long max) {
		List<Message> msList = jdbc.query("select msgid,user.userimg,user.userurl,user.username, message.userid, msg, thread, timestamp, usertoid, viewed from message, user where message.userid=user.userid and usertoid=? and msgid>? ", new msgMapper1(),id, max);
		return msList;
	}

	// Take all threads which have one unread msg and send top msg from that htread
	// This also select thread which other person have not read (neg impression of platform)
	public List<Message> getMsgsforuser(long userid, int limit, int viewed) {
		List <Message> lst = new ArrayList<Message>();
		Message msg= null;
		try{
			List<Message> thread = jdbc.query("select thread from message where userid=? or usertoid=? and viewed=? group by thread ORDER BY max(timestamp) desc", new threadMapper(), userid, userid, viewed);
			if (thread.size()<limit)
			{
				thread = jdbc.query("select thread from message where userid=? or usertoid=? group by thread order by max(timestamp) DESC limit 5", new threadMapper(), userid, userid);
			}
			if (viewed==0 && limit ==0)
			{
				thread = jdbc.query("select thread from message where userid=? or usertoid=? group by thread order by max(timestamp) DESC ", new threadMapper(), userid, userid);
				
				if (thread!=null && thread.size()>0)
				{
					for (Message message : thread) {
						try 
						{
							msg= jdbc.queryForObject("select msgid,user.userimg,user.userurl,user.username, message.userid, msg, thread, timestamp, usertoid, viewed from message, user where message.userid=user.userid  and thread=?  ORDER BY timestamp  DESC limit 1 ", new msgMapper1(), message.getThread());
							if (msg.getUserid()==userid)
							{msg= jdbc.queryForObject("select msgid,user.userimg,user.userurl,user.username, message.userid, msg, thread, timestamp, usertoid, viewed from message, user where message.usertoid=user.userid  and thread=?  ORDER BY timestamp  DESC limit 1 ", new msgMapper1(), message.getThread());
							}
						}
						catch(Exception e)
						{}
						
						if(msg!=null)
						{
							lst.add(msg);
							msg=null;
						}
					}
				}
				return lst;
			}
			
			if (thread!=null && thread.size()>0)
			{
				for (Message message : thread) {
					try 
					{msg= jdbc.queryForObject("select msgid,user.userimg,user.userurl,user.username, message.userid, msg, thread, timestamp, usertoid, viewed from message, user where message.userid=user.userid and usertoid=? and viewed=1 and thread=?  ORDER BY timestamp  DESC limit 1 ", new msgMapper1(),userid, message.getThread());}
					catch(Exception e)
					{}
					
					if(msg!=null)
					{
						lst.add(msg);
						msg=null;
					}
				}
			}
			if (lst!=null) 
				Collections.reverse(lst);
			
			return lst;
			}
		catch(Exception e)
		{System.out.println(e);}
		return null;
	}
	
	
	
	public int save(Message msg) {
		int thread=0; 
		if (msg.getThread()>0)
		{
			thread= msg.getThread();
		}
		else {
			thread = threadDiscoverer(msg.getUserto(), msg.getUserid() );
		}
		return jdbc.update("INSERT INTO message (msgid, userid, msg, thread,timestamp, usertoid) VALUES ( Default , ? , ?, ?, Default, ?)",msg.getUserid() , msg.getMessage(), thread , msg.getUserto());
	
	}
	
	public int viewed(long msgid)
	{
		return jdbc.update("UPDATE message SET viewed=2 WHERE msgid =? ", msgid);
		
	}
	
	
	// Concurrency test
	public int threadDiscoverer(long userto, long userid) {
		Message msg= null;
		try {
		msg =jdbc.queryForObject("SELECT * FROM message WHERE userid=? and usertoid=? limit 1", new msgMapper(), userid, userto);
		}
		catch(Exception e)
		{}
		if (msg!=null)
		{
			return msg.getThread();
		}
		else {
			try{
			msg =jdbc.queryForObject("SELECT * FROM message WHERE usertoid=? and userid=? limit 1", new msgMapper(), userid, userto);
			}catch(Exception e)
			{}
			if (msg!=null)
			{
				return msg.getThread();
			}
			else
			{
				msg =jdbc.queryForObject("SELECT * FROM message order by thread Desc limit 1", new msgMapper());
				return msg.getThread()+1;
			}
			
		}
	}
	
	public List<Message> getMsgs(long id, long id2) {
		int thread = threadfinder(id,id2);
		if (thread==0)
			{return null;}
		return jdbc.query("SELECT * FROM message WHERE thread=? order by msgid desc", new msgMapper(), thread);			
		
		
	}
	

	public List<Message> getMsgsbythread(long thread) {
		return jdbc.query("select msgid,user.userimg,user.userurl,user.username, message.userid, msg, thread, timestamp, usertoid, viewed from message, user where message.userid=user.userid and thread=? order by msgid", new msgMapper1(), thread);	
	}

	public int threadfinder(long userto, long userid) {
		Message msg= null;
		try {
		msg =jdbc.queryForObject("SELECT * FROM message WHERE userid=? and usertoid=? limit 1", new msgMapper(), userid, userto);
		}
		catch(Exception e)
		{}
		if (msg!=null)
		{
			return msg.getThread();
		}
		else {
			try{
			msg =jdbc.queryForObject("SELECT * FROM message WHERE usertoid=? and userid=? limit 1", new msgMapper(), userid, userto);
			}catch(Exception e)
			{}
			if (msg!=null)
			{
				return msg.getThread();
			}
		}
		return 0;
	}

	// Result set mapper
	   private class msgMapper implements RowMapper<Message> {
				public Message mapRow(ResultSet rs, int rowNum) throws SQLException {
				Message msg = new Message();
	        	msg.setMsgid(rs.getLong("msgid"));
	        	msg.setUserid(rs.getLong("userid"));
	        	msg.setThread(rs.getInt("thread"));
	        	msg.setUserto(rs.getLong("usertoid"));
	        	msg.setMessage(rs.getString("msg"));
	        	msg.setTimestamp(rs.getTimestamp("timestamp"));
	        	msg.setViewed(rs.getShort("viewed"));
	            return msg;
	        }
	    }
	   
	   private class msgMapper1 implements RowMapper<Message> {
			public Message mapRow(ResultSet rs, int rowNum) throws SQLException {
			Message msg = new Message();
       	msg.setMsgid(rs.getLong("msgid"));
       	msg.setUserimg(rs.getString("userimg"));
       	msg.setUserlink(rs.getString("userurl"));
       	msg.setUserid(rs.getLong("userid"));
       	msg.setThread(rs.getInt("thread"));
       	msg.setUserto(rs.getLong("usertoid"));
       	msg.setMessage(rs.getString("msg"));
       	msg.setUsername(rs.getString("username"));
       	msg.setTimestamp(rs.getTimestamp("timestamp"));
       	msg.setViewed(rs.getShort("viewed"));
           return msg;
       }
   }

	   private class threadMapper implements RowMapper<Message> {
			public Message mapRow(ResultSet rs, int rowNum) throws SQLException {
			Message msg = new Message();
			msg.setThread(rs.getInt("thread"));
           return msg;
       }
   }



}
