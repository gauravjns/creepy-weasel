package org.helpiez.api.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.helpiez.api.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class MessageDAO {
	
	@Autowired
    protected JdbcTemplate jdbc;
	
	public Message getMsgbyID(int id) {
		Message msg= new Message();
		msg =jdbc.queryForObject("SELECT * FROM message WHERE msgid=?", new msgMapper(), id);
		return msg;
	}
	
	// Result set mapper
	   private class msgMapper implements RowMapper<Message> {
				public Message mapRow(ResultSet rs, int rowNum) throws SQLException {
				Message msg = new Message();
	        	msg.setMsgid(rs.getLong("msgid"));
	        	msg.setUserid(rs.getLong("userid"));
	        	msg.setThread(rs.getInt("thread"));
	        	msg.setUserto(rs.getLong("usertoid"));
	        	msg.setMessage(rs.getString("msg"));
	        	msg.setTimestamp(rs.getTimestamp("timestamp"));
	        	msg.setViewed(rs.getShort("viewed"));
	            return msg;
	        }
	    }


}
