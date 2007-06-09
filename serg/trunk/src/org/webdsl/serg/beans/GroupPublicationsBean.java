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
    initPerson1010List();
    initProject1110List();
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

  @DataModel("person1010List") private List<Person> person1010List;

  public List<Person> getPerson1010List()
  { 
    log.info("getPerson1010List");
    return person1010List;
  }

  @Factory("person1010List") public void initPerson1010List()
  { 
    log.info("initPerson1010List");
    person1010List = em.createQuery("from " + "Person").getResultList();
  }

  @DataModel("project1110List") private List<ResearchProject> project1110List;

  public List<ResearchProject> getProject1110List()
  { 
    log.info("getProject1110List");
    return project1110List;
  }

  @Factory("project1110List") public void initProject1110List()
  { 
    log.info("initProject1110List");
    project1110List = em.createQuery("from " + "ResearchProject").getResultList();
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