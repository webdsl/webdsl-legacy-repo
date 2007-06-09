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

@Stateful @Name("viewNews") public class ViewNewsBean  implements ViewNewsBeanInterface
{ 
  @Logger private Log log;

  @PersistenceContext(type = EXTENDED) private EntityManager em;

  @In private FacesMessages facesMessages;

  @Create @Begin public void initialize()
  { 
    log.info("viewNews" + ".initalize()");
    if(newsId == null)
    { 
      log.info("No " + "newsId" + " defined, creating new " + "News");
      news = new News();
    }
    else
    { 
      news = em.find(News.class, newsId);
    }
    initPerson1047List();
    initProject1147List();
  }

  @Destroy @Remove public void destroy()
  { }

  @RequestParameter("news") private Long newsId;

  private News news;

  public void setNews(News news)
  { 
    log.info("setNews");
    this.news = news;
  }

  public News getNews()
  { 
    log.info("getNews");
    return news;
  }

  @DataModel("person1047List") private List<Person> person1047List;

  public List<Person> getPerson1047List()
  { 
    log.info("getPerson1047List");
    return person1047List;
  }

  @Factory("person1047List") public void initPerson1047List()
  { 
    log.info("initPerson1047List");
    person1047List = em.createQuery("from " + "Person").getResultList();
  }

  @DataModel("project1147List") private List<ResearchProject> project1147List;

  public List<ResearchProject> getProject1147List()
  { 
    log.info("getProject1147List");
    return project1147List;
  }

  @Factory("project1147List") public void initProject1147List()
  { 
    log.info("initProject1147List");
    project1147List = em.createQuery("from " + "ResearchProject").getResultList();
  }
}