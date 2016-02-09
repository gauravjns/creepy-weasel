package org.helpiez.api.controller;

public class URI_Constants {
	
	public static final String HELLO ="/hello";
	
	//Activity Controller
	
	
	
	// Comment Controller
	public static final String GET_COMMENT_BY_ID="/comments/{id}";
	public static final String GET_COMMENTS="/comments/{meta}/{id}/{order}";
	public static final String POST_COMMENT="/comments/";
	public static final String PUT_COMMENT="/comments/";

	
	// MESSAGE Controller
	public static final String GET_MESSAGE_BY_ID="/messages/{id}";
	public static final String GET_MESSAGES_BETWEEN_USERS="/messages/2users/";
	public static final String POST_MESSAGE="/messages/";
	public static final String VIEW_MESSAGE="/messages/{id}";
	public static final String GET_MESSAGES_USER="/messages/user/{userid}"; // max parameter
	
	
}
