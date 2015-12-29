package org.helpiez.api.controller;

import org.helpiez.api.DAO.EventsDAO;
import org.helpiez.api.model.Events;
import org.helpiez.api.model.Group;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/events")
public class EventController {
	

	@Autowired
    private EventsDAO eventdao;
	

  	@RequestMapping(value="/{id}", method=RequestMethod.GET)
    public Events getEventbyId(@PathVariable("id") int id) {	    	
  		Events event = eventdao.getEventbyID(id); 
    	return event;
    }
  	
  	@RequestMapping(value="/", method=RequestMethod.PUT)
    public Boolean updateEvent(@RequestBody Events event) {	 
  		Events event2= eventdao.getEventbyID(event.getId()); 
  		return eventdao.update(event, event2);
    }
  	
	@RequestMapping(value="/", method=RequestMethod.POST)
    public Boolean insertEvent(@RequestBody Events event) {	 
    	return eventdao.save(event);
    }
	

}
