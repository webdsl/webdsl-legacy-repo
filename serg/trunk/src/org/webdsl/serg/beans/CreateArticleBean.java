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
    Article var55 = new Article();
    article = var55;
    Person var56 = new Person();
    newAuthor7 = var56;
    initPerson186List();
    initResearchProject43List();
    initJournal7List();
    initPerson80List();
    initProject70List();
  }

  @Destroy @Remove public void destroy()
  { }

  public void removePerson9(Person person185)
  { 
    this.getArticle().getAuthors().remove(person185);
  }

  public void addPerson9(Person person185)
  { 
    this.getArticle().getAuthors().add(person185);
  }

  public void addNewAuthor()
  { 
    this.getArticle().getAuthors().add(this.getNewAuthor7());
    Person var54 = new Person();
    newAuthor7 = var54;
  }

  public void removeResearchProject11(ResearchProject researchProject42)
  { 
    this.getArticle().getProjects().remove(researchProject42);
  }

  public void addResearchProject11(ResearchProject researchProject42)
  { 
    this.getArticle().getProjects().add(researchProject42);
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

  private String newPerson186;

  public void setNewPerson186(String p)
  { 
    newPerson186 = p;
  }

  public String getNewPerson186()
  { 
    return newPerson186;
  }

  public void selectPerson186(ValueChangeEvent event)
  { 
    log.info("selectPerson186" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Person person186 = em.find(Person.class, id);
      addPerson9(person186);
    }
  }

  @DataModel("person186List") private Map<String, String> person186List;

  public Map<String, String> getPerson186List()
  { 
    return person186List;
  }

  @Factory("person186List") public void initPerson186List()
  { 
    log.info("initPerson186List");
    person186List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Person").getResultList())
    { 
      Person p = (Person)o;
      person186List.put(p.getName(), p.getId().toString());
    }
  }

  private String newResearchProject43;

  public void setNewResearchProject43(String p)
  { 
    newResearchProject43 = p;
  }

  public String getNewResearchProject43()
  { 
    return newResearchProject43;
  }

  public void selectResearchProject43(ValueChangeEvent event)
  { 
    log.info("selectResearchProject43" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      ResearchProject researchProject43 = em.find(ResearchProject.class, id);
      addResearchProject11(researchProject43);
    }
  }

  @DataModel("researchProject43List") private Map<String, String> researchProject43List;

  public Map<String, String> getResearchProject43List()
  { 
    return researchProject43List;
  }

  @Factory("researchProject43List") public void initResearchProject43List()
  { 
    log.info("initResearchProject43List");
    researchProject43List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "ResearchProject").getResultList())
    { 
      ResearchProject p = (ResearchProject)o;
      researchProject43List.put(p.getName(), p.getId().toString());
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

  @DataModel("person80List") private List<Person> person80List;

  public List<Person> getPerson80List()
  { 
    log.info("getPerson80List");
    return person80List;
  }

  @Factory("person80List") public void initPerson80List()
  { 
    log.info("initPerson80List");
    person80List = em.createQuery("from " + "Person").getResultList();
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