package webdsl.topics;

import java.io.*;
import webdsl.topics.*;
import javax.servlet.*;
import javax.servlet.http.*;

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
}

