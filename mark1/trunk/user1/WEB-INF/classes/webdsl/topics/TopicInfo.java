package webdsl.topics;

import java.io.*;
import webdsl.topics.*;

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

    public void renderTopicText(Writer out) throws IOException
    {
	WikiParser p = new WikiParser(out, getTopictext());
	p.parse();
    }
}

