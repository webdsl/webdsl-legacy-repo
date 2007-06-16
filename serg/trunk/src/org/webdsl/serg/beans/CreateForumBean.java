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

@Stateful @Name("createForum") public class CreateForumBean  implements CreateForumBeanInterface
{ 
  @Logger private Log log;

  @PersistenceContext(type = EXTENDED) private EntityManager em;

  @In private FacesMessages facesMessages;

  @Create @Begin public void initialize()
  { 
    log.info("createForum" + ".initalize()");
    Forum var77 = new Forum();
    forum = var77;
    initDiscussion9List();
    initPerson115List();
    initProject110List();
  }

  @Destroy @Remove public void destroy()
  { }

  public void removeDiscussion1(Discussion discussion8)
  { 
    this.getForum().getDiscussions().remove(discussion8);
  }

  public void addDiscussion1(Discussion discussion8)
  { 
    this.getForum().getDiscussions().add(discussion8);
  }

  @End public String cancel()
  { 
    return "/" + "home" + ".seam?" + "";
  }

  @End public String save()
  { 
    em.persist(this.getForum());
    return "/" + "viewForum" + ".seam?" + ("forum" + "=" + forum.getId() + "");
  }

  private String newDiscussion9;

  public void setNewDiscussion9(String p)
  { 
    newDiscussion9 = p;
  }

  public String getNewDiscussion9()
  { 
    return newDiscussion9;
  }

  public void selectDiscussion9(ValueChangeEvent event)
  { 
    log.info("selectDiscussion9" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Discussion discussion9 = em.find(Discussion.class, id);
      addDiscussion1(discussion9);
    }
  }

  @DataModel("discussion9List") private Map<String, String> discussion9List;

  public Map<String, String> getDiscussion9List()
  { 
    return discussion9List;
  }

  @Factory("discussion9List") public void initDiscussion9List()
  { 
    log.info("initDiscussion9List");
    discussion9List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Discussion").getResultList())
    { 
      Discussion p = (Discussion)o;
      discussion9List.put(p.getName(), p.getId().toString());
    }
  }

  @DataModel("person115List") private List<Person> person115List;

  public List<Person> getPerson115List()
  { 
    log.info("getPerson115List");
    return person115List;
  }

  @Factory("person115List") public void initPerson115List()
  { 
    log.info("initPerson115List");
    person115List = em.createQuery("from " + "Person").getResultList();
  }

  @DataModel("project110List") private List<ResearchProject> project110List;

  public List<ResearchProject> getProject110List()
  { 
    log.info("getProject110List");
    return project110List;
  }

  @Factory("project110List") public void initProject110List()
  { 
    log.info("initProject110List");
    project110List = em.createQuery("from " + "ResearchProject").getResultList();
  }

  private Forum forum;

  public Forum getForum()
  { 
    log.info("getForum");
    return forum;
  }

  public void setForum(Forum forum)
  { 
    log.info("setForum");
    this.forum = forum;
  }
}