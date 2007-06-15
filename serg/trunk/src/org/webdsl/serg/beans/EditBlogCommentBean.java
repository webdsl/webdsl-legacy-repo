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

@Stateful @Name("editBlogComment") public class EditBlogCommentBean  implements EditBlogCommentBeanInterface
{ 
  @Logger private Log log;

  @PersistenceContext(type = EXTENDED) private EntityManager em;

  @In private FacesMessages facesMessages;

  @Create @Begin public void initialize()
  { 
    log.info("editBlogComment" + ".initalize()");
    if(blogCommentId == null)
    { 
      log.info("No " + "blogCommentId" + " defined, creating new " + "BlogComment");
      blogComment = new BlogComment();
    }
    else
    { 
      blogComment = em.find(BlogComment.class, blogCommentId);
    }
    initPerson141List();
    initPerson52List();
    initProject42List();
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

  public void setPerson5(Person person142)
  { 
    blogComment.setAuthor(person142);
  }

  @End public String cancel()
  { 
    return "/" + "viewBlogComment" + ".seam?" + ("blogComment" + "=" + blogComment.getId() + "");
  }

  @End public String save()
  { 
    em.persist(this.getBlogComment());
    return "/" + "viewBlogComment" + ".seam?" + ("blogComment" + "=" + blogComment.getId() + "");
  }

  private String newPerson141;

  public void setNewPerson141(String p)
  { 
    newPerson141 = p;
  }

  public String getNewPerson141()
  { 
    return newPerson141;
  }

  public void selectPerson141(ValueChangeEvent event)
  { 
    log.info("selectPerson141" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Person person141 = em.find(Person.class, id);
      setPerson5(person141);
    }
  }

  @DataModel("person141List") private Map<String, String> person141List;

  public Map<String, String> getPerson141List()
  { 
    return person141List;
  }

  @Factory("person141List") public void initPerson141List()
  { 
    log.info("initPerson141List");
    person141List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Person").getResultList())
    { 
      Person p = (Person)o;
      person141List.put(p.getName(), p.getId().toString());
    }
  }

  @DataModel("person52List") private List<Person> person52List;

  public List<Person> getPerson52List()
  { 
    log.info("getPerson52List");
    return person52List;
  }

  @Factory("person52List") public void initPerson52List()
  { 
    log.info("initPerson52List");
    person52List = em.createQuery("from " + "Person").getResultList();
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
}