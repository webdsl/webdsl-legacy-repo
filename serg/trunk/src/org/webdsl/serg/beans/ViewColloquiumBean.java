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

@Stateful @Name("viewColloquium") public class ViewColloquiumBean  implements ViewColloquiumBeanInterface
{ 
  @Logger private Log log;

  @PersistenceContext(type = EXTENDED) private EntityManager em;

  @In private FacesMessages facesMessages;

  @Create @Begin public void initialize()
  { 
    log.info("viewColloquium" + ".initalize()");
    if(colloquiumId == null)
    { 
      log.info("No " + "colloquiumId" + " defined, creating new " + "Colloquium");
      colloquium = new Colloquium();
    }
    else
    { 
      colloquium = em.find(Colloquium.class, colloquiumId);
    }
    initPerson18List();
    initProject7List();
  }

  @Destroy @Remove public void destroy()
  { }

  @RequestParameter("colloquium") private Long colloquiumId;

  private Colloquium colloquium;

  public void setColloquium(Colloquium colloquium)
  { 
    log.info("setColloquium");
    this.colloquium = colloquium;
  }

  public Colloquium getColloquium()
  { 
    log.info("getColloquium");
    return colloquium;
  }

  @DataModel("person18List") private List<Person> person18List;

  public List<Person> getPerson18List()
  { 
    log.info("getPerson18List");
    return person18List;
  }

  @Factory("person18List") public void initPerson18List()
  { 
    log.info("initPerson18List");
    person18List = em.createQuery("from " + "Person").getResultList();
  }

  @DataModel("project7List") private List<ResearchProject> project7List;

  public List<ResearchProject> getProject7List()
  { 
    log.info("getProject7List");
    return project7List;
  }

  @Factory("project7List") public void initProject7List()
  { 
    log.info("initProject7List");
    project7List = em.createQuery("from " + "ResearchProject").getResultList();
  }
}