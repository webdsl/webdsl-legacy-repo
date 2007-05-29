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

  private String fullname;

  public String getFullname()
  { 
    return fullname;
  }

  public void setFullname(String fullname)
  { 
    this.fullname = fullname;
  }

  private String acronym;

  public String getAcronym()
  { 
    return acronym;
  }

  public void setAcronym(String acronym)
  { 
    this.acronym = acronym;
  }

  private @Column(length = 1000000) String description;

  public String getDescription()
  { 
    return description;
  }

  public void setDescription(String description)
  { 
    this.description = description;
  }

  @ManyToMany @org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.PERSIST, org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.MERGE}) private Set<Person> members = new HashSet<Person>();

  public Set<Person> getMembers()
  { 
    return members;
  }

  public List<Person> getMembersList()
  { 
    return new ArrayList(members);
  }

  public void setMembers(Set<Person> members)
  { 
    this.members = members;
  }

  public void addMembers(Person c_0)
  { 
    this.members.add(c_0);
  }

  @ManyToOne @org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.PERSIST, org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.MERGE}) private Publication proposal;

  public Publication getProposal()
  { 
    return proposal;
  }

  public void setProposal(Publication proposal)
  { 
    this.proposal = proposal;
  }

  @ManyToMany @org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.PERSIST, org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.MERGE}) private Set<Publication> publications = new HashSet<Publication>();

  public Set<Publication> getPublications()
  { 
    return publications;
  }

  public List<Publication> getPublicationsList()
  { 
    return new ArrayList(publications);
  }

  public void setPublications(Set<Publication> publications)
  { 
    this.publications = publications;
  }

  public void addPublications(Publication d_0)
  { 
    this.publications.add(d_0);
  }

  public String getName()
  { 
    return getAcronym().toString();
  }
}