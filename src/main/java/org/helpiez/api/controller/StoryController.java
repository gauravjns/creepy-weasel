package org.helpiez.api.controller;

import org.helpiez.api.DAO.StoryDAO;
import org.helpiez.api.model.Story;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/storys")
public class StoryController {
	
	@Autowired
    private StoryDAO storydao;

  	@RequestMapping(value="/{id}", method=RequestMethod.GET)
    public Story getStorybyId(@PathVariable("id") int id) {	    	
  		Story story = storydao.getStorybyID(id); 
    	return story;
    }
  	
  	@RequestMapping(value="/", method=RequestMethod.PUT)
    public boolean editStorybyId(@RequestBody Story story) {	    	
  		Story story2 = storydao.getStorybyID(story.getId()); 
    	return storydao.update(story, story2);
    }
  	
  	@RequestMapping(value="/", method=RequestMethod.POST)
    public int createstory(@RequestBody Story story) {	    	
  		// check whether in proper format
		if (story.getName()!=null)
		{
    	return storydao.createStory(story);
		}
		else{ return 0;}
    }

}
