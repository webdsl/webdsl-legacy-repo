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

@Stateful @Name("viewDiscussion") public class ViewDiscussionBean  implements ViewDiscussionBeanInterface
{ 
  @Logger private Log log;

  @PersistenceContext(type = EXTENDED) private EntityManager em;

  @In private FacesMessages facesMessages;

  @Create @Begin public void initialize()
  { 
    log.info("viewDiscussion" + ".initalize()");
    if(discussionId == null)
    { 
      log.info("No " + "discussionId" + " defined, creating new " + "Discussion");
      discussion = new Discussion();
    }
    else
    { 
      discussion = em.find(Discussion.class, discussionId);
    }
    Reply var17 = new Reply();
    var17.setDiscussion(this.getDiscussion());
    newReply0 = var17;
    initPerson126List();
    initPerson20List();
    initProject19List();
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

  public void delete(Reply reply2)
  { 
    this.getDiscussion().getReplies().remove(reply2);
  }

  public void setPerson0(Person person127)
  { 
    newReply0.setAuthor(person127);
  }

  public void post()
  { 
    em.persist(this.getNewReply0());
    this.getDiscussion().getReplies().add(this.getNewReply0());
    Reply var16 = new Reply();
    var16.setDiscussion(this.getDiscussion());
    newReply0 = var16;
  }

  private String newPerson126;

  public void setNewPerson126(String p)
  { 
    newPerson126 = p;
  }

  public String getNewPerson126()
  { 
    return newPerson126;
  }

  public void selectPerson126(ValueChangeEvent event)
  { 
    log.info("selectPerson126" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Person person126 = em.find(Person.class, id);
      setPerson0(person126);
    }
  }

  @DataModel("person126List") private Map<String, String> person126List;

  public Map<String, String> getPerson126List()
  { 
    return person126List;
  }

  @Factory("person126List") public void initPerson126List()
  { 
    log.info("initPerson126List");
    person126List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Person").getResultList())
    { 
      Person p = (Person)o;
      person126List.put(p.getName(), p.getId().toString());
    }
  }

  @DataModel("person20List") private List<Person> person20List;

  public List<Person> getPerson20List()
  { 
    log.info("getPerson20List");
    return person20List;
  }

  @Factory("person20List") public void initPerson20List()
  { 
    log.info("initPerson20List");
    person20List = em.createQuery("from " + "Person").getResultList();
  }

  @DataModel("project19List") private List<ResearchProject> project19List;

  public List<ResearchProject> getProject19List()
  { 
    log.info("getProject19List");
    return project19List;
  }

  @Factory("project19List") public void initProject19List()
  { 
    log.info("initProject19List");
    project19List = em.createQuery("from " + "ResearchProject").getResultList();
  }

  private Reply newReply0;

  public Reply getNewReply0()
  { 
    log.info("getNewReply0");
    return newReply0;
  }

  public void setNewReply0(Reply newReply0)
  { 
    log.info("setNewReply0");
    this.newReply0 = newReply0;
  }
}