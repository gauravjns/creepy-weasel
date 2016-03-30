package org.helpiez.api.model;

import java.sql.Timestamp;

//Can add ( userid, actmeta, actmetaid, type) as primary key
public class Activity {
	
	private long id;
	private long userid;
	private String actmeta; // USER, POST, GROUP, COMMENT
	private long actmetaid; //
	private String type; // upvote, DOWNVOTE, APPLY (event), CREATE
	private short status; // 1-APPLY 2-SHORTLIST, 3-SELECT , 4-REJECT , 5-COMPLETE 6- PARTIAL 7-Started
	private String extra; // Text id number for answer in case of apply, Text in case of auto generated journey
	private Timestamp timestamp;
	public long getId() {
		return id;
	}
	public long getUserid() {
		return userid;
	}
	public String getActmeta() {
		return actmeta;
	}
	public long getActmetaid() {
		return actmetaid;
	}
	public String getType() {
		return type;
	}
	public short getStatus() {
		return status;
	}
	public String getExtra() {
		return extra;
	}
	public Timestamp getTimestamp() {
		return timestamp;
	}
	public void setId(long id) {
		this.id = id;
	}
	public void setUserid(long userid) {
		this.userid = userid;
	}
	public void setActmeta(String actmeta) {
		this.actmeta = actmeta;
	}
	public void setActmetaid(long actmetaid) {
		this.actmetaid = actmetaid;
	}
	public void setType(String type) {
		this.type = type;
	}
	public void setStatus(short status) {
		this.status = status;
	}
	public void setExtra(String extra) {
		this.extra = extra;
	}
	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}
	
	
}	
