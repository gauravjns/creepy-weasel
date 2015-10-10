package org.helpiez.api.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.helpiez.api.model.CommonMeta;
import org.helpiez.api.model.Organization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class OrgDAO {
	
	@Autowired
    protected JdbcTemplate jdbc;
	
	public Organization getOrgbyID(int id){
		Organization organization= new Organization();
		organization =jdbc.queryForObject("SELECT * FROM groups WHERE groupid=?", new groupMapper(), id);
		return organization;
		
	}
	
	public Boolean update(Organization org, Organization org2update) {
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
	
	public Boolean save(Organization org) {
		int check = jdbc.update("INSERT INTO groups (groupid, groupname, groupstatus, grouptype, groupurl, groupimg, groupxtra) VALUES ( Default , ? , ?, ?, ?, ? , '')", org.getName() , org.getStatus(), org.getType(), org.getUrl(), org.getLogo() );
		int check2= insertupdate(org, getgroupbyName(org.getName()) );
		if (check ==1 && check2==1)
		{
			return true;
		}
		else {
			return false;
		}
	}

	private Organization getgroupbyName(String name) {
		Organization org =jdbc.queryForObject("SELECT * FROM groups WHERE groupname=?", new groupMapper(), name);
		return org;
	}

	public List<Organization> getlistofGrp() {
		List<Organization> ls = new ArrayList<Organization>();
		ls= jdbc.query("SELECT * FROM groups", new groupMapper() );
		return ls;
	}
	
	
	// Extra meta functions
	
		  public int insertupdate(Organization org, Organization org2update)
		  {
			 if(org.getAbout()!=null)
			 {
				 if(org2update.getAbout()!=null)
				 {
					 jdbc.update("UPDATE groupmeta SET groupmetavalue=? WHERE groupid =? and groupmetakey=?",org.getAbout(), org2update.getId(), "about" );
						 
				 }
				 else{
					 jdbc.update("INSERT INTO groupmeta (groupmetaid, groupid, groupmetakey, groupmetavalue) VALUES ( Default , ? , ?, ?)", org2update.getId(), "about" , org.getAbout() );
				 }
			 }
			 if(org.getBanner()!=null)
			 {
				 if(org2update.getBanner()!=null)
				 {
					 jdbc.update("UPDATE groupmeta SET groupmetavalue=? WHERE groupid =? and groupmetakey=?",org.getBanner(), org2update.getId(), "banner" );
						 
				 }
				 else{
					 jdbc.update("INSERT INTO groupmeta (groupmetaid, groupid, groupmetakey, groupmetavalue) VALUES ( Default , ? , ?, ?)", org2update.getId(), "banner" , org.getBanner() );
				 }
			 }
			 if(org.getEmail()!=null)
			 {
				 if(org2update.getEmail()!=null)
				 {
					 jdbc.update("UPDATE groupmeta SET groupmetavalue=? WHERE groupid =? and groupmetakey=?",org.getEmail(), org2update.getId(), "email" );
						 
				 }
				 else{
					 jdbc.update("INSERT INTO groupmeta (groupmetaid, groupid, groupmetakey, groupmetavalue) VALUES ( Default , ? , ?, ?)", org2update.getId(), "email" , org.getEmail() );
				 }
			 }
			 if(org.getMission()!=null)
			 {
				 if(org2update.getMission()!=null)
				 {
					 jdbc.update("UPDATE groupmeta SET groupmetavalue=? WHERE groupid =? and groupmetakey=?",org.getMission(), org2update.getId(), "mission" );
						 
				 }
				 else{
					 jdbc.update("INSERT INTO groupmeta (groupmetaid, groupid, groupmetakey, groupmetavalue) VALUES ( Default , ? , ?, ?)", org2update.getId(), "mission" , org.getMission() );
				 }
			 }
			 if(org.getPincode()!=null)
			 {
				 if(org2update.getPincode()!=null)
				 {
					 jdbc.update("UPDATE groupmeta SET groupmetavalue=? WHERE groupid =? and groupmetakey=?",org.getPincode(), org2update.getId(), "pincode" );
						 
				 }
				 else{
					 jdbc.update("INSERT INTO groupmeta (groupmetaid, groupid, groupmetakey, groupmetavalue) VALUES ( Default , ? , ?, ?)", org2update.getId(), "pincode" , org.getPincode() );
				 }
			 }
			 if(org.getAddress()!=null)
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
			 if(org.getPhone()!=null)
			 {
				 if(org2update.getPhone()!=null)
				 {
					 jdbc.update("UPDATE groupmeta SET groupmetavalue=? WHERE groupid =? and groupmetakey=?",org.getPhone(), org2update.getId(), "phone" );
						 
				 }
				 else{
					 jdbc.update("INSERT INTO groupmeta (groupmetaid, groupid, groupmetakey, groupmetavalue) VALUES ( Default , ? , ?, ?)", org2update.getId(), "phone" , org.getPhone() );
				 }
			 }
			 if(org.getState()!=null)
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
			 if(org.getFacebookpage()!=null)
			 {
				 if(org2update.getFacebookpage()!=null)
				 {
					 jdbc.update("UPDATE groupmeta SET groupmetavalue=? WHERE groupid =? and groupmetakey=?",org.getFacebookpage(), org2update.getId(), "facebookpage" );
						 
				 }
				 else{
					 jdbc.update("INSERT INTO groupmeta (groupmetaid, groupid, groupmetakey, groupmetavalue) VALUES ( Default , ? , ?, ?)", org2update.getId(), "facebookpage" , org.getFacebookpage() );
				 }
			 }
			 if(org.getTwitter()!=null)
			 {
				 if(org2update.getTwitter()!=null)
				 {
					 jdbc.update("UPDATE groupmeta SET groupmetavalue=? WHERE groupid =? and groupmetakey=?",org.getTwitter(), org2update.getId(), "twitter" );
						 
				 }
				 else{
					 jdbc.update("INSERT INTO groupmeta (groupmetaid, groupid, groupmetakey, groupmetavalue) VALUES ( Default , ? , ?, ?)", org2update.getId(), "twitter" , org.getTwitter() );
				 }
			 }
			 if(org.getInstagram()!=null)
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
			 if(org.getView()!=null)
			 {
				 if(org2update.getView()!=null)
				 {
					 jdbc.update("UPDATE groupmeta SET groupmetavalue=? WHERE groupid =? and groupmetakey=?",org.getView(), org2update.getId(), "view" );
						 
				 }
				 else{
					 jdbc.update("INSERT INTO groupmeta (groupmetaid, groupid, groupmetakey, groupmetavalue) VALUES ( Default , ? , ?, ?)", org2update.getId(), "view" , org.getView() );
				 }
			 }
			 if(org.getFoundationdate()!=null)
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
		  
		  
	 public Organization orgmetamapper(Organization organization) throws ParseException
	    {
	    	List<CommonMeta> ls= new ArrayList<CommonMeta>();
	    	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			ls = jdbc.query("SELECT * FROM groupmeta WHERE groupid=?", new groupMetaMapper(), organization.getId());
			for (CommonMeta groupmeta : ls) {
				String key= groupmeta.getKey();
				if(key.equals("email"))
				{
					organization.setEmail(groupmeta.getValue());
				}
				if(key.equals("banner"))
				{
					organization.setBanner(groupmeta.getValue());
				}
				if(key.equals("weblink"))
				{
					organization.setWeblink(groupmeta.getValue());
				}
				if(key.equals("mission"))
				{
					organization.setMission(groupmeta.getValue());
				}
				if(key.equals("about"))
				{
					organization.setAbout(groupmeta.getValue());
				}
				if(key.equals("city"))
				{
					organization.setCity(groupmeta.getValue());
				}
				if(key.equals("phone"))
				{
					organization.setPhone(groupmeta.getValue());
				}
				if(key.equals("address"))
				{
					organization.setAddress(groupmeta.getValue());
				}
				if(key.equals("state"))
				{
					organization.setState(groupmeta.getValue());
				}
				if(key.equals("pincode"))
				{
					organization.setPincode(groupmeta.getValue());
				}
				if(key.equals("googlepage"))
				{
					organization.setGooglepage(groupmeta.getValue());
				}
				if(key.equals("facebookpage"))
				{
					organization.setFacebookpage(groupmeta.getValue());
				}
				if(key.equals("twitter"))
				{
					organization.setTwitter(groupmeta.getValue());
				}
				if(key.equals("instagram"))
				{
					organization.setInstagram(groupmeta.getValue());
				}
				if(key.equals("view"))
				{
					organization.setView(groupmeta.getValue());
				}
				if(key.equals("foundationdate"))
				{
					organization.setFoundationdate(formatter.parse(groupmeta.getValue()));
				}
			
				
			}
	    	return organization;
	    	
	    }
	
	 // Result set mapper
	   private class groupMapper implements RowMapper<Organization> {
				public Organization mapRow(ResultSet rs, int rowNum) throws SQLException {
	        	Organization organization = new Organization();
	        	Organization organization2 = new Organization();
	        	try {
	        	organization.setId(rs.getInt(1));
	        	organization.setType(rs.getString(4));
	        	organization.setStatus(rs.getInt(3));
	        	organization.setLogo(rs.getString(7));
	        	organization.setUrl(rs.getString(6));
	        	organization.setTimestamp(rs.getTimestamp(5));
	        	organization.setName(rs.getString(2));
	        	organization2 = orgmetamapper(organization);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	            return organization2;
	        }
	    }
	    
	   private class groupMetaMapper implements RowMapper<CommonMeta> {
			public CommonMeta mapRow(ResultSet rs, int rowNum) throws SQLException {
				CommonMeta groupmeta= new CommonMeta();
	        	groupmeta.setId(rs.getInt(1));
	        	groupmeta.setPid(rs.getInt(2));
	        	groupmeta.setKey(rs.getString(3));
	        	groupmeta.setValue(rs.getString(4));
	        	groupmeta.setTimestamp(rs.getTimestamp(5));
	            return groupmeta;
	        }
	    }

}
