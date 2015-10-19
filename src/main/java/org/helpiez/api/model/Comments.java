package org.helpiez.api.model;

import java.sql.Timestamp;

public class Comments {
	
	private long id;
	private long userid;
	private String commeta;  //  user, post, org
	private long commetaid;
	private String content; // 5000 chars 
	private String extra; // inactive, flagged
	private Timestamp timestamp;
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
