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
    initIssue20List();
    initPerson170List();
    initPerson96List();
    initProject91List();
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

  public void removeIssue2(Issue issue19)
  { 
    this.getProject().getIssues().remove(issue19);
  }

  public void addIssue2(Issue issue19)
  { 
    this.getProject().getIssues().add(issue19);
  }

  public void removePerson16(Person person169)
  { 
    this.getProject().getAssigned().remove(person169);
  }

  public void addPerson16(Person person169)
  { 
    this.getProject().getAssigned().add(person169);
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

  private String newIssue20;

  public void setNewIssue20(String p)
  { 
    newIssue20 = p;
  }

  public String getNewIssue20()
  { 
    return newIssue20;
  }

  public void selectIssue20(ValueChangeEvent event)
  { 
    log.info("selectIssue20" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Issue issue20 = em.find(Issue.class, id);
      addIssue2(issue20);
    }
  }

  @DataModel("issue20List") private Map<String, String> issue20List;

  public Map<String, String> getIssue20List()
  { 
    return issue20List;
  }

  @Factory("issue20List") public void initIssue20List()
  { 
    log.info("initIssue20List");
    issue20List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Issue").getResultList())
    { 
      Issue p = (Issue)o;
      issue20List.put(p.getName(), p.getId().toString());
    }
  }

  private String newPerson170;

  public void setNewPerson170(String p)
  { 
    newPerson170 = p;
  }

  public String getNewPerson170()
  { 
    return newPerson170;
  }

  public void selectPerson170(ValueChangeEvent event)
  { 
    log.info("selectPerson170" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Person person170 = em.find(Person.class, id);
      addPerson16(person170);
    }
  }

  @DataModel("person170List") private Map<String, String> person170List;

  public Map<String, String> getPerson170List()
  { 
    return person170List;
  }

  @Factory("person170List") public void initPerson170List()
  { 
    log.info("initPerson170List");
    person170List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Person").getResultList())
    { 
      Person p = (Person)o;
      person170List.put(p.getName(), p.getId().toString());
    }
  }

  @DataModel("person96List") private List<Person> person96List;

  public List<Person> getPerson96List()
  { 
    log.info("getPerson96List");
    return person96List;
  }

  @Factory("person96List") public void initPerson96List()
  { 
    log.info("initPerson96List");
    person96List = em.createQuery("from " + "Person").getResultList();
  }

  @DataModel("project91List") private List<ResearchProject> project91List;

  public List<ResearchProject> getProject91List()
  { 
    log.info("getProject91List");
    return project91List;
  }

  @Factory("project91List") public void initProject91List()
  { 
    log.info("initProject91List");
    project91List = em.createQuery("from " + "ResearchProject").getResultList();
  }
}