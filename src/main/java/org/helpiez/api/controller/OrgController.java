package org.helpiez.api.controller;

import java.util.ArrayList;
import java.util.List;

import org.helpiez.api.DAO.OrgDAO;
import org.helpiez.api.model.Organization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/groups")
public class OrgController {
	
	@Autowired
    private OrgDAO orgdao;
	

  	@RequestMapping(value="/{id}", method=RequestMethod.GET)
    public Organization getOrgbyId(@PathVariable("id") int id) {	    	
    	Organization organization = orgdao.getOrgbyID(id); 
    	return organization;
    }
  	
  	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
    public Boolean updateOrg(@RequestBody Organization org, @PathVariable("id") int id) {	 
  		Organization org2update= orgdao.getOrgbyID(id); 
  		return orgdao.update(org, org2update);
    }
  	
	@RequestMapping(value="/", method=RequestMethod.POST)
    public Boolean insertOrg(@RequestBody Organization org) {	 
    	return orgdao.save(org);
    }
	
	@RequestMapping(value="/", method=RequestMethod.GET)
    public List<Organization> listGroup() {	 
  		List<Organization> ls = new ArrayList<Organization>();
  		ls=orgdao.getlistofGrp();	  		
    	return ls;
    }
  	

}
