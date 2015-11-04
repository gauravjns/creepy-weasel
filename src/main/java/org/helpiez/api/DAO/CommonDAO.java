package org.helpiez.api.DAO;



import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

import org.helpiez.api.model.Group;
import org.helpiez.api.model.Post;
import org.helpiez.api.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class CommonDAO {
	
	@Autowired
    protected JdbcTemplate jdbc;
	
	
	public String urlgenerator(String name, String meta){
		
		final Set<String> stopwords = new HashSet<String>();
		//System.out.println("Stopword:"+stopword);
		String stopword="a about above after again against all am an and any are aren't as at be because been before being below between both but by can't cannot could couldn't did didn't do does doesn't doing don't down during each few for from further had hadn't has hasn't have haven't having he he'd he'll he's her here here's hers herself him himself his how how's i i'd i'll i'm i've if in into is isn't it it's its itself let's me more most mustn't my myself no nor not of off on once only or other ought our ours ourselves out over own same shan't she she'd she'll she's should shouldn't so some such than that that's the their theirs them themselves then there there's these they they'd they'll they're they've this those through to too under until up very was wasn't we we'd we'll we're we've were weren't what what's when when's where where's which while who who's whom why why's with won't would wouldn't you you'd you'll you're you've your yours yourself yourselves . , / & ";
		StringTokenizer st = new StringTokenizer(stopword, " ");
		while(st.hasMoreTokens())
			stopwords.add(st.nextToken());
		
		name=name.replace(".", "");
		name=name.replace("-", "");
		name=name.replace("/", "");
		name=name.replace("?", "");
		name=name.replace("~", "");
		name=name.replace("#", "");
		name=name.replace("_", "");
		name=name.replace("&", "");
		name=name.replace("(", "");
		name=name.replace(")", "");
		name=name.replace("{", "");
		name=name.replace("}", "");
		name=name.replace("[", "");
		name=name.replace("]", "");
		name=name.replace("'", "");
		name=name.replace(":", "");
		name=name.replace(";", "");
		name=name.replace("<", "");
		name=name.replace(">", "");
		
		String[] parts = name.split(" ");
		StringBuilder builder = new StringBuilder();
		for (String string : parts) {
			string=string.toLowerCase();
			
			if (stopwords.contains(string))
				continue;
			
			if (builder.length() > 0) {
		        builder.append("-");
		    }
		    builder.append(string);
		}

		String proposedname = builder.toString();
		String name1 =proposedname; 
		if (meta.equals("user"))
		{	User user= null;
			int i=0;
			while(i==0||user!=null )
			{	
			user=null;
			try
			{	String sql = "SELECT * FROM user WHERE userurl like '"+ name1+"'";
				user =jdbc.queryForObject(sql, new usershortMapper());
				
			}
			catch(Exception e)
			{
				System.out.println(e);
			}
			i++;
			if (user!=null)
				name1= proposedname+i;
			}
			return name1;
			
		}
		else if (meta.equals("post"))
		{
			Post post= null;
			int i=0;
			while(i==0||post!=null )
			{	
			post=null;
			try
			{	String sql = "SELECT * FROM posts WHERE posturl like '"+ name1+"'";
				post =jdbc.queryForObject(sql, new shortpostMapper());
				
			}
			catch(Exception e)
			{
				System.out.println(e);
			}
			i++;
			if (post!=null)
				name1= proposedname+i;
			}
			return name1;
			
		}
		else if (meta.equals("group"))
		{
			Group group= null;
			int i=0;
			while(i==0||group!=null )
			{	
			group=null;
			try
			{	String sql = "SELECT * FROM groups WHERE groupurl like '"+ name1+"'";
				group =jdbc.queryForObject(sql, new shortgroupMapper());
				
			}
			catch(Exception e)
			{
				System.out.println(e);
			}
			i++;
			if (group!=null)
				name1= proposedname+i;
			}
			return name1;
		}
		return null;

	}
	
	// User Short
	  public class usershortMapper implements RowMapper<User> {
			public User mapRow(ResultSet rs, int rowNum) throws SQLException {
				User user = new User();
	            user.setId(rs.getLong(1));
	            user.setEmail(rs.getString(2));
	            user.setStatus(rs.getShort(5));
	            user.setImg(rs.getString(7));
	            user.setUrl(rs.getString(8));
	            user.setTimestamp(rs.getTimestamp(6));
	            user.setName(rs.getString("username"));
				return user;
			}
		}
	  private class shortpostMapper implements RowMapper<Post> {
			public Post mapRow(ResultSet rs, int rowNum) throws SQLException {
				Post post = new Post();
		     	post.setId(rs.getLong("postid"));
		     	post.setType(rs.getString("posttype"));
		     	post.setStatus(rs.getShort("poststatus"));
		     	post.setUrl(rs.getString("posturl"));
		     	post.setTimestamp(rs.getTimestamp("posttimestamp"));
		     	post.setName(rs.getString("postname"));
		     	post.setGroupid(rs.getLong("postgroupid"));
		     	post.setExtra(rs.getString("postxtra"));
		        return post;
			}
	  }
	  
	   private class shortgroupMapper implements RowMapper<Group> {
		   public Group mapRow(ResultSet rs, int rowNum) throws SQLException {
		       	Group group = new Group();
		       	group.setId(rs.getLong(1));
		       	group.setType(rs.getString(4));
		       	group.setStatus(rs.getShort(3));
		       	group.setLogo(rs.getString(7));
		       	group.setUrl(rs.getString(6));
		       	group.setTimestamp(rs.getTimestamp(5));
		       	group.setName(rs.getString(2));
		       	return group;
       }
   }
	    


}
