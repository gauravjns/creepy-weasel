package org.helpiez.api.model;

import java.sql.Date;
import java.sql.Timestamp;

public class Organization {
	
	private int id;
	private String name;    // Registered
	private int status;		//	
	private Timestamp timestamp;
	private String logo;
	private String url;
	private String type;  // NSS, COMPANY, NGO, CAMPAIGN, STARTUP, GROUP, CAUSE
	private String phone;  // Publc phone
	private String address;  // 
	private String email;
	private String banner;
	private String googlepage;
	private String facebookpage;
	private String twitter;
	private Date foundationdate;
	private String about; // Brief description 
	private String mission;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Timestamp getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getBanner() {
		return banner;
	}
	public void setBanner(String banner) {
		this.banner = banner;
	}
	public String getGooglepage() {
		return googlepage;
	}
	public void setGooglepage(String googlepage) {
		this.googlepage = googlepage;
	}
	public String getFacebookpage() {
		return facebookpage;
	}
	public void setFacebookpage(String facebookpage) {
		this.facebookpage = facebookpage;
	}
	public String getTwitter() {
		return twitter;
	}
	public void setTwitter(String twitter) {
		this.twitter = twitter;
	}
	public Date getFoundationdate() {
		return foundationdate;
	}
	public void setFoundationdate(Date foundationdate) {
		this.foundationdate = foundationdate;
	}
	public String getAbout() {
		return about;
	}
	public void setAbout(String about) {
		this.about = about;
	}
	public String getMission() {
		return mission;
	}
	public void setMission(String mission) {
		this.mission = mission;
	}
	
	
}
