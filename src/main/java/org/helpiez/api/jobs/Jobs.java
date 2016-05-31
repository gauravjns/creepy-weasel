package org.helpiez.api.jobs;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.helpiez.api.DAO.EmailDAO;
import org.helpiez.api.model.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Jobs {
	
	
	@Autowired
	private EmailDAO emailDAO;
	
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
	
	@Scheduled(fixedRate = 6000)
    public void flagcommentdelete() {
        System.out.println("The time is now " + dateFormat.format(new Date()));
    	
    }

    @Scheduled(fixedRate = 600000)
    public void emailsender() {
        /*//System.out.println("The time is now " + dateFormat.format(new Date()));
    	Date date= new Date();
    	Timestamp time= new Timestamp(date.getTime());
    	List<Email> lst = emailDAO.getEmailList(time);
    	if (lst.size()>0)
    	{
    		for (Email email : lst) {
				int success=sendaws(email);
				if(success>0)
					emailDAO.update(email.getMsgid(), success);	
			}
    		
    	}
    	else {
    		System.out.println("No email to send");
    	}*/
    }

	private int sendaws(Email email) {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
