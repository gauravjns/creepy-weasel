package org.helpiez.api.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.helpiez.api.model.Follow;
import org.helpiez.api.model.Group;
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
	
	
	
	public int save(Follow follow) {
		try{
		int check = jdbc.update("INSERT INTO follow (followid, userid, followmeta, followmetaid , followtype, followstatus) VALUES ( Default , ? , ?, ?, ?, ? )", follow.getUserid() , follow.getMeta(), follow.getMetaid(), follow.getType(), follow.getStatus());
		return check;
		} catch(Exception e)
		{return 0;}
	}
	public int update(Follow follow) {
		int check =jdbc.update("UPDATE follow SET followstatus=? where userid=? and followmeta=? and followmetaid=? and followtype =? ", follow.getStatus() ,follow.getUserid() , follow.getMeta(), follow.getMetaid(), follow.getType());
		return check;
	}


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
				lstuser.add(userDAO.getshortuserbyid(follow.getMetaid()));
			}
		}
		return lstuser;
	}
	
	// Followed Groups of any type
	public List<Group> getFollowedGroups(int id, String type) {
		List<Group> lst = new ArrayList<Group>();
		String Sql="SELECT groupid, groupname, groupstatus, grouptype, grouptimestamp, groupurl, groupimg, groupxtra from groups inner join follow on followmetaid= groupid where userid=? and followmeta=? and grouptype=?"; 
		lst =jdbc.query(Sql, new shortgroupMapper(),id, "group", type);
		return lst;
	}
	
	public List<Post> getFollowedPost(int id, String type) {
		List<Post> lst = new ArrayList<Post>();
		String Sql="SELECT postid, postname, posttype, postxtra, poststatus,postgroupid,posttimestamp, posturl from posts inner join follow on followmetaid= postid where userid=? and followmeta=? and posttype=?"; 
		lst =jdbc.query(Sql, new shortpostMapper(),id, "post", type);
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
	   private class shortgroupMapper implements RowMapper<Group> {
		   public Group mapRow(ResultSet rs, int rowNum) throws SQLException {
		       	Group group = new Group();
		       	group.setId(rs.getLong(1));
		       	group.setType(rs.getString(4));
		       	group.setStatus(rs.getShort(3));
		       	group.setLogo(rs.getString(7));
		       	group.setUrl(rs.getString(6));
		       	group.setTimestamp(rs.getTimestamp(5));
		       	group.setName(rs.getString(2));
		       	return group;
       }
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

	public int checkfollowing(long userid, String meta, long id, int type) {
		try
		{Follow follow=jdbc.queryForObject("SELECT * FROM follow WHERE followmeta=? and followmetaid=? and userid=? and followtype=?", new followMapper(), meta, id, userid, type);
		   if (follow!=null)
				 return follow.getStatus();
		   return 0;
		}catch(Exception e)
		{
		   return -1;
		}
	}
	public int delete(Follow follow) {
		
		return jdbc.update("Delete from follow where  userid=?  and followmeta=? and followmetaid=? and followtype=? ", follow.getUserid() , follow.getMeta(), follow.getMetaid(), follow.getType());
		
	}

	
	
	
	

}
