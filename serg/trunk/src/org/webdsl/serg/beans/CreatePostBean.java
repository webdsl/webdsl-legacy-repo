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

@Stateful @Name("createPost") public class CreatePostBean  implements CreatePostBeanInterface
{ 
  @Logger private Log log;

  @PersistenceContext(type = EXTENDED) private EntityManager em;

  @In private FacesMessages facesMessages;

  @Create @Begin public void initialize()
  { 
    log.info("createPost" + ".initalize()");
    Post var59 = new Post();
    post = var59;
    initPerson212List();
    initDiscussion16List();
    initPerson121List();
    initProject121List();
  }

  @Destroy @Remove public void destroy()
  { }

  public void setPerson16(Person person213)
  { 
    post.setAuthor(person213);
  }

  public void setDiscussion3(Discussion discussion17)
  { 
    post.setDiscussion(discussion17);
  }

  @End public String cancel()
  { 
    return "/" + "home" + ".seam?" + "";
  }

  @End public String save()
  { 
    em.persist(this.getPost());
    return "/" + "viewPost" + ".seam?" + ("post" + "=" + post.getId() + "");
  }

  private String newPerson212;

  public void setNewPerson212(String p)
  { 
    newPerson212 = p;
  }

  public String getNewPerson212()
  { 
    return newPerson212;
  }

  public void selectPerson212(ValueChangeEvent event)
  { 
    log.info("selectPerson212" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Person person212 = em.find(Person.class, id);
      setPerson16(person212);
    }
  }

  @DataModel("person212List") private Map<String, String> person212List;

  public Map<String, String> getPerson212List()
  { 
    return person212List;
  }

  @Factory("person212List") public void initPerson212List()
  { 
    log.info("initPerson212List");
    person212List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Person").getResultList())
    { 
      Person p = (Person)o;
      person212List.put(p.getName(), p.getId().toString());
    }
  }

  private String newDiscussion16;

  public void setNewDiscussion16(String p)
  { 
    newDiscussion16 = p;
  }

  public String getNewDiscussion16()
  { 
    return newDiscussion16;
  }

  public void selectDiscussion16(ValueChangeEvent event)
  { 
    log.info("selectDiscussion16" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Discussion discussion16 = em.find(Discussion.class, id);
      setDiscussion3(discussion16);
    }
  }

  @DataModel("discussion16List") private Map<String, String> discussion16List;

  public Map<String, String> getDiscussion16List()
  { 
    return discussion16List;
  }

  @Factory("discussion16List") public void initDiscussion16List()
  { 
    log.info("initDiscussion16List");
    discussion16List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Discussion").getResultList())
    { 
      Discussion p = (Discussion)o;
      discussion16List.put(p.getName(), p.getId().toString());
    }
  }

  @DataModel("person121List") private List<Person> person121List;

  public List<Person> getPerson121List()
  { 
    log.info("getPerson121List");
    return person121List;
  }

  @Factory("person121List") public void initPerson121List()
  { 
    log.info("initPerson121List");
    person121List = em.createQuery("from " + "Person").getResultList();
  }

  @DataModel("project121List") private List<ResearchProject> project121List;

  public List<ResearchProject> getProject121List()
  { 
    log.info("getProject121List");
    return project121List;
  }

  @Factory("project121List") public void initProject121List()
  { 
    log.info("initProject121List");
    project121List = em.createQuery("from " + "ResearchProject").getResultList();
  }

  private Post post;

  public Post getPost()
  { 
    log.info("getPost");
    return post;
  }

  public void setPost(Post post)
  { 
    log.info("setPost");
    this.post = post;
  }
}