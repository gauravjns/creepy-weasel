package org.helpiez.api.DAO;

import org.helpiez.api.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class UserDAO {
	
	@Autowired

	
	public User getuserbyid(String id) {
		User user= new User();
		
		return user;
	}

}
