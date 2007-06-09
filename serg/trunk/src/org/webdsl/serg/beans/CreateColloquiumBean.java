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

@Stateful @Name("createColloquium") public class CreateColloquiumBean  implements CreateColloquiumBeanInterface
{ 
  @Logger private Log log;

  @PersistenceContext(type = EXTENDED) private EntityManager em;

  @In private FacesMessages facesMessages;

  @Create @Begin public void initialize()
  { 
    log.info("createColloquium" + ".initalize()");
    Colloquium var23 = new Colloquium();
    colloquium = var23;
    initPresentation6List();
    initPerson26List();
    initPerson1028List();
    initProject1128List();
  }

  @Destroy @Remove public void destroy()
  { }

  public void removePresentation1(Presentation presentation5)
  { 
    this.getColloquium().getTalks().remove(presentation5);
  }

  public void addPresentation1(Presentation presentation5)
  { 
    this.getColloquium().getTalks().add(presentation5);
  }

  public void setPerson10(Person person27)
  { 
    colloquium.setContact(person27);
  }

  @End public String cancel()
  { 
    return "/" + "home" + ".seam?" + "";
  }

  @End public String save()
  { 
    em.persist(this.getColloquium());
    return "/" + "viewColloquium" + ".seam?" + ("colloquium" + "=" + colloquium.getId() + "");
  }

  private String newPresentation6;

  public void setNewPresentation6(String p)
  { 
    newPresentation6 = p;
  }

  public String getNewPresentation6()
  { 
    return newPresentation6;
  }

  public void selectPresentation6(ValueChangeEvent event)
  { 
    log.info("selectPresentation6" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Presentation presentation6 = em.find(Presentation.class, id);
      addPresentation1(presentation6);
    }
  }

  @DataModel("presentation6List") private Map<String, String> presentation6List;

  public Map<String, String> getPresentation6List()
  { 
    return presentation6List;
  }

  @Factory("presentation6List") public void initPresentation6List()
  { 
    log.info("initPresentation6List");
    presentation6List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Presentation").getResultList())
    { 
      Presentation p = (Presentation)o;
      presentation6List.put(p.getName(), p.getId().toString());
    }
  }

  private String newPerson26;

  public void setNewPerson26(String p)
  { 
    newPerson26 = p;
  }

  public String getNewPerson26()
  { 
    return newPerson26;
  }

  public void selectPerson26(ValueChangeEvent event)
  { 
    log.info("selectPerson26" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Person person26 = em.find(Person.class, id);
      setPerson10(person26);
    }
  }

  @DataModel("person26List") private Map<String, String> person26List;

  public Map<String, String> getPerson26List()
  { 
    return person26List;
  }

  @Factory("person26List") public void initPerson26List()
  { 
    log.info("initPerson26List");
    person26List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Person").getResultList())
    { 
      Person p = (Person)o;
      person26List.put(p.getName(), p.getId().toString());
    }
  }

  @DataModel("person1028List") private List<Person> person1028List;

  public List<Person> getPerson1028List()
  { 
    log.info("getPerson1028List");
    return person1028List;
  }

  @Factory("person1028List") public void initPerson1028List()
  { 
    log.info("initPerson1028List");
    person1028List = em.createQuery("from " + "Person").getResultList();
  }

  @DataModel("project1128List") private List<ResearchProject> project1128List;

  public List<ResearchProject> getProject1128List()
  { 
    log.info("getProject1128List");
    return project1128List;
  }

  @Factory("project1128List") public void initProject1128List()
  { 
    log.info("initProject1128List");
    project1128List = em.createQuery("from " + "ResearchProject").getResultList();
  }

  private Colloquium colloquium;

  public Colloquium getColloquium()
  { 
    log.info("getColloquium");
    return colloquium;
  }

  public void setColloquium(Colloquium colloquium)
  { 
    log.info("setColloquium");
    this.colloquium = colloquium;
  }
}