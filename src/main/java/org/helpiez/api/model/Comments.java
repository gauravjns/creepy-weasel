package org.helpiez.api.model;

import java.sql.Timestamp;

public class Comments {
	
	private long id;
	private int userid;
	private String commentmeta;  //  user, story, feed , event, group, organization
	private long commentmetaid;
	private String content; // 5000 words 
	private String type; // comment, rating , review
	private String extra;
	private int status; // inactive, flagged
	private Timestamp timestamp;
	private String url;
	
}
