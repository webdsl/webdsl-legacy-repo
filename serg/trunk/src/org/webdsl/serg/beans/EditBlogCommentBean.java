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
    initPerson19List();
    initPerson1026List();
    initProject1126List();
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

  public void setPerson6(Person person20)
  { 
    blogComment.setAuthor(person20);
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

  private String newPerson19;

  public void setNewPerson19(String p)
  { 
    newPerson19 = p;
  }

  public String getNewPerson19()
  { 
    return newPerson19;
  }

  public void selectPerson19(ValueChangeEvent event)
  { 
    log.info("selectPerson19" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Person person19 = em.find(Person.class, id);
      setPerson6(person19);
    }
  }

  @DataModel("person19List") private Map<String, String> person19List;

  public Map<String, String> getPerson19List()
  { 
    return person19List;
  }

  @Factory("person19List") public void initPerson19List()
  { 
    log.info("initPerson19List");
    person19List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Person").getResultList())
    { 
      Person p = (Person)o;
      person19List.put(p.getName(), p.getId().toString());
    }
  }

  @DataModel("person1026List") private List<Person> person1026List;

  public List<Person> getPerson1026List()
  { 
    log.info("getPerson1026List");
    return person1026List;
  }

  @Factory("person1026List") public void initPerson1026List()
  { 
    log.info("initPerson1026List");
    person1026List = em.createQuery("from " + "Person").getResultList();
  }

  @DataModel("project1126List") private List<ResearchProject> project1126List;

  public List<ResearchProject> getProject1126List()
  { 
    log.info("getProject1126List");
    return project1126List;
  }

  @Factory("project1126List") public void initProject1126List()
  { 
    log.info("initProject1126List");
    project1126List = em.createQuery("from " + "ResearchProject").getResultList();
  }
}