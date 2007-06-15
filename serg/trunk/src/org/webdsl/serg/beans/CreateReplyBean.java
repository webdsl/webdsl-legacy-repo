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
    Reply var58 = new Reply();
    reply = var58;
    initPerson208List();
    initDiscussion12List();
    initPerson117List();
    initProject117List();
  }

  @Destroy @Remove public void destroy()
  { }

  public void setPerson14(Person person209)
  { 
    reply.setAuthor(person209);
  }

  public void setDiscussion1(Discussion discussion13)
  { 
    reply.setDiscussion(discussion13);
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

  private String newPerson208;

  public void setNewPerson208(String p)
  { 
    newPerson208 = p;
  }

  public String getNewPerson208()
  { 
    return newPerson208;
  }

  public void selectPerson208(ValueChangeEvent event)
  { 
    log.info("selectPerson208" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Person person208 = em.find(Person.class, id);
      setPerson14(person208);
    }
  }

  @DataModel("person208List") private Map<String, String> person208List;

  public Map<String, String> getPerson208List()
  { 
    return person208List;
  }

  @Factory("person208List") public void initPerson208List()
  { 
    log.info("initPerson208List");
    person208List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Person").getResultList())
    { 
      Person p = (Person)o;
      person208List.put(p.getName(), p.getId().toString());
    }
  }

  private String newDiscussion12;

  public void setNewDiscussion12(String p)
  { 
    newDiscussion12 = p;
  }

  public String getNewDiscussion12()
  { 
    return newDiscussion12;
  }

  public void selectDiscussion12(ValueChangeEvent event)
  { 
    log.info("selectDiscussion12" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Discussion discussion12 = em.find(Discussion.class, id);
      setDiscussion1(discussion12);
    }
  }

  @DataModel("discussion12List") private Map<String, String> discussion12List;

  public Map<String, String> getDiscussion12List()
  { 
    return discussion12List;
  }

  @Factory("discussion12List") public void initDiscussion12List()
  { 
    log.info("initDiscussion12List");
    discussion12List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Discussion").getResultList())
    { 
      Discussion p = (Discussion)o;
      discussion12List.put(p.getName(), p.getId().toString());
    }
  }

  @DataModel("person117List") private List<Person> person117List;

  public List<Person> getPerson117List()
  { 
    log.info("getPerson117List");
    return person117List;
  }

  @Factory("person117List") public void initPerson117List()
  { 
    log.info("initPerson117List");
    person117List = em.createQuery("from " + "Person").getResultList();
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