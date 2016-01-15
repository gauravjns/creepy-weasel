package org.helpiez.api.controller;


import java.util.ArrayList;
import java.util.List;

import org.helpiez.api.DAO.UserDAO;
import org.helpiez.api.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
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
	  	
	  	@RequestMapping(value="/url/{name}", method=RequestMethod.GET)
	    public User getUserbyURl(@PathVariable("name") String name) {	    	
	    	User user = userdao.getuserbyurl(name); 
	    	return user;
	    }
	  	
	  	@RequestMapping(value="/login/{email}/{password}", method=RequestMethod.GET)
	    public long getUserforlogin(@PathVariable("email") String email,@PathVariable("password") String pass) {	    	
	    	return userdao.checkuser(email, pass);
	    }
	  	@RequestMapping(value="/", method=RequestMethod.POST)
	    public Boolean insertUser(@RequestBody User user, @RequestParam(value = "password", required = false)  String password) {	 
	    	return userdao.save(user, password);
	    }
	  	
	  	@RequestMapping(value="/", method=RequestMethod.GET)
	    public List<User> listUser() {	 
	  		List<User> ls = new ArrayList<User>();
	  		ls=userdao.getlistofuser();	  		
	    	return ls;
	    }
	  	
	  	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	    public Boolean updateUser(@RequestBody User user, @PathVariable("id") int id) {	 
	    	User usertoupdate= userdao.getuserbyid(id); 
	  		return userdao.update(user, usertoupdate);
	    }
}
