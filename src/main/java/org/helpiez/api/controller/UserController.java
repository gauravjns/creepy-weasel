package org.helpiez.api.controller;


import java.util.concurrent.atomic.AtomicLong;

import org.helpiez.api.DAO.UserDAO;
import org.helpiez.api.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/users")
public class UserController {
		
	  	@Autowired
	    private UserDAO userdao;
		

	  	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	    public User getUserbyId(@PathVariable("id") int id) {
	    	
	    	User user = userdao.getuserbyid(id); 
	    	return user;
	    }
}
