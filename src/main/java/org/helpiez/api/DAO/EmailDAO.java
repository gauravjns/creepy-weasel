package org.helpiez.api.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import org.helpiez.api.model.Comments;
import org.helpiez.api.model.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class EmailDAO {
	
	@Autowired
    protected JdbcTemplate jdbc;
	
	public List<Email> getEmailList(Timestamp notbefore) {
		List<Email> lst =jdbc.query("SELECT * FROM email WHERE success=0 and NOW() > ? LIMIT 100 ", new emailmapper(),notbefore);			
		return lst; 
	}	
	
	 private class emailmapper implements RowMapper<Email> {
			public Email mapRow(ResultSet rs, int rowNum) throws SQLException {
		Email email = new Email();
     	email.setMsgid(rs.getLong("msgid"));
     	email.setMsgto(rs.getString("msgto"));
     	email.setMsgcc(rs.getString("msgcc"));
     	email.setMsgbcc(rs.getString("msgbcc"));
     	email.setSubject(rs.getString("subject"));
     	email.setText(rs.getString("text"));
     	email.setNotbefore(rs.getTimestamp("notbefore"));
     	email.setSendtime(rs.getTimestamp("sendtime"));
     	email.setInsertitme(rs.getTimestamp("inserttime"));
     	email.setSuccess(rs.getInt("success"));
     	email.setMsgmeta(rs.getString("msgmeta"));
     	return email;
     }
 }

	public void update(Email email) {
		// TODO Auto-generated method stub
		
	}


}
