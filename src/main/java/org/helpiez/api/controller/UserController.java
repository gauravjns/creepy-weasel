package org.helpiez.api.controller;


import java.util.concurrent.atomic.AtomicLong;

import org.helpiez.api.DAO.UserDAO;
import org.helpiez.api.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
		
	  	@Autowired
	    private UserDAO userdao;
		

	    @RequestMapping("/users/{id}")
	    public User getUserbyId(@RequestParam(value="name", defaultValue="World") String id) {
	    	
	    	User user = userdao.getuserbyid(id); 
	    	return user;
	    }
}
