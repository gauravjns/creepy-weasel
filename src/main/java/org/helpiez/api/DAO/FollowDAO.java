package org.helpiez.api.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.helpiez.api.model.Follow;
import org.helpiez.api.model.Organization;
import org.helpiez.api.model.Post;
import org.helpiez.api.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class FollowDAO {
	
	@Autowired
    protected JdbcTemplate jdbc;
	
	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private OrgDAO orgDAO;
	
	@Autowired
	private PostDAO postDAO;
	
	
	
	public List<User> getFollower( String meta, long metaid )
	{	
		List<User> lstuser = new ArrayList<User>();
		List<Follow> lstfollow =jdbc.query("SELECT * FROM follow WHERE followmeta=? and followmetaid=?", new followMapper(), meta, metaid);
		for (Follow follow : lstfollow) {
			if(follow.getType()==1 && follow.getStatus()==1)
			{
				lstuser.add(userDAO.getshortuserbyid(follow.getUserid()));
			}
		}
		return lstuser;
	}
	public List<User> getFollowedUser(int id) {
		List<User> lstuser = new ArrayList<User>();
		List<Follow> lstfollow =jdbc.query("SELECT * FROM follow WHERE followmeta=? and userid=?", new followMapper(), "user", id);
		for (Follow follow : lstfollow) {
			if(follow.getType()==1 && follow.getStatus()==1)
			{
				lstuser.add(userDAO.getshortuserbyid(follow.getUserid()));
			}
		}
		return lstuser;
	}
	
	// Followed Orgs of any type
	public List<Organization> getFollowedOrgs(int id, String type) {
		List<Organization> lst = new ArrayList<Organization>();
		List<Follow> lstfollow =jdbc.query("SELECT * FROM follow WHERE followmeta=? and userid=?", new followMapper(), "org", id);
		for (Follow follow : lstfollow) {
			if(follow.getType()==1 && follow.getStatus()==1)
			{
				lst.add(orgDAO.getshortorgbyid(follow.getMetaid()));
			}
		}
		return lst;
	}
	
	public List<Post> getFollowedPost(int id, String type) {
		List<Post> lst = new ArrayList<Post>();
		List<Follow> lstfollow =jdbc.query("SELECT * FROM follow WHERE followmeta=? and userid=?", new followMapper(), "post", id);
		for (Follow follow : lstfollow) {
			if(follow.getType()==1 && follow.getStatus()==1)
			{
				lst.add(postDAO.getpostbyid(follow.getUserid()));
			}
		}
		return lst;
	}
	// Result set mapper
	   private class followMapper implements RowMapper<Follow> {
				public Follow mapRow(ResultSet rs, int rowNum) throws SQLException {
				Follow follow = new Follow();
	        	follow.setFollowid(rs.getLong("followid"));
	        	follow.setUserid(rs.getLong("userid"));
	        	follow.setMetaid(rs.getLong("followmetaid"));
	        	follow.setMeta(rs.getString("followmeta"));
	        	follow.setStatus(rs.getShort("followstatus"));
	        	follow.setTimestamp(rs.getTimestamp("timestamp"));
	        	follow.setType(rs.getShort("followtype"));
	            return follow;
	        }
	    }

	

}
