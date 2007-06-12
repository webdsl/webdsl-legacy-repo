package org.webdsl.serg.beans;

import java.util.*;
import java.io.Serializable;
import static javax.persistence.PersistenceContextType.EXTENDED;
import javax.persistence.PersistenceContext;
import javax.persistence.EntityManager;
import javax.faces.event.ValueChangeEvent;
import javax.ejb.Stateless;
import javax.ejb.Stateful;
import javax.ejb.Remove;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.End;
import org.jboss.seam.annotations.Destroy;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.Create;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.RequestParameter;
import org.jboss.seam.annotations.datamodel.DataModel;
import org.jboss.seam.annotations.datamodel.DataModelSelection;
import org.jboss.seam.core.FacesMessages;
import org.jboss.seam.log.Log;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Factory;
import org.webdsl.serg.domain.*;

@Stateful @Name("viewBlogEntry") public class ViewBlogEntryBean  implements ViewBlogEntryBeanInterface
{ 
  @Logger private Log log;

  @PersistenceContext(type = EXTENDED) private EntityManager em;

  @In private FacesMessages facesMessages;

  @Create @Begin public void initialize()
  { 
    log.info("viewBlogEntry" + ".initalize()");
    if(entryId == null)
    { 
      log.info("No " + "entryId" + " defined, creating new " + "BlogEntry");
      entry = new BlogEntry();
    }
    else
    { 
      entry = em.find(BlogEntry.class, entryId);
    }
    initProjects3();
    initPerson8List();
    initProject4List();
  }

  @Destroy @Remove public void destroy()
  { }

  @RequestParameter("entry") private Long entryId;

  private BlogEntry entry;

  public void setEntry(BlogEntry entry)
  { 
    log.info("setEntry");
    this.entry = entry;
  }

  public BlogEntry getEntry()
  { 
    log.info("getEntry");
    return entry;
  }

  @DataModel("person8List") private List<Person> person8List;

  public List<Person> getPerson8List()
  { 
    log.info("getPerson8List");
    return person8List;
  }

  @Factory("person8List") public void initPerson8List()
  { 
    log.info("initPerson8List");
    person8List = em.createQuery("from " + "Person").getResultList();
  }

  @DataModel("project4List") private List<ResearchProject> project4List;

  public List<ResearchProject> getProject4List()
  { 
    log.info("getProject4List");
    return project4List;
  }

  @Factory("project4List") public void initProject4List()
  { 
    log.info("initProject4List");
    project4List = em.createQuery("from " + "ResearchProject").getResultList();
  }

  @DataModel("projects3") private java.util.List<ResearchProject> projects3;

  public java.util.List<ResearchProject> getProjects3()
  { 
    log.info("getProjects3");
    return projects3;
  }

  public void setProjects3(java.util.List<ResearchProject> projects3)
  { 
    log.info("setProjects3");
    this.projects3 = projects3;
  }

  @Factory("projects3") public void initProjects3()
  { 
    log.info("initProjects3");
    projects3 = em.createQuery("select pr from ResearchProject as pr , Person as pers where ( pers . id = :param4 ) and ( pers member of pr . _members )").setParameter("param4", this.getEntry().getBlog().getAuthor().getId()).getResultList();
  }
}