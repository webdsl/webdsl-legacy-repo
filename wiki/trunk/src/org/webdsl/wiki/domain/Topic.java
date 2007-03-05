package org.webdsl.wiki.domain;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.persistence.*;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.webdsl.wiki.utilities.HibernateUtil;

@Entity
@Table(name = "TOPIC")
public class Topic 
{
	@Id @GeneratedValue
	@Column(name = "TOPIC_ID")
    private Long id;
	
    private boolean isroot = false;
    private String title = "";
    private String text = "";
    private String mimetype = "text/plain";
    
    @ManyToMany()
    private Set authors = new HashSet();
    
    @ManyToMany()
    private Map subtopics = new HashMap(); 

    private Date created;
    private Date modified;

    public Topic() {}

    public Long getId() {
        return id;
    }

    private void setId(Long id) {
        this.id = id;
    }

    public boolean getIsroot() {
	return isroot;
    }

    public void setIsroot(boolean isroot) {
	this.isroot = isroot;
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

    public String getMimetype() {
        return this.mimetype;
    }

    public void setMimetype(String type) {
        this.mimetype = type;
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


