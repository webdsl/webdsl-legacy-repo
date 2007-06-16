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
    initPerson223List();
    initDiscussion11List();
    initPerson122List();
    initProject116List();
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

  public void setPerson13(Person person224)
  { 
    reply.setAuthor(person224);
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

  private String newPerson223;

  public void setNewPerson223(String p)
  { 
    newPerson223 = p;
  }

  public String getNewPerson223()
  { 
    return newPerson223;
  }

  public void selectPerson223(ValueChangeEvent event)
  { 
    log.info("selectPerson223" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Person person223 = em.find(Person.class, id);
      setPerson13(person223);
    }
  }

  @DataModel("person223List") private Map<String, String> person223List;

  public Map<String, String> getPerson223List()
  { 
    return person223List;
  }

  @Factory("person223List") public void initPerson223List()
  { 
    log.info("initPerson223List");
    person223List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Person").getResultList())
    { 
      Person p = (Person)o;
      person223List.put(p.getName(), p.getId().toString());
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

  @DataModel("project116List") private List<ResearchProject> project116List;

  public List<ResearchProject> getProject116List()
  { 
    log.info("getProject116List");
    return project116List;
  }

  @Factory("project116List") public void initProject116List()
  { 
    log.info("initProject116List");
    project116List = em.createQuery("from " + "ResearchProject").getResultList();
  }
}