package org.helpiez.api.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.helpiez.api.model.CommonMeta;
import org.helpiez.api.model.Events;
import org.helpiez.api.model.Group;
import org.helpiez.api.response.ResActivity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

@Repository
public class EventsDAO {
	
	@Autowired
    protected JdbcTemplate jdbc;
	
	@Autowired
    protected CommonDAO commonDAO;
	

	@Autowired
    protected GroupDAO groupDAO;
	
	public Events getEventbyID(long id){
		Events event= new Events();
		event =jdbc.queryForObject("SELECT * FROM posts WHERE postid=?", new eventMapper(), id);
		return event;	
	}

	public Events getEvent(String urlname) {
		Events event= new Events();
		event =jdbc.queryForObject("SELECT * FROM posts WHERE posturl=?", new eventMapper(), urlname);
		return event;
	}
	
	public String save(Events event) {
		
	SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbc)
	            .withTableName("posts").usingColumns("postname", "posttype", "postxtra", "poststatus", "postgroupid", "posturl", "body", "img")
	            .usingGeneratedKeyColumns("postid");
	String url= commonDAO.urlgenerator(event.getName(), "post"); 
	Map<String,Object> insertParameters = new HashMap<String, Object>();
	insertParameters.put("postname", event.getName());
	insertParameters.put("posttype", event.getType());
	insertParameters.put("postxtra", event.getExtra());
	insertParameters.put("poststatus", event.getStatus());
	insertParameters.put("postgroupid", event.getGroupid());
	insertParameters.put("posturl", url);
	insertParameters.put("body", "");
	insertParameters.put("img", "");
	
	
	Number id = insert.executeAndReturnKey(insertParameters);
	System.out.println(id);
	int check2= insertupdate(event,getEventbyID(id.intValue()));
	if (check2==1)
	{
	return url;
	}
	else {return "";}
	}
	
	public Boolean update(Events event, Events event2) {
		if(event.getName()!=null)
		{
			event2.setName(event.getName());
		}
		if(event.getType()!=null)
		{
			event2.setType(event.getType());	
		}
		
		if(event.getStatus()!=0)
		{
			event2.setStatus(event.getStatus());
			
		}
		/*if(event.getUrl()!=null)
		{
			event2.setUrl(event.getUrl());
		}*/
		if(event.getGroupid()!=0)
		{
			event2.setGroupid(event.getGroupid());
		}
		if(event.getExtra()!=null)
		{
			event2.setExtra(event.getExtra());
		}
		
		int check =jdbc.update("UPDATE posts SET postname=?, poststatus=? ,posttype=?, posturl=?, postxtra=?, postgroupid=? WHERE postid =? ",event2.getName(), event2.getStatus(), event2.getType(), event2.getUrl(), event2.getExtra(), event2.getGroupid(),  event2.getId());
	
		int check2 = insertupdate(event, event2);
		
		if (check ==1 && check2==1)
		{
			return true;
		}
		else {
			return false;
		}
	}
	
	private int insertupdate(Events event,  Events event2update) {
		
		if(event.getCommentstatus()!=null)
		 {
			 if(event2update.getCommentstatus()!=null)
			 {
				 jdbc.update("UPDATE postmeta SET postmetavalue=? WHERE postid =? and postmetakey=?",event.getCommentstatus(), event2update.getId(), "commentstatus" );
					 
			 }
			 else{
				
				 jdbc.update("INSERT INTO postmeta (postmetaid, postid, postmetakey, postmetavalue) VALUES ( Default , ? , ?, ?)", event2update.getId(), "commentstatus" , event.getCommentstatus() );
				
			 }
		 }
		
		if(event.getAuthorid()!=null)
		 {
			 if(event2update.getAuthorid()!=null)
			 {
				 jdbc.update("UPDATE postmeta SET postmetavalue=? WHERE postid =? and postmetakey=?",event.getAuthorid(), event2update.getId(), "authorid" );
					 
			 }
			 else{
				
				 jdbc.update("INSERT INTO postmeta (postmetaid, postid, postmetakey, postmetavalue) VALUES ( Default , ? , ?, ?)", event2update.getId(), "authorid" , event.getAuthorid() );
				
			 }
		 }
		
		if(event.getQuestion()!=null)
		 {
			 if(event2update.getQuestion()!=null)
			 {
				 jdbc.update("UPDATE postmeta SET postmetavalue=? WHERE postid =? and postmetakey=?",event.getQuestion(), event2update.getId(), "question" );
					 
			 }
			 else{
				
				 jdbc.update("INSERT INTO postmeta (postmetaid, postid, postmetakey, postmetavalue) VALUES ( Default , ? , ?, ?)", event2update.getId(), "question" , event.getQuestion() );
				
			 }
		 } 
		if(event.getCertificate()!=null)
		 {
			 if(event2update.getCertificate()!=null)
			 {
				 jdbc.update("UPDATE postmeta SET postmetavalue=? WHERE postid =? and postmetakey=?",event.getCertificate(), event2update.getId(), "certificate" );
					 
			 }
			 else{
				
				 jdbc.update("INSERT INTO postmeta (postmetaid, postid, postmetakey, postmetavalue) VALUES ( Default , ? , ?, ?)", event2update.getId(), "certificate" , event.getCertificate() );
				
			 }
		 } 
		
		if(event.getDeadline()!=null)
		 {
			 if(event2update.getDeadline()!=null)
			 {
				 jdbc.update("UPDATE postmeta SET postmetavalue=? WHERE postid =? and postmetakey=?",event.getDeadline(), event2update.getId(), "deadline" );
					 
			 }
			 else{
				 jdbc.update("INSERT INTO postmeta (postmetaid, postid,postmetakey, postmetavalue) VALUES ( Default , ? , ?, ?)", event2update.getId(), "deadline" , event.getDeadline() );
			 }
		 }
		if(event.getStarttime()!=null)
		 {
			 if(event2update.getStarttime()!=null)
			 {
				 jdbc.update("UPDATE postmeta SET postmetavalue=? WHERE postid =? and postmetakey=?",event.getStarttime(), event2update.getId(), "starttime" );
					 
			 }
			 else{
				 jdbc.update("INSERT INTO postmeta (postmetaid, postid,postmetakey, postmetavalue) VALUES ( Default , ? , ?, ?)", event2update.getId(), "starttime" , event.getStarttime() );
			 }
		 }
		if(event.getEndtime()!=null)
		 {
			 if(event2update.getEndtime()!=null)
			 {
				 jdbc.update("UPDATE postmeta SET postmetavalue=? WHERE postid =? and postmetakey=?",event.getEndtime(), event2update.getId(), "endtime" );
					 
			 }
			 else{
				 jdbc.update("INSERT INTO postmeta (postmetaid, postid,postmetakey, postmetavalue) VALUES ( Default , ? , ?, ?)", event2update.getId(), "endtime" , event.getEndtime() );
			 }
		 }
		
		if(event.getDescription()!=null)
		 {
			 if(event2update.getDescription()!=null)
			 {
				 jdbc.update("UPDATE postmeta SET postmetavalue=? WHERE postid =? and postmetakey=?",event.getDescription(), event2update.getId(), "description" );
					 
			 }
			 else{
				 jdbc.update("INSERT INTO postmeta (postmetaid, postid, postmetakey, postmetavalue) VALUES ( Default , ? , ?, ?)", event2update.getId(), "description" , event.getDescription() );
			 }
		 }
		if(event.getDuration()!=null)
		 {
			 if(event2update.getDuration()!=null)
			 {
				 jdbc.update("UPDATE postmeta SET postmetavalue=? WHERE postid =? and postmetakey=?",event.getDuration(), event2update.getId(), "duration" );
					 
			 }
			 else{
				 jdbc.update("INSERT INTO postmeta (postmetaid, postid, postmetakey, postmetavalue) VALUES ( Default , ? , ?, ?)", event2update.getId(), "duration" , event.getDuration() );
			 }
		 }
		if(event.getEmail()!=null)
		 {
			 if(event2update.getEmail()!=null)
			 {
				 jdbc.update("UPDATE postmeta SET postmetavalue=? WHERE postid =? and postmetakey=?",event.getEmail(), event2update.getId(), "email" );
					 
			 }
			 else{
				 jdbc.update("INSERT INTO postmeta (postmetaid, postid, postmetakey, postmetavalue) VALUES ( Default , ? , ?, ?)", event2update.getId(), "email" , event.getEmail() );
			 }
		 }
		if(event.getImage()!=null)
		 {
			 if(event2update.getImage()!=null)
			 {
				 jdbc.update("UPDATE postmeta SET postmetavalue=? WHERE postid =? and postmetakey=?",event.getImage(), event2update.getId(), "image" );
					 
			 }
			 else{
				 jdbc.update("INSERT INTO postmeta (postmetaid, postid, postmetakey, postmetavalue) VALUES ( Default , ? , ?, ?)", event2update.getId(), "image" , event.getImage() );
			 }
		 }
		if(event.getLocation()!=null)
		 {
			 if(event2update.getLocation()!=null)
			 {
				 jdbc.update("UPDATE postmeta SET postmetavalue=? WHERE postid =? and postmetakey=?",event.getLocation(), event2update.getId(), "location" );
					 
			 }
			 else{
				 jdbc.update("INSERT INTO postmeta (postmetaid, postid, postmetakey, postmetavalue) VALUES ( Default , ? , ?, ?)", event2update.getId(), "location" , event.getLocation() );
			 }
		 }
		if(event.getQualification()!=null)
		 {
			 if(event2update.getQualification()!=null)
			 {
				 jdbc.update("UPDATE postmeta SET postmetavalue=? WHERE postid =? and postmetakey=?",event.getQualification(), event2update.getId(), "qualification" );
					 
			 }
			 else{
				 jdbc.update("INSERT INTO postmeta (postmetaid, postid, postmetakey, postmetavalue) VALUES ( Default , ? , ?, ?)", event2update.getId(), "qualification" , event.getQualification() );
			 }
		 }
		if(event.getRequired()!=null)
		 {
			 if(event2update.getRequired()!=null)
			 {
				 jdbc.update("UPDATE postmeta SET postmetavalue=? WHERE postid =? and postmetakey=?",event.getRequired(), event2update.getId(), "required" );
					 
			 }
			 else{
				 jdbc.update("INSERT INTO postmeta (postmetaid, postid, postmetakey, postmetavalue) VALUES ( Default , ? , ?, ?)", event2update.getId(), "required" , event.getRequired() );
			 }
		 }
		
		if(event.getSalary()!=null)
		 {
			 if(event2update.getSalary()!=null)
			 {
				 jdbc.update("UPDATE postmeta SET postmetavalue=? WHERE postid =? and postmetakey=?",event.getSalary(), event2update.getId(), "salary" );
					 
			 }
			 else{
				 jdbc.update("INSERT INTO postmeta (postmetaid, postid, postmetakey, postmetavalue) VALUES ( Default , ? , ?, ?)", event2update.getId(), "salary" , event.getSalary() );
			 }
		 }
		if(event.getSchedule()!=null)
		 {
			 if(event2update.getSchedule()!=null)
			 {
				 jdbc.update("UPDATE postmeta SET postmetavalue=? WHERE postid =? and postmetakey=?",event.getSchedule(), event2update.getId(), "schedule" );
					 
			 }
			 else{
				 jdbc.update("INSERT INTO postmeta (postmetaid, postid,postmetakey, postmetavalue) VALUES ( Default , ? , ?, ?)", event2update.getId(), "schedule" , event.getSchedule() );
			 }
		 }
		if(event.getVirtual()!=null)
		 {
			 if(event2update.getVirtual()!=null)
			 {
				 jdbc.update("UPDATE postmeta SET postmetavalue=? WHERE postid =? and postmetakey=?",event.getVirtual(), event2update.getId(), "virtual" );
					 
			 }else{
				
					 jdbc.update("INSERT INTO postmeta (postmetaid, postid,postmetakey, postmetavalue) VALUES ( Default , ? , ?, ?)", event2update.getId(), "virtual" , event.getVirtual() );
				 }	
		 }
		
		return 1;
	}
	public Events eventmetamapper(Events event) throws ParseException
	    {
	    	List<CommonMeta> ls= new ArrayList<CommonMeta>();
	    	//SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");			
	    	ls = jdbc.query("SELECT * FROM postmeta WHERE postid=?", new eventMetaMapper(), event.getId());
			for (CommonMeta groupmeta : ls) {
				String key= groupmeta.getKey();
				if(key.equals("email"))
				{
					event.setEmail(groupmeta.getValue());
				}
				if(key.equals("required"))
				{
					event.setRequired(groupmeta.getValue());
				}
				if(key.equals("description"))
				{
					event.setDescription(groupmeta.getValue());
				}
				if(key.equals("location"))
				{
					event.setLocation(groupmeta.getValue());
				}
				if(key.equals("salary"))
				{
					event.setSalary(groupmeta.getValue());
				}
				if(key.equals("qualification"))
				{
					event.setQualification(groupmeta.getValue());
				}
				if(key.equals("duration"))
				{
					event.setDuration(groupmeta.getValue());
				}
				if(key.equals("schedule"))
				{
					event.setSchedule(groupmeta.getValue());
				}
				if(key.equals("image"))
				{
					event.setImage(groupmeta.getValue());
				}
				if(key.equals("virtual"))
				{
					event.setVirtual(groupmeta.getValue());
				}
				if(key.equals("certificate"))
				{
					event.setCertificate(groupmeta.getValue());
				}
				if(key.equals("deadline"))
				{
					event.setDeadline(Timestamp.valueOf(groupmeta.getValue()));
				}
				if(key.equals("starttime"))
				{
					event.setStarttime(Timestamp.valueOf(groupmeta.getValue()));
				}
				if(key.equals("endtime"))
				{
					event.setEndtime(Timestamp.valueOf(groupmeta.getValue()));
				}
				if(key.equals("commentstatus"))
				{
					event.setCommentstatus(groupmeta.getValue());
				}
				if(key.equals("commentstatus"))
				{
					event.setCommentstatus(groupmeta.getValue());
				}
				if(key.equals("question"))
				{
					event.setQuestion(groupmeta.getValue());
				}
				if(key.equals("authorid"))
				{
					event.setAuthorid(groupmeta.getValue());
				}
	
			}
			return event;
	    }
	 // Result set mapper
	   private class eventMapper implements RowMapper<Events> {
				public Events mapRow(ResultSet rs, int rowNum) throws SQLException {
					Events event = new Events();
					Events event2 = new Events();
	        	try {
	        	event.setId(rs.getInt("postid"));
	        	event.setType(rs.getString("posttype"));
	        	event.setStatus(rs.getShort("poststatus"));
	        	event.setUrl(rs.getString("posturl"));
	        	event.setTimestamp(rs.getTimestamp("posttimestamp"));
	        	event.setName(rs.getString("postname"));
	        	event.setGroupid(rs.getLong("postgroupid"));
	        	event.setExtra(rs.getString("postxtra"));
	        	event2 = eventmetamapper(event);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	            return event2;
	        }
	    }
	    
	   private class eventMetaMapper implements RowMapper<CommonMeta> {
			public CommonMeta mapRow(ResultSet rs, int rowNum) throws SQLException {
				CommonMeta eventmeta= new CommonMeta();
	        	eventmeta.setId(rs.getLong(1));
	        	eventmeta.setPid(rs.getLong(2));
	        	eventmeta.setKey(rs.getString(3));
	        	eventmeta.setValue(rs.getString(4));
	        	eventmeta.setTimestamp(rs.getTimestamp(5));
	            return eventmeta;
	        }
	    }

	public List<ResActivity> getEvents(long id) {
		List<ResActivity> lsta = new ArrayList<ResActivity>();
		List<Events> lst= jdbc.query("SELECT status, postid, postname, posttype, postxtra, postgroupid, posttimestamp, posturl from activity INNER JOIN posts where actmetaid=postid AND userid=? and type='apply'", new eventactMapper(), id );
		if (lst!=null && lst.size()>0)
		{
			for (Events events : lst) {
				Group grp = groupDAO.getshortorgbyid(events.getGroupid());
				ResActivity act = new ResActivity();
				act.setEvent(events);
				act.setGroup(grp);
				lsta.add(act);
			}
			return lsta;
		}
		
		return null;
	}

	// Status in the event is of activity
	   private class eventactMapper implements RowMapper<Events> {
				public Events mapRow(ResultSet rs, int rowNum) throws SQLException {
					Events event = new Events();
					Events event2 = new Events();
	        	try {
	        	event.setId(rs.getInt("postid"));
	        	event.setType(rs.getString("posttype"));
	        	event.setStatus(rs.getShort("status"));
	        	event.setUrl(rs.getString("posturl"));
	        	event.setTimestamp(rs.getTimestamp("posttimestamp"));
	        	event.setName(rs.getString("postname"));
	        	event.setGroupid(rs.getLong("postgroupid"));
	        	event.setExtra(rs.getString("postxtra"));
	        	event2 = eventmetamapper(event);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	            return event2;
	        }
	    }
	    
	


}
