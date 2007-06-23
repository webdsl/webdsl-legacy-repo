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
    initPerson86List();
    initProject75List();
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

  @End public String createNewPerson(Publication publication03, java.util.List<Person> authors4)
  { 
    Person var62 = new Person();
    Person person03 = var62;
    authors4.add(person03);
    em.persist(publication03);
    return "/" + "editPerson" + ".seam?" + ("person" + "=" + person03.getId() + "");
  }

  @End public String createNewResearchProject(Publication publication110, java.util.Set<ResearchProject> projects9)
  { 
    ResearchProject var63 = new ResearchProject();
    ResearchProject researchProject13 = var63;
    projects9.add(researchProject13);
    em.persist(publication110);
    return "/" + "editResearchProject" + ".seam?" + ("researchProject" + "=" + researchProject13.getId() + "");
  }

  @DataModel("person86List") private List<Person> person86List;

  public List<Person> getPerson86List()
  { 
    log.info("getPerson86List");
    return person86List;
  }

  @Factory("person86List") public void initPerson86List()
  { 
    log.info("initPerson86List");
    person86List = em.createQuery("from " + "Person").getResultList();
  }

  @DataModel("project75List") private List<ResearchProject> project75List;

  public List<ResearchProject> getProject75List()
  { 
    log.info("getProject75List");
    return project75List;
  }

  @Factory("project75List") public void initProject75List()
  { 
    log.info("initProject75List");
    project75List = em.createQuery("from " + "ResearchProject").getResultList();
  }
}