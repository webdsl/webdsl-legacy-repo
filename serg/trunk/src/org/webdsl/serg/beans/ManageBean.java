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

@Stateful @Name("manage") public class ManageBean  implements ManageBeanInterface
{ 
  @Logger private Log log;

  @PersistenceContext(type = EXTENDED) private EntityManager em;

  @In private FacesMessages facesMessages;

  @Create @Begin public void initialize()
  { 
    log.info("manage" + ".initalize()");
    initPerson8List();
    initProject0List();
  }

  @Destroy @Remove public void destroy()
  { }

  @DataModel("person8List") private List<Person> person8List;

  public List<Person> getPerson8List()
  { 
    log.info("getPerson8List");
    return person8List;
  }

  @Factory("person8List") public void initPerson8List()
  { 
    log.info("initPerson8List");
    person8List = em.createQuery("from " + "Person").getResultList();
  }

  @DataModel("project0List") private List<ResearchProject> project0List;

  public List<ResearchProject> getProject0List()
  { 
    log.info("getProject0List");
    return project0List;
  }

  @Factory("project0List") public void initProject0List()
  { 
    log.info("initProject0List");
    project0List = em.createQuery("from " + "ResearchProject").getResultList();
  }
}