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
    initPerson157List();
    initPerson53List();
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

  public void setPerson5(Person person158)
  { 
    blogComment.setAuthor(person158);
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

  private String newPerson157;

  public void setNewPerson157(String p)
  { 
    newPerson157 = p;
  }

  public String getNewPerson157()
  { 
    return newPerson157;
  }

  public void selectPerson157(ValueChangeEvent event)
  { 
    log.info("selectPerson157" + ": new value = " + " " + event.getNewValue());
    Person person157 = (Person)event.getNewValue();
  }

  @DataModel("person157List") private Map<String, String> person157List;

  public Map<String, String> getPerson157List()
  { 
    return person157List;
  }

  @Factory("person157List") public void initPerson157List()
  { 
    log.info("initPerson157List");
    person157List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Person").getResultList())
    { 
      Person p = (Person)o;
      person157List.put(p.getName(), p.getId().toString());
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