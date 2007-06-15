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

@Stateful @Name("allProject") public class AllProjectBean  implements AllProjectBeanInterface
{ 
  @Logger private Log log;

  @PersistenceContext(type = EXTENDED) private EntityManager em;

  @In private FacesMessages facesMessages;

  @Create @Begin public void initialize()
  { 
    log.info("allProject" + ".initalize()");
    initPerson105List();
    initProject98List();
    initProject99List();
  }

  @Destroy @Remove public void destroy()
  { }

  public void removeProject(Project project100)
  { 
    em.remove(project100);
  }

  @DataModel("person105List") private List<Person> person105List;

  public List<Person> getPerson105List()
  { 
    log.info("getPerson105List");
    return person105List;
  }

  @Factory("person105List") public void initPerson105List()
  { 
    log.info("initPerson105List");
    person105List = em.createQuery("from " + "Person").getResultList();
  }

  @DataModel("project98List") private List<ResearchProject> project98List;

  public List<ResearchProject> getProject98List()
  { 
    log.info("getProject98List");
    return project98List;
  }

  @Factory("project98List") public void initProject98List()
  { 
    log.info("initProject98List");
    project98List = em.createQuery("from " + "ResearchProject").getResultList();
  }

  @DataModel("project99List") private List<Project> project99List;

  public List<Project> getProject99List()
  { 
    log.info("getProject99List");
    return project99List;
  }

  @Factory("project99List") public void initProject99List()
  { 
    log.info("initProject99List");
    project99List = em.createQuery("from " + "Project").getResultList();
  }
}