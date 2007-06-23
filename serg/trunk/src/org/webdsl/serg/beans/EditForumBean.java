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
    initDiscussion7List();
    initPerson120List();
    initProject113List();
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

  public void removeDiscussion0(Discussion discussion6)
  { 
    this.getForum().getDiscussions().remove(discussion6);
  }

  public void addDiscussion0(Discussion discussion6)
  { 
    this.getForum().getDiscussions().add(discussion6);
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

  private String newDiscussion7;

  public void setNewDiscussion7(String p)
  { 
    newDiscussion7 = p;
  }

  public String getNewDiscussion7()
  { 
    return newDiscussion7;
  }

  public void selectDiscussion7(ValueChangeEvent event)
  { 
    log.info("selectDiscussion7" + ": new value = " + " " + event.getNewValue());
    Discussion discussion7 = (Discussion)event.getNewValue();
  }

  @DataModel("discussion7List") private Map<String, String> discussion7List;

  public Map<String, String> getDiscussion7List()
  { 
    return discussion7List;
  }

  @Factory("discussion7List") public void initDiscussion7List()
  { 
    log.info("initDiscussion7List");
    discussion7List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Discussion").getResultList())
    { 
      Discussion p = (Discussion)o;
      discussion7List.put(p.getName(), p.getId().toString());
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

  @DataModel("project113List") private List<ResearchProject> project113List;

  public List<ResearchProject> getProject113List()
  { 
    log.info("getProject113List");
    return project113List;
  }

  @Factory("project113List") public void initProject113List()
  { 
    log.info("initProject113List");
    project113List = em.createQuery("from " + "ResearchProject").getResultList();
  }
}