package org.helpiez.api.model;

import java.sql.Timestamp;

public class Notification {
	
	private long notid;
	private long userid;
	private String text;  //5000 char
	private Timestamp timestamp; // Time generated, Time viewed
	private String meta; // user group 
	private short viewed; // 0 - not viewed 1- viewed by clicking 2- dismissed 
	private String link; 
	private String image;
	public long getNotid() {
		return notid;
	}
	public void setNotid(long notid) {
		this.notid = notid;
	}
	public long getUserid() {
		return userid;
	}
	public void setUserid(long userid) {
		this.userid = userid;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public Timestamp getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}
	public String getMeta() {
		return meta;
	}
	public void setMeta(String meta) {
		this.meta = meta;
	}
	public short getViewed() {
		return viewed;
	}
	public void setViewed(short viewed) {
		this.viewed = viewed;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
}
