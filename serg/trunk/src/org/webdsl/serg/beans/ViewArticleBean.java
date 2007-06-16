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

@Stateful @Name("viewArticle") public class ViewArticleBean  implements ViewArticleBeanInterface
{ 
  @Logger private Log log;

  @PersistenceContext(type = EXTENDED) private EntityManager em;

  @In private FacesMessages facesMessages;

  @Create @Begin public void initialize()
  { 
    log.info("viewArticle" + ".initalize()");
    if(articleId == null)
    { 
      log.info("No " + "articleId" + " defined, creating new " + "Article");
      article = new Article();
    }
    else
    { 
      article = em.find(Article.class, articleId);
    }
    initPerson81List();
    initProject71List();
  }

  @Destroy @Remove public void destroy()
  { }

  @RequestParameter("article") private Long articleId;

  private Article article;

  public void setArticle(Article article)
  { 
    log.info("setArticle");
    this.article = article;
  }

  public Article getArticle()
  { 
    log.info("getArticle");
    return article;
  }

  @End public String createNewPerson(Publication publication03, java.util.List<Person> authors3)
  { 
    Person var57 = new Person();
    Person person03 = var57;
    authors3.add(person03);
    em.persist(publication03);
    return "/" + "editPerson" + ".seam?" + ("person" + "=" + person03.getId() + "");
  }

  @End public String createNewResearchProject(Publication publication110, java.util.Set<ResearchProject> projects8)
  { 
    ResearchProject var58 = new ResearchProject();
    ResearchProject researchProject13 = var58;
    projects8.add(researchProject13);
    em.persist(publication110);
    return "/" + "editResearchProject" + ".seam?" + ("researchProject" + "=" + researchProject13.getId() + "");
  }

  @DataModel("person81List") private List<Person> person81List;

  public List<Person> getPerson81List()
  { 
    log.info("getPerson81List");
    return person81List;
  }

  @Factory("person81List") public void initPerson81List()
  { 
    log.info("initPerson81List");
    person81List = em.createQuery("from " + "Person").getResultList();
  }

  @DataModel("project71List") private List<ResearchProject> project71List;

  public List<ResearchProject> getProject71List()
  { 
    log.info("getProject71List");
    return project71List;
  }

  @Factory("project71List") public void initProject71List()
  { 
    log.info("initProject71List");
    project71List = em.createQuery("from " + "ResearchProject").getResultList();
  }
}