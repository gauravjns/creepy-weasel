package org.helpiez.api.DAO;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.helpiez.api.model.CommonMeta;
import org.helpiez.api.model.Story;
import org.helpiez.api.model.Text;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

@Repository
public class StoryDAO {
	
	@Autowired
    protected JdbcTemplate jdbc;

	@Autowired 
	private CommonDAO commonDAO;
	
	public Story getStorybyID(long id)
	{	
		Story story = new Story();
		story =jdbc.queryForObject("SELECT * FROM posts WHERE postid=?", new storyMapper(), id);
		return story;		
	}
	
	public boolean update(Story story, Story story2) {
		if(story.getName()!=null)
		{
			story2.setName(story.getName());
		}
		if(story.getType()!=null)
		{
			story2.setType(story.getType());	
		}
		
		if(story.getStatus()!=0)
		{
			story2.setStatus(story.getStatus());
			
		}
		if(story.getUrl()!=null)
		{
			story2.setUrl(story.getUrl());
		}
		if(story.getGroupid()!=0)
		{
			story2.setGroupid(story.getGroupid());
		}
		if(story.getExtra()!=null)
		{
			story2.setExtra(story.getExtra());
		}
		
		int check =jdbc.update("UPDATE posts SET postname=?, poststatus=? ,posttype=?, posturl=?, postxtra=?, postgroupid=? WHERE postid =? ",story2.getName(), story2.getStatus(), story2.getType(), story2.getUrl(), story2.getExtra(), story2.getGroupid(),  story2.getId());
	
		int check2 = insertupdate(story, story2);
		
		if (check ==1 && check2==1)
		{
			return true;
		}
		else {
			return false;
		}
	}
	public int createStory(Story story) {
		
		SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbc)
		            .withTableName("posts").usingColumns("postname", "posttype", "postxtra", "poststatus", "postgroupid", "posturl")
		            .usingGeneratedKeyColumns("postid");
		
		Map<String,Object> insertParameters = new HashMap<String, Object>();
		insertParameters.put("postname", story.getName());
		insertParameters.put("posttype", story.getType());
		insertParameters.put("postxtra", story.getExtra());
		insertParameters.put("poststatus", story.getStatus());
		insertParameters.put("postgroupid", story.getGroupid());
		insertParameters.put("posturl", commonDAO.urlgenerator(story.getName(), "post"));
		
		Number id = insert.executeAndReturnKey(insertParameters);
		System.out.println(id);
		int check2= insert(story, (Long) id);
		if (check2==1)
		{
		return 1;
		}
		else {return 0;}
	}
	
	

	private int insert(Story story, Long i) {
		if(story.getAuthorid()!=null)
		 {
			jdbc.update("INSERT INTO postmeta (postmetaid, postid, postmetakey, postmetavalue) VALUES ( Default , ? , ?, ?)", i, "authorid" , story.getAuthorid() );
		 } 
		if(story.getBlogid()!=null)
		 { jdbc.update("UPDATE postmeta SET postmetavalue=? WHERE postid =? and postmetakey=?",story.getBlogid(), i, "blogid" );			
		 } 
		else {
			if(story.getBlog()!=null)
			{
				long blogid= insertupdateblog(story.getBlog(), i);
				jdbc.update("INSERT INTO postmeta (postmetaid, postid, postmetakey, postmetavalue) VALUES ( Default , ? , ?, ?)", i, "blogid" , blogid );
			}
			else {
				return 0;
			}
		}
		if(story.getCommentstatus()!=null)
		 {
			jdbc.update("INSERT INTO postmeta (postmetaid, postid, postmetakey, postmetavalue) VALUES ( Default , ? , ?, ?)", i, "commentstatus" , story.getCommentstatus() );
		 } 
		if(story.getExcerpt()!=null)
		 {
			jdbc.update("INSERT INTO postmeta (postmetaid, postid, postmetakey, postmetavalue) VALUES ( Default , ? , ?, ?)", i, "excerpt" , story.getExcerpt() );
		 } 
		if(story.getFeaturedimage()!=null)
		 {	
			jdbc.update("INSERT INTO postmeta (postmetaid, postid, postmetakey, postmetavalue) VALUES ( Default , ? , ?, ?)", i, "featuredimage" , story.getFeaturedimage() );
		 }
		if(story.getPostparent()!=null)
		 {	
			jdbc.update("INSERT INTO postmeta (postmetaid, postid, postmetakey, postmetavalue) VALUES ( Default , ? , ?, ?)", i, "postparent" , story.getPostparent() );
		 }
		
		return 1;
	}
	
	private int insertupdate(Story story, Story storybyID) {
		if(story.getAuthorid()!=null)
		 {
			 if(storybyID.getAuthorid()!=null)
			 {
				 jdbc.update("UPDATE postmeta SET postmetavalue=? WHERE postid =? and postmetakey=?",story.getAuthorid(), storybyID.getId(), "authorid" );
					 
			 }
			 else{
				
				 jdbc.update("INSERT INTO postmeta (postmetaid, postid, postmetakey, postmetavalue) VALUES ( Default , ? , ?, ?)", storybyID.getId(), "authorid" , story.getAuthorid() );
				
			 }
		 } 
		if(story.getBlogid()!=null)
		 {
			 if(storybyID.getBlogid()!=null)
			 {
				 jdbc.update("UPDATE postmeta SET postmetavalue=? WHERE postid =? and postmetakey=?",story.getBlogid(), storybyID.getId(), "blogid" );
					 
			 }
			 else{
				
				 jdbc.update("INSERT INTO postmeta (postmetaid, postid, postmetakey, postmetavalue) VALUES ( Default , ? , ?, ?)", storybyID.getId(), "blogid" , story.getBlogid() );
				
			 }
		 } 
		else {
			if(story.getBlog()!=null)
			{
				Long blogid= insertupdateblog(story.getBlog(), story.getId());
				jdbc.update("UPDATE postmeta SET postmetavalue=? WHERE postid =? and postmetakey=?",blogid, storybyID.getId(), "blogid" );
			}
			else {
				return 0;
			}
		}
		if(story.getCommentstatus()!=null)
		 {
			 if(storybyID.getCommentstatus()!=null)
			 {
				 jdbc.update("UPDATE postmeta SET postmetavalue=? WHERE postid =? and postmetakey=?",story.getCommentstatus(), storybyID.getId(), "commentstatus" );
					 
			 }
			 else{
				
				 jdbc.update("INSERT INTO postmeta (postmetaid, postid, postmetakey, postmetavalue) VALUES ( Default , ? , ?, ?)", storybyID.getId(), "commentstatus" , story.getCommentstatus() );
				
			 }
		 } 
		if(story.getExcerpt()!=null)
		 {
			 if(storybyID.getExcerpt()!=null)
			 {
				 jdbc.update("UPDATE postmeta SET postmetavalue=? WHERE postid =? and postmetakey=?",story.getExcerpt(), storybyID.getId(), "excerpt" );
					 
			 }
			 else{
				
				 jdbc.update("INSERT INTO postmeta (postmetaid, postid, postmetakey, postmetavalue) VALUES ( Default , ? , ?, ?)", storybyID.getId(), "excerpt" , story.getExcerpt() );
				
			 }
		 } 
		if(story.getFeaturedimage()!=null)
		 {
			 if(storybyID.getFeaturedimage()!=null)
			 {
				 jdbc.update("UPDATE postmeta SET postmetavalue=? WHERE postid =? and postmetakey=?",story.getFeaturedimage(), storybyID.getId(), "featuredimage" );
					 
			 }
			 else{
				
				 jdbc.update("INSERT INTO postmeta (postmetaid, postid, postmetakey, postmetavalue) VALUES ( Default , ? , ?, ?)", storybyID.getId(), "featuredimage" , story.getFeaturedimage() );
				
			 }
		 }
		if(story.getPostparent()!=null)
		 {
			 if(storybyID.getPostparent()!=null)
			 {
				 jdbc.update("UPDATE postmeta SET postmetavalue=? WHERE postid =? and postmetakey=?",story.getPostparent(), storybyID.getId(), "postparent" );
					 
			 }
			 else{
				
				 jdbc.update("INSERT INTO postmeta (postmetaid, postid, postmetakey, postmetavalue) VALUES ( Default , ? , ?, ?)", storybyID.getId(), "postparent" , story.getPostparent() );
				
			 }
		 }
		
		return 1;
	}

	private long insertupdateblog(Text blog, long postid) {
		SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbc)
	            .withTableName("text").usingColumns("content",  "textxtra", "postid")
	            .usingGeneratedKeyColumns("textid");
		Map<String,Object> insertParameters = new HashMap<String, Object>();
		insertParameters.put("content", blog.getContent());
		insertParameters.put("textxtra", blog.getTextextra());
		insertParameters.put("postid", postid);
		Number id = insert.executeAndReturnKey(insertParameters);
		return (Long) id;
		
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
			if(key.equals("blogid"))
			{
				story.setBlogid(groupmeta.getValue());
			}
			if(key.equals("excerpt"))
			{
				story.setExcerpt(groupmeta.getValue());
			}
			if(key.equals("featuredimage"))
			{
				story.setFeaturedimage(groupmeta.getValue());
			}
			if(key.equals("commentstatus"))
			{
				story.setCommentstatus(groupmeta.getValue());
			}
			if(key.equals("postparent"))
			{
				story.setPostparent(groupmeta.getValue());
			}
			
		}
		Text storymeta = new Text();
		storymeta =jdbc.queryForObject("SELECT * FROM text WHERE textid=?", new textMapper(), story.getBlogid());
		story.setBlog(storymeta);
		return story;
	}
	
	
	 // Result set mapper
	   private class storyMapper implements RowMapper<Story> {
				public Story mapRow(ResultSet rs, int rowNum) throws SQLException {
					Story story = new Story();
					Story story2 = new Story();
	        	try {
	        	story.setId(rs.getLong("postid"));
	        	story.setType(rs.getString("posttype"));
	        	story.setStatus(rs.getShort("poststatus"));
	        	story.setUrl(rs.getString("posturl"));
	        	story.setTimestamp(rs.getTimestamp("posttimestamp"));
	        	story.setName(rs.getString("postname"));
	        	story.setGroupid(rs.getLong("postgroupid"));
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
	        	eventmeta.setId(rs.getLong(1));
	        	eventmeta.setPid(rs.getLong(2));
	        	eventmeta.setKey(rs.getString(3));
	        	eventmeta.setValue(rs.getString(4));
	        	eventmeta.setTimestamp(rs.getTimestamp(5));
	            return eventmeta;
	        }
	    }
	   
	   private class textMapper implements RowMapper<Text> {
			public Text mapRow(ResultSet rs, int rowNum) throws SQLException {
				Text text= new Text();
	        	text.setTextid(rs.getLong(1));
	        	text.setContent(rs.getString(2));
	        	text.setTimestamp(rs.getTimestamp(3));
	        	text.setTextextra(rs.getString(4));
	            return text;
	        }
	    }

	

	

}
