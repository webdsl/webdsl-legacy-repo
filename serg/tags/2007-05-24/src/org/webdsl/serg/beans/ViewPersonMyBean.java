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

@Stateful @Name("viewPersonMy") public class ViewPersonMyBean  implements ViewPersonMyBeanInterface
{ 
  @Logger private Log log;

  @PersistenceContext(type = EXTENDED) private EntityManager em;

  @In private FacesMessages facesMessages;

  @Create @Begin public void initialize()
  { 
    if(personId == null)
    { 
      log.debug("No " + "personId" + " defined, creating new " + "Person");
      person = new Person();
    }
    else
    { 
      person = em.find(Person.class, personId);
    }
    initPrList();
    initPubList();
  }

  @Destroy @Remove public void destroy()
  { }

  @RequestParameter("person") private Long personId;

  private Person person;

  public void setPerson(Person person)
  { 
    this.person = person;
  }

  public Person getPerson()
  { 
    return person;
  }

  @DataModel("prList") private List<ResearchProject> prList;

  public List<ResearchProject> getPrList()
  { 
    return prList;
  }

  @Factory("prList") public void initPrList()
  { 
    log.info("initPrList");
    prList = em.createQuery("from " + "ResearchProject").getResultList();
  }

  @DataModel("pubList") private List<Publication> pubList;

  public List<Publication> getPubList()
  { 
    return pubList;
  }

  @Factory("pubList") public void initPubList()
  { 
    log.info("initPubList");
    pubList = em.createQuery("from " + "Publication").getResultList();
  }
}