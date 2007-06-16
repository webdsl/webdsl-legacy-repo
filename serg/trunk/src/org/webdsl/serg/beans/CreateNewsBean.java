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

@Stateful @Name("createNews") public class CreateNewsBean  implements CreateNewsBeanInterface
{ 
  @Logger private Log log;

  @PersistenceContext(type = EXTENDED) private EntityManager em;

  @In private FacesMessages facesMessages;

  @Create @Begin public void initialize()
  { 
    log.info("createNews" + ".initalize()");
    News var64 = new News();
    news = var64;
    initPerson95List();
    initProject85List();
  }

  @Destroy @Remove public void destroy()
  { }

  @End public String cancel()
  { 
    return "/" + "home" + ".seam?" + "";
  }

  @End public String save()
  { 
    em.persist(this.getNews());
    return "/" + "viewNews" + ".seam?" + ("news" + "=" + news.getId() + "");
  }

  @DataModel("person95List") private List<Person> person95List;

  public List<Person> getPerson95List()
  { 
    log.info("getPerson95List");
    return person95List;
  }

  @Factory("person95List") public void initPerson95List()
  { 
    log.info("initPerson95List");
    person95List = em.createQuery("from " + "Person").getResultList();
  }

  @DataModel("project85List") private List<ResearchProject> project85List;

  public List<ResearchProject> getProject85List()
  { 
    log.info("getProject85List");
    return project85List;
  }

  @Factory("project85List") public void initProject85List()
  { 
    log.info("initProject85List");
    project85List = em.createQuery("from " + "ResearchProject").getResultList();
  }

  private News news;

  public News getNews()
  { 
    log.info("getNews");
    return news;
  }

  public void setNews(News news)
  { 
    log.info("setNews");
    this.news = news;
  }
}