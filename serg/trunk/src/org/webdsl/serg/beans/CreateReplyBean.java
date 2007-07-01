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
    initPerson249List();
    initDiscussion13List();
    initPerson130List();
    initProject121List();
  }

  @Destroy @Remove public void destroy()
  { }

  public void setPerson14(Person person250)
  { 
    reply.setAuthor(person250);
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

  private String newPerson249;

  public void setNewPerson249(String p)
  { 
    newPerson249 = p;
  }

  public String getNewPerson249()
  { 
    return newPerson249;
  }

  public void selectPerson249(ValueChangeEvent event)
  { 
    log.info("selectPerson249" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Person person249 = em.find(Person.class, id);
      setPerson14(person249);
    }
  }

  @DataModel("person249List") private Map<String, String> person249List;

  public Map<String, String> getPerson249List()
  { 
    return person249List;
  }

  @Factory("person249List") public void initPerson249List()
  { 
    log.info("initPerson249List");
    person249List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Person").getResultList())
    { 
      Person p = (Person)o;
      person249List.put(p.getName(), p.getId().toString());
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

  @DataModel("person130List") private List<Person> person130List;

  public List<Person> getPerson130List()
  { 
    log.info("getPerson130List");
    return person130List;
  }

  @Factory("person130List") public void initPerson130List()
  { 
    log.info("initPerson130List");
    person130List = em.createQuery("from " + "Person").getResultList();
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