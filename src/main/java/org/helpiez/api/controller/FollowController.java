package org.helpiez.api.controller;

import java.util.List;

import org.helpiez.api.DAO.FollowDAO;
import org.helpiez.api.model.Organization;
import org.helpiez.api.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/follows")
public class FollowController {
	
	@Autowired
	private FollowDAO followDAO;
	
	@RequestMapping(value="/{id}/users/", method=RequestMethod.GET)
    public List<User> getuserfollowlist(@PathVariable("id") int id) {	    	
		return followDAO.getFollowedUser(id); 
    	
    }
	@RequestMapping(value="/{id}/causes/", method=RequestMethod.GET)
    public List<Organization> getcausefollowlist(@PathVariable("id") int id) {	    	
		return followDAO.getFollowedOrgs(id, "cause"); 
    	
    }
	
	// Get follower for anytype user, post, group
	@RequestMapping(value="/{meta}/{id}", method=RequestMethod.GET)
    public List<User> getfollower(@PathVariable("id") int id,@PathVariable("meta") String meta) {	    	
		return followDAO.getFollower(meta, id); 
    	
    }
	
	

}
