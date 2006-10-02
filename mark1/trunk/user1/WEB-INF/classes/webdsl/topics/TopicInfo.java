package webdsl.topics;

import java.io.*;
import java.util.*;
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
	    + "('"   + sqlEscape(this.getTopicname())
	    + "','"   + sqlEscape(this.getTopictext())
	    + "');";
	
	return DataBaseUtilities.updateDataBase(query);
    }

    public int changeInDatabase()
    {
	String query = 
	    "update topic set "
	    + "topictext = '" + sqlEscape(this.getTopictext()) + "' "
	    + "where topicname = '" + sqlEscape(this.getTopicname()) + "';";
	return DataBaseUtilities.updateDataBase(query);
    }

    public TopicInfo getFromDatabase()
    {
	String query = 
	    "SELECT topicname, topictext FROM topic " 
	    + "WHERE topicname = '" + sqlEscape(this.getTopicname()) + "'";
	
	return (TopicInfo)DataBaseUtilities
	    .queryDataBase(query, new MakeTopicInfoFromResultSet(this));
    }


    public ArrayList getSubtopics()
    {
	String query = 
	    "SELECT topicname FROM topic "
	    + "WHERE topicname LIKE '" 
	    + sqlEscape(this.getTopicname()) + "%'";

	return (ArrayList)DataBaseUtilities
	    .queryDataBase(query, new MakeTopicInfoArrayListFromResultSet(new ArrayList()));
    }

    public String getWebName()
    {
	return webName(getTopicname());
    }

    public String getParentWeb()
    {
	int depth = 2;
	if (topicname.charAt(topicname.length() - 1) == '/')
	    {
		depth = 1;
	    }
	String[] dirs = topicname.split("/");
	StringBuffer topicweb = new StringBuffer("/");
	for (int i = 1; i < dirs.length - depth; i++)
	    {
		topicweb.append(dirs[i]);
		topicweb.append("/");
	    }
	return topicweb.toString();
    }

    public static String webName(String topicname)
    {
	if (topicname.charAt(topicname.length() - 1) == '/')
	    {
		return topicname;
	    }
	else
	    {
		String[] dirs = topicname.split("/");
		StringBuffer topicweb = new StringBuffer();
		for (int i = 0; i < dirs.length - 1; i++)
		    {
			topicweb.append(dirs[i]);
			topicweb.append("/");
		    }
		return topicweb.toString();
	    }
    }
	
    public static String sqlEscape(String s)
    {
	StringBuffer text = new StringBuffer("");
	for (int i = 0; i < s.length(); i++)
	    {
		char c = s.charAt(i);
		switch (c) {
		case '\'' : 
		    text.append("''");
		    break;
		default : 
		    text.append(c);
		    break;
		}		
	    }
	return text.toString();
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


class MakeTopicInfoArrayListFromResultSet implements ProcessResultSet
{
    ArrayList topics;

    public MakeTopicInfoArrayListFromResultSet(ArrayList topics)
    {
	this.topics = topics;
    }

    public Object process(ResultSet rs)
    {
	try {
	    while(rs.next())
		{
		    TopicInfo topicinfo = new TopicInfo();
		    topicinfo.setTopicname(rs.getString(1));
		    topics.add(topicinfo);
		}
	} catch(Exception e) {
	    throw new RuntimeException(e);
	}
	return (Object) topics;
    }
}

