package org.helpiez.api.controller;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.helpiez.api.DAO.FeedDAO;
import org.helpiez.api.DAO.UserDAO;
import org.helpiez.api.model.Feed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value="/feeds")
public class FeedController {
	
	@Autowired
    private FeedDAO feeddao;
	
  	@RequestMapping(value="/{id}", method=RequestMethod.GET)
    public List<Feed> getFeeds(@PathVariable("id") long id,
    		@RequestParam(value="max", required=false, defaultValue = "0" ) long max) {	    	
    	
  		List<Feed> lst = feeddao.getFeeds(id, max); 
  		Collections.sort(lst, new Comparator<Feed>() {
			public int compare(Feed f1, Feed f2) {
				if(f1.getFeedid()<f2.getFeedid()){

					return 1;
				} 
				else {
					return -1;
				}
			}
			
		});
    	return lst;
    
  	}
	
}