package org.helpiez.api.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
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
		return jdbc.query("SELECT * FROM message WHERE thread=? order by timestamp desc", new msgMapper(), thread);			
		
		
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

	



}
