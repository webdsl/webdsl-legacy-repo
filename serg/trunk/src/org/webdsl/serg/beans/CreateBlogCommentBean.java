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
    initPerson21List();
    initPerson1027List();
    initProject1127List();
  }

  @Destroy @Remove public void destroy()
  { }

  public void setPerson7(Person person22)
  { 
    blogComment.setAuthor(person22);
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

  private String newPerson21;

  public void setNewPerson21(String p)
  { 
    newPerson21 = p;
  }

  public String getNewPerson21()
  { 
    return newPerson21;
  }

  public void selectPerson21(ValueChangeEvent event)
  { 
    log.info("selectPerson21" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Person person21 = em.find(Person.class, id);
      setPerson7(person21);
    }
  }

  @DataModel("person21List") private Map<String, String> person21List;

  public Map<String, String> getPerson21List()
  { 
    return person21List;
  }

  @Factory("person21List") public void initPerson21List()
  { 
    log.info("initPerson21List");
    person21List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Person").getResultList())
    { 
      Person p = (Person)o;
      person21List.put(p.getName(), p.getId().toString());
    }
  }

  @DataModel("person1027List") private List<Person> person1027List;

  public List<Person> getPerson1027List()
  { 
    log.info("getPerson1027List");
    return person1027List;
  }

  @Factory("person1027List") public void initPerson1027List()
  { 
    log.info("initPerson1027List");
    person1027List = em.createQuery("from " + "Person").getResultList();
  }

  @DataModel("project1127List") private List<ResearchProject> project1127List;

  public List<ResearchProject> getProject1127List()
  { 
    log.info("getProject1127List");
    return project1127List;
  }

  @Factory("project1127List") public void initProject1127List()
  { 
    log.info("initProject1127List");
    project1127List = em.createQuery("from " + "ResearchProject").getResultList();
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