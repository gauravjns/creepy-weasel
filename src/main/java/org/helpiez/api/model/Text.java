package org.helpiez.api.model;

import java.sql.Timestamp;

public class Text {
	
	long textid;
	String content;
	Timestamp timestamp;
	String textextra;
	public long getTextid() {
		return textid;
	}
	public void setTextid(long textid) {
		this.textid = textid;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Timestamp getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}
	public String getTextextra() {
		return textextra;
	}
	public void setTextextra(String textextra) {
		this.textextra = textextra;
	}
	
}
