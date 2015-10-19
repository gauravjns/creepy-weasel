package org.helpiez.api.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;


import org.helpiez.api.model.Events;
import org.helpiez.api.model.Organization;
import org.helpiez.api.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
@Repository
public class PostDAO {
	
	@Autowired
    protected JdbcTemplate jdbc;
	
	public Post getpostbyid(long id) {
		Post post= new Post();
		post =jdbc.queryForObject("SELECT * FROM posts WHERE postid=?", new shortpostMapper(), id);
		return post;
	}
	
	 private class shortpostMapper implements RowMapper<Post> {
			public Post mapRow(ResultSet rs, int rowNum) throws SQLException {
				Post post = new Post();
		     	post.setId(rs.getLong("postid"));
		     	post.setType(rs.getString("posttype"));
		     	post.setStatus(rs.getShort("poststatus"));
		     	post.setUrl(rs.getString("posturl"));
		     	post.setTimestamp(rs.getTimestamp("posttimestamp"));
		     	post.setName(rs.getString("postname"));
		     	post.setGroupid(rs.getLong("postgroupid"));
		     	post.setExtra(rs.getString("postxtra"));
		        return post;
     }
 }

}
