package org.helpiez.api.response;

import org.helpiez.api.model.Events;
import org.helpiez.api.model.Group;
import org.helpiez.api.model.User;

public class ResEvents {

	private Events events;
	private User author;
	private Group group;
	
	
	public Group getGroup() {
		return group;
	}
	public void setGroup(Group group) {
		this.group = group;
	}
	public Events getEvents() {
		return events;
	}
	public User getAuthor() {
		return author;
	}
	public void setEvents(Events events) {
		this.events = events;
	}
	public void setAuthor(User author) {
		this.author = author;
	}
}
