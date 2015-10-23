package org.helpiez.api.controller;


import java.util.List;

import org.helpiez.api.DAO.NotificationDAO;
import org.helpiez.api.model.Comments;
import org.helpiez.api.model.Message;
import org.helpiez.api.model.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/notifications")
public class NotificationController {
	
	@Autowired
	private NotificationDAO notDAO;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
    public Notification getNotbyId(@PathVariable("id") int id) {	    	
		Notification notification = notDAO.getNotificationbyID(id); 
    	return notification;
    }
	
	@RequestMapping(value="/user/{id}", method=RequestMethod.GET)
    public List<Notification> getNots(@PathVariable("id") int id ) {
		List<Notification> lst = notDAO.getNotificationList(id); 
    	return lst;
    }
	
	// Insert 
	@RequestMapping(value="/", method=RequestMethod.POST)
	public int insertMsg(@RequestBody Notification not) {
	// check whether in proper format
		if (not.getText()!=null && not.getText().trim().length()>5 && not.getImage()!=null && not.getImage().trim().length()>0 && not.getUserid()>0  && not.getLink()!=null && not.getLink().trim().length()>0 )
		{
			return notDAO.save(not);
		}
		else{ return 0;}
	}	
	
	

	// Viewed message
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public int viewNotbyId(@PathVariable("id") long id) {	    	
		 return notDAO.viewed(id); 
	}
}