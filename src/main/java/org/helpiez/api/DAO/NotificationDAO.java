package org.helpiez.api.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.helpiez.api.model.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class NotificationDAO {
	@Autowired
    protected JdbcTemplate jdbc;
	
	public Notification getNotificationbyID(int id) {
		Notification notification= new Notification();
		notification =jdbc.queryForObject("SELECT * FROM notification WHERE notid=?", new notMapper(), id);
		return notification;
	}
	public List<Notification> getNotificationList(long id, long max) {
		return jdbc.query("SELECT * FROM notification WHERE userid=? and notid>? ", new notMapper(), id,max);	
	}
	
	public int save(Notification notification) {
		return jdbc.update("INSERT INTO notification (notid, userid, text, timestamp, meta, link, image) VALUES ( Default , ? , ?, Default,  ?, ?, ?)",notification.getUserid() , notification.getText(),notification.getMeta() , notification.getLink(), notification.getImage());
	}
	

	public int viewed(long msgid)
	{
		return jdbc.update("UPDATE notification SET viewed=2 WHERE notid =? ", msgid);
		
	}
	
	// Result set mapper
	   private class notMapper implements RowMapper<Notification> {
				public Notification mapRow(ResultSet rs, int rowNum) throws SQLException {
				Notification notification = new Notification();
	        	notification.setNotid(rs.getLong("notid"));
	        	notification.setUserid(rs.getLong("userid"));
	        	notification.setMeta(rs.getString("meta"));
	        	notification.setLink(rs.getString("link"));
	        	notification.setImage(rs.getString("image"));
	        	notification.setText(rs.getString("text"));
	        	notification.setTimestamp(rs.getTimestamp("timestamp"));
	        	notification.setViewed(rs.getShort("viewed"));
	            return notification;
	        }
	    }

	public List<Notification> getNotificationListfirst(long id) {
		List <Notification> lst = new ArrayList<Notification>();
		long lastviewed=0;
		try { 
			// algo for selecting first not thrown
			List <Notification> lst1 = jdbc.query("SELECT *  from notification where userid=? and viewed=1 order by notid limit 1", new notMapper(), id);
			if (lst1.size()>0)
			{
				lastviewed= lst1.get(0).getNotid();
			}
			lst= jdbc.query("SELECT * FROM notification WHERE userid=? and notid>? ", new notMapper(),id, lastviewed-1);
			// Fallback case
			if (lst.size()>4 && lastviewed!=0)
			{
				return lst;
			}
			else {
				lst1= jdbc.query("SELECT * FROM notification WHERE userid=? order by notid DESC limit 5 ", new notMapper(),id);
				lastviewed=lst1.get(lst1.size()-1).getNotid();
				lst= jdbc.query("SELECT * FROM notification WHERE userid=? and notid>? ", new notMapper(),id, lastviewed-1);
				return lst;
			}
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
		return lst;
	}
	public List<Notification> getNotificationListnologic(long id, long maxl) {
		if (maxl>0)
		{
			List <Notification> lst1= jdbc.query("SELECT * FROM notification WHERE userid=? and notid<? order by notid DESC limit 5", new notMapper(),id, maxl);
			return lst1;
		}
		List <Notification> lst1= jdbc.query("SELECT * FROM notification WHERE userid=? order by notid DESC limit 5 ", new notMapper(),id);
		return lst1;
	}

	

}
