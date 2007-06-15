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
    initPerson44List();
    initProject34List();
    initBlog6List();
  }

  @Destroy @Remove public void destroy()
  { }

  public void removeBlog(Blog blog7)
  { 
    em.remove(blog7);
  }

  @DataModel("person44List") private List<Person> person44List;

  public List<Person> getPerson44List()
  { 
    log.info("getPerson44List");
    return person44List;
  }

  @Factory("person44List") public void initPerson44List()
  { 
    log.info("initPerson44List");
    person44List = em.createQuery("from " + "Person").getResultList();
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

  @DataModel("blog6List") private List<Blog> blog6List;

  public List<Blog> getBlog6List()
  { 
    log.info("getBlog6List");
    return blog6List;
  }

  @Factory("blog6List") public void initBlog6List()
  { 
    log.info("initBlog6List");
    blog6List = em.createQuery("from " + "Blog").getResultList();
  }
}