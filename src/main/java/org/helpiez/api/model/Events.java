package org.helpiez.api.model;

import java.sql.Timestamp;

// Internship, Volunteering, Jobs, Ongroud Events, survey    
public class Events extends Post
{

	private String required; // in case of volunteering, internship, job
	private String description; // upto 10000 words
	private String location;   // 
	private String salary;  // reimbursement 
	private String email;  // inquiry to be automatically filled
	private String qualification;
	private String duration ;
	private String schedule;
	private String image;
	private String virtual;  // Full time , part time, virtual , 
	private String certificate; // postid for certification
	private Timestamp deadline;
//	private String question;// (question, type, qmeta) 
//	private String commentstatus; 
//	private String postparent;
//  private String nu_question;	// number of question 
	
	public String getRequired() {
		return required;
	}
	public void setRequired(String required) {
		this.required = required;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getSalary() {
		return salary;
	}
	public void setSalary(String salary) {
		this.salary = salary;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getQualification() {
		return qualification;
	}
	public void setQualification(String qualification) {
		this.qualification = qualification;
	}
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	public String getSchedule() {
		return schedule;
	}
	public void setSchedule(String schedule) {
		this.schedule = schedule;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getVirtual() {
		return virtual;
	}
	public void setVirtual(String virtual) {
		this.virtual = virtual;
	}
	public String getCertificate() {
		return certificate;
	}
	public void setCertificate(String certificate) {
		this.certificate = certificate;
	}
	public Timestamp getDeadline() {
		return deadline;
	}
	public void setDeadline(Timestamp deadline) {
		this.deadline = deadline;
	}
	

}
