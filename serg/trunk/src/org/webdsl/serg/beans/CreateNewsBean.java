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
    News var33 = new News();
    news = var33;
    initPerson1045List();
    initProject1145List();
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

  @DataModel("person1045List") private List<Person> person1045List;

  public List<Person> getPerson1045List()
  { 
    log.info("getPerson1045List");
    return person1045List;
  }

  @Factory("person1045List") public void initPerson1045List()
  { 
    log.info("initPerson1045List");
    person1045List = em.createQuery("from " + "Person").getResultList();
  }

  @DataModel("project1145List") private List<ResearchProject> project1145List;

  public List<ResearchProject> getProject1145List()
  { 
    log.info("getProject1145List");
    return project1145List;
  }

  @Factory("project1145List") public void initProject1145List()
  { 
    log.info("initProject1145List");
    project1145List = em.createQuery("from " + "ResearchProject").getResultList();
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