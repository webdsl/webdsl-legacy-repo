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

@Stateful @Name("allReply") public class AllReplyBean  implements AllReplyBeanInterface
{ 
  @Logger private Log log;

  @PersistenceContext(type = EXTENDED) private EntityManager em;

  @In private FacesMessages facesMessages;

  @Create @Begin public void initialize()
  { 
    log.info("allReply" + ".initalize()");
    initPerson125List();
    initProject119List();
    initReply6List();
  }

  @Destroy @Remove public void destroy()
  { }

  public void removeReply(Reply reply7)
  { 
    em.remove(reply7);
  }

  @DataModel("person125List") private List<Person> person125List;

  public List<Person> getPerson125List()
  { 
    log.info("getPerson125List");
    return person125List;
  }

  @Factory("person125List") public void initPerson125List()
  { 
    log.info("initPerson125List");
    person125List = em.createQuery("from " + "Person").getResultList();
  }

  @DataModel("project119List") private List<ResearchProject> project119List;

  public List<ResearchProject> getProject119List()
  { 
    log.info("getProject119List");
    return project119List;
  }

  @Factory("project119List") public void initProject119List()
  { 
    log.info("initProject119List");
    project119List = em.createQuery("from " + "ResearchProject").getResultList();
  }

  @DataModel("reply6List") private List<Reply> reply6List;

  public List<Reply> getReply6List()
  { 
    log.info("getReply6List");
    return reply6List;
  }

  @Factory("reply6List") public void initReply6List()
  { 
    log.info("initReply6List");
    reply6List = em.createQuery("from " + "Reply").getResultList();
  }
}