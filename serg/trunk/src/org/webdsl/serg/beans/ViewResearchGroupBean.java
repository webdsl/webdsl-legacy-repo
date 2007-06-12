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

@Stateful @Name("viewResearchGroup") public class ViewResearchGroupBean  implements ViewResearchGroupBeanInterface
{ 
  @Logger private Log log;

  @PersistenceContext(type = EXTENDED) private EntityManager em;

  @In private FacesMessages facesMessages;

  @Create @Begin public void initialize()
  { 
    log.info("viewResearchGroup" + ".initalize()");
    if(groupId == null)
    { 
      log.info("No " + "groupId" + " defined, creating new " + "ResearchGroup");
      group = new ResearchGroup();
    }
    else
    { 
      group = em.find(ResearchGroup.class, groupId);
    }
    initPublications1();
    initPerson14List();
    initProject10List();
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

  @DataModel("person14List") private List<Person> person14List;

  public List<Person> getPerson14List()
  { 
    log.info("getPerson14List");
    return person14List;
  }

  @Factory("person14List") public void initPerson14List()
  { 
    log.info("initPerson14List");
    person14List = em.createQuery("from " + "Person").getResultList();
  }

  @DataModel("project10List") private List<ResearchProject> project10List;

  public List<ResearchProject> getProject10List()
  { 
    log.info("getProject10List");
    return project10List;
  }

  @Factory("project10List") public void initProject10List()
  { 
    log.info("initProject10List");
    project10List = em.createQuery("from " + "ResearchProject").getResultList();
  }

  @DataModel("publications1") private java.util.List<Publication> publications1;

  public java.util.List<Publication> getPublications1()
  { 
    log.info("getPublications1");
    return publications1;
  }

  public void setPublications1(java.util.List<Publication> publications1)
  { 
    log.info("setPublications1");
    this.publications1 = publications1;
  }

  @Factory("publications1") public void initPublications1()
  { 
    log.info("initPublications1");
    publications1 = em.createQuery("select distinct pub from Publication as pub , Person as pers , ResearchGroup as g where ( g . id = :param7 ) and ( ( pers member of g . _members ) and ( pers member of pub . _authors ) ) order by pub . _year desc").setParameter("param7", this.getGroup().getId()).getResultList();
  }
}