package org.helpiez.api.controller;

import java.util.ArrayList;
import java.util.List;

import org.helpiez.api.DAO.GroupDAO;
import org.helpiez.api.model.Group;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/groups")
public class GroupController {
	
	@Autowired
    private GroupDAO orgdao;
	

  	@RequestMapping(value="/{id}", method=RequestMethod.GET)
    public Group getOrgbyId(@PathVariable("id") int id) {	    	
    	Group group = orgdao.getOrgbyID(id); 
    	return group;
    }
  	
  	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
    public Boolean updateOrg(@RequestBody Group org, @PathVariable("id") int id) {	 
  		Group org2update= orgdao.getOrgbyID(id); 
  		return orgdao.update(org, org2update);
    }
  	
	@RequestMapping(value="/", method=RequestMethod.POST)
    public Boolean insertOrg(@RequestBody Group org) {	 
    	return orgdao.save(org);
    }
	
	@RequestMapping(value="/", method=RequestMethod.GET)
    public List<Group> listGroup() {	 
  		List<Group> ls = new ArrayList<Group>();
  		ls=orgdao.getlistofGrp();	  		
    	return ls;
    }
  	

}
