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

@Stateful @Name("createArticle") public class CreateArticleBean  implements CreateArticleBeanInterface
{ 
  @Logger private Log log;

  @PersistenceContext(type = EXTENDED) private EntityManager em;

  @In private FacesMessages facesMessages;

  @Create @Begin public void initialize()
  { 
    log.info("createArticle" + ".initalize()");
    Article var60 = new Article();
    article = var60;
    Person var61 = new Person();
    newAuthor7 = var61;
    initPerson197List();
    initResearchProject50List();
    initJournal7List();
    initPerson85List();
    initProject74List();
  }

  @Destroy @Remove public void destroy()
  { }

  public void removePerson11(Person person196)
  { 
    this.getArticle().getAuthors().remove(person196);
  }

  public void addPerson11(Person person196)
  { 
    this.getArticle().getAuthors().add(person196);
  }

  public void addNewAuthor()
  { 
    this.getArticle().getAuthors().add(this.getNewAuthor7());
    Person var59 = new Person();
    newAuthor7 = var59;
  }

  public void removeResearchProject13(ResearchProject researchProject49)
  { 
    this.getArticle().getProjects().remove(researchProject49);
  }

  public void addResearchProject13(ResearchProject researchProject49)
  { 
    this.getArticle().getProjects().add(researchProject49);
  }

  public void setJournal1(Journal journal8)
  { 
    article.setJournal(journal8);
  }

  @End public String cancel()
  { 
    return "/" + "home" + ".seam?" + "";
  }

  @End public String save()
  { 
    em.persist(this.getArticle());
    return "/" + "viewArticle" + ".seam?" + ("article" + "=" + article.getId() + "");
  }

  private String newPerson197;

  public void setNewPerson197(String p)
  { 
    newPerson197 = p;
  }

  public String getNewPerson197()
  { 
    return newPerson197;
  }

  public void selectPerson197(ValueChangeEvent event)
  { 
    log.info("selectPerson197" + ": new value = " + " " + event.getNewValue());
    Person person197 = (Person)event.getNewValue();
  }

  @DataModel("person197List") private Map<String, String> person197List;

  public Map<String, String> getPerson197List()
  { 
    return person197List;
  }

  @Factory("person197List") public void initPerson197List()
  { 
    log.info("initPerson197List");
    person197List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Person").getResultList())
    { 
      Person p = (Person)o;
      person197List.put(p.getName(), p.getId().toString());
    }
  }

  private String newResearchProject50;

  public void setNewResearchProject50(String p)
  { 
    newResearchProject50 = p;
  }

  public String getNewResearchProject50()
  { 
    return newResearchProject50;
  }

  public void selectResearchProject50(ValueChangeEvent event)
  { 
    log.info("selectResearchProject50" + ": new value = " + " " + event.getNewValue());
    ResearchProject researchProject50 = (ResearchProject)event.getNewValue();
  }

  @DataModel("researchProject50List") private Map<String, String> researchProject50List;

  public Map<String, String> getResearchProject50List()
  { 
    return researchProject50List;
  }

  @Factory("researchProject50List") public void initResearchProject50List()
  { 
    log.info("initResearchProject50List");
    researchProject50List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "ResearchProject").getResultList())
    { 
      ResearchProject p = (ResearchProject)o;
      researchProject50List.put(p.getName(), p.getId().toString());
    }
  }

  private String newJournal7;

  public void setNewJournal7(String p)
  { 
    newJournal7 = p;
  }

  public String getNewJournal7()
  { 
    return newJournal7;
  }

  public void selectJournal7(ValueChangeEvent event)
  { 
    log.info("selectJournal7" + ": new value = " + " " + event.getNewValue());
    Journal journal7 = (Journal)event.getNewValue();
  }

  @DataModel("journal7List") private Map<String, String> journal7List;

  public Map<String, String> getJournal7List()
  { 
    return journal7List;
  }

  @Factory("journal7List") public void initJournal7List()
  { 
    log.info("initJournal7List");
    journal7List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Journal").getResultList())
    { 
      Journal p = (Journal)o;
      journal7List.put(p.getName(), p.getId().toString());
    }
  }

  @DataModel("person85List") private List<Person> person85List;

  public List<Person> getPerson85List()
  { 
    log.info("getPerson85List");
    return person85List;
  }

  @Factory("person85List") public void initPerson85List()
  { 
    log.info("initPerson85List");
    person85List = em.createQuery("from " + "Person").getResultList();
  }

  @DataModel("project74List") private List<ResearchProject> project74List;

  public List<ResearchProject> getProject74List()
  { 
    log.info("getProject74List");
    return project74List;
  }

  @Factory("project74List") public void initProject74List()
  { 
    log.info("initProject74List");
    project74List = em.createQuery("from " + "ResearchProject").getResultList();
  }

  private Article article;

  public Article getArticle()
  { 
    log.info("getArticle");
    return article;
  }

  public void setArticle(Article article)
  { 
    log.info("setArticle");
    this.article = article;
  }

  private Person newAuthor7;

  public Person getNewAuthor7()
  { 
    log.info("getNewAuthor7");
    return newAuthor7;
  }

  public void setNewAuthor7(Person newAuthor7)
  { 
    log.info("setNewAuthor7");
    this.newAuthor7 = newAuthor7;
  }
}