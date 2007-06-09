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
    initPerson30List();
    initPerson1030List();
    initProject1130List();
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

  public void setPerson12(Person person31)
  { 
    presentation.setSpeaker(person31);
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

  private String newPerson30;

  public void setNewPerson30(String p)
  { 
    newPerson30 = p;
  }

  public String getNewPerson30()
  { 
    return newPerson30;
  }

  public void selectPerson30(ValueChangeEvent event)
  { 
    log.info("selectPerson30" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Person person30 = em.find(Person.class, id);
      setPerson12(person30);
    }
  }

  @DataModel("person30List") private Map<String, String> person30List;

  public Map<String, String> getPerson30List()
  { 
    return person30List;
  }

  @Factory("person30List") public void initPerson30List()
  { 
    log.info("initPerson30List");
    person30List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Person").getResultList())
    { 
      Person p = (Person)o;
      person30List.put(p.getName(), p.getId().toString());
    }
  }

  @DataModel("person1030List") private List<Person> person1030List;

  public List<Person> getPerson1030List()
  { 
    log.info("getPerson1030List");
    return person1030List;
  }

  @Factory("person1030List") public void initPerson1030List()
  { 
    log.info("initPerson1030List");
    person1030List = em.createQuery("from " + "Person").getResultList();
  }

  @DataModel("project1130List") private List<ResearchProject> project1130List;

  public List<ResearchProject> getProject1130List()
  { 
    log.info("getProject1130List");
    return project1130List;
  }

  @Factory("project1130List") public void initProject1130List()
  { 
    log.info("initProject1130List");
    project1130List = em.createQuery("from " + "ResearchProject").getResultList();
  }
}