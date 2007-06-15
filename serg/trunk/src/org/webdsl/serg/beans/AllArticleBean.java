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

@Stateful @Name("allArticle") public class AllArticleBean  implements AllArticleBeanInterface
{ 
  @Logger private Log log;

  @PersistenceContext(type = EXTENDED) private EntityManager em;

  @In private FacesMessages facesMessages;

  @Create @Begin public void initialize()
  { 
    log.info("allArticle" + ".initalize()");
    initPerson77List();
    initProject72List();
    initArticle3List();
  }

  @Destroy @Remove public void destroy()
  { }

  public void removeArticle(Article article4)
  { 
    em.remove(article4);
  }

  @DataModel("person77List") private List<Person> person77List;

  public List<Person> getPerson77List()
  { 
    log.info("getPerson77List");
    return person77List;
  }

  @Factory("person77List") public void initPerson77List()
  { 
    log.info("initPerson77List");
    person77List = em.createQuery("from " + "Person").getResultList();
  }

  @DataModel("project72List") private List<ResearchProject> project72List;

  public List<ResearchProject> getProject72List()
  { 
    log.info("getProject72List");
    return project72List;
  }

  @Factory("project72List") public void initProject72List()
  { 
    log.info("initProject72List");
    project72List = em.createQuery("from " + "ResearchProject").getResultList();
  }

  @DataModel("article3List") private List<Article> article3List;

  public List<Article> getArticle3List()
  { 
    log.info("getArticle3List");
    return article3List;
  }

  @Factory("article3List") public void initArticle3List()
  { 
    log.info("initArticle3List");
    article3List = em.createQuery("from " + "Article").getResultList();
  }
}