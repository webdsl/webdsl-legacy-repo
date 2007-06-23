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
    initPerson111List();
    initProject102List();
    initProject103List();
  }

  @Destroy @Remove public void destroy()
  { }

  public void removeProject(Project project104)
  { 
    em.remove(project104);
  }

  @DataModel("person111List") private List<Person> person111List;

  public List<Person> getPerson111List()
  { 
    log.info("getPerson111List");
    return person111List;
  }

  @Factory("person111List") public void initPerson111List()
  { 
    log.info("initPerson111List");
    person111List = em.createQuery("from " + "Person").getResultList();
  }

  @DataModel("project102List") private List<ResearchProject> project102List;

  public List<ResearchProject> getProject102List()
  { 
    log.info("getProject102List");
    return project102List;
  }

  @Factory("project102List") public void initProject102List()
  { 
    log.info("initProject102List");
    project102List = em.createQuery("from " + "ResearchProject").getResultList();
  }

  @DataModel("project103List") private List<Project> project103List;

  public List<Project> getProject103List()
  { 
    log.info("getProject103List");
    return project103List;
  }

  @Factory("project103List") public void initProject103List()
  { 
    log.info("initProject103List");
    project103List = em.createQuery("from " + "Project").getResultList();
  }
}