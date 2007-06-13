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
    initPerson120List();
    initPerson47List();
    initProject42List();
  }

  @Destroy @Remove public void destroy()
  { }

  public void setPerson5(Person person121)
  { 
    blogComment.setAuthor(person121);
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

  private String newPerson120;

  public void setNewPerson120(String p)
  { 
    newPerson120 = p;
  }

  public String getNewPerson120()
  { 
    return newPerson120;
  }

  public void selectPerson120(ValueChangeEvent event)
  { 
    log.info("selectPerson120" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Person person120 = em.find(Person.class, id);
      setPerson5(person120);
    }
  }

  @DataModel("person120List") private Map<String, String> person120List;

  public Map<String, String> getPerson120List()
  { 
    return person120List;
  }

  @Factory("person120List") public void initPerson120List()
  { 
    log.info("initPerson120List");
    person120List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Person").getResultList())
    { 
      Person p = (Person)o;
      person120List.put(p.getName(), p.getId().toString());
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