package topics;

import java.io.*;
import java.util.*;
import java.util.Date;
import users.*;

import org.hibernate.*;
import util.HibernateUtil;

public class Topic 
{
    private Long id;
    private Boolean isroot;
    private String title;
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

    public Boolean getIsroot() {
	return isroot;
    }

    public void setIsroot(Boolean isroot) {
	if(RootTopic.getRootTopic() == null)
	  {
	     this.isroot = isroot;
	  }
    }
   
    public String getTitle() {
	return title;
    }

    public void setTitle(String title) {
	this.title = title;
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

    public Set getAuthorNames() {
      Set authors = new HashSet();
      for(Object name : this.getAuthors()) { 
        authors.add(((User)name).getUsername()); 
      }
      return authors;
    }

    public void addAuthor(User author) {
      getAuthors().add(author);
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

    public void addSubtopic(String name, Topic topic) {
      getSubtopics().put(name, topic);
    }

    public void update()
    {
      Session hsession = HibernateUtil.getSessionFactory().openSession();
      Transaction tr = hsession.beginTransaction();
      hsession.update(this);
      tr.commit();
      hsession.close();
    }

}


