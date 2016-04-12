package org.helpiez.api.response;

import java.util.List;

import org.helpiez.api.model.Group;
import org.helpiez.api.model.Notification;

public class ResPanel {
	
	private Group group;
	private int home;
	private int stories;
	private int member;
	private int program;
	private int setting;
	private List<Notification> actvity ;
	
	public Group getGroup() {
		return group;
	}
	public void setGroup(Group group) {
		this.group = group;
	}
	public int getHome() {
		return home;
	}
	public void setHome(int home) {
		this.home = home;
	}
	public int getStories() {
		return stories;
	}
	public void setStories(int stories) {
		this.stories = stories;
	}
	public int getMember() {
		return member;
	}
	public void setMember(int member) {
		this.member = member;
	}
	public int getProgram() {
		return program;
	}
	public void setProgram(int program) {
		this.program = program;
	}
	public int getSetting() {
		return setting;
	}
	public void setSetting(int setting) {
		this.setting = setting;
	}
	public List<Notification> getActvity() {
		return actvity;
	}
	public void setActvity(List<Notification> actvity) {
		this.actvity = actvity;
	}
	
}
