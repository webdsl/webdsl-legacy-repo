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

@Stateful @Name("editPresentation") public class EditPresentationBean  implements EditPresentationBeanInterface
{ 
  @Logger private Log log;

  @PersistenceContext(type = EXTENDED) private EntityManager em;

  @In private FacesMessages facesMessages;

  @Create @Begin public void initialize()
  { 
    log.info("editPresentation" + ".initalize()");
    if(presentationId == null)
    { 
      log.info("No " + "presentationId" + " defined, creating new " + "Presentation");
      presentation = new Presentation();
    }
    else
    { 
      presentation = em.find(Presentation.class, presentationId);
    }
    initPerson148List();
    initResearchProject15List();
    initPerson53List();
    initProject48List();
  }

  @Destroy @Remove public void destroy()
  { }

  @RequestParameter("presentation") private Long presentationId;

  private Presentation presentation;

  public void setPresentation(Presentation presentation)
  { 
    log.info("setPresentation");
    this.presentation = presentation;
  }

  public Presentation getPresentation()
  { 
    log.info("getPresentation");
    return presentation;
  }

  public void setPerson8(Person person149)
  { 
    presentation.setSpeaker(person149);
  }

  public void removeResearchProject2(ResearchProject researchProject14)
  { 
    this.getPresentation().getProjects().remove(researchProject14);
  }

  public void addResearchProject2(ResearchProject researchProject14)
  { 
    this.getPresentation().getProjects().add(researchProject14);
  }

  @End public String cancel()
  { 
    return "/" + "viewPresentation" + ".seam?" + ("presentation" + "=" + presentation.getId() + "");
  }

  @End public String save()
  { 
    em.persist(this.getPresentation());
    return "/" + "viewPresentation" + ".seam?" + ("presentation" + "=" + presentation.getId() + "");
  }

  private String newPerson148;

  public void setNewPerson148(String p)
  { 
    newPerson148 = p;
  }

  public String getNewPerson148()
  { 
    return newPerson148;
  }

  public void selectPerson148(ValueChangeEvent event)
  { 
    log.info("selectPerson148" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Person person148 = em.find(Person.class, id);
      setPerson8(person148);
    }
  }

  @DataModel("person148List") private Map<String, String> person148List;

  public Map<String, String> getPerson148List()
  { 
    return person148List;
  }

  @Factory("person148List") public void initPerson148List()
  { 
    log.info("initPerson148List");
    person148List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Person").getResultList())
    { 
      Person p = (Person)o;
      person148List.put(p.getName(), p.getId().toString());
    }
  }

  private String newResearchProject15;

  public void setNewResearchProject15(String p)
  { 
    newResearchProject15 = p;
  }

  public String getNewResearchProject15()
  { 
    return newResearchProject15;
  }

  public void selectResearchProject15(ValueChangeEvent event)
  { 
    log.info("selectResearchProject15" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      ResearchProject researchProject15 = em.find(ResearchProject.class, id);
      addResearchProject2(researchProject15);
    }
  }

  @DataModel("researchProject15List") private Map<String, String> researchProject15List;

  public Map<String, String> getResearchProject15List()
  { 
    return researchProject15List;
  }

  @Factory("researchProject15List") public void initResearchProject15List()
  { 
    log.info("initResearchProject15List");
    researchProject15List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "ResearchProject").getResultList())
    { 
      ResearchProject p = (ResearchProject)o;
      researchProject15List.put(p.getName(), p.getId().toString());
    }
  }

  @DataModel("person53List") private List<Person> person53List;

  public List<Person> getPerson53List()
  { 
    log.info("getPerson53List");
    return person53List;
  }

  @Factory("person53List") public void initPerson53List()
  { 
    log.info("initPerson53List");
    person53List = em.createQuery("from " + "Person").getResultList();
  }

  @DataModel("project48List") private List<ResearchProject> project48List;

  public List<ResearchProject> getProject48List()
  { 
    log.info("getProject48List");
    return project48List;
  }

  @Factory("project48List") public void initProject48List()
  { 
    log.info("initProject48List");
    project48List = em.createQuery("from " + "ResearchProject").getResultList();
  }
}