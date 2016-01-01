package org.helpiez.api.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.helpiez.api.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
@Repository
public class PostDAO {
	
	@Autowired
    protected JdbcTemplate jdbc;
	
	@Autowired
	private CommonDAO commonDAO;
	
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

	public int update(Post post) {
		return jdbc.update("INSERT INTO posts (postid, postname, posttype , postxtra , poststatus, postgroupid , posturl) VALUES ( Default , ? , ?, ?, ?, ?, ?)",post.getName() , post.getType(),post.getExtra() , post.getStatus(), post.getGroupid(), commonDAO.urlgenerator(post.getName(), "post"));
		
	}

	public int save(Post post) {
		return jdbc.update("UPDATE posts SET postname=?, poststatus=?, postxtra=?, postgroupid=? WHERE postid =? ",post.getName(), post.getStatus(), post.getExtra(), post.getGroupid(), post.getId());
		
	}

}
