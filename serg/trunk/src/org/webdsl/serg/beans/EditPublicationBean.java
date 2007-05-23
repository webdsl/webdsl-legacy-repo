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

@Stateful @Name("editPublication") public class EditPublicationBean  implements EditPublicationBeanInterface
{ 
  @Logger private Log log;

  @PersistenceContext(type = EXTENDED) private EntityManager em;

  @In private FacesMessages facesMessages;

  @Create @Begin public void initialize()
  { 
    if(publicationId == null)
    { 
      log.debug("No " + "publicationId" + " defined, creating new " + "Publication");
      publication = new Publication();
    }
    else
    { 
      publication = em.find(Publication.class, publicationId);
    }
    initPerson3List();
    initResearchProject1List();
  }

  @Destroy @Remove public void destroy()
  { }

  @RequestParameter("publication") private Long publicationId;

  private Publication publication;

  public void setPublication(Publication publication)
  { 
    this.publication = publication;
  }

  public Publication getPublication()
  { 
    return publication;
  }

  public void removePerson0(Person person2)
  { 
    this.getPublication().getAuthors().remove(person2);
  }

  public void addPerson0(Person person2)
  { 
    this.getPublication().getAuthors().add(person2);
  }

  public void removeResearchProject0(ResearchProject researchProject0)
  { 
    this.getPublication().getProjectsList().remove(researchProject0);
  }

  public void addResearchProject0(ResearchProject researchProject0)
  { 
    this.getPublication().getProjectsList().add(researchProject0);
  }

  @End public String cancel()
  { 
    return "/" + "viewPublication" + ".seam?" + ("publication" + "=" + publication.getId() + "");
  }

  @End public String save()
  { 
    em.persist(this.getPublication());
    return "/" + "viewPublication" + ".seam?" + ("publication" + "=" + publication.getId() + "");
  }

  private String newPerson3;

  public void setNewPerson3(String p)
  { 
    newPerson3 = p;
  }

  public String getNewPerson3()
  { 
    return newPerson3;
  }

  public String selectPerson3()
  { 
    log.info("selectPerson3" + " " + newPerson3);
    Person person3 = em.find(Person.class, new Long(newPerson3));
    addPerson0(person3);
    return null;
  }

  @DataModel("person3List") private Map<String, String> person3List;

  public Map<String, String> getPerson3List()
  { 
    return person3List;
  }

  @Factory("person3List") public void initPerson3List()
  { 
    log.info("initPerson3List");
    person3List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Person").getResultList())
    { 
      Person p = (Person)o;
      person3List.put(p.getName(), p.getId().toString());
    }
  }

  private String newResearchProject1;

  public void setNewResearchProject1(String p)
  { 
    newResearchProject1 = p;
  }

  public String getNewResearchProject1()
  { 
    return newResearchProject1;
  }

  public String selectResearchProject1()
  { 
    log.info("selectResearchProject1" + " " + newResearchProject1);
    ResearchProject researchProject1 = em.find(ResearchProject.class, new Long(newResearchProject1));
    addResearchProject0(researchProject1);
    return null;
  }

  @DataModel("researchProject1List") private Map<String, String> researchProject1List;

  public Map<String, String> getResearchProject1List()
  { 
    return researchProject1List;
  }

  @Factory("researchProject1List") public void initResearchProject1List()
  { 
    log.info("initResearchProject1List");
    researchProject1List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "ResearchProject").getResultList())
    { 
      ResearchProject p = (ResearchProject)o;
      researchProject1List.put(p.getName(), p.getId().toString());
    }
  }
}