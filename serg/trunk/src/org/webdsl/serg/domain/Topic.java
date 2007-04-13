package org.webdsl.serg.domain;

import java.util.*;
import javax.persistence.*;
import org.webdsl.serg.domain.*;

@Entity public class Topic  
{ 
  public Topic () 
  { }

  @Id @GeneratedValue private Long id;

  public Long getId()
  { 
    return id;
  }

  private void setId(Long id)
  { 
    this.id = id;
  }

  private String title;

  public String getTitle()
  { 
    return title;
  }

  public void setTitle(String title)
  { 
    this.title = title;
  }

  private String text;

  public String getText()
  { 
    return text;
  }

  public void setText(String text)
  { 
    this.text = text;
  }

  @ManyToMany() @org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.MERGE}) private Map<String, Topic> subtopic = new HashMap<String, Topic>();

  public Map<String, Topic> getSubtopic()
  { 
    return subtopic;
  }

  public void setSubtopic(Map<String, Topic> subtopic)
  { 
    this.subtopic = subtopic;
  }

  public void putSubtopic(String key, Topic value)
  { 
    this.subtopic.put(key, value);
  }

  @ManyToMany @org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.MERGE}) private Set<User> authors = new HashSet<User>();

  public Set<User> getAuthors()
  { 
    return authors;
  }

  public void setAuthors(Set<User> authors)
  { 
    this.authors = authors;
  }

  public void addAuthors(User i_0)
  { 
    this.authors.add(i_0);
  }

  public String getName()
  { 
    return getTitle().toString();
  }
}