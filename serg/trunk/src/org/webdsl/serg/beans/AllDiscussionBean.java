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

@Stateful @Name("allDiscussion") public class AllDiscussionBean  implements AllDiscussionBeanInterface
{ 
  @Logger private Log log;

  @PersistenceContext(type = EXTENDED) private EntityManager em;

  @In private FacesMessages facesMessages;

  @Create @Begin public void initialize()
  { 
    log.info("allDiscussion" + ".initalize()");
    initPerson115List();
    initProject115List();
    initDiscussion3List();
  }

  @Destroy @Remove public void destroy()
  { }

  public void removeDiscussion(Discussion discussion4)
  { 
    em.remove(discussion4);
  }

  @DataModel("person115List") private List<Person> person115List;

  public List<Person> getPerson115List()
  { 
    log.info("getPerson115List");
    return person115List;
  }

  @Factory("person115List") public void initPerson115List()
  { 
    log.info("initPerson115List");
    person115List = em.createQuery("from " + "Person").getResultList();
  }

  @DataModel("project115List") private List<ResearchProject> project115List;

  public List<ResearchProject> getProject115List()
  { 
    log.info("getProject115List");
    return project115List;
  }

  @Factory("project115List") public void initProject115List()
  { 
    log.info("initProject115List");
    project115List = em.createQuery("from " + "ResearchProject").getResultList();
  }

  @DataModel("discussion3List") private List<Discussion> discussion3List;

  public List<Discussion> getDiscussion3List()
  { 
    log.info("getDiscussion3List");
    return discussion3List;
  }

  @Factory("discussion3List") public void initDiscussion3List()
  { 
    log.info("initDiscussion3List");
    discussion3List = em.createQuery("from " + "Discussion").getResultList();
  }
}