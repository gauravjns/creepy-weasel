package org.helpiez.api.controller;

import java.util.List;

import org.helpiez.api.DAO.FollowDAO;
import org.helpiez.api.model.Follow;
import org.helpiez.api.model.Organization;
import org.helpiez.api.model.Post;
import org.helpiez.api.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/follows")
public class FollowController {
	
	@Autowired
	private FollowDAO followDAO;
	
	
	@RequestMapping(value="/", method=RequestMethod.PUT)
    public int updateStatus(@RequestBody Follow follow) {	 
  		if ( follow.getUserid()!=0 || follow.getMeta()!=null || follow.getMetaid()!=0 || follow.getType()!=0 ) 
  			{return followDAO.update(follow);}
  		else return 0;
    }
  	
	@RequestMapping(value="/", method=RequestMethod.POST)
    public int follow(@RequestBody Follow follow) {	 
		if ( follow.getUserid()!=0 || follow.getMeta()!=null || follow.getMetaid()!=0 || follow.getType()!=0) 
			{	
			if (follow.getType()==1)
				{
				follow.setStatus((short)1);
				return followDAO.save(follow);
				}
			else if(follow.getType()==2)  {
				follow.setStatus((short)2);
				return followDAO.save(follow);
			}
			else 
			{return 0;}
			}
		else return 0;
		
    }
	
	// User followed
	@RequestMapping(value="/{id}/user/", method=RequestMethod.GET)
    public List<User> getuserfollowlist(@PathVariable("id") int id) {	    	
		return followDAO.getFollowedUser(id); 
    	
    }
	
	// Groups followes
	@RequestMapping(value="/{id}/cause/", method=RequestMethod.GET)
    public List<Organization> getcausefollowlist(@PathVariable("id") int id) {	    	
		return followDAO.getFollowedOrgs(id, "cause"); 
    	
    }
	@RequestMapping(value="/{id}/nss/", method=RequestMethod.GET)
    public List<Organization> getnssfollowlist(@PathVariable("id") int id) {	    	
		return followDAO.getFollowedOrgs(id, "nss"); 
    	
    }
	@RequestMapping(value="/{id}/ngo/", method=RequestMethod.GET)
    public List<Organization> getngofollowlist(@PathVariable("id") int id) {	    	
		return followDAO.getFollowedOrgs(id, "ngo"); 
    	
    }@RequestMapping(value="/{id}/group/", method=RequestMethod.GET)
    public List<Organization> getgroupfollowlist(@PathVariable("id") int id) {	    	
		return followDAO.getFollowedOrgs(id, "group"); 
    	
    }@RequestMapping(value="/{id}/campaign/", method=RequestMethod.GET)
    public List<Organization> getcamfollowlist(@PathVariable("id") int id) {	    	
		return followDAO.getFollowedOrgs(id, "campaign"); 
    	
    }@RequestMapping(value="/{id}/startup/", method=RequestMethod.GET)
    public List<Organization> getupfollowlist(@PathVariable("id") int id) {	    	
		return followDAO.getFollowedOrgs(id, "startup"); 
    	
    }@RequestMapping(value="/{id}/company/", method=RequestMethod.GET)
    public List<Organization> getcomfollowlist(@PathVariable("id") int id) {	    	
		return followDAO.getFollowedOrgs(id, "company"); 
    	
    }
    
    // Post followers
    // Event
    @RequestMapping(value="/{id}/volunteering/", method=RequestMethod.GET)
    public List<Post> getvolfollowlist(@PathVariable("id") int id) {	    	
		return followDAO.getFollowedPost(id, "volunteering"); 
    	
    }
    @RequestMapping(value="/{id}/job/", method=RequestMethod.GET)
    public List<Post> getjobfollowlist(@PathVariable("id") int id) {	    	
		return followDAO.getFollowedPost(id, "job"); 
    	
    }@RequestMapping(value="/{id}/event/", method=RequestMethod.GET)
    public List<Post> geteventfollowlist(@PathVariable("id") int id) {	    	
		return followDAO.getFollowedPost(id, "event"); 
    	
    }@RequestMapping(value="/{id}/intern/", method=RequestMethod.GET)
    public List<Post> getintfollowlist(@PathVariable("id") int id) {	    	
		return followDAO.getFollowedPost(id, "intern"); 
    	
    }@RequestMapping(value="/{id}/story/", method=RequestMethod.GET)
    public List<Post> getstoryfollowlist(@PathVariable("id") int id) {	    	
		return followDAO.getFollowedPost(id, "story");
    }
    
	// Get follower for anytype user, post, group
	@RequestMapping(value="/{meta}/{id}", method=RequestMethod.GET)
    public List<User> getfollower(@PathVariable("id") int id,@PathVariable("meta") String meta) {	    	
		return followDAO.getFollower(meta, id); 
    	
    }

}
