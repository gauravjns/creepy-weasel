package org.helpiez.api.controller;

import org.helpiez.api.DAO.PostDAO;
import org.helpiez.api.model.Events;
import org.helpiez.api.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/posts")
public class PostController {
	
	@Autowired
	private PostDAO postdao;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
    public Post getPostbyId(@PathVariable("id") long id) {	    	
		Post post = postdao.getpostbyid(id); 
    	return post;
    }
	
	@RequestMapping(value="/", method=RequestMethod.PUT)
    public boolean updateEvent(@RequestBody Post post) {	 
		
		if  (postdao.update(post)==1)
		{
			return true;
		}
		else {
			return false;
		}
    }
  	
	@RequestMapping(value="/", method=RequestMethod.POST)
    public Boolean insertEvent(@RequestBody Post post) {	 
    	
    	if ( postdao.save(post)==1)
		{
			return true;
		}
		else {
			return false;
		}
    }

}
