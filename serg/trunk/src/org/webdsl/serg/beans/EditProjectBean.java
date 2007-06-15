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

@Stateful @Name("editProject") public class EditProjectBean  implements EditProjectBeanInterface
{ 
  @Logger private Log log;

  @PersistenceContext(type = EXTENDED) private EntityManager em;

  @In private FacesMessages facesMessages;

  @Create @Begin public void initialize()
  { 
    log.info("editProject" + ".initalize()");
    if(projectId == null)
    { 
      log.info("No " + "projectId" + " defined, creating new " + "Project");
      project = new Project();
    }
    else
    { 
      project = em.find(Project.class, projectId);
    }
    initIssue30List();
    initPerson193List();
    initPerson102List();
    initProject92List();
  }

  @Destroy @Remove public void destroy()
  { }

  @RequestParameter("project") private Long projectId;

  private Project project;

  public void setProject(Project project)
  { 
    log.info("setProject");
    this.project = project;
  }

  public Project getProject()
  { 
    log.info("getProject");
    return project;
  }

  public void removeIssue2(Issue issue29)
  { 
    this.getProject().getIssues().remove(issue29);
  }

  public void addIssue2(Issue issue29)
  { 
    this.getProject().getIssues().add(issue29);
  }

  public void removePerson16(Person person192)
  { 
    this.getProject().getAssigned().remove(person192);
  }

  public void addPerson16(Person person192)
  { 
    this.getProject().getAssigned().add(person192);
  }

  @End public String cancel()
  { 
    return "/" + "viewProject" + ".seam?" + ("project" + "=" + project.getId() + "");
  }

  @End public String save()
  { 
    em.persist(this.getProject());
    return "/" + "viewProject" + ".seam?" + ("project" + "=" + project.getId() + "");
  }

  private String newIssue30;

  public void setNewIssue30(String p)
  { 
    newIssue30 = p;
  }

  public String getNewIssue30()
  { 
    return newIssue30;
  }

  public void selectIssue30(ValueChangeEvent event)
  { 
    log.info("selectIssue30" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Issue issue30 = em.find(Issue.class, id);
      addIssue2(issue30);
    }
  }

  @DataModel("issue30List") private Map<String, String> issue30List;

  public Map<String, String> getIssue30List()
  { 
    return issue30List;
  }

  @Factory("issue30List") public void initIssue30List()
  { 
    log.info("initIssue30List");
    issue30List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Issue").getResultList())
    { 
      Issue p = (Issue)o;
      issue30List.put(p.getName(), p.getId().toString());
    }
  }

  private String newPerson193;

  public void setNewPerson193(String p)
  { 
    newPerson193 = p;
  }

  public String getNewPerson193()
  { 
    return newPerson193;
  }

  public void selectPerson193(ValueChangeEvent event)
  { 
    log.info("selectPerson193" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Person person193 = em.find(Person.class, id);
      addPerson16(person193);
    }
  }

  @DataModel("person193List") private Map<String, String> person193List;

  public Map<String, String> getPerson193List()
  { 
    return person193List;
  }

  @Factory("person193List") public void initPerson193List()
  { 
    log.info("initPerson193List");
    person193List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Person").getResultList())
    { 
      Person p = (Person)o;
      person193List.put(p.getName(), p.getId().toString());
    }
  }

  @DataModel("person102List") private List<Person> person102List;

  public List<Person> getPerson102List()
  { 
    log.info("getPerson102List");
    return person102List;
  }

  @Factory("person102List") public void initPerson102List()
  { 
    log.info("initPerson102List");
    person102List = em.createQuery("from " + "Person").getResultList();
  }

  @DataModel("project92List") private List<ResearchProject> project92List;

  public List<ResearchProject> getProject92List()
  { 
    log.info("getProject92List");
    return project92List;
  }

  @Factory("project92List") public void initProject92List()
  { 
    log.info("initProject92List");
    project92List = em.createQuery("from " + "ResearchProject").getResultList();
  }
}