package org.helpiez.api.model;

import java.sql.Timestamp;

public class Comments {
	
	private long id;
	private long userid;
	private String commeta;  //  USER, POST, GROUP
	private long commetaid;
	private String content; // 5000 chars 
	private String extra; // UNVERFIFED , FLAGGED , ACTIVE, INACTIVE
	private Timestamp timestamp;
	private long parent;
	private String upvote;
	private String downvote;
	
	private String username;
	private String userimage;
	private String userurl;
	
	
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUserimage() {
		return userimage;
	}
	public void setUserimage(String userimage) {
		this.userimage = userimage;
	}
	public String getUserurl() {
		return userurl;
	}
	public void setUserurl(String userurl) {
		this.userurl = userurl;
	}
	public String getUpvote() {
		return upvote;
	}
	public void setUpvote(String upvote) {
		this.upvote = upvote;
	}
	public String getDownvote() {
		return downvote;
	}
	public void setDownvote(String downvote) {
		this.downvote = downvote;
	}
	public long getParent() {
		return parent;
	}
	public void setParent(long parent) {
		this.parent = parent;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getUserid() {
		return userid;
	}
	public void setUserid(long userid) {
		this.userid = userid;
	}
	public String getCommeta() {
		return commeta;
	}
	public void setCommeta(String commeta) {
		this.commeta = commeta;
	}
	public long getCommetaid() {
		return commetaid;
	}
	public void setCommetaid(long commetaid) {
		this.commetaid = commetaid;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getExtra() {
		return extra;
	}
	public void setExtra(String extra) {
		this.extra = extra;
	}
	public Timestamp getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}
	
}
