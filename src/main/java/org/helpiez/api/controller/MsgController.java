package org.helpiez.api.controller;


import org.helpiez.api.DAO.MessageDAO;
import org.helpiez.api.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/messages")
public class MsgController {
	
	@Autowired
	private MessageDAO msgDAO;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
    public Message getMsgbyId(@PathVariable("id") int id) {	    	
		Message msg = msgDAO.getMsgbyID(id); 
    	return msg;
    }

}
