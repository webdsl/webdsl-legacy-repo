package topics;

import java.io.*;
import java.util.*;
import java.util.Date;
import users.*;

public class Topic 
{
    private Long id;
    private String text;
    private Date created;
    private Date modified;
    private Set authors = new HashSet(); // users that have contributed to this topic
    private Map subtopics = new HashMap(); 

    public Topic() {}

    public Long getId() {
        return id;
    }

    private void setId(Long id) {
        this.id = id;
    }

    public String getText() {
	return text;
    }

    public void setText(String text) {
	this.text = text;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getModified() {
        return modified;
    }

    public void setModified(Date modified) {
        this.modified = modified;
    }

    public Set getAuthors() {
      return authors;
    }

    public void setAuthors(Set authors) {
      this.authors = authors;
    }

    public Map getSubtopics() {
      return subtopics;
    }

    public void setSubtopics(Map subtopics) {
      this.subtopics = subtopics;
    }

    public Topic getSubtopic(String name) {
      return (Topic) this.getSubtopics().get(name);
    }
}


