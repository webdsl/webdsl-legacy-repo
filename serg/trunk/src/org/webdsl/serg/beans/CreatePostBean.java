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
    initPerson239List();
    initDiscussion17List();
    initPerson132List();
    initProject125List();
  }

  @Destroy @Remove public void destroy()
  { }

  public void setPerson16(Person person240)
  { 
    post.setAuthor(person240);
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

  private String newPerson239;

  public void setNewPerson239(String p)
  { 
    newPerson239 = p;
  }

  public String getNewPerson239()
  { 
    return newPerson239;
  }

  public void selectPerson239(ValueChangeEvent event)
  { 
    log.info("selectPerson239" + ": new value = " + " " + event.getNewValue());
    Person person239 = (Person)event.getNewValue();
  }

  @DataModel("person239List") private Map<String, String> person239List;

  public Map<String, String> getPerson239List()
  { 
    return person239List;
  }

  @Factory("person239List") public void initPerson239List()
  { 
    log.info("initPerson239List");
    person239List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Person").getResultList())
    { 
      Person p = (Person)o;
      person239List.put(p.getName(), p.getId().toString());
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
    Discussion discussion17 = (Discussion)event.getNewValue();
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

  @DataModel("person132List") private List<Person> person132List;

  public List<Person> getPerson132List()
  { 
    log.info("getPerson132List");
    return person132List;
  }

  @Factory("person132List") public void initPerson132List()
  { 
    log.info("initPerson132List");
    person132List = em.createQuery("from " + "Person").getResultList();
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