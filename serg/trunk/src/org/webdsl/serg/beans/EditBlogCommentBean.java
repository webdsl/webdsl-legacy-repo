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
    initPerson18List();
    initPerson1024List();
    initProject1124List();
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

  public void setPerson6(Person person19)
  { 
    blogComment.setAuthor(person19);
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

  private String newPerson18;

  public void setNewPerson18(String p)
  { 
    newPerson18 = p;
  }

  public String getNewPerson18()
  { 
    return newPerson18;
  }

  public void selectPerson18(ValueChangeEvent event)
  { 
    log.info("selectPerson18" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Person person18 = em.find(Person.class, id);
      setPerson6(person18);
    }
  }

  @DataModel("person18List") private Map<String, String> person18List;

  public Map<String, String> getPerson18List()
  { 
    return person18List;
  }

  @Factory("person18List") public void initPerson18List()
  { 
    log.info("initPerson18List");
    person18List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Person").getResultList())
    { 
      Person p = (Person)o;
      person18List.put(p.getName(), p.getId().toString());
    }
  }

  @DataModel("person1024List") private List<Person> person1024List;

  public List<Person> getPerson1024List()
  { 
    log.info("getPerson1024List");
    return person1024List;
  }

  @Factory("person1024List") public void initPerson1024List()
  { 
    log.info("initPerson1024List");
    person1024List = em.createQuery("from " + "Person").getResultList();
  }

  @DataModel("project1124List") private List<ResearchProject> project1124List;

  public List<ResearchProject> getProject1124List()
  { 
    log.info("getProject1124List");
    return project1124List;
  }

  @Factory("project1124List") public void initProject1124List()
  { 
    log.info("initProject1124List");
    project1124List = em.createQuery("from " + "ResearchProject").getResultList();
  }
}