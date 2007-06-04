package org.webdsl.serg.beans;

import java.util.*;
import java.io.Serializable;
import static javax.persistence.PersistenceContextType.EXTENDED;
import javax.persistence.PersistenceContext;
import javax.persistence.EntityManager;
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

@Stateful @Name("createResearchProject") public class CreateResearchProjectBean  implements CreateResearchProjectBeanInterface
{ 
  @Logger private Log log;

  @PersistenceContext(type = EXTENDED) private EntityManager em;

  @In private FacesMessages facesMessages;

  @Create @Begin public void initialize()
  { 
    log.info("createResearchProject" + ".initalize()");
    ResearchProject var35 = new ResearchProject();
    researchProject = var35;
    initPerson12List();
    initProposal0List();
    initPublication3List();
    initPerson10List();
  }

  @Destroy @Remove public void destroy()
  { }

  public void removePerson4(Person person11)
  { 
    this.getResearchProject().getMembersList().remove(person11);
  }

  public void addPerson4(Person person11)
  { 
    this.getResearchProject().getMembersList().add(person11);
  }

  public void setPublication1(Publication proposal1)
  { 
    researchProject.setProposal(proposal1);
  }

  public void removePublication1(Publication publication2)
  { 
    this.getResearchProject().getPublicationsList().remove(publication2);
  }

  public void addPublication1(Publication publication2)
  { 
    this.getResearchProject().getPublicationsList().add(publication2);
  }

  @End public String cancel()
  { 
    return "/" + "home" + ".seam?" + "";
  }

  @End public String save()
  { 
    em.persist(this.getResearchProject());
    return "/" + "viewResearchProject" + ".seam?" + ("researchProject" + "=" + researchProject.getId() + "");
  }

  private String newPerson12;

  public void setNewPerson12(String p)
  { 
    newPerson12 = p;
  }

  public String getNewPerson12()
  { 
    return newPerson12;
  }

  public String selectPerson12()
  { 
    log.info("selectPerson12" + " " + newPerson12);
    Person person12 = em.find(Person.class, new Long(newPerson12));
    addPerson4(person12);
    return null;
  }

  @DataModel("person12List") private Map<String, String> person12List;

  public Map<String, String> getPerson12List()
  { 
    return person12List;
  }

  @Factory("person12List") public void initPerson12List()
  { 
    log.info("initPerson12List");
    person12List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Person").getResultList())
    { 
      Person p = (Person)o;
      person12List.put(p.getName(), p.getId().toString());
    }
  }

  private String newProposal0;

  public void setNewProposal0(String p)
  { 
    newProposal0 = p;
  }

  public String getNewProposal0()
  { 
    return newProposal0;
  }

  public String selectProposal0()
  { 
    log.info("selectProposal0" + " " + newProposal0);
    Publication proposal0 = em.find(Publication.class, new Long(newProposal0));
    setPublication1(proposal0);
    return null;
  }

  @DataModel("proposal0List") private Map<String, String> proposal0List;

  public Map<String, String> getProposal0List()
  { 
    return proposal0List;
  }

  @Factory("proposal0List") public void initProposal0List()
  { 
    log.info("initProposal0List");
    proposal0List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Publication").getResultList())
    { 
      Publication p = (Publication)o;
      proposal0List.put(p.getName(), p.getId().toString());
    }
  }

  private String newPublication3;

  public void setNewPublication3(String p)
  { 
    newPublication3 = p;
  }

  public String getNewPublication3()
  { 
    return newPublication3;
  }

  public String selectPublication3()
  { 
    log.info("selectPublication3" + " " + newPublication3);
    Publication publication3 = em.find(Publication.class, new Long(newPublication3));
    addPublication1(publication3);
    return null;
  }

  @DataModel("publication3List") private Map<String, String> publication3List;

  public Map<String, String> getPublication3List()
  { 
    return publication3List;
  }

  @Factory("publication3List") public void initPublication3List()
  { 
    log.info("initPublication3List");
    publication3List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Publication").getResultList())
    { 
      Publication p = (Publication)o;
      publication3List.put(p.getName(), p.getId().toString());
    }
  }

  @DataModel("person10List") private List<Person> person10List;

  public List<Person> getPerson10List()
  { 
    log.info("getPerson10List");
    return person10List;
  }

  @Factory("person10List") public void initPerson10List()
  { 
    log.info("initPerson10List");
    person10List = em.createQuery("from " + "Person").getResultList();
  }

  private ResearchProject researchProject;

  public ResearchProject getResearchProject()
  { 
    log.info("getResearchProject");
    return researchProject;
  }

  public void setResearchProject(ResearchProject researchProject)
  { 
    log.info("setResearchProject");
    this.researchProject = researchProject;
  }
}