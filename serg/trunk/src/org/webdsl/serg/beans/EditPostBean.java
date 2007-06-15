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
    initPerson210List();
    initDiscussion14List();
    initPerson120List();
    initProject120List();
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

  public void setPerson15(Person person211)
  { 
    post.setAuthor(person211);
  }

  public void setDiscussion2(Discussion discussion15)
  { 
    post.setDiscussion(discussion15);
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

  private String newPerson210;

  public void setNewPerson210(String p)
  { 
    newPerson210 = p;
  }

  public String getNewPerson210()
  { 
    return newPerson210;
  }

  public void selectPerson210(ValueChangeEvent event)
  { 
    log.info("selectPerson210" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Person person210 = em.find(Person.class, id);
      setPerson15(person210);
    }
  }

  @DataModel("person210List") private Map<String, String> person210List;

  public Map<String, String> getPerson210List()
  { 
    return person210List;
  }

  @Factory("person210List") public void initPerson210List()
  { 
    log.info("initPerson210List");
    person210List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Person").getResultList())
    { 
      Person p = (Person)o;
      person210List.put(p.getName(), p.getId().toString());
    }
  }

  private String newDiscussion14;

  public void setNewDiscussion14(String p)
  { 
    newDiscussion14 = p;
  }

  public String getNewDiscussion14()
  { 
    return newDiscussion14;
  }

  public void selectDiscussion14(ValueChangeEvent event)
  { 
    log.info("selectDiscussion14" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Discussion discussion14 = em.find(Discussion.class, id);
      setDiscussion2(discussion14);
    }
  }

  @DataModel("discussion14List") private Map<String, String> discussion14List;

  public Map<String, String> getDiscussion14List()
  { 
    return discussion14List;
  }

  @Factory("discussion14List") public void initDiscussion14List()
  { 
    log.info("initDiscussion14List");
    discussion14List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Discussion").getResultList())
    { 
      Discussion p = (Discussion)o;
      discussion14List.put(p.getName(), p.getId().toString());
    }
  }

  @DataModel("person120List") private List<Person> person120List;

  public List<Person> getPerson120List()
  { 
    log.info("getPerson120List");
    return person120List;
  }

  @Factory("person120List") public void initPerson120List()
  { 
    log.info("initPerson120List");
    person120List = em.createQuery("from " + "Person").getResultList();
  }

  @DataModel("project120List") private List<ResearchProject> project120List;

  public List<ResearchProject> getProject120List()
  { 
    log.info("getProject120List");
    return project120List;
  }

  @Factory("project120List") public void initProject120List()
  { 
    log.info("initProject120List");
    project120List = em.createQuery("from " + "ResearchProject").getResultList();
  }
}