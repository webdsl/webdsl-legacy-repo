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

@Stateful @Name("allNews") public class AllNewsBean  implements AllNewsBeanInterface
{ 
  @Logger private Log log;

  @PersistenceContext(type = EXTENDED) private EntityManager em;

  @In private FacesMessages facesMessages;

  @Create @Begin public void initialize()
  { 
    log.info("allNews" + ".initalize()");
    initPerson102List();
    initProject91List();
    initNews4List();
  }

  @Destroy @Remove public void destroy()
  { }

  public void removeNews(News news5)
  { 
    em.remove(news5);
  }

  @DataModel("person102List") private List<Person> person102List;

  public List<Person> getPerson102List()
  { 
    log.info("getPerson102List");
    return person102List;
  }

  @Factory("person102List") public void initPerson102List()
  { 
    log.info("initPerson102List");
    person102List = em.createQuery("from " + "Person").getResultList();
  }

  @DataModel("project91List") private List<ResearchProject> project91List;

  public List<ResearchProject> getProject91List()
  { 
    log.info("getProject91List");
    return project91List;
  }

  @Factory("project91List") public void initProject91List()
  { 
    log.info("initProject91List");
    project91List = em.createQuery("from " + "ResearchProject").getResultList();
  }

  @DataModel("news4List") private List<News> news4List;

  public List<News> getNews4List()
  { 
    log.info("getNews4List");
    return news4List;
  }

  @Factory("news4List") public void initNews4List()
  { 
    log.info("initNews4List");
    news4List = em.createQuery("from " + "News").getResultList();
  }
}