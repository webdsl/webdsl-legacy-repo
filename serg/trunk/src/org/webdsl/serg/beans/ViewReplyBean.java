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

@Stateful @Name("viewReply") public class ViewReplyBean  implements ViewReplyBeanInterface
{ 
  @Logger private Log log;

  @PersistenceContext(type = EXTENDED) private EntityManager em;

  @In private FacesMessages facesMessages;

  @Create @Begin public void initialize()
  { 
    log.info("viewReply" + ".initalize()");
    if(replyId == null)
    { 
      log.info("No " + "replyId" + " defined, creating new " + "Reply");
      reply = new Reply();
    }
    else
    { 
      reply = em.find(Reply.class, replyId);
    }
    initPerson118List();
    initProject118List();
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

  @DataModel("person118List") private List<Person> person118List;

  public List<Person> getPerson118List()
  { 
    log.info("getPerson118List");
    return person118List;
  }

  @Factory("person118List") public void initPerson118List()
  { 
    log.info("initPerson118List");
    person118List = em.createQuery("from " + "Person").getResultList();
  }

  @DataModel("project118List") private List<ResearchProject> project118List;

  public List<ResearchProject> getProject118List()
  { 
    log.info("getProject118List");
    return project118List;
  }

  @Factory("project118List") public void initProject118List()
  { 
    log.info("initProject118List");
    project118List = em.createQuery("from " + "ResearchProject").getResultList();
  }
}