package org.helpiez.api.model;

import java.sql.Timestamp;

public class Follow {
	
	private long followid;
	private long userid;
	private String meta; // user, org, post
	private long metaid; 
	private short type; // 1- follow, 2- member (require aporoval)
	private short status; // 1- active 2- applied , 3- deactivated 
	private Timestamp timestamp;
	public long getFollowid() {
		return followid;
	}
	public void setFollowid(long followid) {
		this.followid = followid;
	}
	public long getUserid() {
		return userid;
	}
	public void setUserid(long userid) {
		this.userid = userid;
	}
	public String getMeta() {
		return meta;
	}
	public void setMeta(String meta) {
		this.meta = meta;
	}
	public long getMetaid() {
		return metaid;
	}
	public void setMetaid(long metaid) {
		this.metaid = metaid;
	}
	public short getType() {
		return type;
	}
	public void setType(short type) {
		this.type = type;
	}
	public short getStatus() {
		return status;
	}
	public void setStatus(short status) {
		this.status = status;
	}
	public Timestamp getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}
	
	

}
