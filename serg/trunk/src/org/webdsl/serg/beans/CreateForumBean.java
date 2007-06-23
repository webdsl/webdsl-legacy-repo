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
    Forum var82 = new Forum();
    forum = var82;
    initDiscussion9List();
    initPerson121List();
    initProject114List();
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
    Discussion discussion9 = (Discussion)event.getNewValue();
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

  @DataModel("project114List") private List<ResearchProject> project114List;

  public List<ResearchProject> getProject114List()
  { 
    log.info("getProject114List");
    return project114List;
  }

  @Factory("project114List") public void initProject114List()
  { 
    log.info("initProject114List");
    project114List = em.createQuery("from " + "ResearchProject").getResultList();
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