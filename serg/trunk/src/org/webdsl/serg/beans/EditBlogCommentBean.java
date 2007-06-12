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
    initPerson123List();
    initPerson45List();
    initProject40List();
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

  public void setPerson6(Person person124)
  { 
    blogComment.setAuthor(person124);
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

  private String newPerson123;

  public void setNewPerson123(String p)
  { 
    newPerson123 = p;
  }

  public String getNewPerson123()
  { 
    return newPerson123;
  }

  public void selectPerson123(ValueChangeEvent event)
  { 
    log.info("selectPerson123" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Person person123 = em.find(Person.class, id);
      setPerson6(person123);
    }
  }

  @DataModel("person123List") private Map<String, String> person123List;

  public Map<String, String> getPerson123List()
  { 
    return person123List;
  }

  @Factory("person123List") public void initPerson123List()
  { 
    log.info("initPerson123List");
    person123List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Person").getResultList())
    { 
      Person p = (Person)o;
      person123List.put(p.getName(), p.getId().toString());
    }
  }

  @DataModel("person45List") private List<Person> person45List;

  public List<Person> getPerson45List()
  { 
    log.info("getPerson45List");
    return person45List;
  }

  @Factory("person45List") public void initPerson45List()
  { 
    log.info("initPerson45List");
    person45List = em.createQuery("from " + "Person").getResultList();
  }

  @DataModel("project40List") private List<ResearchProject> project40List;

  public List<ResearchProject> getProject40List()
  { 
    log.info("getProject40List");
    return project40List;
  }

  @Factory("project40List") public void initProject40List()
  { 
    log.info("initProject40List");
    project40List = em.createQuery("from " + "ResearchProject").getResultList();
  }
}