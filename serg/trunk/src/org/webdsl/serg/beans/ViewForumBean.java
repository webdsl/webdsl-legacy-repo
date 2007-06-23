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

@Stateful @Name("viewForum") public class ViewForumBean  implements ViewForumBeanInterface
{ 
  @Logger private Log log;

  @PersistenceContext(type = EXTENDED) private EntityManager em;

  @In private FacesMessages facesMessages;

  @Create @Begin public void initialize()
  { 
    log.info("viewForum" + ".initalize()");
    if(forumId == null)
    { 
      log.info("No " + "forumId" + " defined, creating new " + "Forum");
      forum = new Forum();
    }
    else
    { 
      forum = em.find(Forum.class, forumId);
    }
    initPerson122List();
    initProject115List();
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

  @End public String createNewDiscussion(Forum forum00, java.util.List<Discussion> discussions0)
  { 
    Discussion var83 = new Discussion();
    Discussion discussion00 = var83;
    discussions0.add(discussion00);
    em.persist(forum00);
    return "/" + "editDiscussion" + ".seam?" + ("discussion" + "=" + discussion00.getId() + "");
  }

  @DataModel("person122List") private List<Person> person122List;

  public List<Person> getPerson122List()
  { 
    log.info("getPerson122List");
    return person122List;
  }

  @Factory("person122List") public void initPerson122List()
  { 
    log.info("initPerson122List");
    person122List = em.createQuery("from " + "Person").getResultList();
  }

  @DataModel("project115List") private List<ResearchProject> project115List;

  public List<ResearchProject> getProject115List()
  { 
    log.info("getProject115List");
    return project115List;
  }

  @Factory("project115List") public void initProject115List()
  { 
    log.info("initProject115List");
    project115List = em.createQuery("from " + "ResearchProject").getResultList();
  }
}