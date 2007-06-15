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

@Stateful @Name("allForum") public class AllForumBean  implements AllForumBeanInterface
{ 
  @Logger private Log log;

  @PersistenceContext(type = EXTENDED) private EntityManager em;

  @In private FacesMessages facesMessages;

  @Create @Begin public void initialize()
  { 
    log.info("allForum" + ".initalize()");
    initPerson117List();
    initProject112List();
    initForum4List();
  }

  @Destroy @Remove public void destroy()
  { }

  public void removeForum(Forum forum5)
  { 
    em.remove(forum5);
  }

  @DataModel("person117List") private List<Person> person117List;

  public List<Person> getPerson117List()
  { 
    log.info("getPerson117List");
    return person117List;
  }

  @Factory("person117List") public void initPerson117List()
  { 
    log.info("initPerson117List");
    person117List = em.createQuery("from " + "Person").getResultList();
  }

  @DataModel("project112List") private List<ResearchProject> project112List;

  public List<ResearchProject> getProject112List()
  { 
    log.info("getProject112List");
    return project112List;
  }

  @Factory("project112List") public void initProject112List()
  { 
    log.info("initProject112List");
    project112List = em.createQuery("from " + "ResearchProject").getResultList();
  }

  @DataModel("forum4List") private List<Forum> forum4List;

  public List<Forum> getForum4List()
  { 
    log.info("getForum4List");
    return forum4List;
  }

  @Factory("forum4List") public void initForum4List()
  { 
    log.info("initForum4List");
    forum4List = em.createQuery("from " + "Forum").getResultList();
  }
}