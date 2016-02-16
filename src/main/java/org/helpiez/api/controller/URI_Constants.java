package org.helpiez.api.controller;

public class URI_Constants {
	
	public static final String HELLO ="/hello";
	
	//ACTIVITY Controller
	public static final String GET_ACTIVITY_BY_ID="/activitys/{id}";
	public static final String GET_ACTIVITY="/activitys/{meta}/{id}/{type}";
	public static final String POST_ACTIVITY="/activitys/";
	public static final String PUT_ACTIVITY="/activitys/";

	
	
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
	
	// EVENT Controller , intern , job , volunteering
	public static final String GET_EVENT_BY_ID="/events/{id}";
	public static final String UPDATE_EVENT="/events/";
	public static final String INSERT_EVENT="/events/";
	public static final String GET_EVENT="/events/name/{urlname}"; 
	
	// STORY Controller
	public static final String GET_STORY_BY_ID="/storys/{id}";
	public static final String UPDATE_STORY="/storys/";
	public static final String INSERT_STORY="/storys/";
	public static final String GET_STORY="/storys/name/{urlname}";
	
}
