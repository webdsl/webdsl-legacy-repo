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
    initPerson96List();
    initProject85List();
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

  @DataModel("person96List") private List<Person> person96List;

  public List<Person> getPerson96List()
  { 
    log.info("getPerson96List");
    return person96List;
  }

  @Factory("person96List") public void initPerson96List()
  { 
    log.info("initPerson96List");
    person96List = em.createQuery("from " + "Person").getResultList();
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
}