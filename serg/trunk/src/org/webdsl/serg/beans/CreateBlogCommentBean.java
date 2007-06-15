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
    initPerson142List();
    initPerson47List();
    initProject42List();
  }

  @Destroy @Remove public void destroy()
  { }

  public void setPerson5(Person person143)
  { 
    blogComment.setAuthor(person143);
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

  private String newPerson142;

  public void setNewPerson142(String p)
  { 
    newPerson142 = p;
  }

  public String getNewPerson142()
  { 
    return newPerson142;
  }

  public void selectPerson142(ValueChangeEvent event)
  { 
    log.info("selectPerson142" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Person person142 = em.find(Person.class, id);
      setPerson5(person142);
    }
  }

  @DataModel("person142List") private Map<String, String> person142List;

  public Map<String, String> getPerson142List()
  { 
    return person142List;
  }

  @Factory("person142List") public void initPerson142List()
  { 
    log.info("initPerson142List");
    person142List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Person").getResultList())
    { 
      Person p = (Person)o;
      person142List.put(p.getName(), p.getId().toString());
    }
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

  @DataModel("project42List") private List<ResearchProject> project42List;

  public List<ResearchProject> getProject42List()
  { 
    log.info("getProject42List");
    return project42List;
  }

  @Factory("project42List") public void initProject42List()
  { 
    log.info("initProject42List");
    project42List = em.createQuery("from " + "ResearchProject").getResultList();
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