package org.webdsl.serg.domain;

import java.util.*;
import javax.persistence.*;
import org.webdsl.serg.domain.*;

@Entity public class ResearchProject  
{ 
  public ResearchProject () 
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

  private String name;

  public String getName()
  { 
    return name;
  }

  public void setName(String name)
  { 
    this.name = name;
  }

  @ManyToMany() @org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.MERGE}) private Map<String, Person> members = new HashMap<String, Person>();

  public Map<String, Person> getMembers()
  { 
    return members;
  }

  public void setMembers(Map<String, Person> members)
  { 
    this.members = members;
  }

  public void putMembers(String key, Person value)
  { 
    this.members.put(key, value);
  }

  @ManyToMany @org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.MERGE}) private Set<TechnicalReport> publications = new HashSet<TechnicalReport>();

  public Set<TechnicalReport> getPublications()
  { 
    return publications;
  }

  public void setPublications(Set<TechnicalReport> publications)
  { 
    this.publications = publications;
  }

  public void addPublications(TechnicalReport u_2)
  { 
    this.publications.add(u_2);
  }
}