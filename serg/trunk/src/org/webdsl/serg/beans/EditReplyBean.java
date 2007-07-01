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

@Stateful @Name("editReply") public class EditReplyBean  implements EditReplyBeanInterface
{ 
  @Logger private Log log;

  @PersistenceContext(type = EXTENDED) private EntityManager em;

  @In private FacesMessages facesMessages;

  @Create @Begin public void initialize()
  { 
    log.info("editReply" + ".initalize()");
    if(replyId == null)
    { 
      log.info("No " + "replyId" + " defined, creating new " + "Reply");
      reply = new Reply();
    }
    else
    { 
      reply = em.find(Reply.class, replyId);
    }
    initPerson247List();
    initDiscussion11List();
    initPerson129List();
    initProject120List();
  }

  @Destroy @Remove public void destroy()
  { }

  @RequestParameter("reply") private Long replyId;

  private Reply reply;

  public void setReply(Reply reply)
  { 
    log.info("setReply");
    this.reply = reply;
  }

  public Reply getReply()
  { 
    log.info("getReply");
    return reply;
  }

  public void setPerson13(Person person248)
  { 
    reply.setAuthor(person248);
  }

  public void setDiscussion0(Discussion discussion12)
  { 
    reply.setDiscussion(discussion12);
  }

  @End public String cancel()
  { 
    return "/" + "viewReply" + ".seam?" + ("reply" + "=" + reply.getId() + "");
  }

  @End public String save()
  { 
    em.persist(this.getReply());
    return "/" + "viewReply" + ".seam?" + ("reply" + "=" + reply.getId() + "");
  }

  private String newPerson247;

  public void setNewPerson247(String p)
  { 
    newPerson247 = p;
  }

  public String getNewPerson247()
  { 
    return newPerson247;
  }

  public void selectPerson247(ValueChangeEvent event)
  { 
    log.info("selectPerson247" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Person person247 = em.find(Person.class, id);
      setPerson13(person247);
    }
  }

  @DataModel("person247List") private Map<String, String> person247List;

  public Map<String, String> getPerson247List()
  { 
    return person247List;
  }

  @Factory("person247List") public void initPerson247List()
  { 
    log.info("initPerson247List");
    person247List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Person").getResultList())
    { 
      Person p = (Person)o;
      person247List.put(p.getName(), p.getId().toString());
    }
  }

  private String newDiscussion11;

  public void setNewDiscussion11(String p)
  { 
    newDiscussion11 = p;
  }

  public String getNewDiscussion11()
  { 
    return newDiscussion11;
  }

  public void selectDiscussion11(ValueChangeEvent event)
  { 
    log.info("selectDiscussion11" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Discussion discussion11 = em.find(Discussion.class, id);
      setDiscussion0(discussion11);
    }
  }

  @DataModel("discussion11List") private Map<String, String> discussion11List;

  public Map<String, String> getDiscussion11List()
  { 
    return discussion11List;
  }

  @Factory("discussion11List") public void initDiscussion11List()
  { 
    log.info("initDiscussion11List");
    discussion11List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Discussion").getResultList())
    { 
      Discussion p = (Discussion)o;
      discussion11List.put(p.getName(), p.getId().toString());
    }
  }

  @DataModel("person129List") private List<Person> person129List;

  public List<Person> getPerson129List()
  { 
    log.info("getPerson129List");
    return person129List;
  }

  @Factory("person129List") public void initPerson129List()
  { 
    log.info("initPerson129List");
    person129List = em.createQuery("from " + "Person").getResultList();
  }

  @DataModel("project120List") private List<ResearchProject> project120List;

  public List<ResearchProject> getProject120List()
  { 
    log.info("getProject120List");
    return project120List;
  }

  @Factory("project120List") public void initProject120List()
  { 
    log.info("initProject120List");
    project120List = em.createQuery("from " + "ResearchProject").getResultList();
  }
}