package org.webdsl.serg.domain;

import java.util.*;
import javax.persistence.*;
import org.webdsl.serg.domain.*;

@Entity public class Article extends Publication 
{ 
  public Article () 
  { }

  @ManyToOne @org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.PERSIST, org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.MERGE}) private Journal _journal = null;

  public Journal getJournal()
  { 
    return _journal;
  }

  public void setJournal(Journal _journal)
  { 
    this._journal = _journal;
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

  private int _impact = 0;

  public int getImpact()
  { 
    return _impact;
  }

  public void setImpact(int _impact)
  { 
    this._impact = _impact;
  }

  public String getName()
  { 
    return getTitle().toString();
  }
}