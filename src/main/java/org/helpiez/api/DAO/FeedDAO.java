package org.helpiez.api.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.helpiez.api.model.Feed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class FeedDAO {

	@Autowired
    protected JdbcTemplate jdbc;
	
	public List<Feed> getFeeds(long id, long max) {
		if (max==0)
		{max = jdbc.queryForObject("Select feedid from feed order by feedid desc limit 1 ", int.class);
		max++;}
		List<Feed> lstpostfeedfromgroup = jdbc.query( "SELECT * FROM feed inner join posts on feedmetaid= postid inner join user on feed.userid=user.userid  inner join groups on postgroupid=groupid where feedid<? and postgroupid in (SELECT followmetaid FROM follow WHERE followmeta='group' and userid=?) order by feedid desc limit 6 ",new postfeedmapper(),max, id);		
		List<Feed> lstpostfeedfromuser = jdbc.query( "SELECT * FROM feed inner join posts on feedmetaid= postid inner join user on feed.userid=user.userid  inner join groups on postgroupid=groupid  where feedid<? and feed.userid in (SELECT followmetaid FROM follow WHERE followmeta='user' and userid=?) order by feedid desc limit 6 ",new postfeedmapper(),max, id);		
		List<Feed> lstpostfeedglobal = jdbc.query( "SELECT * FROM feed inner join posts on feedmetaid= postid inner join user on feed.userid=user.userid  inner join groups on postgroupid=groupid  where feedid<? and feed.status =3 and (feed.upvote-feed.downvote)>5 or (feed.upvote>10) order by feedid desc limit 7 ",new postfeedmapper(), max);

		List<Feed> newList = new ArrayList<Feed>(lstpostfeedfromgroup);
		newList.addAll(lstpostfeedfromuser);
		newList.addAll(lstpostfeedglobal);
		Collections.sort(newList, new Comparator<Feed>() {
			public int compare(Feed f1, Feed f2) {
				if((f1.getUpvotes()+5)*(f1.getFeedid())*(f1.getComment()+10)/((f1.getDownvote()+5)) < (f2.getUpvotes()+5)*(f2.getFeedid())*(f2.getComment()+10)/((f2.getDownvote()+5))){

					return 1;
				} 
				else {
					return -1;
				}
			}
			
		});
		


		List<Feed> finallist = new ArrayList<Feed>();
		System.out.println(newList);
		long feedid=0;
		int number=0;
		for (Feed feed : newList) {
			
			if (number==15)
			{
				return finallist;
			}
			try {
				String str = jdbc.queryForObject("SELECT type from activity where userid=? and type in ('upvote', 'downvote') and actmeta='post' and actmetaid=? ",String.class, id, feed.getFeedmetaid());

				if (str.equalsIgnoreCase("upvote")) {
					feed.setVotestatus(1);
				} else {
					feed.setVotestatus(-1);
				}
			} catch (Exception e) {
				feed.setVotestatus(0);
			}
			if (feed.getFeedid()!=feedid)
			{
				finallist.add(feed);
				feedid= feed.getFeedid();
				number ++;
			}
		}
		
		
		return finallist;
		
		
	}
	
	 private class postfeedmapper implements RowMapper<Feed> {
			public Feed mapRow(ResultSet rs, int rowNum) throws SQLException {
				Feed feed= new Feed();
				feed.setBody(rs.getString("body"));
				feed.setComment(rs.getInt("comment"));
				feed.setFeedid(rs.getLong("feedid"));
				feed.setFeedmeta(rs.getString("feedmeta"));
				feed.setFeedmetaid(rs.getLong("feedmetaid"));
				feed.setMedia(rs.getString("img"));
				feed.setStatus(rs.getInt("status"));
				feed.setTimestamp(rs.getTimestamp("timestamp"));
				feed.setTitle(rs.getString("postname"));
				feed.setUrl(rs.getString("posturl")+"."+rs.getString("posttype"));
				feed.setUserimg(rs.getString("userimg"));
				feed.setUsername(rs.getString("username"));
				feed.setUserurl(rs.getString("userurl"));
				feed.setUpvotes(rs.getInt("upvote"));
				feed.setDownvote(rs.getInt("downvote"));
				feed.setGroupimg(rs.getString("groupimg"));
				feed.setGroupname(rs.getString("groupname"));
				feed.setGroupurl(rs.getString("groupurl")+"."+rs.getString("grouptype"));
	        	return feed;
	        	
	        }
	    }

}
