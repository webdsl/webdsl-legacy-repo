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

@Stateful @Name("allUser") public class AllUserBean  implements AllUserBeanInterface
{ 
  @Logger private Log log;

  @PersistenceContext(type = EXTENDED) private EntityManager em;

  @In private FacesMessages facesMessages;

  @Create @Begin public void initialize()
  { 
    log.info("allUser" + ".initalize()");
    initPerson30List();
    initProject24List();
    initUser3List();
  }

  @Destroy @Remove public void destroy()
  { }

  public void removeUser(User user4)
  { 
    em.remove(user4);
  }

  @DataModel("person30List") private List<Person> person30List;

  public List<Person> getPerson30List()
  { 
    log.info("getPerson30List");
    return person30List;
  }

  @Factory("person30List") public void initPerson30List()
  { 
    log.info("initPerson30List");
    person30List = em.createQuery("from " + "Person").getResultList();
  }

  @DataModel("project24List") private List<ResearchProject> project24List;

  public List<ResearchProject> getProject24List()
  { 
    log.info("getProject24List");
    return project24List;
  }

  @Factory("project24List") public void initProject24List()
  { 
    log.info("initProject24List");
    project24List = em.createQuery("from " + "ResearchProject").getResultList();
  }

  @DataModel("user3List") private List<User> user3List;

  public List<User> getUser3List()
  { 
    log.info("getUser3List");
    return user3List;
  }

  @Factory("user3List") public void initUser3List()
  { 
    log.info("initUser3List");
    user3List = em.createQuery("from " + "User").getResultList();
  }
}