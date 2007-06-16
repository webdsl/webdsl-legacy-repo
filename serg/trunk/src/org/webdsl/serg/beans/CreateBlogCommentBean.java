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
    initPerson154List();
    initPerson53List();
    initProject43List();
  }

  @Destroy @Remove public void destroy()
  { }

  public void setPerson6(Person person155)
  { 
    blogComment.setAuthor(person155);
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

  private String newPerson154;

  public void setNewPerson154(String p)
  { 
    newPerson154 = p;
  }

  public String getNewPerson154()
  { 
    return newPerson154;
  }

  public void selectPerson154(ValueChangeEvent event)
  { 
    log.info("selectPerson154" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Person person154 = em.find(Person.class, id);
      setPerson6(person154);
    }
  }

  @DataModel("person154List") private Map<String, String> person154List;

  public Map<String, String> getPerson154List()
  { 
    return person154List;
  }

  @Factory("person154List") public void initPerson154List()
  { 
    log.info("initPerson154List");
    person154List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Person").getResultList())
    { 
      Person p = (Person)o;
      person154List.put(p.getName(), p.getId().toString());
    }
  }

  @DataModel("person53List") private List<Person> person53List;

  public List<Person> getPerson53List()
  { 
    log.info("getPerson53List");
    return person53List;
  }

  @Factory("person53List") public void initPerson53List()
  { 
    log.info("initPerson53List");
    person53List = em.createQuery("from " + "Person").getResultList();
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