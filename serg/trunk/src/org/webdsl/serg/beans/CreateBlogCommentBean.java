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
    initPerson173List();
    initPerson56List();
    initProject43List();
  }

  @Destroy @Remove public void destroy()
  { }

  public void setPerson6(Person person174)
  { 
    blogComment.setAuthor(person174);
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

  private String newPerson173;

  public void setNewPerson173(String p)
  { 
    newPerson173 = p;
  }

  public String getNewPerson173()
  { 
    return newPerson173;
  }

  public void selectPerson173(ValueChangeEvent event)
  { 
    log.info("selectPerson173" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Person person173 = em.find(Person.class, id);
      setPerson6(person173);
    }
  }

  @DataModel("person173List") private Map<String, String> person173List;

  public Map<String, String> getPerson173List()
  { 
    return person173List;
  }

  @Factory("person173List") public void initPerson173List()
  { 
    log.info("initPerson173List");
    person173List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Person").getResultList())
    { 
      Person p = (Person)o;
      person173List.put(p.getName(), p.getId().toString());
    }
  }

  @DataModel("person56List") private List<Person> person56List;

  public List<Person> getPerson56List()
  { 
    log.info("getPerson56List");
    return person56List;
  }

  @Factory("person56List") public void initPerson56List()
  { 
    log.info("initPerson56List");
    person56List = em.createQuery("from " + "Person").getResultList();
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