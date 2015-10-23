package org.helpiez.api.DAO;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

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
			lst =jdbc.query("SELECT * FROM comment WHERE commeta=? and commetaid=? and parentid=-1 and comxtra like '%active%' order by timestamp desc", new commentMapper(),meta, id);			
			
		}
		else if (order.equals("earliest"))
		{
			lst =jdbc.query("SELECT * FROM comment WHERE commeta=? and commetaid=? and parentid=-1 and comxtra like '%active%' order by timestamp", new commentMapper(),meta, id);			
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

	public int edit(Comments comment) {
		return jdbc.update("UPDATE comment SET comment=?, comxtra=? WHERE comid =? ",comment.getContent(), comment.getExtra(), comment.getId());
		
	}



	


	
}
