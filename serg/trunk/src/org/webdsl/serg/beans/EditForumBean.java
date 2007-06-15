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

@Stateful @Name("editForum") public class EditForumBean  implements EditForumBeanInterface
{ 
  @Logger private Log log;

  @PersistenceContext(type = EXTENDED) private EntityManager em;

  @In private FacesMessages facesMessages;

  @Create @Begin public void initialize()
  { 
    log.info("editForum" + ".initalize()");
    if(forumId == null)
    { 
      log.info("No " + "forumId" + " defined, creating new " + "Forum");
      forum = new Forum();
    }
    else
    { 
      forum = em.find(Forum.class, forumId);
    }
    initDiscussion6List();
    initPerson109List();
    initProject109List();
  }

  @Destroy @Remove public void destroy()
  { }

  @RequestParameter("forum") private Long forumId;

  private Forum forum;

  public void setForum(Forum forum)
  { 
    log.info("setForum");
    this.forum = forum;
  }

  public Forum getForum()
  { 
    log.info("getForum");
    return forum;
  }

  public void removeDiscussion0(Discussion discussion5)
  { 
    this.getForum().getDiscussions().remove(discussion5);
  }

  public void addDiscussion0(Discussion discussion5)
  { 
    this.getForum().getDiscussions().add(discussion5);
  }

  @End public String cancel()
  { 
    return "/" + "viewForum" + ".seam?" + ("forum" + "=" + forum.getId() + "");
  }

  @End public String save()
  { 
    em.persist(this.getForum());
    return "/" + "viewForum" + ".seam?" + ("forum" + "=" + forum.getId() + "");
  }

  private String newDiscussion6;

  public void setNewDiscussion6(String p)
  { 
    newDiscussion6 = p;
  }

  public String getNewDiscussion6()
  { 
    return newDiscussion6;
  }

  public void selectDiscussion6(ValueChangeEvent event)
  { 
    log.info("selectDiscussion6" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Discussion discussion6 = em.find(Discussion.class, id);
      addDiscussion0(discussion6);
    }
  }

  @DataModel("discussion6List") private Map<String, String> discussion6List;

  public Map<String, String> getDiscussion6List()
  { 
    return discussion6List;
  }

  @Factory("discussion6List") public void initDiscussion6List()
  { 
    log.info("initDiscussion6List");
    discussion6List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Discussion").getResultList())
    { 
      Discussion p = (Discussion)o;
      discussion6List.put(p.getName(), p.getId().toString());
    }
  }

  @DataModel("person109List") private List<Person> person109List;

  public List<Person> getPerson109List()
  { 
    log.info("getPerson109List");
    return person109List;
  }

  @Factory("person109List") public void initPerson109List()
  { 
    log.info("initPerson109List");
    person109List = em.createQuery("from " + "Person").getResultList();
  }

  @DataModel("project109List") private List<ResearchProject> project109List;

  public List<ResearchProject> getProject109List()
  { 
    log.info("getProject109List");
    return project109List;
  }

  @Factory("project109List") public void initProject109List()
  { 
    log.info("initProject109List");
    project109List = em.createQuery("from " + "ResearchProject").getResultList();
  }
}