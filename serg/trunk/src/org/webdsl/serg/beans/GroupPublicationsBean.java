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
    initPerson108List();
    initProject118List();
    initPub2List();
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

  @DataModel("person108List") private List<Person> person108List;

  public List<Person> getPerson108List()
  { 
    log.info("getPerson108List");
    return person108List;
  }

  @Factory("person108List") public void initPerson108List()
  { 
    log.info("initPerson108List");
    person108List = em.createQuery("from " + "Person").getResultList();
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

  @DataModel("pub2List") private List<Publication> pub2List;

  public List<Publication> getPub2List()
  { 
    log.info("getPub2List");
    return pub2List;
  }

  @Factory("pub2List") public void initPub2List()
  { 
    log.info("initPub2List");
    pub2List = em.createQuery("from " + "Publication").getResultList();
  }
}