package org.helpiez.api.controller;


import java.util.List;

import org.helpiez.api.DAO.CommentDAO;
import org.helpiez.api.model.Comments;
import org.helpiez.api.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/comments")
public class CommentController {
	
	@Autowired
	private CommentDAO commentDAO;
	
	// Get
	
	@RequestMapping(value="/{meta}/{id}/{order}", method=RequestMethod.GET)
    public List<Comments> getComments(@PathVariable("meta") String meta, @PathVariable("id") int id, @PathVariable("order")String order ) {
		List<Comments> lst = commentDAO.getCommentList(id, meta, order); 
    	return lst;
    }
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
    public Comments getCommentbyId(@PathVariable("id") int id) {	    	
  		Comments comments = commentDAO.getCommentbyID(id); 
    	return comments;
    }
	
	
	
	// Insert 
	@RequestMapping(value="/", method=RequestMethod.POST)
    public int insertComment(@RequestBody Comments comment) {
		// check whether in proper format
		if (comment.getCommeta()!=null && comment.getCommetaid()>0 && comment.getUserid()>0 && comment.getContent()!=null && !comment.getContent().trim().equals("") && 
				(comment.getCommeta().equals("group")||comment.getCommeta().equals("user")||comment.getCommeta().equals("post"))
			&& (comment.getExtra().equals("active")||comment.getExtra().equals("unverified"))	
				)
		{
    	return commentDAO.save(comment);
		}
		else{ return 0;}
    }
	
	// Edit comment
	@RequestMapping(value="/", method=RequestMethod.PUT)
    public int editCommentbyId(@RequestBody Comments comment) {	    	
		
		if (comment.getCommeta()!=null && comment.getCommetaid()>0  && comment.getContent()!=null && !comment.getContent().trim().equals("") && 
				(comment.getCommeta().equals("group")||comment.getCommeta().equals("user")||comment.getCommeta().equals("post")) && comment.getId()>0
			&& (comment.getExtra().equals("active")||comment.getExtra().equals("unverified")||comment.getExtra().equals("delete")||comment.getExtra().equals("flagged")) 	
				)
		{
    	return commentDAO.edit(comment);
		}
		else{ return 0;}
    	
    }
	
	

}
