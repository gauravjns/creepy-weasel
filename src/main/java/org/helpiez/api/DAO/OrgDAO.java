package org.helpiez.api.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.helpiez.api.model.Groupmeta;
import org.helpiez.api.model.Organization;
import org.helpiez.api.model.User;
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
		organization =jdbc.queryForObject("SELECT * FROM groups WHERE groupid=?", groupMapper, id);
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
					 jdbc.update("INSERT INTO usermeta (usermetaid, userid, usermetakey, usermetavalue) VALUES ( Default , ? , ?, ?)", org2update.getId(), "about" , org.getAbout() );
				 }
			 }
			 return 1;   
		  }
		  
		  
	 public Organization orgmetamapper(Organization organization)
	    {
	    	List<Groupmeta> ls= new ArrayList<Groupmeta>();
			ls = jdbc.query("SELECT * FROM groupmeta WHERE groupid=?", groupmetaMapper, organization.getId());
			for (Groupmeta groupmeta : ls) {
				String key= groupmeta.getKey();
				if(key.equals("email"))
				{
					organization.setEmail(groupmeta.getValue());
				}
				if(key.equals("banner"))
				{
					organization.setBanner(groupmeta.getValue());
				}
			
				
			}
	    	return organization;
	    	
	    }
	
	 // Result set mapper
	 private final RowMapper<Organization> groupMapper = new RowMapper<Organization>() {
	        public Organization mapRow(ResultSet rs, int rowNum) throws SQLException {
	        	Organization organization = new Organization();
	        	Organization organization2 = new Organization();
	        	organization.setId(rs.getInt(1));
	        	organization.setType(rs.getString(4));
	        	organization.setStatus(rs.getInt(3));
	        	organization.setLogo(rs.getString(7));
	        	organization.setUrl(rs.getString(6));
	        	organization.setTimestamp(rs.getTimestamp(5));
	        	organization.setName(rs.getString(2));
	        	organization2 = orgmetamapper(organization);
	            return organization2;
	        }
	    };
	    
	    private static final RowMapper<Groupmeta> groupmetaMapper = new RowMapper<Groupmeta>() {
	        public Groupmeta mapRow(ResultSet rs, int rowNum) throws SQLException {
	        	Groupmeta groupmeta= new Groupmeta();
	        	groupmeta.setId(rs.getInt(1));
	        	groupmeta.setOrgid(rs.getInt(2));
	        	groupmeta.setKey(rs.getString(3));
	        	groupmeta.setValue(rs.getString(4));
	        	groupmeta.setTimestamp(rs.getTimestamp(5));
	            return groupmeta;
	        }
	    };

}
