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

@Stateful @Name("groupPublications") public class GroupPublicationsBean  implements GroupPublicationsBeanInterface
{ 
  @Logger private Log log;

  @PersistenceContext(type = EXTENDED) private EntityManager em;

  @In private FacesMessages facesMessages;

  @Create @Begin public void initialize()
  { 
    log.info("groupPublications" + ".initalize()");
    if(groupId == null)
    { 
      log.info("No " + "groupId" + " defined, creating new " + "ResearchGroup");
      group = new ResearchGroup();
    }
    else
    { 
      group = em.find(ResearchGroup.class, groupId);
    }
    initPerson18List();
    initProject17List();
    initPub6List();
  }

  @Destroy @Remove public void destroy()
  { }

  @RequestParameter("group") private Long groupId;

  private ResearchGroup group;

  public void setGroup(ResearchGroup group)
  { 
    log.info("setGroup");
    this.group = group;
  }

  public ResearchGroup getGroup()
  { 
    log.info("getGroup");
    return group;
  }

  @DataModel("person18List") private List<Person> person18List;

  public List<Person> getPerson18List()
  { 
    log.info("getPerson18List");
    return person18List;
  }

  @Factory("person18List") public void initPerson18List()
  { 
    log.info("initPerson18List");
    person18List = em.createQuery("from " + "Person").getResultList();
  }

  @DataModel("project17List") private List<ResearchProject> project17List;

  public List<ResearchProject> getProject17List()
  { 
    log.info("getProject17List");
    return project17List;
  }

  @Factory("project17List") public void initProject17List()
  { 
    log.info("initProject17List");
    project17List = em.createQuery("from " + "ResearchProject").getResultList();
  }

  @DataModel("pub6List") private List<Publication> pub6List;

  public List<Publication> getPub6List()
  { 
    log.info("getPub6List");
    return pub6List;
  }

  @Factory("pub6List") public void initPub6List()
  { 
    log.info("initPub6List");
    pub6List = em.createQuery("from " + "Publication").getResultList();
  }
}