package org.helpiez.api.response;

import org.helpiez.api.model.Group;
import org.helpiez.api.model.Story;
import org.helpiez.api.model.User;

public class ResStory {
	
	private Story story;
	private User author;
	private String authordesc;
	private Group group ;
	
	
	
	public Group getGroup() {
		return group;
	}
	public void setGroup(Group group) {
		this.group = group;
	}
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
