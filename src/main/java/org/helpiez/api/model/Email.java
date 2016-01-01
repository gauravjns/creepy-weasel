package org.helpiez.api.model;

import java.sql.Timestamp;

public class Email {
	private long msgid;
	private int success;
	private String msgto;
	private String msgcc;
	private String msgbcc;
	private String subject;
	private String text;
	private String msgmeta;
	private Timestamp notbefore;
	private Timestamp sendtime;
	private Timestamp insertitme;
	public long getMsgid() {
		return msgid;
	}
	public void setMsgid(long msgid) {
		this.msgid = msgid;
	}
	public int getSuccess() {
		return success;
	}
	public void setSuccess(int success) {
		this.success = success;
	}
	public String getMsgto() {
		return msgto;
	}
	public void setMsgto(String msgto) {
		this.msgto = msgto;
	}
	public String getMsgcc() {
		return msgcc;
	}
	public void setMsgcc(String msgcc) {
		this.msgcc = msgcc;
	}
	public String getMsgbcc() {
		return msgbcc;
	}
	public void setMsgbcc(String msgbcc) {
		this.msgbcc = msgbcc;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getMsgmeta() {
		return msgmeta;
	}
	public void setMsgmeta(String msgmeta) {
		this.msgmeta = msgmeta;
	}
	public Timestamp getNotbefore() {
		return notbefore;
	}
	public void setNotbefore(Timestamp notbefore) {
		this.notbefore = notbefore;
	}
	public Timestamp getSendtime() {
		return sendtime;
	}
	public void setSendtime(Timestamp sendtime) {
		this.sendtime = sendtime;
	}
	public Timestamp getInsertitme() {
		return insertitme;
	}
	public void setInsertitme(Timestamp insertitme) {
		this.insertitme = insertitme;
	}
	
}
