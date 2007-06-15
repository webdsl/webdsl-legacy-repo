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
    Forum var56 = new Forum();
    forum = var56;
    initDiscussion8List();
    initPerson110List();
    initProject110List();
  }

  @Destroy @Remove public void destroy()
  { }

  public void removeDiscussion1(Discussion discussion7)
  { 
    this.getForum().getDiscussions().remove(discussion7);
  }

  public void addDiscussion1(Discussion discussion7)
  { 
    this.getForum().getDiscussions().add(discussion7);
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

  private String newDiscussion8;

  public void setNewDiscussion8(String p)
  { 
    newDiscussion8 = p;
  }

  public String getNewDiscussion8()
  { 
    return newDiscussion8;
  }

  public void selectDiscussion8(ValueChangeEvent event)
  { 
    log.info("selectDiscussion8" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Discussion discussion8 = em.find(Discussion.class, id);
      addDiscussion1(discussion8);
    }
  }

  @DataModel("discussion8List") private Map<String, String> discussion8List;

  public Map<String, String> getDiscussion8List()
  { 
    return discussion8List;
  }

  @Factory("discussion8List") public void initDiscussion8List()
  { 
    log.info("initDiscussion8List");
    discussion8List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Discussion").getResultList())
    { 
      Discussion p = (Discussion)o;
      discussion8List.put(p.getName(), p.getId().toString());
    }
  }

  @DataModel("person110List") private List<Person> person110List;

  public List<Person> getPerson110List()
  { 
    log.info("getPerson110List");
    return person110List;
  }

  @Factory("person110List") public void initPerson110List()
  { 
    log.info("initPerson110List");
    person110List = em.createQuery("from " + "Person").getResultList();
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