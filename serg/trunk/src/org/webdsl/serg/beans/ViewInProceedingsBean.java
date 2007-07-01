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

@Stateful @Name("viewInProceedings") public class ViewInProceedingsBean  implements ViewInProceedingsBeanInterface
{ 
  @Logger private Log log;

  @PersistenceContext(type = EXTENDED) private EntityManager em;

  @In private FacesMessages facesMessages;

  @Create @Begin public void initialize()
  { 
    log.info("viewInProceedings" + ".initalize()");
    if(inProceedingsId == null)
    { 
      log.info("No " + "inProceedingsId" + " defined, creating new " + "InProceedings");
      inProceedings = new InProceedings();
    }
    else
    { 
      inProceedings = em.find(InProceedings.class, inProceedingsId);
    }
    initPerson80List();
    initProject67List();
  }

  @Destroy @Remove public void destroy()
  { }

  @RequestParameter("inProceedings") private Long inProceedingsId;

  private InProceedings inProceedings;

  public void setInProceedings(InProceedings inProceedings)
  { 
    log.info("setInProceedings");
    this.inProceedings = inProceedings;
  }

  public InProceedings getInProceedings()
  { 
    log.info("getInProceedings");
    return inProceedings;
  }

  @End public String createNewPerson(Publication publication02, java.util.List<Person> authors3)
  { 
    Person var53 = new Person();
    Person person02 = var53;
    authors3.add(person02);
    em.persist(publication02);
    return "/" + "editPerson" + ".seam?" + ("person" + "=" + person02.getId() + "");
  }

  @End public String createNewResearchProject(Publication publication17, java.util.Set<ResearchProject> projects8)
  { 
    ResearchProject var54 = new ResearchProject();
    ResearchProject researchProject12 = var54;
    projects8.add(researchProject12);
    em.persist(publication17);
    return "/" + "editResearchProject" + ".seam?" + ("researchProject" + "=" + researchProject12.getId() + "");
  }

  @DataModel("person80List") private List<Person> person80List;

  public List<Person> getPerson80List()
  { 
    log.info("getPerson80List");
    return person80List;
  }

  @Factory("person80List") public void initPerson80List()
  { 
    log.info("initPerson80List");
    person80List = em.createQuery("from " + "Person").getResultList();
  }

  @DataModel("project67List") private List<ResearchProject> project67List;

  public List<ResearchProject> getProject67List()
  { 
    log.info("getProject67List");
    return project67List;
  }

  @Factory("project67List") public void initProject67List()
  { 
    log.info("initProject67List");
    project67List = em.createQuery("from " + "ResearchProject").getResultList();
  }
}