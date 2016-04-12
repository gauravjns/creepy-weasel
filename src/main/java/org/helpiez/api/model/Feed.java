package org.helpiez.api.model;

import java.sql.Timestamp;

public class Feed {
	
	long feedid;  
	
	// User property by userid stored
	String username;
	String userimg;
	String userurl;
	
	// Post property using feedmeta and feedmetaid
	String title;
	String body;
	String media;
	String url; 

	String feedmeta; // post, org, 
	long feedmetaid; //
	Timestamp timestamp;
	
	
	
	// upvotes-downvotes
	int upvotes;
	int downvote;
	int comment;
	int votestatus; // 0 no vote, 1 upvote , -1 downvote 
	
	
	int status; // 1 - closed(only to group ) 2- local(only to followers) 3 - global 
	
	String groupurl;
	String groupimg;
	String groupname;
	
	
	
	
	

	public int getUpvotes() {
		return upvotes;
	}

	public int getDownvote() {
		return downvote;
	}

	public void setUpvotes(int upvotes) {
		this.upvotes = upvotes;
	}

	public void setDownvote(int downvote) {
		this.downvote = downvote;
	}

	public String getGroupurl() {
		return groupurl;
	}

	public String getGroupimg() {
		return groupimg;
	}

	public String getGroupname() {
		return groupname;
	}

	public void setGroupurl(String groupurl) {
		this.groupurl = groupurl;
	}

	public void setGroupimg(String groupimg) {
		this.groupimg = groupimg;
	}

	public void setGroupname(String groupname) {
		this.groupname = groupname;
	}

	public long getFeedmetaid() {
		return feedmetaid;
	}

	public void setFeedmetaid(long feedmetaid) {
		this.feedmetaid = feedmetaid;
	}

	public long getFeedid() {
		return feedid;
	}

	public String getUsername() {
		return username;
	}

	public String getUserimg() {
		return userimg;
	}

	public String getUserurl() {
		return userurl;
	}

	public String getTitle() {
		return title;
	}

	public String getBody() {
		return body;
	}

	public String getMedia() {
		return media;
	}

	public String getUrl() {
		return url;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public int getComment() {
		return comment;
	}

	
	public int getVotestatus() {
		return votestatus;
	}

	public int getStatus() {
		return status;
	}

	public String getFeedmeta() {
		return feedmeta;
	}

	public void setFeedid(long feedid) {
		this.feedid = feedid;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setUserimg(String userimg) {
		this.userimg = userimg;
	}

	public void setUserurl(String userurl) {
		this.userurl = userurl;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public void setMedia(String media) {
		this.media = media;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	public void setComment(int comment) {
		this.comment = comment;
	}


	public void setVotestatus(int votestatus) {
		this.votestatus = votestatus;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public void setFeedmeta(String feedmeta) {
		this.feedmeta = feedmeta;
	}
	
	
	
	
}
