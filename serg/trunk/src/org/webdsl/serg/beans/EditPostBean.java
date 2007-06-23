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

@Stateful @Name("editPost") public class EditPostBean  implements EditPostBeanInterface
{ 
  @Logger private Log log;

  @PersistenceContext(type = EXTENDED) private EntityManager em;

  @In private FacesMessages facesMessages;

  @Create @Begin public void initialize()
  { 
    log.info("editPost" + ".initalize()");
    if(postId == null)
    { 
      log.info("No " + "postId" + " defined, creating new " + "Post");
      post = new Post();
    }
    else
    { 
      post = em.find(Post.class, postId);
    }
    initPerson237List();
    initDiscussion15List();
    initPerson131List();
    initProject124List();
  }

  @Destroy @Remove public void destroy()
  { }

  @RequestParameter("post") private Long postId;

  private Post post;

  public void setPost(Post post)
  { 
    log.info("setPost");
    this.post = post;
  }

  public Post getPost()
  { 
    log.info("getPost");
    return post;
  }

  public void setPerson15(Person person238)
  { 
    post.setAuthor(person238);
  }

  public void setDiscussion2(Discussion discussion16)
  { 
    post.setDiscussion(discussion16);
  }

  @End public String cancel()
  { 
    return "/" + "viewPost" + ".seam?" + ("post" + "=" + post.getId() + "");
  }

  @End public String save()
  { 
    em.persist(this.getPost());
    return "/" + "viewPost" + ".seam?" + ("post" + "=" + post.getId() + "");
  }

  private String newPerson237;

  public void setNewPerson237(String p)
  { 
    newPerson237 = p;
  }

  public String getNewPerson237()
  { 
    return newPerson237;
  }

  public void selectPerson237(ValueChangeEvent event)
  { 
    log.info("selectPerson237" + ": new value = " + " " + event.getNewValue());
    Person person237 = (Person)event.getNewValue();
  }

  @DataModel("person237List") private Map<String, String> person237List;

  public Map<String, String> getPerson237List()
  { 
    return person237List;
  }

  @Factory("person237List") public void initPerson237List()
  { 
    log.info("initPerson237List");
    person237List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Person").getResultList())
    { 
      Person p = (Person)o;
      person237List.put(p.getName(), p.getId().toString());
    }
  }

  private String newDiscussion15;

  public void setNewDiscussion15(String p)
  { 
    newDiscussion15 = p;
  }

  public String getNewDiscussion15()
  { 
    return newDiscussion15;
  }

  public void selectDiscussion15(ValueChangeEvent event)
  { 
    log.info("selectDiscussion15" + ": new value = " + " " + event.getNewValue());
    Discussion discussion15 = (Discussion)event.getNewValue();
  }

  @DataModel("discussion15List") private Map<String, String> discussion15List;

  public Map<String, String> getDiscussion15List()
  { 
    return discussion15List;
  }

  @Factory("discussion15List") public void initDiscussion15List()
  { 
    log.info("initDiscussion15List");
    discussion15List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Discussion").getResultList())
    { 
      Discussion p = (Discussion)o;
      discussion15List.put(p.getName(), p.getId().toString());
    }
  }

  @DataModel("person131List") private List<Person> person131List;

  public List<Person> getPerson131List()
  { 
    log.info("getPerson131List");
    return person131List;
  }

  @Factory("person131List") public void initPerson131List()
  { 
    log.info("initPerson131List");
    person131List = em.createQuery("from " + "Person").getResultList();
  }

  @DataModel("project124List") private List<ResearchProject> project124List;

  public List<ResearchProject> getProject124List()
  { 
    log.info("getProject124List");
    return project124List;
  }

  @Factory("project124List") public void initProject124List()
  { 
    log.info("initProject124List");
    project124List = em.createQuery("from " + "ResearchProject").getResultList();
  }
}