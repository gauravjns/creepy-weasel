package org.helpiez.api.jobs;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class DailyJobs {
	
	 	@Scheduled(fixedRate = 60000)
	    public void birthdaywish() {
		 
		 // runs at 3 am find a day before and set email at 11.59
		 
	 }

}
