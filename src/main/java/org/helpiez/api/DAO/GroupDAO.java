package org.helpiez.api.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.helpiez.api.model.CommonMeta;
import org.helpiez.api.model.Group;
import org.helpiez.api.model.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class GroupDAO {
	
	@Autowired
    protected JdbcTemplate jdbc;
	
	@Autowired CommonDAO commonDAO;
	
	
	public Group getOrgbyID(long id){
		Group group= new Group();
		group =jdbc.queryForObject("SELECT * FROM groups WHERE groupid=?", new groupMapper(), id);
		return group;
		
	}
	public Group getshortorgbyid(long id) {
		Group group= new Group();
		group =jdbc.queryForObject("SELECT * FROM groups WHERE groupid=?", new shortgroupMapper(), id);
		return group;
	}
	
	public Boolean update(Group org, Group org2update) {
		if(org.getName()!=null)
		{
			org2update.setName(org.getName());
		}
		if(org.getStatus()!=0)
		{
			org2update.setStatus(org.getStatus());
		}
		if(org.getUrl()!=null)
		{
			org2update.setUrl(org.getUrl());
		}
		if(org.getLogo()!=null)
		{
			org2update.setLogo(org.getLogo());
		}
		if(org.getType()!=null)
		{
			org2update.setType(org.getType());
		}
		int check =jdbc.update("UPDATE groups SET groupname=?, groupstatus=? ,groupimg=?, groupurl=? WHERE groupid =? ",org2update.getName(), org2update.getStatus(), org2update.getLogo(), org2update.getUrl(), org2update.getId());
	
		int check2 = insertupdate(org, org2update);
		
		if (check ==1 && check2==1)
		{
			return true;
		}
		else {
			return false;
		}
	}	
	
	public Boolean save(Group group) {
		String url= commonDAO.urlgenerator(group.getName(), "group");
		int check = jdbc.update("INSERT INTO groups (groupid, groupname, groupstatus, grouptype, groupurl, groupimg, groupxtra) VALUES ( Default , ? , ?, ?, ?, ? , '')",group.getName() , group.getStatus(), group.getType(), url, group.getLogo() );
		int check2= insertupdate(group, getgroupbyName(group.getName()) );
		if (check ==1 && check2==1)
		{
			return true;
		}
		else {
			return false;
		}
	}

	private Group getgroupbyName(String name) {
		Group org =jdbc.queryForObject("SELECT * FROM groups WHERE groupname=?", new groupMapper(), name);
		return org;
	}

	public List<Group> getlistofGrp() {
		List<Group> ls = new ArrayList<Group>();
		ls= jdbc.query("SELECT * FROM groups", new groupMapper() );
		return ls;
	}
	
	
	// Extra meta functions
	
		  public int insertupdate(Group org, Group org2update)
		  {
			 if(org.getAbout()!=null )
			 {
				 if(org2update.getAbout()!=null)
				 {
					 jdbc.update("UPDATE groupmeta SET groupmetavalue=? WHERE groupid =? and groupmetakey=?",org.getAbout(), org2update.getId(), "about" );
						 
				 }
				 else{
					 jdbc.update("INSERT INTO groupmeta (groupmetaid, groupid, groupmetakey, groupmetavalue) VALUES ( Default , ? , ?, ?)", org2update.getId(), "about" , org.getAbout() );
				 }
			 }
			 if(org.getBanner()!=null )
			 {
				 if(org2update.getBanner()!=null)
				 {
					 jdbc.update("UPDATE groupmeta SET groupmetavalue=? WHERE groupid =? and groupmetakey=?",org.getBanner(), org2update.getId(), "banner" );
						 
				 }
				 else{
					 jdbc.update("INSERT INTO groupmeta (groupmetaid, groupid, groupmetakey, groupmetavalue) VALUES ( Default , ? , ?, ?)", org2update.getId(), "banner" , org.getBanner() );
				 }
			 }
			 if(org.getEmail()!=null )
			 {
				 if(org2update.getEmail()!=null)
				 {
					 jdbc.update("UPDATE groupmeta SET groupmetavalue=? WHERE groupid =? and groupmetakey=?",org.getEmail(), org2update.getId(), "email" );
						 
				 }
				 else{
					 jdbc.update("INSERT INTO groupmeta (groupmetaid, groupid, groupmetakey, groupmetavalue) VALUES ( Default , ? , ?, ?)", org2update.getId(), "email" , org.getEmail() );
				 }
			 }
			 if(org.getMission()!=null )
			 {
				 if(org2update.getMission()!=null)
				 {
					 jdbc.update("UPDATE groupmeta SET groupmetavalue=? WHERE groupid =? and groupmetakey=?",org.getMission(), org2update.getId(), "mission" );
						 
				 }
				 else{
					 jdbc.update("INSERT INTO groupmeta (groupmetaid, groupid, groupmetakey, groupmetavalue) VALUES ( Default , ? , ?, ?)", org2update.getId(), "mission" , org.getMission() );
				 }
			 }
			 if(org.getPincode()!=null )
			 {
				 if(org2update.getPincode()!=null)
				 {
					 jdbc.update("UPDATE groupmeta SET groupmetavalue=? WHERE groupid =? and groupmetakey=?",org.getPincode(), org2update.getId(), "pincode" );
						 
				 }
				 else{
					 jdbc.update("INSERT INTO groupmeta (groupmetaid, groupid, groupmetakey, groupmetavalue) VALUES ( Default , ? , ?, ?)", org2update.getId(), "pincode" , org.getPincode() );
				 }
			 }
			 if(org.getAddress()!=null )
			 {
				 if(org2update.getAddress()!=null)
				 {
					 jdbc.update("UPDATE groupmeta SET groupmetavalue=? WHERE groupid =? and groupmetakey=?",org.getAddress(), org2update.getId(), "address" );
						 
				 }
				 else{
					 jdbc.update("INSERT INTO groupmeta (groupmetaid, groupid, groupmetakey, groupmetavalue) VALUES ( Default , ? , ?, ?)", org2update.getId(), "address" , org.getAddress() );
				 }
			 }
			 if(org.getCity()!=null)
			 {
				 if(org2update.getCity()!=null)
				 {
					 jdbc.update("UPDATE groupmeta SET groupmetavalue=? WHERE groupid =? and groupmetakey=?",org.getCity(), org2update.getId(), "city" );
						 
				 }
				 else{
					 jdbc.update("INSERT INTO groupmeta (groupmetaid, groupid, groupmetakey, groupmetavalue) VALUES ( Default , ? , ?, ?)", org2update.getId(), "city" , org.getCity() );
				 }
			 }
			 if(org.getPhone()!=null )
			 {
				 if(org2update.getPhone()!=null)
				 {
					 jdbc.update("UPDATE groupmeta SET groupmetavalue=? WHERE groupid =? and groupmetakey=?",org.getPhone(), org2update.getId(), "phone" );
						 
				 }
				 else{
					 jdbc.update("INSERT INTO groupmeta (groupmetaid, groupid, groupmetakey, groupmetavalue) VALUES ( Default , ? , ?, ?)", org2update.getId(), "phone" , org.getPhone() );
				 }
			 }
			 if(org.getState()!=null )
			 {
				 if(org2update.getState()!=null)
				 {
					 jdbc.update("UPDATE groupmeta SET groupmetavalue=? WHERE groupid =? and groupmetakey=?",org.getState(), org2update.getId(), "state" );
						 
				 }
				 else{
					 jdbc.update("INSERT INTO groupmeta (groupmetaid, groupid, groupmetakey, groupmetavalue) VALUES ( Default , ? , ?, ?)", org2update.getId(), "state" , org.getState() );
				 }
			 }
			 if(org.getWeblink()!=null)
			 {
				 if(org2update.getWeblink()!=null)
				 {
					 jdbc.update("UPDATE groupmeta SET groupmetavalue=? WHERE groupid =? and groupmetakey=?",org.getWeblink(), org2update.getId(), "weblink" );
						 
				 }
				 else{
					 jdbc.update("INSERT INTO groupmeta (groupmetaid, groupid, groupmetakey, groupmetavalue) VALUES ( Default , ? , ?, ?)", org2update.getId(), "weblink" , org.getWeblink() );
				 }
			 }
			 if(org.getFacebookpage()!=null )
			 {
				 if(org2update.getFacebookpage()!=null)
				 {
					 jdbc.update("UPDATE groupmeta SET groupmetavalue=? WHERE groupid =? and groupmetakey=?",org.getFacebookpage(), org2update.getId(), "facebookpage" );
						 
				 }
				 else{
					 jdbc.update("INSERT INTO groupmeta (groupmetaid, groupid, groupmetakey, groupmetavalue) VALUES ( Default , ? , ?, ?)", org2update.getId(), "facebookpage" , org.getFacebookpage() );
				 }
			 }
			 if(org.getTwitter()!=null )
			 {
				 if(org2update.getTwitter()!=null)
				 {
					 jdbc.update("UPDATE groupmeta SET groupmetavalue=? WHERE groupid =? and groupmetakey=?",org.getTwitter(), org2update.getId(), "twitter" );
						 
				 }
				 else{
					 jdbc.update("INSERT INTO groupmeta (groupmetaid, groupid, groupmetakey, groupmetavalue) VALUES ( Default , ? , ?, ?)", org2update.getId(), "twitter" , org.getTwitter() );
				 }
			 }
			 if(org.getInstagram()!=null )
			 {
				 if(org2update.getInstagram()!=null)
				 {
					 jdbc.update("UPDATE groupmeta SET groupmetavalue=? WHERE groupid =? and groupmetakey=?",org.getInstagram(), org2update.getId(), "instagram" );
						 
				 }
				 else{
					 jdbc.update("INSERT INTO groupmeta (groupmetaid, groupid, groupmetakey, groupmetavalue) VALUES ( Default , ? , ?, ?)", org2update.getId(), "instagram" , org.getInstagram() );
				 }
			 }
			 if(org.getGooglepage()!=null)
			 {
				 if(org2update.getGooglepage()!=null)
				 {
					 jdbc.update("UPDATE groupmeta SET groupmetavalue=? WHERE groupid =? and groupmetakey=?",org.getGooglepage(), org2update.getId(), "googlepage" );
						 
				 }
				 else{
					 jdbc.update("INSERT INTO groupmeta (groupmetaid, groupid, groupmetakey, groupmetavalue) VALUES ( Default , ? , ?, ?)", org2update.getId(), "googlepage" , org.getGooglepage() );
				 }
			 }
			 if(org.getView()!=null )
			 {
				 if(org2update.getView()!=null)
				 {
					 jdbc.update("UPDATE groupmeta SET groupmetavalue=? WHERE groupid =? and groupmetakey=?",org.getView(), org2update.getId(), "view" );
						 
				 }
				 else{
					 jdbc.update("INSERT INTO groupmeta (groupmetaid, groupid, groupmetakey, groupmetavalue) VALUES ( Default , ? , ?, ?)", org2update.getId(), "view" , org.getView() );
				 }
			 }
			 if(org.getYoutube()!=null )
			 {
				 if(org2update.getYoutube()!=null)
				 {
					 jdbc.update("UPDATE groupmeta SET groupmetavalue=? WHERE groupid =? and groupmetakey=?",org.getYoutube(), org2update.getId(), "youtube" );
						 
				 }
				 else{
					 jdbc.update("INSERT INTO groupmeta (groupmetaid, groupid, groupmetakey, groupmetavalue) VALUES ( Default , ? , ?, ?)", org2update.getId(), "youtube" , org.getYoutube() );
				 }
			 }
			 if(org.getStrength()!=null )
			 {
				 if(org2update.getStrength()!=null)
				 {
					 jdbc.update("UPDATE groupmeta SET groupmetavalue=? WHERE groupid =? and groupmetakey=?",org.getStrength(), org2update.getId(), "strength" );
						 
				 }
				 else{
					 jdbc.update("INSERT INTO groupmeta (groupmetaid, groupid, groupmetakey, groupmetavalue) VALUES ( Default , ? , ?, ?)", org2update.getId(), "strength" , org.getStrength() );
				 }
			 }
			 if(org.getFoundationdate()!=null )
			 {
				 if(org2update.getFoundationdate()!=null)
				 {
					 jdbc.update("UPDATE groupmeta SET groupmetavalue=? WHERE groupid =? and groupmetakey=?",org.getFoundationdate(), org2update.getId(), "foundationdate" );
						 
				 }
				 else{
					 jdbc.update("INSERT INTO groupmeta (groupmetaid, groupid, groupmetakey, groupmetavalue) VALUES ( Default , ? , ?, ?)", org2update.getId(), "foundationdate" , org.getFoundationdate() );
				 }
			 }
			 return 1;   
		  }
		  
		  
	 public Group orgmetamapper(Group group) throws ParseException
	    {
	    	List<CommonMeta> ls= new ArrayList<CommonMeta>();
	    	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			ls = jdbc.query("SELECT * FROM groupmeta WHERE groupid=?", new groupMetaMapper(), group.getId());
			for (CommonMeta groupmeta : ls) {
				String key= groupmeta.getKey();
				if(key.equals("email"))
				{
					group.setEmail(groupmeta.getValue());
				}
				if(key.equals("banner"))
				{
					group.setBanner(groupmeta.getValue());
				}
				if(key.equals("weblink"))
				{
					group.setWeblink(groupmeta.getValue());
				}
				if(key.equals("mission"))
				{
					group.setMission(groupmeta.getValue());
				}
				if(key.equals("about"))
				{
					group.setAbout(groupmeta.getValue());
				}
				if(key.equals("city"))
				{
					group.setCity(groupmeta.getValue());
				}
				if(key.equals("phone"))
				{
					group.setPhone(groupmeta.getValue());
				}
				if(key.equals("address"))
				{
					group.setAddress(groupmeta.getValue());
				}
				if(key.equals("state"))
				{
					group.setState(groupmeta.getValue());
				}
				if(key.equals("pincode"))
				{
					group.setPincode(groupmeta.getValue());
				}
				if(key.equals("googlepage"))
				{
					group.setGooglepage(groupmeta.getValue());
				}
				if(key.equals("facebookpage"))
				{
					group.setFacebookpage(groupmeta.getValue());
				}
				if(key.equals("twitter"))
				{
					group.setTwitter(groupmeta.getValue());
				}
				if(key.equals("instagram"))
				{
					group.setInstagram(groupmeta.getValue());
				}
				if(key.equals("view"))
				{
					group.setView(groupmeta.getValue());
				}
				if(key.equals("strength"))
				{
					group.setStrength(groupmeta.getValue());
				}
				if(key.equals("youtube"))
				{
					group.setYoutube(groupmeta.getValue());
				}
				if(key.equals("foundationdate"))
				{
					group.setFoundationdate(formatter.parse(groupmeta.getValue()));
				}
				
			}
	    	return group;
	    	
	    }
	
	 // Result set mapper
	   private class groupMapper implements RowMapper<Group> {
				public Group mapRow(ResultSet rs, int rowNum) throws SQLException {
	        	Group group = new Group();
	        	Group organization2 = new Group();
	        	try {
	        	group.setId(rs.getLong(1));
	        	group.setType(rs.getString(4));
	        	group.setStatus(rs.getShort(3));
	        	group.setLogo(rs.getString(7));
	        	group.setUrl(rs.getString(6));
	        	group.setTimestamp(rs.getTimestamp(5));
	        	group.setName(rs.getString(2));
	        	organization2 = orgmetamapper(group);
				} catch (ParseException e) {
					e.printStackTrace();
				}
	            return organization2;
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
	    
	   private class groupMetaMapper implements RowMapper<CommonMeta> {
			public CommonMeta mapRow(ResultSet rs, int rowNum) throws SQLException {
				CommonMeta groupmeta= new CommonMeta();
	        	groupmeta.setId(rs.getLong(1));
	        	groupmeta.setPid(rs.getLong(2));
	        	groupmeta.setKey(rs.getString(3));
	        	groupmeta.setValue(rs.getString(4));
	        	groupmeta.setTimestamp(rs.getTimestamp(5));
	            return groupmeta;
	        }
	    }

	public List<Notification> getact(long id) {
		String sql ="Select * from activity inner join user where user.userid=activity.userid and actmeta='group' and actmetaid=? order by timestamp desc limit 4";
		List<Notification> ls= jdbc.query(sql, new actnotMapper(),id );
		return ls;
	}
	 private class actnotMapper implements RowMapper<Notification> {
			public Notification mapRow(ResultSet rs, int rowNum) throws SQLException {
				Notification not = new Notification();
				not.setImage(rs.getString("userimg"));
				not.setText(rs.getString("actxtra"));
				not.setTimestamp(rs.getTimestamp("timestamp"));
				return not;
	        }
	    }

	public List<String> groupmod(long id, String type) {
		String s = jdbc.queryForObject("Select userxtra from user where userid=?", String.class, id);
        String[] part = s.split(" ");
        List<String> ls = new ArrayList<String>();
        for (int i = 0; i < part.length; i++) {
			Group gr = getshortorgbyid(Integer.parseInt(part[i]));
			if (gr.getType().equalsIgnoreCase(type))
			{
				ls.add(part[i]);
			}
		}
		return ls;
	}
	
	

}
