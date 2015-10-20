package org.helpiez.api.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;

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

}
