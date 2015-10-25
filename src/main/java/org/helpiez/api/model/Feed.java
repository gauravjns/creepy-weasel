package org.helpiez.api.model;

import java.sql.Timestamp;


public class Feed extends Post {
	
	private String userid;
	private String content; // 10,000 words
	private String media;
	private Timestamp live;
	private String permission; // user, group , world, 
	private String type; // batch , journey, wallpost
	

}
