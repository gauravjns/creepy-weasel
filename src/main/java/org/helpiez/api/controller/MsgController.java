package org.helpiez.api.controller;


import java.util.List;

import org.helpiez.api.DAO.MessageDAO;
import org.helpiez.api.model.Comments;
import org.helpiez.api.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
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
	
	
	// limit and offset lagao
	@RequestMapping(value="/{id}/{id2}", method=RequestMethod.GET)
    public List<Message> getMsgs(@PathVariable("id") long id,@PathVariable("id2") long id2) {	    	
		return msgDAO.getMsgs(id, id2); 
   
    }

	// Insert 
		@RequestMapping(value="/", method=RequestMethod.POST)
	    public int insertMsg(@RequestBody Message msg) {
			// check whether in proper format
			if (msg.getMessage()!=null && msg.getMessage().trim().length()>0 && msg.getUserid()>0  && msg.getUserto()>0 )
			{
				return msgDAO.save(msg);
			}
			else{ return 0;}
	    }	
		
	// Viewed message
		@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	    public int viewMsgbyId(@PathVariable("id") long id) {	    	
			 return msgDAO.viewed(id); 
	    }
		

}
