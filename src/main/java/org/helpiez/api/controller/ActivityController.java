package org.helpiez.api.controller;

import java.util.List;

import org.helpiez.api.DAO.ActivityDAO;
import org.helpiez.api.model.Activity;
import org.helpiez.api.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/activitys")
public class ActivityController {

	@Autowired
    private ActivityDAO activitydao;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
    public Activity getActivitybyId(@PathVariable("id") int id) {	    	
  		Activity activity = activitydao.getActivitybyID(id); 
    	return activity;
    }
	
	@RequestMapping(value="/", method=RequestMethod.PUT)
    public int updateActivity(@RequestBody Activity activity) {	 
		return activitydao.update(activity);
    }
	
	@RequestMapping(value="/", method=RequestMethod.POST)
    public Boolean insertActivity(@RequestBody Activity activity) {	 
    	return activitydao.save(activity);
    }
	
	
	
	// This Function should be last in this class
    // Get follower for anytype user, post, group
	@RequestMapping(value="/{meta}/{id}/{type}", method=RequestMethod.GET)
    public List<Activity> getActivity(@PathVariable("id") int id,@PathVariable("meta") String meta,@PathVariable("type") String type) {	    	
		return activitydao.getActivity(meta, id, type); 
    	
    }
	
}
