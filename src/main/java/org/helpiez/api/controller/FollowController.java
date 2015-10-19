package org.helpiez.api.controller;

import java.util.List;

import org.helpiez.api.DAO.FollowDAO;
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
	
	// Get follower for anytype
	@RequestMapping(value="/{meta}/{id}", method=RequestMethod.GET)
    public List<User> getfollower(@PathVariable("id") int id,@PathVariable("meta") String meta) {	    	
		return followDAO.getFollower(meta, id); 
    	
    }
	

}
