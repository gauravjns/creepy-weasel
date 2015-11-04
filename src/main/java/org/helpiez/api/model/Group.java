package org.helpiez.api.model;


import java.sql.Timestamp;
import java.util.Date;

// NSS, COM, NGO, CAMP, GOV, CLUB, CAUSE
public class Group {
	
	private long id;
	private String name;    // Registered
	private short status;		//	1 created 2 active 3 with head 
	private Timestamp timestamp;
	private String logo;
	private String url;
	private String type; //NSS, COM, NGO, CAMP, GOV, CLUB, CAUSE
	private String phone;  // Public phone
	private String address;  // Main office
	private String city;
	private String state;
	private String pincode;
	private String weblink;
	private String email;
	private String banner;
	private String googlepage;
	private String facebookpage;
	private String twitter;
	private String instagram;
	private String view;
	private Date foundationdate;
	private String about; // Brief description 
	private String mission;
	private String youtube;
	private String strength;
	
	
	
	public String getYoutube() {
		return youtube;
	}
	public void setYoutube(String youtube) {
		this.youtube = youtube;
	}
	public String getStrength() {
		return strength;
	}
	public void setStrength(String strength) {
		this.strength = strength;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getPincode() {
		return pincode;
	}
	public void setPincode(String pincode) {
		this.pincode = pincode;
	}
	public String getWeblink() {
		return weblink;
	}
	public void setWeblink(String weblink) {
		this.weblink = weblink;
	}
	public String getInstagram() {
		return instagram;
	}
	public void setInstagram(String instagram) {
		this.instagram = instagram;
	}
	public String getView() {
		return view;
	}
	public void setView(String view) {
		this.view = view;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public short getStatus() {
		return status;
	}
	public void setStatus(short status) {
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
