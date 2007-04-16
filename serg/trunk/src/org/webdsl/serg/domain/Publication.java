package org.webdsl.serg.domain;

import java.util.*;
import javax.persistence.*;
import org.webdsl.serg.domain.*;

@Entity public class Publication  
{ 
  public Publication () 
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

  @ManyToMany @org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.MERGE}) private List<Person> authors = new LinkedList<Person>();

  public List<Person> getAuthors()
  { 
    return authors;
  }

  public void setAuthors(List<Person> authors)
  { 
    this.authors = authors;
  }

  public void addAuthors(Person g_0)
  { 
    this.authors.add(g_0);
  }

  private int year;

  public int getYear()
  { 
    return year;
  }

  public void setYear(int year)
  { 
    this.year = year;
  }

  private String pubabstract;

  public String getPubabstract()
  { 
    return pubabstract;
  }

  public void setPubabstract(String pubabstract)
  { 
    this.pubabstract = pubabstract;
  }

  @ManyToOne @JoinColumn(name = "PublicationPdf") @org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.ALL}) private URL pdf = new URL();

  public URL getPdf()
  { 
    return pdf;
  }

  public void setPdf(URL pdf)
  { 
    this.pdf = pdf;
  }

  public String getName()
  { 
    return getTitle().toString();
  }
}