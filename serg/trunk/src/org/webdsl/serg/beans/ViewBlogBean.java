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

@Stateful @Name("viewBlog") public class ViewBlogBean  implements ViewBlogBeanInterface
{ 
  @Logger private Log log;

  @PersistenceContext(type = EXTENDED) private EntityManager em;

  @In private FacesMessages facesMessages;

  @Create @Begin public void initialize()
  { 
    log.info("viewBlog" + ".initalize()");
    if(blogId == null)
    { 
      log.info("No " + "blogId" + " defined, creating new " + "Blog");
      blog = new Blog();
    }
    else
    { 
      blog = em.find(Blog.class, blogId);
    }
    initPr1List();
    initPerson102List();
    initProject112List();
  }

  @Destroy @Remove public void destroy()
  { }

  @RequestParameter("blog") private Long blogId;

  private Blog blog;

  public void setBlog(Blog blog)
  { 
    log.info("setBlog");
    this.blog = blog;
  }

  public Blog getBlog()
  { 
    log.info("getBlog");
    return blog;
  }

  @End public String createNewBlogEntry()
  { 
    BlogEntry var0 = new BlogEntry();
    var0.setBlog(this.getBlog());
    var0.setTitle("title here");
    BlogEntry entry1 = var0;
    this.getBlog().getEntries().add(entry1);
    em.persist(this.getBlog());
    return "/" + "editBlogEntry" + ".seam?" + ("blogEntry" + "=" + entry1.getId() + "");
  }

  @DataModel("pr1List") private List<ResearchProject> pr1List;

  public List<ResearchProject> getPr1List()
  { 
    log.info("getPr1List");
    return pr1List;
  }

  @Factory("pr1List") public void initPr1List()
  { 
    log.info("initPr1List");
    pr1List = em.createQuery("from " + "ResearchProject").getResultList();
  }

  @DataModel("person102List") private List<Person> person102List;

  public List<Person> getPerson102List()
  { 
    log.info("getPerson102List");
    return person102List;
  }

  @Factory("person102List") public void initPerson102List()
  { 
    log.info("initPerson102List");
    person102List = em.createQuery("from " + "Person").getResultList();
  }

  @DataModel("project112List") private List<ResearchProject> project112List;

  public List<ResearchProject> getProject112List()
  { 
    log.info("getProject112List");
    return project112List;
  }

  @Factory("project112List") public void initProject112List()
  { 
    log.info("initProject112List");
    project112List = em.createQuery("from " + "ResearchProject").getResultList();
  }
}