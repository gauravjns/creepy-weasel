package org.helpiez.api.controller;

import org.helpiez.api.DAO.StoryDAO;
import org.helpiez.api.DAO.UserDAO;
import org.helpiez.api.model.Story;
import org.helpiez.api.model.User;
import org.helpiez.api.response.ResStory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/")
public class StoryController {
	
	@Autowired
    private StoryDAO storydao;
	
	@Autowired
    private UserDAO userDAO;

  	@RequestMapping(value=URI_Constants.GET_STORY_BY_ID, method=RequestMethod.GET)
    public Story getStorybyId(@PathVariable("id") int id) {	    	
  		Story story = storydao.getStorybyID(id); 
    	return story;
    }
  	
  	@RequestMapping(value=URI_Constants.GET_STORY, method=RequestMethod.GET)
    public ResStory getStory(@PathVariable("urlname") String uString) {
  		ResStory res= new ResStory();
  		Story story = storydao.getStory(uString); 
  		res.setStory(story);
  		User user = userDAO.getshortuserbyid(Integer.parseInt(story.getAuthorid()));
  		res.setAuthor(user);
  		String str= userDAO.getUsermeta(Integer.parseInt(story.getAuthorid()),"about");
  		res.setAuthordesc(str);
    	return res;
    }
  	
  	@RequestMapping(value=URI_Constants.UPDATE_STORY, method=RequestMethod.PUT)
    public boolean editStorybyId(@RequestBody Story story) {	    	
  		Story story2 = storydao.getStorybyID(story.getId()); 
    	return storydao.update(story, story2);
    }
  	
  	@RequestMapping(value=URI_Constants.INSERT_STORY, method=RequestMethod.POST)
    public long createstory(@RequestBody Story story) {	    	
  		// check whether in proper format
		if (story.getName()!=null)
		{
    	return storydao.createStory(story);
		}
		else{ return 0;}
    }

}
