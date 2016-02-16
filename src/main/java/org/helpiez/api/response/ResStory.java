package org.helpiez.api.response;

import org.helpiez.api.model.Story;
import org.helpiez.api.model.User;

public class ResStory {
	
	private Story story;
	private User author;
	private String authordesc;
	public Story getStory() {
		return story;
	}
	public void setStory(Story story) {
		this.story = story;
	}
	public User getAuthor() {
		return author;
	}
	public void setAuthor(User author) {
		this.author = author;
	}
	public String getAuthordesc() {
		return authordesc;
	}
	public void setAuthordesc(String authordesc) {
		this.authordesc = authordesc;
	}
	
	
	
	
}
