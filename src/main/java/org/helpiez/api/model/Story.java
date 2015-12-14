package org.helpiez.api.model;

//Article, Long Text etc 
public class Story extends Post {
	
	private String authorid; // story author 
	private String blogid; // Stored text
	private String excerpt;
	private String featuredimage;
	private String commentstatus; //
	private String postparent; //
	private Text blog;
	
	
	
	public String getAuthorid() {
		return authorid;
	}
	public void setAuthorid(String authorid) {
		this.authorid = authorid;
	}
	public String getBlogid() {
		return blogid;
	}
	public void setBlogid(String blogid) {
		this.blogid = blogid;
	}
	public String getExcerpt() {
		return excerpt;
	}
	public void setExcerpt(String excerpt) {
		this.excerpt = excerpt;
	}
	public String getFeaturedimage() {
		return featuredimage;
	}
	public void setFeaturedimage(String featuredimage) {
		this.featuredimage = featuredimage;
	}
	public String getCommentstatus() {
		return commentstatus;
	}
	public void setCommentstatus(String commentstatus) {
		this.commentstatus = commentstatus;
	}
	public String getPostparent() {
		return postparent;
	}
	public void setPostparent(String postparent) {
		this.postparent = postparent;
	}
	public Text getBlog() {
		return blog;
	}
	public void setBlog(Text blog) {
		this.blog = blog;
	}
	
}
