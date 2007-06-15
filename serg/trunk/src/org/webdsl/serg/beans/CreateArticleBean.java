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
    Article var46 = new Article();
    article = var46;
    Person var47 = new Person();
    newAuthor7 = var47;
    initPerson171List();
    initResearchProject32List();
    initJournal7List();
    initPerson75List();
    initProject70List();
  }

  @Destroy @Remove public void destroy()
  { }

  public void removePerson9(Person person170)
  { 
    this.getArticle().getAuthors().remove(person170);
  }

  public void addPerson9(Person person170)
  { 
    this.getArticle().getAuthors().add(person170);
  }

  public void addNewAuthor()
  { 
    this.getArticle().getAuthors().add(this.getNewAuthor7());
    Person var45 = new Person();
    newAuthor7 = var45;
  }

  public void removeResearchProject11(ResearchProject researchProject31)
  { 
    this.getArticle().getProjects().remove(researchProject31);
  }

  public void addResearchProject11(ResearchProject researchProject31)
  { 
    this.getArticle().getProjects().add(researchProject31);
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

  private String newPerson171;

  public void setNewPerson171(String p)
  { 
    newPerson171 = p;
  }

  public String getNewPerson171()
  { 
    return newPerson171;
  }

  public void selectPerson171(ValueChangeEvent event)
  { 
    log.info("selectPerson171" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Person person171 = em.find(Person.class, id);
      addPerson9(person171);
    }
  }

  @DataModel("person171List") private Map<String, String> person171List;

  public Map<String, String> getPerson171List()
  { 
    return person171List;
  }

  @Factory("person171List") public void initPerson171List()
  { 
    log.info("initPerson171List");
    person171List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Person").getResultList())
    { 
      Person p = (Person)o;
      person171List.put(p.getName(), p.getId().toString());
    }
  }

  private String newResearchProject32;

  public void setNewResearchProject32(String p)
  { 
    newResearchProject32 = p;
  }

  public String getNewResearchProject32()
  { 
    return newResearchProject32;
  }

  public void selectResearchProject32(ValueChangeEvent event)
  { 
    log.info("selectResearchProject32" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      ResearchProject researchProject32 = em.find(ResearchProject.class, id);
      addResearchProject11(researchProject32);
    }
  }

  @DataModel("researchProject32List") private Map<String, String> researchProject32List;

  public Map<String, String> getResearchProject32List()
  { 
    return researchProject32List;
  }

  @Factory("researchProject32List") public void initResearchProject32List()
  { 
    log.info("initResearchProject32List");
    researchProject32List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "ResearchProject").getResultList())
    { 
      ResearchProject p = (ResearchProject)o;
      researchProject32List.put(p.getName(), p.getId().toString());
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
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Journal journal7 = em.find(Journal.class, id);
      setJournal1(journal7);
    }
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

  @DataModel("person75List") private List<Person> person75List;

  public List<Person> getPerson75List()
  { 
    log.info("getPerson75List");
    return person75List;
  }

  @Factory("person75List") public void initPerson75List()
  { 
    log.info("initPerson75List");
    person75List = em.createQuery("from " + "Person").getResultList();
  }

  @DataModel("project70List") private List<ResearchProject> project70List;

  public List<ResearchProject> getProject70List()
  { 
    log.info("getProject70List");
    return project70List;
  }

  @Factory("project70List") public void initProject70List()
  { 
    log.info("initProject70List");
    project70List = em.createQuery("from " + "ResearchProject").getResultList();
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