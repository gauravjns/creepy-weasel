package org.helpiez.api.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.helpiez.api.model.User;
import org.helpiez.api.model.Usermeta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;


@Repository
public class UserDAO {
	
	
	@Autowired
    protected JdbcTemplate jdbc;
	
	public User getuserbyid(int id) {
		User user= new User();
		user =jdbc.queryForObject("SELECT * FROM user WHERE userid=?", userMapper, id);
		return user;
	}
	
	  private final RowMapper<User> userMapper = new RowMapper<User>() {
	        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
	            User user = new User();
	            User user2 = new User();
	            user.setId(rs.getInt(1));
	            user.setEmail(rs.getString(2));
	            user.setStatus(rs.getInt(5));
	            user.setImg(rs.getString(7));
	            user.setUrl(rs.getString(8));
	            user.setTimestamp(rs.getTimestamp(6));
	            user.setName(rs.getString("username"));
	            user2 = usermetamapper(user);
	            return user2;
	        }
	    };
	    
	    public User usermetamapper(User user)
	    {
	    	List<Usermeta> ls= new ArrayList<Usermeta>();
			ls = jdbc.query("SELECT * FROM usermeta WHERE userid=?", usermetaMapper, user.getId());
			for (Usermeta usermeta : ls) {
				String key= usermeta.getKey();
				if(key.equals("gender"))
				{
					user.setGender(usermeta.getValue());
				}
				if(key.equals("phone"))
				{
					user.setPhone(usermeta.getValue());
				}
				if(key.equals("address"))
				{
					user.setAddress(usermeta.getValue());
				}
				if(key.equals("company"))
				{
					user.setCompany(usermeta.getValue());
				}
				if(key.equals("facebook"))
				{
					user.setFacebook(usermeta.getValue());
				}
				if(key.equals("google"))
				{
					user.setGoogle(usermeta.getValue());
				}
				if(key.equals("banner"))
				{
					user.setBanner(usermeta.getValue());
				}
				if(key.equals("rollnumber"))
				{
					user.setRollnumber(usermeta.getValue());
				}
				if(key.equals("city"))
				{
					user.setCity(usermeta.getValue());
				}
				if(key.equals("about"))
				{
					user.setAbout(usermeta.getValue());
				}
				if(key.equals("resume"))
				{
					user.setResume(usermeta.getValue());
				}
				if(key.equals("college"))
				{
					user.setCollege(usermeta.getValue());
				}
			}
	    	return user;
	    	
	    }
	    
	    private static final RowMapper<Usermeta> usermetaMapper = new RowMapper<Usermeta>() {
	        public Usermeta mapRow(ResultSet rs, int rowNum) throws SQLException {
	            Usermeta usermeta = new Usermeta();
	            usermeta.setId(rs.getInt(1));
	            usermeta.setUserid(rs.getInt(2));
	            usermeta.setKey(rs.getString(3));
	            usermeta.setValue(rs.getString(4));
	            usermeta.setTimestamp(rs.getTimestamp(5));
	            return usermeta;
	        }
	    };
}
