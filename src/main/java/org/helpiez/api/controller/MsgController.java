package org.helpiez.api.controller;


import java.util.ArrayList;
import java.util.List;

import org.helpiez.api.DAO.MessageDAO;
import org.helpiez.api.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MsgController {
	
	@Autowired
	private MessageDAO msgDAO;
	
	@RequestMapping(value=URI_Constants.GET_MESSAGE_BY_ID, method=RequestMethod.GET)
    public Message getMsgbyId(@PathVariable("id") int id) {	    	
		Message msg = msgDAO.getMsgbyID(id); 
    	return msg;
    }
	
	@RequestMapping(value=URI_Constants.GET_MESSAGES_USER, method=RequestMethod.GET)
    public List<Message> getMsgbyUserId(@PathVariable("userid") int id, @RequestParam(value="max", required=false, defaultValue = "1" ) long max) {	    	
		List<Message> lstmsg= new ArrayList<Message>();
		if (max>1)
		{
			lstmsg=msgDAO.getMsgslist(id, max);
		}	
		else {
			lstmsg= msgDAO.getMsgsforuser(id);
		}
		return lstmsg;
        }
	
	// limit and offset lagao
	@RequestMapping(value=URI_Constants.GET_MESSAGES_BETWEEN_USERS, method=RequestMethod.GET)
    public List<Message> getMsgs(@PathVariable("userid") long id,@PathVariable("userid2") long id2) {	    	
		return msgDAO.getMsgs(id, id2); 
   
    }

	// Insert 
	@RequestMapping(value=URI_Constants.POST_MESSAGE, method=RequestMethod.POST)
    public int insertMsg(@RequestBody Message msg) {
		// check whether in proper format
		if (msg.getMessage()!=null && msg.getMessage().trim().length()>0 && msg.getUserid()>0  && msg.getUserto()>0 )
		{
			return msgDAO.save(msg);
		}
		else{ return 0;}
    }	
		
	// Viewed message
	@RequestMapping(value=URI_Constants.VIEW_MESSAGE, method=RequestMethod.PUT)
    public int viewMsgbyId(@PathVariable("id") long id) {	    	
		 return msgDAO.viewed(id); 
    }
		

}
