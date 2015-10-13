package org.helpiez.api.model;

import java.sql.Timestamp;

public class Storymeta {
	
	long blogid;
	long postid;
	String content;
	int userid;
	Timestamp timestamp;
	String blogxtra;
	public long getBlogid() {
		return blogid;
	}
	public void setBlogid(long blogid) {
		this.blogid = blogid;
	}
	public long getPostid() {
		return postid;
	}
	public void setPostid(long postid) {
		this.postid = postid;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public Timestamp getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}
	public String getBlogxtra() {
		return blogxtra;
	}
	public void setBlogxtra(String blogxtra) {
		this.blogxtra = blogxtra;
	}
	
	
}
