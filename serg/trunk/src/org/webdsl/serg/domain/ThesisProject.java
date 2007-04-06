package org.webdsl.serg.domain;

import java.util.*;
import javax.persistence.*;

@Entity public class ThesisProject extends Project 
{ 
  public ThesisProject () 
  { }

  @Id @GeneratedValue private Long id;

  @ManyToMany @org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.MERGE}) private List<Document> milestones = new LinkedList<Document>();

  public List<Document> getMilestones()
  { 
    return milestones;
  }

  public void setMilestones(List<Document> milestones)
  { 
    this.milestones = milestones;
  }

  public void addMilestones(Document g_0)
  { 
    this.milestones.add(g_0);
  }

  private URL website;

  public URL getWebsite()
  { 
    return website;
  }

  public void setWebsite(URL website)
  { 
    this.website = website;
  }

  private URL subversion;

  public URL getSubversion()
  { 
    return subversion;
  }

  public void setSubversion(URL subversion)
  { 
    this.subversion = subversion;
  }
}