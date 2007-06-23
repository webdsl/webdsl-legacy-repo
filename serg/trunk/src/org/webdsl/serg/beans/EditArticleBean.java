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
    Person var58 = new Person();
    newAuthor6 = var58;
    initPerson195List();
    initResearchProject48List();
    initJournal5List();
    initPerson84List();
    initProject73List();
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

  public void removePerson10(Person person194)
  { 
    this.getArticle().getAuthors().remove(person194);
  }

  public void addPerson10(Person person194)
  { 
    this.getArticle().getAuthors().add(person194);
  }

  public void addNewAuthor()
  { 
    this.getArticle().getAuthors().add(this.getNewAuthor6());
    Person var57 = new Person();
    newAuthor6 = var57;
  }

  public void removeResearchProject12(ResearchProject researchProject47)
  { 
    this.getArticle().getProjects().remove(researchProject47);
  }

  public void addResearchProject12(ResearchProject researchProject47)
  { 
    this.getArticle().getProjects().add(researchProject47);
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

  private String newPerson195;

  public void setNewPerson195(String p)
  { 
    newPerson195 = p;
  }

  public String getNewPerson195()
  { 
    return newPerson195;
  }

  public void selectPerson195(ValueChangeEvent event)
  { 
    log.info("selectPerson195" + ": new value = " + " " + event.getNewValue());
    Person person195 = (Person)event.getNewValue();
  }

  @DataModel("person195List") private Map<String, String> person195List;

  public Map<String, String> getPerson195List()
  { 
    return person195List;
  }

  @Factory("person195List") public void initPerson195List()
  { 
    log.info("initPerson195List");
    person195List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Person").getResultList())
    { 
      Person p = (Person)o;
      person195List.put(p.getName(), p.getId().toString());
    }
  }

  private String newResearchProject48;

  public void setNewResearchProject48(String p)
  { 
    newResearchProject48 = p;
  }

  public String getNewResearchProject48()
  { 
    return newResearchProject48;
  }

  public void selectResearchProject48(ValueChangeEvent event)
  { 
    log.info("selectResearchProject48" + ": new value = " + " " + event.getNewValue());
    ResearchProject researchProject48 = (ResearchProject)event.getNewValue();
  }

  @DataModel("researchProject48List") private Map<String, String> researchProject48List;

  public Map<String, String> getResearchProject48List()
  { 
    return researchProject48List;
  }

  @Factory("researchProject48List") public void initResearchProject48List()
  { 
    log.info("initResearchProject48List");
    researchProject48List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "ResearchProject").getResultList())
    { 
      ResearchProject p = (ResearchProject)o;
      researchProject48List.put(p.getName(), p.getId().toString());
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
    Journal journal5 = (Journal)event.getNewValue();
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

  @DataModel("person84List") private List<Person> person84List;

  public List<Person> getPerson84List()
  { 
    log.info("getPerson84List");
    return person84List;
  }

  @Factory("person84List") public void initPerson84List()
  { 
    log.info("initPerson84List");
    person84List = em.createQuery("from " + "Person").getResultList();
  }

  @DataModel("project73List") private List<ResearchProject> project73List;

  public List<ResearchProject> getProject73List()
  { 
    log.info("getProject73List");
    return project73List;
  }

  @Factory("project73List") public void initProject73List()
  { 
    log.info("initProject73List");
    project73List = em.createQuery("from " + "ResearchProject").getResultList();
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