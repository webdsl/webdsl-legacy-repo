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
    BlogComment var22 = new BlogComment();
    blogComment = var22;
    initPerson20List();
    initPerson1025List();
    initProject1125List();
  }

  @Destroy @Remove public void destroy()
  { }

  public void setPerson7(Person person21)
  { 
    blogComment.setAuthor(person21);
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

  private String newPerson20;

  public void setNewPerson20(String p)
  { 
    newPerson20 = p;
  }

  public String getNewPerson20()
  { 
    return newPerson20;
  }

  public void selectPerson20(ValueChangeEvent event)
  { 
    log.info("selectPerson20" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Person person20 = em.find(Person.class, id);
      setPerson7(person20);
    }
  }

  @DataModel("person20List") private Map<String, String> person20List;

  public Map<String, String> getPerson20List()
  { 
    return person20List;
  }

  @Factory("person20List") public void initPerson20List()
  { 
    log.info("initPerson20List");
    person20List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Person").getResultList())
    { 
      Person p = (Person)o;
      person20List.put(p.getName(), p.getId().toString());
    }
  }

  @DataModel("person1025List") private List<Person> person1025List;

  public List<Person> getPerson1025List()
  { 
    log.info("getPerson1025List");
    return person1025List;
  }

  @Factory("person1025List") public void initPerson1025List()
  { 
    log.info("initPerson1025List");
    person1025List = em.createQuery("from " + "Person").getResultList();
  }

  @DataModel("project1125List") private List<ResearchProject> project1125List;

  public List<ResearchProject> getProject1125List()
  { 
    log.info("getProject1125List");
    return project1125List;
  }

  @Factory("project1125List") public void initProject1125List()
  { 
    log.info("initProject1125List");
    project1125List = em.createQuery("from " + "ResearchProject").getResultList();
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