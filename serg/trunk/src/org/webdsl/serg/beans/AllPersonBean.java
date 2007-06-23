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

@Stateful @Name("allPerson") public class AllPersonBean  implements AllPersonBeanInterface
{ 
  @Logger private Log log;

  @PersistenceContext(type = EXTENDED) private EntityManager em;

  @In private FacesMessages facesMessages;

  @Create @Begin public void initialize()
  { 
    log.info("allPerson" + ".initalize()");
    initPerson40List();
    initProject31List();
    initPerson41List();
  }

  @Destroy @Remove public void destroy()
  { }

  public void removePerson(Person person42)
  { 
    em.remove(person42);
  }

  @DataModel("person40List") private List<Person> person40List;

  public List<Person> getPerson40List()
  { 
    log.info("getPerson40List");
    return person40List;
  }

  @Factory("person40List") public void initPerson40List()
  { 
    log.info("initPerson40List");
    person40List = em.createQuery("from " + "Person").getResultList();
  }

  @DataModel("project31List") private List<ResearchProject> project31List;

  public List<ResearchProject> getProject31List()
  { 
    log.info("getProject31List");
    return project31List;
  }

  @Factory("project31List") public void initProject31List()
  { 
    log.info("initProject31List");
    project31List = em.createQuery("from " + "ResearchProject").getResultList();
  }

  @DataModel("person41List") private List<Person> person41List;

  public List<Person> getPerson41List()
  { 
    log.info("getPerson41List");
    return person41List;
  }

  @Factory("person41List") public void initPerson41List()
  { 
    log.info("initPerson41List");
    person41List = em.createQuery("from " + "Person").getResultList();
  }
}