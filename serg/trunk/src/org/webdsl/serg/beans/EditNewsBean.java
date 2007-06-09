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

@Stateful @Name("editNews") public class EditNewsBean  implements EditNewsBeanInterface
{ 
  @Logger private Log log;

  @PersistenceContext(type = EXTENDED) private EntityManager em;

  @In private FacesMessages facesMessages;

  @Create @Begin public void initialize()
  { 
    log.info("editNews" + ".initalize()");
    if(newsId == null)
    { 
      log.info("No " + "newsId" + " defined, creating new " + "News");
      news = new News();
    }
    else
    { 
      news = em.find(News.class, newsId);
    }
    initPerson1044List();
    initProject1144List();
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

  @End public String cancel()
  { 
    return "/" + "viewNews" + ".seam?" + ("news" + "=" + news.getId() + "");
  }

  @End public String save()
  { 
    em.persist(this.getNews());
    return "/" + "viewNews" + ".seam?" + ("news" + "=" + news.getId() + "");
  }

  @DataModel("person1044List") private List<Person> person1044List;

  public List<Person> getPerson1044List()
  { 
    log.info("getPerson1044List");
    return person1044List;
  }

  @Factory("person1044List") public void initPerson1044List()
  { 
    log.info("initPerson1044List");
    person1044List = em.createQuery("from " + "Person").getResultList();
  }

  @DataModel("project1144List") private List<ResearchProject> project1144List;

  public List<ResearchProject> getProject1144List()
  { 
    log.info("getProject1144List");
    return project1144List;
  }

  @Factory("project1144List") public void initProject1144List()
  { 
    log.info("initProject1144List");
    project1144List = em.createQuery("from " + "ResearchProject").getResultList();
  }
}