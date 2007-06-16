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

@Stateful @Name("createReply") public class CreateReplyBean  implements CreateReplyBeanInterface
{ 
  @Logger private Log log;

  @PersistenceContext(type = EXTENDED) private EntityManager em;

  @In private FacesMessages facesMessages;

  @Create @Begin public void initialize()
  { 
    log.info("createReply" + ".initalize()");
    Reply var80 = new Reply();
    reply = var80;
    initPerson224List();
    initDiscussion13List();
    initPerson122List();
    initProject117List();
  }

  @Destroy @Remove public void destroy()
  { }

  public void setPerson14(Person person225)
  { 
    reply.setAuthor(person225);
  }

  public void setDiscussion1(Discussion discussion14)
  { 
    reply.setDiscussion(discussion14);
  }

  @End public String cancel()
  { 
    return "/" + "home" + ".seam?" + "";
  }

  @End public String save()
  { 
    em.persist(this.getReply());
    return "/" + "viewReply" + ".seam?" + ("reply" + "=" + reply.getId() + "");
  }

  private String newPerson224;

  public void setNewPerson224(String p)
  { 
    newPerson224 = p;
  }

  public String getNewPerson224()
  { 
    return newPerson224;
  }

  public void selectPerson224(ValueChangeEvent event)
  { 
    log.info("selectPerson224" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Person person224 = em.find(Person.class, id);
      setPerson14(person224);
    }
  }

  @DataModel("person224List") private Map<String, String> person224List;

  public Map<String, String> getPerson224List()
  { 
    return person224List;
  }

  @Factory("person224List") public void initPerson224List()
  { 
    log.info("initPerson224List");
    person224List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Person").getResultList())
    { 
      Person p = (Person)o;
      person224List.put(p.getName(), p.getId().toString());
    }
  }

  private String newDiscussion13;

  public void setNewDiscussion13(String p)
  { 
    newDiscussion13 = p;
  }

  public String getNewDiscussion13()
  { 
    return newDiscussion13;
  }

  public void selectDiscussion13(ValueChangeEvent event)
  { 
    log.info("selectDiscussion13" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Discussion discussion13 = em.find(Discussion.class, id);
      setDiscussion1(discussion13);
    }
  }

  @DataModel("discussion13List") private Map<String, String> discussion13List;

  public Map<String, String> getDiscussion13List()
  { 
    return discussion13List;
  }

  @Factory("discussion13List") public void initDiscussion13List()
  { 
    log.info("initDiscussion13List");
    discussion13List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Discussion").getResultList())
    { 
      Discussion p = (Discussion)o;
      discussion13List.put(p.getName(), p.getId().toString());
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

  private Reply reply;

  public Reply getReply()
  { 
    log.info("getReply");
    return reply;
  }

  public void setReply(Reply reply)
  { 
    log.info("setReply");
    this.reply = reply;
  }
}