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

@Stateful @Name("allPresentation") public class AllPresentationBean  implements AllPresentationBeanInterface
{ 
  @Logger private Log log;

  @PersistenceContext(type = EXTENDED) private EntityManager em;

  @In private FacesMessages facesMessages;

  @Create @Begin public void initialize()
  { 
    log.info("allPresentation" + ".initalize()");
    initPerson65List();
    initProject52List();
    initPresentation5List();
  }

  @Destroy @Remove public void destroy()
  { }

  public void removePresentation(Presentation presentation6)
  { 
    em.remove(presentation6);
  }

  @DataModel("person65List") private List<Person> person65List;

  public List<Person> getPerson65List()
  { 
    log.info("getPerson65List");
    return person65List;
  }

  @Factory("person65List") public void initPerson65List()
  { 
    log.info("initPerson65List");
    person65List = em.createQuery("from " + "Person").getResultList();
  }

  @DataModel("project52List") private List<ResearchProject> project52List;

  public List<ResearchProject> getProject52List()
  { 
    log.info("getProject52List");
    return project52List;
  }

  @Factory("project52List") public void initProject52List()
  { 
    log.info("initProject52List");
    project52List = em.createQuery("from " + "ResearchProject").getResultList();
  }

  @DataModel("presentation5List") private List<Presentation> presentation5List;

  public List<Presentation> getPresentation5List()
  { 
    log.info("getPresentation5List");
    return presentation5List;
  }

  @Factory("presentation5List") public void initPresentation5List()
  { 
    log.info("initPresentation5List");
    presentation5List = em.createQuery("from " + "Presentation").getResultList();
  }
}