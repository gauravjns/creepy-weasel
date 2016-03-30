package org.helpiez.api.response;

import org.helpiez.api.model.Activity;
import org.helpiez.api.model.Events;
import org.helpiez.api.model.Group;
import org.helpiez.api.model.Post;

public class ResActivity {
	
	private Group group;
	private Events event  ;
	
	public Group getGroup() {
		return group;
	}
	public void setGroup(Group group) {
		this.group = group;
	}
	public Events getEvent() {
		return event;
	}
	public void setEvent(Events event) {
		this.event = event;
	}
	
	

}
