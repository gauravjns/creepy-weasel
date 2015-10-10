package org.helpiez.api.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.helpiez.api.model.CommonMeta;
import org.helpiez.api.model.Events;
import org.helpiez.api.model.Organization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

@Repository
public class EventsDAO {
	
	@Autowired
    protected JdbcTemplate jdbc;
	
	public Events getEventbyID(int id){
		Events event= new Events();
		event =jdbc.queryForObject("SELECT * FROM posts WHERE postid=?", new eventMapper(), id);
		return event;	
	}
	public Boolean save(Events event) {
		
	SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbc)
	            .withTableName("posts").usingColumns("postname", "posttype", "postxtra", "poststatus", "postgroupid", "posturl")
	            .usingGeneratedKeyColumns("postid");
	
	Map<String,Object> insertParameters = new HashMap<String, Object>();
	insertParameters.put("postname", event.getName());
	insertParameters.put("posttype", event.getType());
	insertParameters.put("postxtra", event.getExtra());
	insertParameters.put("poststatus", event.getStatus());
	insertParameters.put("postgroupid", event.getGroupid());
	insertParameters.put("posturl", event.getUrl());
	
	Number id = insert.executeAndReturnKey(insertParameters);
	System.out.println(id);
	int check2= insertupdate(event,id);
	if (check2==1)
	{
	return true;
	}
	else {return false;}
	}
	
	private int insertupdate(Events event, Number id) {
		// TODO Auto-generated method stub
		return 0;
	}
	public Events eventmetamapper(Events event) throws ParseException
	    {
	    	List<CommonMeta> ls= new ArrayList<CommonMeta>();
	    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			
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
					event.setVirtual(Integer.parseInt(groupmeta.getValue()));
				}
				if(key.equals("certificate"))
				{
					event.setCertificate(Integer.parseInt(groupmeta.getValue()));
				}
				if(key.equals("deadline"))
				{
					event.setDeadline(Timestamp.valueOf(groupmeta.getValue()));
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
	        	event.setStatus(rs.getInt("poststatus"));
	        	event.setUrl(rs.getString("posturl"));
	        	event.setTimestamp(rs.getTimestamp("posttimestamp"));
	        	event.setName(rs.getString("postname"));
	        	event.setGroupid(""+rs.getInt("postgroupid"));
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
	        	eventmeta.setId(rs.getInt(1));
	        	eventmeta.setPid(rs.getInt(2));
	        	eventmeta.setKey(rs.getString(3));
	        	eventmeta.setValue(rs.getString(4));
	        	eventmeta.setTimestamp(rs.getTimestamp(5));
	            return eventmeta;
	        }
	    }

	public Boolean update(Events event, Events event2) {
		// TODO Auto-generated method stub
		return null;
	}


}
