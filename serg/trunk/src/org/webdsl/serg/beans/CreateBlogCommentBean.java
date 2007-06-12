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
    initPerson125List();
    initPerson46List();
    initProject41List();
  }

  @Destroy @Remove public void destroy()
  { }

  public void setPerson7(Person person126)
  { 
    blogComment.setAuthor(person126);
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

  private String newPerson125;

  public void setNewPerson125(String p)
  { 
    newPerson125 = p;
  }

  public String getNewPerson125()
  { 
    return newPerson125;
  }

  public void selectPerson125(ValueChangeEvent event)
  { 
    log.info("selectPerson125" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Person person125 = em.find(Person.class, id);
      setPerson7(person125);
    }
  }

  @DataModel("person125List") private Map<String, String> person125List;

  public Map<String, String> getPerson125List()
  { 
    return person125List;
  }

  @Factory("person125List") public void initPerson125List()
  { 
    log.info("initPerson125List");
    person125List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Person").getResultList())
    { 
      Person p = (Person)o;
      person125List.put(p.getName(), p.getId().toString());
    }
  }

  @DataModel("person46List") private List<Person> person46List;

  public List<Person> getPerson46List()
  { 
    log.info("getPerson46List");
    return person46List;
  }

  @Factory("person46List") public void initPerson46List()
  { 
    log.info("initPerson46List");
    person46List = em.createQuery("from " + "Person").getResultList();
  }

  @DataModel("project41List") private List<ResearchProject> project41List;

  public List<ResearchProject> getProject41List()
  { 
    log.info("getProject41List");
    return project41List;
  }

  @Factory("project41List") public void initProject41List()
  { 
    log.info("initProject41List");
    project41List = em.createQuery("from " + "ResearchProject").getResultList();
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