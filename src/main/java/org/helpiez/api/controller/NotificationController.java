package org.helpiez.api.controller;


import org.helpiez.api.DAO.NotificationDAO;
import org.helpiez.api.model.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
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

}
