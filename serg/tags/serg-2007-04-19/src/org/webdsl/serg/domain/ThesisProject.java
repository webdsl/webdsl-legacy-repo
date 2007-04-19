package org.webdsl.serg.domain;

import java.util.*;
import javax.persistence.*;
import org.webdsl.serg.domain.*;

@Entity public class ThesisProject extends StudentProject 
{ 
  public ThesisProject () 
  { }

  @ManyToMany @org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.MERGE}) private List<Document> milestones = new LinkedList<Document>();

  public List<Document> getMilestones()
  { 
    return milestones;
  }

  public void setMilestones(List<Document> milestones)
  { 
    this.milestones = milestones;
  }

  public void addMilestones(Document d_0)
  { 
    this.milestones.add(d_0);
  }

  @ManyToOne @JoinColumn(name = "ThesisProjectWebsite") @org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.ALL}) private URL website = new URL();

  public URL getWebsite()
  { 
    return website;
  }

  public void setWebsite(URL website)
  { 
    this.website = website;
  }

  @ManyToOne @JoinColumn(name = "ThesisProjectSubversion") @org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.ALL}) private URL subversion = new URL();

  public URL getSubversion()
  { 
    return subversion;
  }

  public void setSubversion(URL subversion)
  { 
    this.subversion = subversion;
  }

  public String getName()
  { 
    return getId().toString();
  }
}