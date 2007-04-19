package org.webdsl.serg.domain;

import java.util.*;
import javax.persistence.*;
import org.webdsl.serg.domain.*;

@Entity public class TechnicalReport extends Publication 
{ 
  public TechnicalReport () 
  { }

  private int number;

  public int getNumber()
  { 
    return number;
  }

  public void setNumber(int number)
  { 
    this.number = number;
  }

  private String document;

  public String getDocument()
  { 
    return document;
  }

  public void setDocument(String document)
  { 
    this.document = document;
  }

  @ManyToMany @org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.MERGE}) private Set<ResearchProject> project = new HashSet<ResearchProject>();

  public Set<ResearchProject> getProject()
  { 
    return project;
  }

  public void setProject(Set<ResearchProject> project)
  { 
    this.project = project;
  }

  public void addProject(ResearchProject h_0)
  { 
    this.project.add(h_0);
  }

  @ManyToOne @org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.MERGE}) private Publication preprintof;

  public Publication getPreprintof()
  { 
    return preprintof;
  }

  public void setPreprintof(Publication preprintof)
  { 
    this.preprintof = preprintof;
  }

  public String getName()
  { 
    return getId().toString();
  }
}