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
    Discussion var79 = new Discussion();
    discussion = var79;
    initPerson220List();
    initForum8List();
    initReply11List();
    initPerson119List();
    initProject114List();
  }

  @Destroy @Remove public void destroy()
  { }

  public void setPerson12(Person person221)
  { 
    discussion.setAuthor(person221);
  }

  public void setForum1(Forum forum9)
  { 
    discussion.setForum(forum9);
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

  private String newPerson220;

  public void setNewPerson220(String p)
  { 
    newPerson220 = p;
  }

  public String getNewPerson220()
  { 
    return newPerson220;
  }

  public void selectPerson220(ValueChangeEvent event)
  { 
    log.info("selectPerson220" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Person person220 = em.find(Person.class, id);
      setPerson12(person220);
    }
  }

  @DataModel("person220List") private Map<String, String> person220List;

  public Map<String, String> getPerson220List()
  { 
    return person220List;
  }

  @Factory("person220List") public void initPerson220List()
  { 
    log.info("initPerson220List");
    person220List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Person").getResultList())
    { 
      Person p = (Person)o;
      person220List.put(p.getName(), p.getId().toString());
    }
  }

  private String newForum8;

  public void setNewForum8(String p)
  { 
    newForum8 = p;
  }

  public String getNewForum8()
  { 
    return newForum8;
  }

  public void selectForum8(ValueChangeEvent event)
  { 
    log.info("selectForum8" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Forum forum8 = em.find(Forum.class, id);
      setForum1(forum8);
    }
  }

  @DataModel("forum8List") private Map<String, String> forum8List;

  public Map<String, String> getForum8List()
  { 
    return forum8List;
  }

  @Factory("forum8List") public void initForum8List()
  { 
    log.info("initForum8List");
    forum8List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Forum").getResultList())
    { 
      Forum p = (Forum)o;
      forum8List.put(p.getName(), p.getId().toString());
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

  @DataModel("person119List") private List<Person> person119List;

  public List<Person> getPerson119List()
  { 
    log.info("getPerson119List");
    return person119List;
  }

  @Factory("person119List") public void initPerson119List()
  { 
    log.info("initPerson119List");
    person119List = em.createQuery("from " + "Person").getResultList();
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