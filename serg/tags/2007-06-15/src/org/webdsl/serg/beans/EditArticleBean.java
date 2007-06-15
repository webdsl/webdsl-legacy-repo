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

@Stateful @Name("editArticle") public class EditArticleBean  implements EditArticleBeanInterface
{ 
  @Logger private Log log;

  @PersistenceContext(type = EXTENDED) private EntityManager em;

  @In private FacesMessages facesMessages;

  @Create @Begin public void initialize()
  { 
    log.info("editArticle" + ".initalize()");
    if(articleId == null)
    { 
      log.info("No " + "articleId" + " defined, creating new " + "Article");
      article = new Article();
    }
    else
    { 
      article = em.find(Article.class, articleId);
    }
    Person var51 = new Person();
    newAuthor6 = var51;
    initPerson174List();
    initResearchProject41List();
    initJournal5List();
    initPerson79List();
    initProject69List();
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

  public void removePerson8(Person person173)
  { 
    this.getArticle().getAuthors().remove(person173);
  }

  public void addPerson8(Person person173)
  { 
    this.getArticle().getAuthors().add(person173);
  }

  public void addNewAuthor()
  { 
    this.getArticle().getAuthors().add(this.getNewAuthor6());
    Person var50 = new Person();
    newAuthor6 = var50;
  }

  public void removeResearchProject10(ResearchProject researchProject40)
  { 
    this.getArticle().getProjects().remove(researchProject40);
  }

  public void addResearchProject10(ResearchProject researchProject40)
  { 
    this.getArticle().getProjects().add(researchProject40);
  }

  public void setJournal0(Journal journal6)
  { 
    article.setJournal(journal6);
  }

  @End public String cancel()
  { 
    return "/" + "viewArticle" + ".seam?" + ("article" + "=" + article.getId() + "");
  }

  @End public String save()
  { 
    em.persist(this.getArticle());
    return "/" + "viewArticle" + ".seam?" + ("article" + "=" + article.getId() + "");
  }

  private String newPerson174;

  public void setNewPerson174(String p)
  { 
    newPerson174 = p;
  }

  public String getNewPerson174()
  { 
    return newPerson174;
  }

  public void selectPerson174(ValueChangeEvent event)
  { 
    log.info("selectPerson174" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Person person174 = em.find(Person.class, id);
      addPerson8(person174);
    }
  }

  @DataModel("person174List") private Map<String, String> person174List;

  public Map<String, String> getPerson174List()
  { 
    return person174List;
  }

  @Factory("person174List") public void initPerson174List()
  { 
    log.info("initPerson174List");
    person174List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Person").getResultList())
    { 
      Person p = (Person)o;
      person174List.put(p.getName(), p.getId().toString());
    }
  }

  private String newResearchProject41;

  public void setNewResearchProject41(String p)
  { 
    newResearchProject41 = p;
  }

  public String getNewResearchProject41()
  { 
    return newResearchProject41;
  }

  public void selectResearchProject41(ValueChangeEvent event)
  { 
    log.info("selectResearchProject41" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      ResearchProject researchProject41 = em.find(ResearchProject.class, id);
      addResearchProject10(researchProject41);
    }
  }

  @DataModel("researchProject41List") private Map<String, String> researchProject41List;

  public Map<String, String> getResearchProject41List()
  { 
    return researchProject41List;
  }

  @Factory("researchProject41List") public void initResearchProject41List()
  { 
    log.info("initResearchProject41List");
    researchProject41List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "ResearchProject").getResultList())
    { 
      ResearchProject p = (ResearchProject)o;
      researchProject41List.put(p.getName(), p.getId().toString());
    }
  }

  private String newJournal5;

  public void setNewJournal5(String p)
  { 
    newJournal5 = p;
  }

  public String getNewJournal5()
  { 
    return newJournal5;
  }

  public void selectJournal5(ValueChangeEvent event)
  { 
    log.info("selectJournal5" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Journal journal5 = em.find(Journal.class, id);
      setJournal0(journal5);
    }
  }

  @DataModel("journal5List") private Map<String, String> journal5List;

  public Map<String, String> getJournal5List()
  { 
    return journal5List;
  }

  @Factory("journal5List") public void initJournal5List()
  { 
    log.info("initJournal5List");
    journal5List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Journal").getResultList())
    { 
      Journal p = (Journal)o;
      journal5List.put(p.getName(), p.getId().toString());
    }
  }

  @DataModel("person79List") private List<Person> person79List;

  public List<Person> getPerson79List()
  { 
    log.info("getPerson79List");
    return person79List;
  }

  @Factory("person79List") public void initPerson79List()
  { 
    log.info("initPerson79List");
    person79List = em.createQuery("from " + "Person").getResultList();
  }

  @DataModel("project69List") private List<ResearchProject> project69List;

  public List<ResearchProject> getProject69List()
  { 
    log.info("getProject69List");
    return project69List;
  }

  @Factory("project69List") public void initProject69List()
  { 
    log.info("initProject69List");
    project69List = em.createQuery("from " + "ResearchProject").getResultList();
  }

  private Person newAuthor6;

  public Person getNewAuthor6()
  { 
    log.info("getNewAuthor6");
    return newAuthor6;
  }

  public void setNewAuthor6(Person newAuthor6)
  { 
    log.info("setNewAuthor6");
    this.newAuthor6 = newAuthor6;
  }
}