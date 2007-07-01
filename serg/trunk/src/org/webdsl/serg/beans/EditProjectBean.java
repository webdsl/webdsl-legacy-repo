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
    initPerson229List();
    initPerson109List();
    initProject96List();
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

  public void removePerson18(Person person228)
  { 
    this.getProject().getAssigned().remove(person228);
  }

  public void addPerson18(Person person228)
  { 
    this.getProject().getAssigned().add(person228);
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

  private String newPerson229;

  public void setNewPerson229(String p)
  { 
    newPerson229 = p;
  }

  public String getNewPerson229()
  { 
    return newPerson229;
  }

  public void selectPerson229(ValueChangeEvent event)
  { 
    log.info("selectPerson229" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Person person229 = em.find(Person.class, id);
      addPerson18(person229);
    }
  }

  @DataModel("person229List") private Map<String, String> person229List;

  public Map<String, String> getPerson229List()
  { 
    return person229List;
  }

  @Factory("person229List") public void initPerson229List()
  { 
    log.info("initPerson229List");
    person229List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Person").getResultList())
    { 
      Person p = (Person)o;
      person229List.put(p.getName(), p.getId().toString());
    }
  }

  @DataModel("person109List") private List<Person> person109List;

  public List<Person> getPerson109List()
  { 
    log.info("getPerson109List");
    return person109List;
  }

  @Factory("person109List") public void initPerson109List()
  { 
    log.info("initPerson109List");
    person109List = em.createQuery("from " + "Person").getResultList();
  }

  @DataModel("project96List") private List<ResearchProject> project96List;

  public List<ResearchProject> getProject96List()
  { 
    log.info("getProject96List");
    return project96List;
  }

  @Factory("project96List") public void initProject96List()
  { 
    log.info("initProject96List");
    project96List = em.createQuery("from " + "ResearchProject").getResultList();
  }
}