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
    Discussion var84 = new Discussion();
    discussion = var84;
    initPerson231List();
    initForum8List();
    initReply11List();
    initPerson125List();
    initProject118List();
  }

  @Destroy @Remove public void destroy()
  { }

  public void setPerson12(Person person232)
  { 
    discussion.setAuthor(person232);
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

  private String newPerson231;

  public void setNewPerson231(String p)
  { 
    newPerson231 = p;
  }

  public String getNewPerson231()
  { 
    return newPerson231;
  }

  public void selectPerson231(ValueChangeEvent event)
  { 
    log.info("selectPerson231" + ": new value = " + " " + event.getNewValue());
    Person person231 = (Person)event.getNewValue();
  }

  @DataModel("person231List") private Map<String, String> person231List;

  public Map<String, String> getPerson231List()
  { 
    return person231List;
  }

  @Factory("person231List") public void initPerson231List()
  { 
    log.info("initPerson231List");
    person231List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Person").getResultList())
    { 
      Person p = (Person)o;
      person231List.put(p.getName(), p.getId().toString());
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
    Forum forum8 = (Forum)event.getNewValue();
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
    Reply reply11 = (Reply)event.getNewValue();
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

  @DataModel("person125List") private List<Person> person125List;

  public List<Person> getPerson125List()
  { 
    log.info("getPerson125List");
    return person125List;
  }

  @Factory("person125List") public void initPerson125List()
  { 
    log.info("initPerson125List");
    person125List = em.createQuery("from " + "Person").getResultList();
  }

  @DataModel("project118List") private List<ResearchProject> project118List;

  public List<ResearchProject> getProject118List()
  { 
    log.info("getProject118List");
    return project118List;
  }

  @Factory("project118List") public void initProject118List()
  { 
    log.info("initProject118List");
    project118List = em.createQuery("from " + "ResearchProject").getResultList();
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