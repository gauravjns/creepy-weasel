package org.helpiez.api.DAO;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.helpiez.api.model.CommonMeta;
import org.helpiez.api.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAO {
	
	@Autowired CommonDAO commonDAO;
	
	@Autowired
    protected JdbcTemplate jdbc;
	
	private SecureRandom random = new SecureRandom();
	
	public String getUsermeta(int parseInt, String meta) {
		List<CommonMeta>  cms=jdbc.query("SELECT * FROM usermeta WHERE userid=? and usermetakey=?", new userMetaMapper(),  parseInt, meta);
		if (cms.size()>0)
			{return cms.get(0).getValue();}
		else{
			return "--";
		}
	}
	public User getuserbyid(long id) {
		User user= new User();
		user =jdbc.queryForObject("SELECT * FROM user WHERE userid=?", new userMapper(), id);
		return user;
	}
	
	@Cacheable("User")
	public User getshortuserbyid(long id) {
		User user= new User();
		user =jdbc.queryForObject("SELECT * FROM user WHERE userid=?", new usershortMapper(), id);
		return user;
	}
	
	public Boolean userlogin(long id, String password)
	{	User user= new User();
		try {
		user =jdbc.queryForObject("SELECT * FROM user WHERE userid=? and userpassword=?", new usershortMapper(), id, password);
		}
		catch(Exception e){
			System.out.println(e);
		} 
		if (user!=null)
			return true;
		return false;
	}
	
	public User getuserbyemail(String email) {
		User user= new User();
		user =jdbc.queryForObject("SELECT * FROM user WHERE useremail=?", new userMapper(), email);
		return user;
	}
	
	public List<User> getlistofuser() {
		List<User> ls = new ArrayList<User>();
		ls= jdbc.query("SELECT * FROM user where userstatus in (2,3) ", new userMapper());
		return ls;
	}
	
	public Boolean update(User user, User usertoupdate) {
		if(user.getName()!=null)
		{
			usertoupdate.setName(user.getName());
		}
		if(user.getImg()!=null)
		{
			usertoupdate.setImg(user.getImg());	
		}
		
		if(user.getStatus()!=0)
		{
			usertoupdate.setStatus(user.getStatus());
			
		}
		if(user.getUrl()!=null)
		{
			usertoupdate.setUrl(user.getUrl());
		}
		int check =jdbc.update("UPDATE user SET username=?, userstatus=? ,userimg=?, userurl=? WHERE userid =? ",usertoupdate.getName(), usertoupdate.getStatus(), usertoupdate.getImg(), usertoupdate.getUrl(), usertoupdate.getId());
	
		int check2 = insertupdate(user, usertoupdate);
		
		if (check ==1 && check2==1)
		{
			return true;
		}
		else {
			return false;
		}
	}
	
	public Boolean save(User user, String Password) {		
		
		if (Password==null)
			Password="";
		String url= commonDAO.urlgenerator(user.getName(), "user");
		String email = user.getEmail();
		if(email.equals(""))
		{
			email=url+"@helpiez.org";
		}
		int check = jdbc.update("INSERT INTO user (userid, useremail, userpassword, username, userstatus, userimg, userurl, userxtra) VALUES ( Default , ? , ?, ?, ?, ?, ? , '')", email, Password , user.getName(), user.getStatus(), user.getImg(),url);
		User user2 = getuserbyemail(email);
		int check2= insertupdate(user, user2);
		//int check3= EmailKeyGen(user2.getId()); // Remove after migration
		int check3=1;
		if (check ==1 && check2==1 && check3==1)
		{
			return true;
		}
		else {
			return false;
		}
	
	}
	
	private int EmailKeyGen(long id)
	{
		  String key=  new BigInteger(130, random).toString(32);
		  return jdbc.update("INSERT INTO usermeta (usermetaid, userid, usermetakey, usermetavalue) VALUES ( Default , ? , ?, ?)", id, "emailkey" , key );
	}
	
	public Boolean EmailkeyVer(long id, String key)
	{ 
		CommonMeta userkey= jdbc.queryForObject("SELECT * FROM usermeta WHERE userid=? and usermetakey=? order by usermetaid Desc limit 1", new userMetaMapper(), id, "emailkey");
		if(key.equals(userkey.getValue()))
		{
			int check =jdbc.update("UPDATE user SET  userstatus=?  WHERE userid =? ", 2 , id);
			if (check==1)
				return true;
			return false;
		}
		else 
		{
			return false;
		}
		
	}
	
	
	// Extra meta functions
	
	  public int insertupdate(User user, User usertoupdate)
	  {
		 if(user.getAbout()!=null)
		 {
			 if(usertoupdate.getAbout()!=null)
			 {
				 jdbc.update("UPDATE usermeta SET usermetavalue=? WHERE userid =? and usermetakey=?",user.getAbout(), usertoupdate.getId(), "about" );
					 
			 }
			 else{
				 jdbc.update("INSERT INTO usermeta (usermetaid, userid, usermetakey, usermetavalue) VALUES ( Default , ? , ?, ?)", usertoupdate.getId(), "about" , user.getAbout() );
			 }
		 }
		 if(user.getAddress()!=null)
		 {
			 if(usertoupdate.getAddress()!=null)
			 {
				 jdbc.update("UPDATE usermeta SET usermetavalue=? WHERE userid =? and usermetakey=?",user.getAddress(), usertoupdate.getId(), "address" );
					 
			 }
			 else{
				 jdbc.update("INSERT INTO usermeta (usermetaid, userid, usermetakey, usermetavalue) VALUES ( Default , ? , ?, ?)", usertoupdate.getId(), "address" , user.getAddress() );
			 }
		 }
		 if(user.getBanner()!=null)
		 {
			 if(usertoupdate.getBanner()!=null)
			 {
				 jdbc.update("UPDATE usermeta SET usermetavalue=? WHERE userid =? and usermetakey=?",user.getBanner(), usertoupdate.getId(), "banner" );
					 
			 }
			 else{
				 jdbc.update("INSERT INTO usermeta (usermetaid, userid, usermetakey, usermetavalue) VALUES ( Default , ? , ?, ?)", usertoupdate.getId(), "banner" , user.getBanner() );
			 }
		 }
		 if(user.getBirthday()!=null)
		 {
			 if(usertoupdate.getBirthday()!=null)
			 {
				 jdbc.update("UPDATE usermeta SET usermetavalue=? WHERE userid =? and usermetakey=?",user.getBirthday(), usertoupdate.getId(), "birthday" );
					 
			 }
			 else{
				 jdbc.update("INSERT INTO usermeta (usermetaid, userid, usermetakey, usermetavalue) VALUES ( Default , ? , ?, ?)", usertoupdate.getId(), "birthday" , user.getBirthday() );
			 }
		 }
		 if(user.getCity()!=null)
		 {
			 if(usertoupdate.getCity()!=null)
			 {
				 jdbc.update("UPDATE usermeta SET usermetavalue=? WHERE userid =? and usermetakey=?",user.getCity(), usertoupdate.getId(), "city" );
					 
			 }
			 else{
				 jdbc.update("INSERT INTO usermeta (usermetaid, userid, usermetakey, usermetavalue) VALUES ( Default , ? , ?, ?)", usertoupdate.getId(), "city" , user.getCity() );
			 }
		 }
		 if(user.getCollege()!=null)
		 {
			 if(usertoupdate.getCollege()!=null)
			 {
				 jdbc.update("UPDATE usermeta SET usermetavalue=? WHERE userid =? and usermetakey=?",user.getCollege(), usertoupdate.getId(), "college" );
					 
			 }
			 else{
				 jdbc.update("INSERT INTO usermeta (usermetaid, userid, usermetakey, usermetavalue) VALUES ( Default , ? , ?, ?)", usertoupdate.getId(), "college" , user.getCollege() );
			 }
		 }
		 
		 if(user.getCompany()!=null)
		 {
			 if(usertoupdate.getCompany()!=null)
			 {
				 jdbc.update("UPDATE usermeta SET usermetavalue=? WHERE userid =? and usermetakey=?",user.getCompany(), usertoupdate.getId(), "company" );
					 
			 }
			 else{
				 jdbc.update("INSERT INTO usermeta (usermetaid, userid, usermetakey, usermetavalue) VALUES ( Default , ? , ?, ?)", usertoupdate.getId(), "company" , user.getCompany() );
			 }
		 }
		 if(user.getFacebook()!=null)
		 {
			 if(usertoupdate.getFacebook()!=null)
			 {
				 jdbc.update("UPDATE usermeta SET usermetavalue=? WHERE userid =? and usermetakey=?",user.getFacebook(), usertoupdate.getId(), "facebook" );
					 
			 }
			 else{
				 jdbc.update("INSERT INTO usermeta (usermetaid, userid, usermetakey, usermetavalue) VALUES ( Default , ? , ?, ?)", usertoupdate.getId(), "facebook" , user.getFacebook() );
			 }
		 }
		 if(user.getGender()!=null)
		 {
			 if(usertoupdate.getGender()!=null)
			 {
				 jdbc.update("UPDATE usermeta SET usermetavalue=? WHERE userid =? and usermetakey=?",user.getGender(), usertoupdate.getId(), "gender" );
					 
			 }
			 else{
				 jdbc.update("INSERT INTO usermeta (usermetaid, userid, usermetakey, usermetavalue) VALUES ( Default , ? , ?, ?)", usertoupdate.getId(), "gender" , user.getGender() );
			 }
		 }
		 
		 if(user.getGoogle()!=null)
		 {
			 if(usertoupdate.getGoogle()!=null)
			 {
				 jdbc.update("UPDATE usermeta SET usermetavalue=? WHERE userid =? and usermetakey=?",user.getGoogle(), usertoupdate.getId(), "google" );
					 
			 }
			 else{
				 jdbc.update("INSERT INTO usermeta (usermetaid, userid, usermetakey, usermetavalue) VALUES ( Default , ? , ?, ?)", usertoupdate.getId(), "google" , user.getGoogle() );
			 }
		 }
		 if(user.getPhone()!=null)
		 {
			 if(usertoupdate.getPhone()!=null)
			 {
				 jdbc.update("UPDATE usermeta SET usermetavalue=? WHERE userid =? and usermetakey=?",user.getPhone(), usertoupdate.getId(), "phone" );
					 
			 }
			 else{
				 jdbc.update("INSERT INTO usermeta (usermetaid, userid, usermetakey, usermetavalue) VALUES ( Default , ? , ?, ?)", usertoupdate.getId(), "phone" , user.getPhone() );
			 }
		 }
		 if(user.getResume()!=null)
		 {
			 if(usertoupdate.getResume()!=null)
			 {
				 jdbc.update("UPDATE usermeta SET usermetavalue=? WHERE userid =? and usermetakey=?",user.getResume(), usertoupdate.getId(), "resume" );
					 
			 }
			 else{
				 jdbc.update("INSERT INTO usermeta (usermetaid, userid, usermetakey, usermetavalue) VALUES ( Default , ? , ?, ?)", usertoupdate.getId(), "resume" , user.getResume() );
			 }
		 }
		 if(user.getRollnumber()!=null)
		 {
			 if(usertoupdate.getRollnumber()!=null)
			 {
				 jdbc.update("UPDATE usermeta SET usermetavalue=? WHERE userid =? and usermetakey=?",user.getRollnumber(), usertoupdate.getId(), "rollnumber" );
					 
			 }
			 else{
				 jdbc.update("INSERT INTO usermeta (usermetaid, userid, usermetakey, usermetavalue) VALUES ( Default , ? , ?, ?)", usertoupdate.getId(), "rollnumber" , user.getRollnumber() );
			 }
		 }
		 if(user.getTwitter()!=null)
		 {
			 if(usertoupdate.getTwitter()!=null)
			 {
				 jdbc.update("UPDATE usermeta SET usermetavalue=? WHERE userid =? and usermetakey=?",user.getTwitter(), usertoupdate.getId(), "twitter" );
					 
			 }
			 else{
				 jdbc.update("INSERT INTO usermeta (usermetaid, userid, usermetakey, usermetavalue) VALUES ( Default , ? , ?, ?)", usertoupdate.getId(), "twitter" , user.getTwitter() );
			 }
		 }
	
		 return 1;   
	  }
	
	  public User usermetamapper(User user) throws ParseException
	    {
	    	List<CommonMeta> ls= new ArrayList<CommonMeta>();
			ls = jdbc.query("SELECT * FROM usermeta WHERE userid=?", new userMetaMapper(), user.getId());
			for (CommonMeta usermeta : ls) {
				String key= usermeta.getKey();
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
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
				if(key.equals("twitter"))
				{
					user.setTwitter(usermeta.getValue());
				}
				if(key.equals("birthday"))
				{
					user.setBirthday( formatter.parse(usermeta.getValue()) );
				
				}
			}
	    	return user;
	    	
	    }
	  // User mapper Implementation  
	    public class userMapper implements RowMapper<User> {
			public User mapRow(ResultSet rs, int rowNum) throws SQLException {
				User user = new User();
	            User user2 = new User();
				try {
		            user.setId(rs.getLong(1));
		            user.setEmail(rs.getString(2));
		            user.setStatus(rs.getShort(5));
		            user.setImg(rs.getString(7));
		            user.setUrl(rs.getString(8));
		            user.setTimestamp(rs.getTimestamp(6));
		            user.setName(rs.getString("username"));
		            user2 = usermetamapper(user);
					} 
				catch (ParseException e) {
						e.printStackTrace();
					}  
				return user2;
			}
		}
	    
	    public class usershortMapper implements RowMapper<User> {
			public User mapRow(ResultSet rs, int rowNum) throws SQLException {
				User user = new User();
	            user.setId(rs.getLong(1));
	            user.setEmail(rs.getString(2));
	            user.setStatus(rs.getShort(5));
	            user.setImg(rs.getString(7));
	            user.setUrl(rs.getString(8));
	            user.setTimestamp(rs.getTimestamp(6));
	            user.setName(rs.getString("username"));
				return user;
			}
		}
	    
	    private class userMetaMapper implements RowMapper<CommonMeta> {
			public CommonMeta mapRow(ResultSet rs, int rowNum) throws SQLException {
				CommonMeta usermeta = new CommonMeta();
	            usermeta.setId(rs.getLong(1));
	            usermeta.setPid(rs.getLong(2));
	            usermeta.setKey(rs.getString(3));
	            usermeta.setValue(rs.getString(4));
	            usermeta.setTimestamp(rs.getTimestamp(5));
	            return usermeta;
	        }
	    }

		public User getuserbyurl(String url) {
			User user= new User();
			user =jdbc.queryForObject("SELECT * FROM user WHERE userurl=?", new userMapper(), url);
			return user;
		}

		public long checkuser(String email, String pass) {
			try 
			{String passa= jdbc.queryForObject("SELECT userpassword FROM user WHERE useremail=?", String.class, email);
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(pass.getBytes());
			byte[] digest = md.digest();
			StringBuffer sb = new StringBuffer();
			for (byte b : digest) {
				sb.append(String.format("%02x", b & 0xff));
			}
			pass= sb.toString();
			if (passa.equals(pass))
				{ User user =getuserbyemail(email);
				return user.getId();}
			else {return -1;}
			}
			catch(Exception e)
			{return 0;}
			
		}

		
	
}
