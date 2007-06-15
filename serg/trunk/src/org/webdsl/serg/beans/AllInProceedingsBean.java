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

@Stateful @Name("allInProceedings") public class AllInProceedingsBean  implements AllInProceedingsBeanInterface
{ 
  @Logger private Log log;

  @PersistenceContext(type = EXTENDED) private EntityManager em;

  @In private FacesMessages facesMessages;

  @Create @Begin public void initialize()
  { 
    log.info("allInProceedings" + ".initalize()");
    initPerson74List();
    initProject64List();
    initInProceedings3List();
  }

  @Destroy @Remove public void destroy()
  { }

  public void removeInProceedings(InProceedings inProceedings4)
  { 
    em.remove(inProceedings4);
  }

  @DataModel("person74List") private List<Person> person74List;

  public List<Person> getPerson74List()
  { 
    log.info("getPerson74List");
    return person74List;
  }

  @Factory("person74List") public void initPerson74List()
  { 
    log.info("initPerson74List");
    person74List = em.createQuery("from " + "Person").getResultList();
  }

  @DataModel("project64List") private List<ResearchProject> project64List;

  public List<ResearchProject> getProject64List()
  { 
    log.info("getProject64List");
    return project64List;
  }

  @Factory("project64List") public void initProject64List()
  { 
    log.info("initProject64List");
    project64List = em.createQuery("from " + "ResearchProject").getResultList();
  }

  @DataModel("inProceedings3List") private List<InProceedings> inProceedings3List;

  public List<InProceedings> getInProceedings3List()
  { 
    log.info("getInProceedings3List");
    return inProceedings3List;
  }

  @Factory("inProceedings3List") public void initInProceedings3List()
  { 
    log.info("initInProceedings3List");
    inProceedings3List = em.createQuery("from " + "InProceedings").getResultList();
  }
}