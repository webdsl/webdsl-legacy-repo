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
    Post var86 = new Post();
    post = var86;
    initPerson253List();
    initDiscussion17List();
    initPerson134List();
    initProject125List();
  }

  @Destroy @Remove public void destroy()
  { }

  public void setPerson16(Person person254)
  { 
    post.setAuthor(person254);
  }

  public void setDiscussion3(Discussion discussion18)
  { 
    post.setDiscussion(discussion18);
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

  private String newPerson253;

  public void setNewPerson253(String p)
  { 
    newPerson253 = p;
  }

  public String getNewPerson253()
  { 
    return newPerson253;
  }

  public void selectPerson253(ValueChangeEvent event)
  { 
    log.info("selectPerson253" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Person person253 = em.find(Person.class, id);
      setPerson16(person253);
    }
  }

  @DataModel("person253List") private Map<String, String> person253List;

  public Map<String, String> getPerson253List()
  { 
    return person253List;
  }

  @Factory("person253List") public void initPerson253List()
  { 
    log.info("initPerson253List");
    person253List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Person").getResultList())
    { 
      Person p = (Person)o;
      person253List.put(p.getName(), p.getId().toString());
    }
  }

  private String newDiscussion17;

  public void setNewDiscussion17(String p)
  { 
    newDiscussion17 = p;
  }

  public String getNewDiscussion17()
  { 
    return newDiscussion17;
  }

  public void selectDiscussion17(ValueChangeEvent event)
  { 
    log.info("selectDiscussion17" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Discussion discussion17 = em.find(Discussion.class, id);
      setDiscussion3(discussion17);
    }
  }

  @DataModel("discussion17List") private Map<String, String> discussion17List;

  public Map<String, String> getDiscussion17List()
  { 
    return discussion17List;
  }

  @Factory("discussion17List") public void initDiscussion17List()
  { 
    log.info("initDiscussion17List");
    discussion17List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Discussion").getResultList())
    { 
      Discussion p = (Discussion)o;
      discussion17List.put(p.getName(), p.getId().toString());
    }
  }

  @DataModel("person134List") private List<Person> person134List;

  public List<Person> getPerson134List()
  { 
    log.info("getPerson134List");
    return person134List;
  }

  @Factory("person134List") public void initPerson134List()
  { 
    log.info("initPerson134List");
    person134List = em.createQuery("from " + "Person").getResultList();
  }

  @DataModel("project125List") private List<ResearchProject> project125List;

  public List<ResearchProject> getProject125List()
  { 
    log.info("getProject125List");
    return project125List;
  }

  @Factory("project125List") public void initProject125List()
  { 
    log.info("initProject125List");
    project125List = em.createQuery("from " + "ResearchProject").getResultList();
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