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
    initPr2List();
    initPerson103List();
    initProject113List();
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

  @DataModel("pr2List") private List<ResearchProject> pr2List;

  public List<ResearchProject> getPr2List()
  { 
    log.info("getPr2List");
    return pr2List;
  }

  @Factory("pr2List") public void initPr2List()
  { 
    log.info("initPr2List");
    pr2List = em.createQuery("from " + "ResearchProject").getResultList();
  }

  @DataModel("person103List") private List<Person> person103List;

  public List<Person> getPerson103List()
  { 
    log.info("getPerson103List");
    return person103List;
  }

  @Factory("person103List") public void initPerson103List()
  { 
    log.info("initPerson103List");
    person103List = em.createQuery("from " + "Person").getResultList();
  }

  @DataModel("project113List") private List<ResearchProject> project113List;

  public List<ResearchProject> getProject113List()
  { 
    log.info("getProject113List");
    return project113List;
  }

  @Factory("project113List") public void initProject113List()
  { 
    log.info("initProject113List");
    project113List = em.createQuery("from " + "ResearchProject").getResultList();
  }
}