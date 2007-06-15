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
    initPerson97List();
    initProject87List();
    initNews4List();
  }

  @Destroy @Remove public void destroy()
  { }

  public void removeNews(News news5)
  { 
    em.remove(news5);
  }

  @DataModel("person97List") private List<Person> person97List;

  public List<Person> getPerson97List()
  { 
    log.info("getPerson97List");
    return person97List;
  }

  @Factory("person97List") public void initPerson97List()
  { 
    log.info("initPerson97List");
    person97List = em.createQuery("from " + "Person").getResultList();
  }

  @DataModel("project87List") private List<ResearchProject> project87List;

  public List<ResearchProject> getProject87List()
  { 
    log.info("getProject87List");
    return project87List;
  }

  @Factory("project87List") public void initProject87List()
  { 
    log.info("initProject87List");
    project87List = em.createQuery("from " + "ResearchProject").getResultList();
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