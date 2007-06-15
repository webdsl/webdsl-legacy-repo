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

@Stateful @Name("allBlog") public class AllBlogBean  implements AllBlogBeanInterface
{ 
  @Logger private Log log;

  @PersistenceContext(type = EXTENDED) private EntityManager em;

  @In private FacesMessages facesMessages;

  @Create @Begin public void initialize()
  { 
    log.info("allBlog" + ".initalize()");
    initPerson39List();
    initProject34List();
    initBlog5List();
  }

  @Destroy @Remove public void destroy()
  { }

  public void removeBlog(Blog blog6)
  { 
    em.remove(blog6);
  }

  @DataModel("person39List") private List<Person> person39List;

  public List<Person> getPerson39List()
  { 
    log.info("getPerson39List");
    return person39List;
  }

  @Factory("person39List") public void initPerson39List()
  { 
    log.info("initPerson39List");
    person39List = em.createQuery("from " + "Person").getResultList();
  }

  @DataModel("project34List") private List<ResearchProject> project34List;

  public List<ResearchProject> getProject34List()
  { 
    log.info("getProject34List");
    return project34List;
  }

  @Factory("project34List") public void initProject34List()
  { 
    log.info("initProject34List");
    project34List = em.createQuery("from " + "ResearchProject").getResultList();
  }

  @DataModel("blog5List") private List<Blog> blog5List;

  public List<Blog> getBlog5List()
  { 
    log.info("getBlog5List");
    return blog5List;
  }

  @Factory("blog5List") public void initBlog5List()
  { 
    log.info("initBlog5List");
    blog5List = em.createQuery("from " + "Blog").getResultList();
  }
}