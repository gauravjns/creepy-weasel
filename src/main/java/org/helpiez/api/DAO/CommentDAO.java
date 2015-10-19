package org.helpiez.api.DAO;


import java.sql.ResultSet;
import java.sql.SQLException;

import org.helpiez.api.model.Comments;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;


@Repository
public class CommentDAO {
	
	@Autowired
    protected JdbcTemplate jdbc;

	public Comments getCommentbyID(int id) {
		Comments comments= new Comments();
		comments =jdbc.queryForObject("SELECT * FROM comment WHERE comid=?", new commentMapper(), id);
		return comments;
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
	            return comment;
	        }
	    }

}
