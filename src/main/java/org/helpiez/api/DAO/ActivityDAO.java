package org.helpiez.api.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.helpiez.api.model.Activity;
import org.helpiez.api.model.CommonMeta;
import org.helpiez.api.model.Events;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class ActivityDAO {
	
	@Autowired
    protected JdbcTemplate jdbc;
	
	
	public Activity getActivitybyID(long id){
		Activity activity= new Activity();
		activity =jdbc.queryForObject("SELECT * FROM activity WHERE actid=?", new actMapper(), id);
		return activity;	
	}
	
	private class actMapper implements RowMapper<Activity> {
		public Activity mapRow(ResultSet rs, int rowNum) throws SQLException {
			Activity activity= new Activity();
	    	activity.setId(rs.getInt("actid"));
	    	activity.setUserid(rs.getLong("userid"));
	    	activity.setTimestamp(rs.getTimestamp("timestamp"));
	    	activity.setActmeta(rs.getString("actmeta"));
	    	activity.setActmetaid(rs.getLong("actmetaid"));
	    	activity.setExtra(rs.getString("actxtra"));
	    	activity.setType(rs.getString("type"));
	    	activity.setStatus(rs.getShort("status"));
	        return activity;
		}
	}
	
	


}
