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

@Stateful @Name("createDiscussion") public class CreateDiscussionBean  implements CreateDiscussionBeanInterface
{ 
  @Logger private Log log;

  @PersistenceContext(type = EXTENDED) private EntityManager em;

  @In private FacesMessages facesMessages;

  @Create @Begin public void initialize()
  { 
    log.info("createDiscussion" + ".initalize()");
    Discussion var57 = new Discussion();
    discussion = var57;
    initPerson204List();
    initForum7List();
    initReply11List();
    initPerson114List();
    initProject114List();
  }

  @Destroy @Remove public void destroy()
  { }

  public void setPerson12(Person person205)
  { 
    discussion.setAuthor(person205);
  }

  public void setForum1(Forum forum8)
  { 
    discussion.setForum(forum8);
  }

  public void removeReply1(Reply reply10)
  { 
    this.getDiscussion().getReplies().remove(reply10);
  }

  public void addReply1(Reply reply10)
  { 
    this.getDiscussion().getReplies().add(reply10);
  }

  @End public String cancel()
  { 
    return "/" + "home" + ".seam?" + "";
  }

  @End public String save()
  { 
    em.persist(this.getDiscussion());
    return "/" + "viewDiscussion" + ".seam?" + ("discussion" + "=" + discussion.getId() + "");
  }

  private String newPerson204;

  public void setNewPerson204(String p)
  { 
    newPerson204 = p;
  }

  public String getNewPerson204()
  { 
    return newPerson204;
  }

  public void selectPerson204(ValueChangeEvent event)
  { 
    log.info("selectPerson204" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Person person204 = em.find(Person.class, id);
      setPerson12(person204);
    }
  }

  @DataModel("person204List") private Map<String, String> person204List;

  public Map<String, String> getPerson204List()
  { 
    return person204List;
  }

  @Factory("person204List") public void initPerson204List()
  { 
    log.info("initPerson204List");
    person204List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Person").getResultList())
    { 
      Person p = (Person)o;
      person204List.put(p.getName(), p.getId().toString());
    }
  }

  private String newForum7;

  public void setNewForum7(String p)
  { 
    newForum7 = p;
  }

  public String getNewForum7()
  { 
    return newForum7;
  }

  public void selectForum7(ValueChangeEvent event)
  { 
    log.info("selectForum7" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Forum forum7 = em.find(Forum.class, id);
      setForum1(forum7);
    }
  }

  @DataModel("forum7List") private Map<String, String> forum7List;

  public Map<String, String> getForum7List()
  { 
    return forum7List;
  }

  @Factory("forum7List") public void initForum7List()
  { 
    log.info("initForum7List");
    forum7List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Forum").getResultList())
    { 
      Forum p = (Forum)o;
      forum7List.put(p.getName(), p.getId().toString());
    }
  }

  private String newReply11;

  public void setNewReply11(String p)
  { 
    newReply11 = p;
  }

  public String getNewReply11()
  { 
    return newReply11;
  }

  public void selectReply11(ValueChangeEvent event)
  { 
    log.info("selectReply11" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Reply reply11 = em.find(Reply.class, id);
      addReply1(reply11);
    }
  }

  @DataModel("reply11List") private Map<String, String> reply11List;

  public Map<String, String> getReply11List()
  { 
    return reply11List;
  }

  @Factory("reply11List") public void initReply11List()
  { 
    log.info("initReply11List");
    reply11List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Reply").getResultList())
    { 
      Reply p = (Reply)o;
      reply11List.put(p.getName(), p.getId().toString());
    }
  }

  @DataModel("person114List") private List<Person> person114List;

  public List<Person> getPerson114List()
  { 
    log.info("getPerson114List");
    return person114List;
  }

  @Factory("person114List") public void initPerson114List()
  { 
    log.info("initPerson114List");
    person114List = em.createQuery("from " + "Person").getResultList();
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

  private Discussion discussion;

  public Discussion getDiscussion()
  { 
    log.info("getDiscussion");
    return discussion;
  }

  public void setDiscussion(Discussion discussion)
  { 
    log.info("setDiscussion");
    this.discussion = discussion;
  }
}