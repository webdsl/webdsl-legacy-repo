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

@Stateful @Name("allBlogComment") public class AllBlogCommentBean  implements AllBlogCommentBeanInterface
{ 
  @Logger private Log log;

  @PersistenceContext(type = EXTENDED) private EntityManager em;

  @In private FacesMessages facesMessages;

  @Create @Begin public void initialize()
  { 
    log.info("allBlogComment" + ".initalize()");
    initPerson50List();
    initProject45List();
    initBlogComment4List();
  }

  @Destroy @Remove public void destroy()
  { }

  public void removeBlogComment(BlogComment blogComment5)
  { 
    em.remove(blogComment5);
  }

  @DataModel("person50List") private List<Person> person50List;

  public List<Person> getPerson50List()
  { 
    log.info("getPerson50List");
    return person50List;
  }

  @Factory("person50List") public void initPerson50List()
  { 
    log.info("initPerson50List");
    person50List = em.createQuery("from " + "Person").getResultList();
  }

  @DataModel("project45List") private List<ResearchProject> project45List;

  public List<ResearchProject> getProject45List()
  { 
    log.info("getProject45List");
    return project45List;
  }

  @Factory("project45List") public void initProject45List()
  { 
    log.info("initProject45List");
    project45List = em.createQuery("from " + "ResearchProject").getResultList();
  }

  @DataModel("blogComment4List") private List<BlogComment> blogComment4List;

  public List<BlogComment> getBlogComment4List()
  { 
    log.info("getBlogComment4List");
    return blogComment4List;
  }

  @Factory("blogComment4List") public void initBlogComment4List()
  { 
    log.info("initBlogComment4List");
    blogComment4List = em.createQuery("from " + "BlogComment").getResultList();
  }
}