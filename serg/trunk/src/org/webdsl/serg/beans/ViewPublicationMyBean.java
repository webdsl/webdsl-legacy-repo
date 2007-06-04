package org.webdsl.serg.beans;

import java.util.*;
import java.io.Serializable;
import static javax.persistence.PersistenceContextType.EXTENDED;
import javax.persistence.PersistenceContext;
import javax.persistence.EntityManager;
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

@Stateful @Name("viewPublicationMy") public class ViewPublicationMyBean  implements ViewPublicationMyBeanInterface
{ 
  @Logger private Log log;

  @PersistenceContext(type = EXTENDED) private EntityManager em;

  @In private FacesMessages facesMessages;

  @Create @Begin public void initialize()
  { 
    log.info("viewPublicationMy" + ".initalize()");
    if(pubId == null)
    { 
      log.info("No " + "pubId" + " defined, creating new " + "Publication");
      pub = new Publication();
    }
    else
    { 
      pub = em.find(Publication.class, pubId);
    }
    initPerson10List();
  }

  @Destroy @Remove public void destroy()
  { }

  @RequestParameter("pub") private Long pubId;

  private Publication pub;

  public void setPub(Publication pub)
  { 
    log.info("setPub");
    this.pub = pub;
  }

  public Publication getPub()
  { 
    log.info("getPub");
    return pub;
  }

  @End public String deletePublication(Publication pub)
  { 
    em.remove(this.getPub());
    return "/" + "home" + ".seam?" + "";
  }

  @DataModel("person10List") private List<Person> person10List;

  public List<Person> getPerson10List()
  { 
    log.info("getPerson10List");
    return person10List;
  }

  @Factory("person10List") public void initPerson10List()
  { 
    log.info("initPerson10List");
    person10List = em.createQuery("from " + "Person").getResultList();
  }
}