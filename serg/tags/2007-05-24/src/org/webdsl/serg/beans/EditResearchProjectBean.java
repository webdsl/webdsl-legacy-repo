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

@Stateful @Name("editResearchProject") public class EditResearchProjectBean  implements EditResearchProjectBeanInterface
{ 
  @Logger private Log log;

  @PersistenceContext(type = EXTENDED) private EntityManager em;

  @In private FacesMessages facesMessages;

  @Create @Begin public void initialize()
  { 
    if(researchProjectId == null)
    { 
      log.debug("No " + "researchProjectId" + " defined, creating new " + "ResearchProject");
      researchProject = new ResearchProject();
    }
    else
    { 
      researchProject = em.find(ResearchProject.class, researchProjectId);
    }
    initPerson7List();
    initProposal0List();
    initPublication1List();
  }

  @Destroy @Remove public void destroy()
  { }

  @RequestParameter("researchProject") private Long researchProjectId;

  private ResearchProject researchProject;

  public void setResearchProject(ResearchProject researchProject)
  { 
    this.researchProject = researchProject;
  }

  public ResearchProject getResearchProject()
  { 
    return researchProject;
  }

  public void removePerson2(Person person6)
  { 
    this.getResearchProject().getMembersList().remove(person6);
  }

  public void addPerson2(Person person6)
  { 
    this.getResearchProject().getMembersList().add(person6);
  }

  public void setPublication1(Publication proposal1)
  { 
    researchProject.setProposal(proposal1);
  }

  public void removePublication0(Publication publication0)
  { 
    this.getResearchProject().getPublicationsList().remove(publication0);
  }

  public void addPublication0(Publication publication0)
  { 
    this.getResearchProject().getPublicationsList().add(publication0);
  }

  @End public String cancel()
  { 
    return "/" + "viewResearchProject" + ".seam?" + ("researchProject" + "=" + researchProject.getId() + "");
  }

  @End public String save()
  { 
    em.persist(this.getResearchProject());
    return "/" + "viewResearchProject" + ".seam?" + ("researchProject" + "=" + researchProject.getId() + "");
  }

  private String newPerson7;

  public void setNewPerson7(String p)
  { 
    newPerson7 = p;
  }

  public String getNewPerson7()
  { 
    return newPerson7;
  }

  public String selectPerson7()
  { 
    log.info("selectPerson7" + " " + newPerson7);
    Person person7 = em.find(Person.class, new Long(newPerson7));
    addPerson2(person7);
    return null;
  }

  @DataModel("person7List") private Map<String, String> person7List;

  public Map<String, String> getPerson7List()
  { 
    return person7List;
  }

  @Factory("person7List") public void initPerson7List()
  { 
    log.info("initPerson7List");
    person7List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Person").getResultList())
    { 
      Person p = (Person)o;
      person7List.put(p.getName(), p.getId().toString());
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

  private String newPublication1;

  public void setNewPublication1(String p)
  { 
    newPublication1 = p;
  }

  public String getNewPublication1()
  { 
    return newPublication1;
  }

  public String selectPublication1()
  { 
    log.info("selectPublication1" + " " + newPublication1);
    Publication publication1 = em.find(Publication.class, new Long(newPublication1));
    addPublication0(publication1);
    return null;
  }

  @DataModel("publication1List") private Map<String, String> publication1List;

  public Map<String, String> getPublication1List()
  { 
    return publication1List;
  }

  @Factory("publication1List") public void initPublication1List()
  { 
    log.info("initPublication1List");
    publication1List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Publication").getResultList())
    { 
      Publication p = (Publication)o;
      publication1List.put(p.getName(), p.getId().toString());
    }
  }
}