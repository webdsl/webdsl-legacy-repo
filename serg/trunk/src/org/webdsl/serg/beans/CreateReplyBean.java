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
    Reply var85 = new Reply();
    reply = var85;
    initPerson235List();
    initDiscussion13List();
    initPerson128List();
    initProject121List();
  }

  @Destroy @Remove public void destroy()
  { }

  public void setPerson14(Person person236)
  { 
    reply.setAuthor(person236);
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

  private String newPerson235;

  public void setNewPerson235(String p)
  { 
    newPerson235 = p;
  }

  public String getNewPerson235()
  { 
    return newPerson235;
  }

  public void selectPerson235(ValueChangeEvent event)
  { 
    log.info("selectPerson235" + ": new value = " + " " + event.getNewValue());
    Person person235 = (Person)event.getNewValue();
  }

  @DataModel("person235List") private Map<String, String> person235List;

  public Map<String, String> getPerson235List()
  { 
    return person235List;
  }

  @Factory("person235List") public void initPerson235List()
  { 
    log.info("initPerson235List");
    person235List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Person").getResultList())
    { 
      Person p = (Person)o;
      person235List.put(p.getName(), p.getId().toString());
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
    Discussion discussion13 = (Discussion)event.getNewValue();
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

  @DataModel("person128List") private List<Person> person128List;

  public List<Person> getPerson128List()
  { 
    log.info("getPerson128List");
    return person128List;
  }

  @Factory("person128List") public void initPerson128List()
  { 
    log.info("initPerson128List");
    person128List = em.createQuery("from " + "Person").getResultList();
  }

  @DataModel("project121List") private List<ResearchProject> project121List;

  public List<ResearchProject> getProject121List()
  { 
    log.info("getProject121List");
    return project121List;
  }

  @Factory("project121List") public void initProject121List()
  { 
    log.info("initProject121List");
    project121List = em.createQuery("from " + "ResearchProject").getResultList();
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