package org.helpiez.api.DAO;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.helpiez.api.model.Activity;
import org.helpiez.api.model.Comments;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;


@Repository
public class CommentDAO {
	
	@Autowired
    protected JdbcTemplate jdbc;

	
	// Get comment
	public Comments getCommentbyID(int id) {
		Comments comments= new Comments();
		comments =jdbc.queryForObject("SELECT * FROM comment WHERE comid=?", new commentMapper(), id);
		return comments;
	}
	public List<Comments> getCommentList(int id, String meta, String order) {
		List<Comments> lst= null;
		if (order.equals("best"))
		{
			
		}
		else if (order.equals("latest"))
		{
			lst =jdbc.query("SELECT parentid, comid,comment.userid,commeta, commetaid,comment,comxtra,timestamp,username, userimg, userurl FROM comment JOIN user where user.userid=comment.userid and commeta=? and commetaid=? and parentid=-1 and comxtra like '%active%' order by timestamp desc", new commentMapper1(),meta, id);			
			
		}
		else if (order.equals("earliest"))
		{
			lst =jdbc.query("SELECT parentid, comid,comment.userid,commeta, commetaid,comment,comxtra,timestamp,username, userimg, userurl FROM comment JOIN user where user.userid=comment.userid and commeta=? and commetaid=? and parentid=-1 and comxtra like '%active%' order by timestamp", new commentMapper1(),meta, id);			
		}
		
		if (lst.size()>0)
		{	String upvote, downvote;
			for (Comments comments : lst) {
				upvote=""; downvote="";
				List<Activity> lstupvote = jdbc.query("Select userid from activity where actmeta like 'comment' and type like 'upvote' and actmetaid=? ",new actMapper(), comments.getId());
				List<Activity> lstdownvote = jdbc.query("Select userid from activity where actmeta like 'comment' and type like 'downvote' and  actmetaid=? ",new actMapper(), comments.getId());
				for (Activity activity : lstdownvote) {
					downvote= downvote + ' '+activity.getUserid();  
				}
				for (Activity activity : lstupvote) {
					upvote= upvote + ' '+activity.getUserid();  
				}
				comments.setUpvote(upvote.trim());
				comments.setDownvote(downvote.trim());
			}
		}
		return lst;
	}

	// Insert comment
	
	public int save(Comments comment) {
		return jdbc.update("INSERT INTO comment (comid, userid, commeta, commetaid, comment, comxtra) VALUES ( Default , ? , ?, ?, ?, ?)",comment.getUserid() , comment.getCommeta(),comment.getCommetaid() , comment.getContent(), comment.getExtra());
		
	}
	
	
	
	// Result set mapper
	   private class commentMapper implements RowMapper<Comments> {
				public Comments mapRow(ResultSet rs, int rowNum) throws SQLException {
				Comments comment = new Comments();
	        	comment.setId(rs.getLong("comid"));
	        	comment.setUserid(rs.getLong("userid"));
	        	comment.setCommetaid(rs.getLong("commetaid"));
	        	comment.setCommeta(rs.getString("commeta"));
	        	comment.setContent(rs.getString("comment"));
	        	comment.setTimestamp(rs.getTimestamp("timestamp"));
	        	comment.setExtra(rs.getString("comxtra"));
	        	comment.setParent(rs.getLong("parentid"));
	            return comment;
	        }
	    }
	   
	// Result set mapper
	   private class commentMapper1 implements RowMapper<Comments> {
				public Comments mapRow(ResultSet rs, int rowNum) throws SQLException {
				Comments comment = new Comments();
	        	comment.setId(rs.getLong("comid"));
	        	comment.setUserid(rs.getLong("userid"));
	        	comment.setCommetaid(rs.getLong("commetaid"));
	        	comment.setCommeta(rs.getString("commeta"));
	        	comment.setContent(rs.getString("comment"));
	        	comment.setTimestamp(rs.getTimestamp("timestamp"));
	        	comment.setExtra(rs.getString("comxtra"));
	        	comment.setParent(rs.getLong("parentid"));
	        	comment.setUserimage(rs.getString("userimg"));
	        	comment.setUsername(rs.getString("username"));
	        	comment.setUserurl(rs.getString("userurl"));
	        	
	            return comment;
	        }
	    }

	public int edit(Comments comment) {
		return jdbc.update("UPDATE comment SET comment=?, comxtra=? WHERE comid =? ",comment.getContent(), comment.getExtra(), comment.getId());
		
	}



	
	private class actMapper implements RowMapper<Activity> {
		public Activity mapRow(ResultSet rs, int rowNum) throws SQLException {
			Activity activity= new Activity();
	    	/*activity.setId(rs.getInt("actid"));*/
	    	activity.setUserid(rs.getLong("userid"));
	    	/*activity.setTimestamp(rs.getTimestamp("timestamp"));
	    	activity.setActmeta(rs.getString("actmeta"));
	    	activity.setActmetaid(rs.getLong("actmetaid"));
	    	activity.setExtra(rs.getString("actxtra"));
	    	activity.setType(rs.getString("type"));
	    	activity.setStatus(rs.getShort("status"));*/
	        return activity;
		}
	}



	
}
