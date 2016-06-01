package org.helpiez.api.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.helpiez.api.model.Activity;
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

	public int update(Activity activity) {
		return jdbc.update("UPDATE activity SET type=?, status= ?, actxtra=? WHERE actid =? ",activity.getType(),activity.getStatus(), activity.getExtra(), activity.getId());
		
	}

	public int updatestatus(int status, long actmetaid, String actmeta, String type) {
		return jdbc.update("UPDATE activity SET status= ? WHERE actmeta =? and actmetaid=? and type=?  ",status,actmeta, actmetaid, type);
		
	}
	public Boolean save(Activity activity) {
		int i= jdbc.update("INSERT INTO activity (actid, userid, actmeta, actmetaid, type, status, actxtra) VALUES ( Default , ? , ?, ?, ?, ?, ?)",activity.getUserid() , activity.getActmeta(),activity.getActmetaid() , activity.getType(), activity.getStatus(), activity.getExtra());
		if ( i==1)
		{	
			if (activity.getType().equals("upvote")&& activity.getActmeta().equals("post"))
			{
				jdbc.update("update feed set upvote= upvote+1 where feedmetaid=?", activity.getActmetaid());
			}
			if (activity.getType().equals("downvote")&& activity.getActmeta().equals("post"))
			{
				jdbc.update("update feed set downvote= downvote+1 where feedmetaid=?", activity.getActmetaid());
			}
			return true;
		}
		else {
			return false ; 
		}
	}

	public List<Activity> getActivity(String meta, int id, String type) {
		List<Activity> lstact = new ArrayList<Activity>();
		lstact =jdbc.query("SELECT * FROM activity WHERE actmeta=? and actmetaid=? and type=?", new actMapper(), meta, id, type);
		return lstact;
	}
	
	public List<Activity> getActivitycount(String meta, int status, String type) {
		List<Activity> lstact = new ArrayList<Activity>();
		lstact =jdbc.query("SELECT count(*) as count, actmetaid FROM activity where actmeta=? and type=? and status=? group by actmetaid", new actMappercount(), meta, type, status);
		return lstact;
	}

	public int delete(Activity id) {
		int i = jdbc.update("DELETE FROM activity WHERE actmeta =? and actmetaid=? and userid=? and type=?",id.getActmeta(), id.getActmetaid(), id.getUserid(), id.getType() );
		if ( i==1)
		{	
			if (id.getType().equals("upvote")&& id.getActmeta().equals("post"))
			{
				jdbc.update("update feed set upvote= upvote-1 where feedmetaid=?", id.getActmetaid());
			}
			if (id.getType().equals("downvote")&& id.getActmeta().equals("post"))
			{
				jdbc.update("update feed set downvote= downvote-1 where feedmetaid=?", id.getActmetaid());
			}
		}
		return i;
	}
	
	private class actMappercount implements RowMapper<Activity> {
		public Activity mapRow(ResultSet rs, int rowNum) throws SQLException {
			Activity activity= new Activity();
	    	activity.setId(rs.getInt("count"));
	    	activity.setActmetaid(rs.getLong("actmetaid"));
	    	return activity;
		}
	}
	
	


}
