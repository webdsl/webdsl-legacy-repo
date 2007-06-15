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

@Stateful @Name("allBlogEntry") public class AllBlogEntryBean  implements AllBlogEntryBeanInterface
{ 
  @Logger private Log log;

  @PersistenceContext(type = EXTENDED) private EntityManager em;

  @In private FacesMessages facesMessages;

  @Create @Begin public void initialize()
  { 
    log.info("allBlogEntry" + ".initalize()");
    initPerson47List();
    initProject37List();
    initBlogEntry4List();
  }

  @Destroy @Remove public void destroy()
  { }

  public void removeBlogEntry(BlogEntry blogEntry5)
  { 
    em.remove(blogEntry5);
  }

  @DataModel("person47List") private List<Person> person47List;

  public List<Person> getPerson47List()
  { 
    log.info("getPerson47List");
    return person47List;
  }

  @Factory("person47List") public void initPerson47List()
  { 
    log.info("initPerson47List");
    person47List = em.createQuery("from " + "Person").getResultList();
  }

  @DataModel("project37List") private List<ResearchProject> project37List;

  public List<ResearchProject> getProject37List()
  { 
    log.info("getProject37List");
    return project37List;
  }

  @Factory("project37List") public void initProject37List()
  { 
    log.info("initProject37List");
    project37List = em.createQuery("from " + "ResearchProject").getResultList();
  }

  @DataModel("blogEntry4List") private List<BlogEntry> blogEntry4List;

  public List<BlogEntry> getBlogEntry4List()
  { 
    log.info("getBlogEntry4List");
    return blogEntry4List;
  }

  @Factory("blogEntry4List") public void initBlogEntry4List()
  { 
    log.info("initBlogEntry4List");
    blogEntry4List = em.createQuery("from " + "BlogEntry").getResultList();
  }
}