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
    initProjects2();
    initPerson6List();
    initProject3List();
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

  @DataModel("person6List") private List<Person> person6List;

  public List<Person> getPerson6List()
  { 
    log.info("getPerson6List");
    return person6List;
  }

  @Factory("person6List") public void initPerson6List()
  { 
    log.info("initPerson6List");
    person6List = em.createQuery("from " + "Person").getResultList();
  }

  @DataModel("project3List") private List<ResearchProject> project3List;

  public List<ResearchProject> getProject3List()
  { 
    log.info("getProject3List");
    return project3List;
  }

  @Factory("project3List") public void initProject3List()
  { 
    log.info("initProject3List");
    project3List = em.createQuery("from " + "ResearchProject").getResultList();
  }

  @DataModel("projects2") private java.util.List<ResearchProject> projects2;

  public java.util.List<ResearchProject> getProjects2()
  { 
    log.info("getProjects2");
    return projects2;
  }

  public void setProjects2(java.util.List<ResearchProject> projects2)
  { 
    log.info("setProjects2");
    this.projects2 = projects2;
  }

  @Factory("projects2") public void initProjects2()
  { 
    log.info("initProjects2");
    projects2 = em.createQuery("select pr from ResearchProject as pr , Person as pers where ( pers . id = :param3 ) and ( pers member of pr . _members )").setParameter("param3", this.getBlog().getAuthor().getId()).getResultList();
  }
}