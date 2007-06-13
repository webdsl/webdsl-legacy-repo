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

@Stateful @Name("viewBlogComment") public class ViewBlogCommentBean  implements ViewBlogCommentBeanInterface
{ 
  @Logger private Log log;

  @PersistenceContext(type = EXTENDED) private EntityManager em;

  @In private FacesMessages facesMessages;

  @Create @Begin public void initialize()
  { 
    log.info("viewBlogComment" + ".initalize()");
    if(blogCommentId == null)
    { 
      log.info("No " + "blogCommentId" + " defined, creating new " + "BlogComment");
      blogComment = new BlogComment();
    }
    else
    { 
      blogComment = em.find(BlogComment.class, blogCommentId);
    }
    initPerson48List();
    initProject43List();
  }

  @Destroy @Remove public void destroy()
  { }

  @RequestParameter("blogComment") private Long blogCommentId;

  private BlogComment blogComment;

  public void setBlogComment(BlogComment blogComment)
  { 
    log.info("setBlogComment");
    this.blogComment = blogComment;
  }

  public BlogComment getBlogComment()
  { 
    log.info("getBlogComment");
    return blogComment;
  }

  @DataModel("person48List") private List<Person> person48List;

  public List<Person> getPerson48List()
  { 
    log.info("getPerson48List");
    return person48List;
  }

  @Factory("person48List") public void initPerson48List()
  { 
    log.info("initPerson48List");
    person48List = em.createQuery("from " + "Person").getResultList();
  }

  @DataModel("project43List") private List<ResearchProject> project43List;

  public List<ResearchProject> getProject43List()
  { 
    log.info("getProject43List");
    return project43List;
  }

  @Factory("project43List") public void initProject43List()
  { 
    log.info("initProject43List");
    project43List = em.createQuery("from " + "ResearchProject").getResultList();
  }
}