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
    initPerson34List();
    initProject31List();
    initPerson35List();
  }

  @Destroy @Remove public void destroy()
  { }

  public void removePerson(Person person36)
  { 
    em.remove(person36);
  }

  @DataModel("person34List") private List<Person> person34List;

  public List<Person> getPerson34List()
  { 
    log.info("getPerson34List");
    return person34List;
  }

  @Factory("person34List") public void initPerson34List()
  { 
    log.info("initPerson34List");
    person34List = em.createQuery("from " + "Person").getResultList();
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

  @DataModel("person35List") private List<Person> person35List;

  public List<Person> getPerson35List()
  { 
    log.info("getPerson35List");
    return person35List;
  }

  @Factory("person35List") public void initPerson35List()
  { 
    log.info("initPerson35List");
    person35List = em.createQuery("from " + "Person").getResultList();
  }
}