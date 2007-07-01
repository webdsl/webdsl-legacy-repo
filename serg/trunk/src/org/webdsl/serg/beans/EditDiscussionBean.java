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

@Stateful @Name("editDiscussion") public class EditDiscussionBean  implements EditDiscussionBeanInterface
{ 
  @Logger private Log log;

  @PersistenceContext(type = EXTENDED) private EntityManager em;

  @In private FacesMessages facesMessages;

  @Create @Begin public void initialize()
  { 
    log.info("editDiscussion" + ".initalize()");
    if(discussionId == null)
    { 
      log.info("No " + "discussionId" + " defined, creating new " + "Discussion");
      discussion = new Discussion();
    }
    else
    { 
      discussion = em.find(Discussion.class, discussionId);
    }
    initPerson243List();
    initForum6List();
    initReply9List();
    initPerson126List();
    initProject117List();
  }

  @Destroy @Remove public void destroy()
  { }

  @RequestParameter("discussion") private Long discussionId;

  private Discussion discussion;

  public void setDiscussion(Discussion discussion)
  { 
    log.info("setDiscussion");
    this.discussion = discussion;
  }

  public Discussion getDiscussion()
  { 
    log.info("getDiscussion");
    return discussion;
  }

  public void setPerson11(Person person244)
  { 
    discussion.setAuthor(person244);
  }

  public void setForum0(Forum forum7)
  { 
    discussion.setForum(forum7);
  }

  public void removeReply0(Reply reply8)
  { 
    this.getDiscussion().getReplies().remove(reply8);
  }

  public void addReply0(Reply reply8)
  { 
    this.getDiscussion().getReplies().add(reply8);
  }

  @End public String cancel()
  { 
    return "/" + "viewDiscussion" + ".seam?" + ("discussion" + "=" + discussion.getId() + "");
  }

  @End public String save()
  { 
    em.persist(this.getDiscussion());
    return "/" + "viewDiscussion" + ".seam?" + ("discussion" + "=" + discussion.getId() + "");
  }

  private String newPerson243;

  public void setNewPerson243(String p)
  { 
    newPerson243 = p;
  }

  public String getNewPerson243()
  { 
    return newPerson243;
  }

  public void selectPerson243(ValueChangeEvent event)
  { 
    log.info("selectPerson243" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Person person243 = em.find(Person.class, id);
      setPerson11(person243);
    }
  }

  @DataModel("person243List") private Map<String, String> person243List;

  public Map<String, String> getPerson243List()
  { 
    return person243List;
  }

  @Factory("person243List") public void initPerson243List()
  { 
    log.info("initPerson243List");
    person243List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Person").getResultList())
    { 
      Person p = (Person)o;
      person243List.put(p.getName(), p.getId().toString());
    }
  }

  private String newForum6;

  public void setNewForum6(String p)
  { 
    newForum6 = p;
  }

  public String getNewForum6()
  { 
    return newForum6;
  }

  public void selectForum6(ValueChangeEvent event)
  { 
    log.info("selectForum6" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Forum forum6 = em.find(Forum.class, id);
      setForum0(forum6);
    }
  }

  @DataModel("forum6List") private Map<String, String> forum6List;

  public Map<String, String> getForum6List()
  { 
    return forum6List;
  }

  @Factory("forum6List") public void initForum6List()
  { 
    log.info("initForum6List");
    forum6List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Forum").getResultList())
    { 
      Forum p = (Forum)o;
      forum6List.put(p.getName(), p.getId().toString());
    }
  }

  private String newReply9;

  public void setNewReply9(String p)
  { 
    newReply9 = p;
  }

  public String getNewReply9()
  { 
    return newReply9;
  }

  public void selectReply9(ValueChangeEvent event)
  { 
    log.info("selectReply9" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Reply reply9 = em.find(Reply.class, id);
      addReply0(reply9);
    }
  }

  @DataModel("reply9List") private Map<String, String> reply9List;

  public Map<String, String> getReply9List()
  { 
    return reply9List;
  }

  @Factory("reply9List") public void initReply9List()
  { 
    log.info("initReply9List");
    reply9List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Reply").getResultList())
    { 
      Reply p = (Reply)o;
      reply9List.put(p.getName(), p.getId().toString());
    }
  }

  @DataModel("person126List") private List<Person> person126List;

  public List<Person> getPerson126List()
  { 
    log.info("getPerson126List");
    return person126List;
  }

  @Factory("person126List") public void initPerson126List()
  { 
    log.info("initPerson126List");
    person126List = em.createQuery("from " + "Person").getResultList();
  }

  @DataModel("project117List") private List<ResearchProject> project117List;

  public List<ResearchProject> getProject117List()
  { 
    log.info("getProject117List");
    return project117List;
  }

  @Factory("project117List") public void initProject117List()
  { 
    log.info("initProject117List");
    project117List = em.createQuery("from " + "ResearchProject").getResultList();
  }
}