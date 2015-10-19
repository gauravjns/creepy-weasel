package org.helpiez.api.model;

import java.sql.Timestamp;

public class Message {
	
	private long msgid;
	private long userid; // user who wrote message
	private String message; //5000 chars
	private int thread; // Message between two user
	private Timestamp timestamp;
	private short viewed; // 0 - not viewed 1- viewed 
	private long userto;
	
	
	public short getViewed() {
		return viewed;
	}
	public void setViewed(short viewed) {
		this.viewed = viewed;
	}
	public long getMsgid() {
		return msgid;
	}
	public void setMsgid(long msgid) {
		this.msgid = msgid;
	}
	public long getUserid() {
		return userid;
	}
	public void setUserid(long userid) {
		this.userid = userid;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public int getThread() {
		return thread;
	}
	public void setThread(int thread) {
		this.thread = thread;
	}
	public Timestamp getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}
	public long getUserto() {
		return userto;
	}
	public void setUserto(long userto) {
		this.userto = userto;
	}

}
