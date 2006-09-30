package webdsl.topics;

import java.io.*;
import webdsl.topics.*;
import javax.servlet.*;
import javax.servlet.http.*;
import webdsl.users.*;
import java.sql.*;


public class TopicInfo 
{
    private String topicname;
    private String topictext;

    public String getTopicname()
    {
	return topicname;
    }

    public String getTopictext()
    {
	return topictext;
    }

    public void setTopicname(String topicname)
    {
	this.topicname = topicname;
    }

    public void setTopictext(String topictext)
    {
	this.topictext = topictext;
    }

    public void renderTopicText(HttpServletRequest request,
				HttpServletResponse response) throws IOException
    {
	WikiParser p = new WikiParser(request, response, this.topictext);
	p.parse();
    }

    public int addToDatabase()
    {
	String query = 
	    "INSERT INTO topic (topicname, topictext) VALUES"
	    + "('"   + this.getTopicname()
	    + "','"   + this.getTopictext()
	    + "');";
	
	return DataBaseUtilities.updateDataBase(query);
    }

    public int changeInDatabase()
    {
	String query = 
	    "update topic set "
	    + "topictext = '" + this.getTopictext() + "' "
	    + "where topicname = '" + this.getTopicname() + "';";
	return DataBaseUtilities.updateDataBase(query);
    }

    public TopicInfo getFromDatabase()
    {
	String query = 
	    "SELECT topicname, topictext FROM topic " 
	    + "WHERE topicname = '" + this.getTopicname() + "'";
	
	return (TopicInfo)DataBaseUtilities
	    .queryDataBase(query, new MakeTopicInfoFromResultSet(this));
    }
}


class MakeTopicInfoFromResultSet implements ProcessResultSet
{
    TopicInfo topicinfo;

    public MakeTopicInfoFromResultSet(TopicInfo topicinfo)
    {
	this.topicinfo = topicinfo;
    }

    public Object process(ResultSet rs)
    {
	try{
	    if(rs.next())
		{
		    topicinfo.setTopicname(rs.getString(1));
		    topicinfo.setTopictext(rs.getString(2));
		}
	} catch(Exception e) {}
	return (Object) topicinfo;
    }
}
