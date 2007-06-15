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
    initPerson188List();
    initPerson97List();
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

  public void removeIssue2(Issue issue19)
  { 
    this.getProject().getIssues().remove(issue19);
  }

  public void addIssue2(Issue issue19)
  { 
    this.getProject().getIssues().add(issue19);
  }

  public void removePerson16(Person person187)
  { 
    this.getProject().getAssigned().remove(person187);
  }

  public void addPerson16(Person person187)
  { 
    this.getProject().getAssigned().add(person187);
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

  private String newPerson188;

  public void setNewPerson188(String p)
  { 
    newPerson188 = p;
  }

  public String getNewPerson188()
  { 
    return newPerson188;
  }

  public void selectPerson188(ValueChangeEvent event)
  { 
    log.info("selectPerson188" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Person person188 = em.find(Person.class, id);
      addPerson16(person188);
    }
  }

  @DataModel("person188List") private Map<String, String> person188List;

  public Map<String, String> getPerson188List()
  { 
    return person188List;
  }

  @Factory("person188List") public void initPerson188List()
  { 
    log.info("initPerson188List");
    person188List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Person").getResultList())
    { 
      Person p = (Person)o;
      person188List.put(p.getName(), p.getId().toString());
    }
  }

  @DataModel("person97List") private List<Person> person97List;

  public List<Person> getPerson97List()
  { 
    log.info("getPerson97List");
    return person97List;
  }

  @Factory("person97List") public void initPerson97List()
  { 
    log.info("initPerson97List");
    person97List = em.createQuery("from " + "Person").getResultList();
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