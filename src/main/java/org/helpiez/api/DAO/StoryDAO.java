package org.helpiez.api.DAO;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


import org.helpiez.api.model.CommonMeta;
import org.helpiez.api.model.Events;
import org.helpiez.api.model.Story;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class StoryDAO {
	
	@Autowired
    protected JdbcTemplate jdbc;
	
	public Story getStorybyID(int id)
	{	
		Story story = new Story();
		story =jdbc.queryForObject("SELECT * FROM posts WHERE postid=?", new storyMapper(), id);
		return story;
		
		
	}
	public Story storymetamapper(Story story)throws ParseException
	{
		List<CommonMeta> ls= new ArrayList<CommonMeta>();
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");			
    	ls = jdbc.query("SELECT * FROM postmeta WHERE postid=?", new postMetaMapper(), story.getId());
		for (CommonMeta groupmeta : ls) {
			String key= groupmeta.getKey();
			if(key.equals("authorid"))
			{
				story.setAuthorid(groupmeta.getValue());
			}
		}
		return story;
	}
	
	
	 // Result set mapper
	   private class storyMapper implements RowMapper<Story> {
				public Story mapRow(ResultSet rs, int rowNum) throws SQLException {
					Story story = new Story();
					Story story2 = new Story();
	        	try {
	        	story.setId(rs.getInt("postid"));
	        	story.setType(rs.getString("posttype"));
	        	story.setStatus(rs.getInt("poststatus"));
	        	story.setUrl(rs.getString("posturl"));
	        	story.setTimestamp(rs.getTimestamp("posttimestamp"));
	        	story.setName(rs.getString("postname"));
	        	story.setGroupid(""+rs.getInt("postgroupid"));
	        	story.setExtra(rs.getString("postxtra"));
	        	story2 = storymetamapper(story);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	            return story2;
	        }	
	    }
	   
	   private class postMetaMapper implements RowMapper<CommonMeta> {
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

	

	

}
