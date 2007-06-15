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
    initPerson206List();
    initDiscussion10List();
    initPerson116List();
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

  public void setPerson13(Person person207)
  { 
    reply.setAuthor(person207);
  }

  public void setDiscussion0(Discussion discussion11)
  { 
    reply.setDiscussion(discussion11);
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

  private String newPerson206;

  public void setNewPerson206(String p)
  { 
    newPerson206 = p;
  }

  public String getNewPerson206()
  { 
    return newPerson206;
  }

  public void selectPerson206(ValueChangeEvent event)
  { 
    log.info("selectPerson206" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Person person206 = em.find(Person.class, id);
      setPerson13(person206);
    }
  }

  @DataModel("person206List") private Map<String, String> person206List;

  public Map<String, String> getPerson206List()
  { 
    return person206List;
  }

  @Factory("person206List") public void initPerson206List()
  { 
    log.info("initPerson206List");
    person206List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Person").getResultList())
    { 
      Person p = (Person)o;
      person206List.put(p.getName(), p.getId().toString());
    }
  }

  private String newDiscussion10;

  public void setNewDiscussion10(String p)
  { 
    newDiscussion10 = p;
  }

  public String getNewDiscussion10()
  { 
    return newDiscussion10;
  }

  public void selectDiscussion10(ValueChangeEvent event)
  { 
    log.info("selectDiscussion10" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Discussion discussion10 = em.find(Discussion.class, id);
      setDiscussion0(discussion10);
    }
  }

  @DataModel("discussion10List") private Map<String, String> discussion10List;

  public Map<String, String> getDiscussion10List()
  { 
    return discussion10List;
  }

  @Factory("discussion10List") public void initDiscussion10List()
  { 
    log.info("initDiscussion10List");
    discussion10List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Discussion").getResultList())
    { 
      Discussion p = (Discussion)o;
      discussion10List.put(p.getName(), p.getId().toString());
    }
  }

  @DataModel("person116List") private List<Person> person116List;

  public List<Person> getPerson116List()
  { 
    log.info("getPerson116List");
    return person116List;
  }

  @Factory("person116List") public void initPerson116List()
  { 
    log.info("initPerson116List");
    person116List = em.createQuery("from " + "Person").getResultList();
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