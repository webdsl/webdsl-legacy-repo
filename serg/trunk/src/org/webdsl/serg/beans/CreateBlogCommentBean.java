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

@Stateful @Name("createBlogComment") public class CreateBlogCommentBean  implements CreateBlogCommentBeanInterface
{ 
  @Logger private Log log;

  @PersistenceContext(type = EXTENDED) private EntityManager em;

  @In private FacesMessages facesMessages;

  @Create @Begin public void initialize()
  { 
    log.info("createBlogComment" + ".initalize()");
    BlogComment var28 = new BlogComment();
    blogComment = var28;
    initPerson159List();
    initPerson54List();
    initProject43List();
  }

  @Destroy @Remove public void destroy()
  { }

  public void setPerson6(Person person160)
  { 
    blogComment.setAuthor(person160);
  }

  @End public String cancel()
  { 
    return "/" + "home" + ".seam?" + "";
  }

  @End public String save()
  { 
    em.persist(this.getBlogComment());
    return "/" + "viewBlogComment" + ".seam?" + ("blogComment" + "=" + blogComment.getId() + "");
  }

  private String newPerson159;

  public void setNewPerson159(String p)
  { 
    newPerson159 = p;
  }

  public String getNewPerson159()
  { 
    return newPerson159;
  }

  public void selectPerson159(ValueChangeEvent event)
  { 
    log.info("selectPerson159" + ": new value = " + " " + event.getNewValue());
    Person person159 = (Person)event.getNewValue();
  }

  @DataModel("person159List") private Map<String, String> person159List;

  public Map<String, String> getPerson159List()
  { 
    return person159List;
  }

  @Factory("person159List") public void initPerson159List()
  { 
    log.info("initPerson159List");
    person159List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Person").getResultList())
    { 
      Person p = (Person)o;
      person159List.put(p.getName(), p.getId().toString());
    }
  }

  @DataModel("person54List") private List<Person> person54List;

  public List<Person> getPerson54List()
  { 
    log.info("getPerson54List");
    return person54List;
  }

  @Factory("person54List") public void initPerson54List()
  { 
    log.info("initPerson54List");
    person54List = em.createQuery("from " + "Person").getResultList();
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

  private BlogComment blogComment;

  public BlogComment getBlogComment()
  { 
    log.info("getBlogComment");
    return blogComment;
  }

  public void setBlogComment(BlogComment blogComment)
  { 
    log.info("setBlogComment");
    this.blogComment = blogComment;
  }
}