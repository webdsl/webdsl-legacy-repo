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
    Post var83 = new Post();
    post = var83;
    initPerson229List();
    initDiscussion17List();
    initPerson127List();
    initProject121List();
  }

  @Destroy @Remove public void destroy()
  { }

  public void setPerson16(Person person230)
  { 
    post.setAuthor(person230);
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

  private String newPerson229;

  public void setNewPerson229(String p)
  { 
    newPerson229 = p;
  }

  public String getNewPerson229()
  { 
    return newPerson229;
  }

  public void selectPerson229(ValueChangeEvent event)
  { 
    log.info("selectPerson229" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Person person229 = em.find(Person.class, id);
      setPerson16(person229);
    }
  }

  @DataModel("person229List") private Map<String, String> person229List;

  public Map<String, String> getPerson229List()
  { 
    return person229List;
  }

  @Factory("person229List") public void initPerson229List()
  { 
    log.info("initPerson229List");
    person229List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Person").getResultList())
    { 
      Person p = (Person)o;
      person229List.put(p.getName(), p.getId().toString());
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

  @DataModel("person127List") private List<Person> person127List;

  public List<Person> getPerson127List()
  { 
    log.info("getPerson127List");
    return person127List;
  }

  @Factory("person127List") public void initPerson127List()
  { 
    log.info("initPerson127List");
    person127List = em.createQuery("from " + "Person").getResultList();
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