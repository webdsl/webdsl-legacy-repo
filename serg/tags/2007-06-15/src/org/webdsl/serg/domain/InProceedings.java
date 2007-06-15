package org.webdsl.serg.domain;

import java.util.*;
import javax.persistence.*;
import org.webdsl.serg.domain.*;

@Entity public class InProceedings extends Publication 
{ 
  public InProceedings () 
  { }

  @ManyToOne @org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.PERSIST, org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.MERGE}) private Conference _conference = null;

  public Conference getConference()
  { 
    return _conference;
  }

  public void setConference(Conference _conference)
  { 
    this._conference = _conference;
  }

  private String _pages = "";

  public String getPages()
  { 
    return _pages;
  }

  public void setPages(String _pages)
  { 
    this._pages = _pages;
  }

  public String getName()
  { 
    return getTitle().toString();
  }
}