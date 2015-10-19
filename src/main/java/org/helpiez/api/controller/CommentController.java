package org.helpiez.api.controller;


import org.helpiez.api.DAO.CommentDAO;
import org.helpiez.api.model.Comments;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/comments")
public class CommentController {
	
	@Autowired
	private CommentDAO commentDAO;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
    public Comments getCommentbyId(@PathVariable("id") int id) {	    	
  		Comments comments = commentDAO.getCommentbyID(id); 
    	return comments;
    }
	
	/*@RequestMapping(value="/{id}", method=RequestMethod.GET)
    public List<Comments> getComments(@PathVariable("id") int id) {	    	
  		Comments comments = commentDAO.getCommentbyID(id); 
    	return comments;
    }*/

}
