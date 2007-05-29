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

@Stateful @Name("home") public class HomeBean  implements HomeBeanInterface
{ 
  @Logger private Log log;

  @PersistenceContext(type = EXTENDED) private EntityManager em;

  @In private FacesMessages facesMessages;

  @Create @Begin public void initialize()
  { 
    initPubList();
    initPersList();
  }

  @Destroy @Remove public void destroy()
  { }

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

  @DataModel("persList") private List<Person> persList;

  public List<Person> getPersList()
  { 
    return persList;
  }

  @Factory("persList") public void initPersList()
  { 
    log.info("initPersList");
    persList = em.createQuery("from " + "Person").getResultList();
  }
}