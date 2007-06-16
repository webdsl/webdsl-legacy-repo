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
    Reply var82 = new Reply();
    reply = var82;
    initPerson225List();
    initDiscussion13List();
    initPerson123List();
    initProject117List();
  }

  @Destroy @Remove public void destroy()
  { }

  public void setPerson14(Person person226)
  { 
    reply.setAuthor(person226);
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

  private String newPerson225;

  public void setNewPerson225(String p)
  { 
    newPerson225 = p;
  }

  public String getNewPerson225()
  { 
    return newPerson225;
  }

  public void selectPerson225(ValueChangeEvent event)
  { 
    log.info("selectPerson225" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Person person225 = em.find(Person.class, id);
      setPerson14(person225);
    }
  }

  @DataModel("person225List") private Map<String, String> person225List;

  public Map<String, String> getPerson225List()
  { 
    return person225List;
  }

  @Factory("person225List") public void initPerson225List()
  { 
    log.info("initPerson225List");
    person225List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Person").getResultList())
    { 
      Person p = (Person)o;
      person225List.put(p.getName(), p.getId().toString());
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

  @DataModel("person123List") private List<Person> person123List;

  public List<Person> getPerson123List()
  { 
    log.info("getPerson123List");
    return person123List;
  }

  @Factory("person123List") public void initPerson123List()
  { 
    log.info("initPerson123List");
    person123List = em.createQuery("from " + "Person").getResultList();
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