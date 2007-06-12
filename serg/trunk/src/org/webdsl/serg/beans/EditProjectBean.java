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
    initIssue23List();
    initPerson194List();
    initPerson95List();
    initProject90List();
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

  public void removeIssue3(Issue issue22)
  { 
    this.getProject().getIssues().remove(issue22);
  }

  public void addIssue3(Issue issue22)
  { 
    this.getProject().getIssues().add(issue22);
  }

  public void removePerson21(Person person193)
  { 
    this.getProject().getAssigned().remove(person193);
  }

  public void addPerson21(Person person193)
  { 
    this.getProject().getAssigned().add(person193);
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

  private String newIssue23;

  public void setNewIssue23(String p)
  { 
    newIssue23 = p;
  }

  public String getNewIssue23()
  { 
    return newIssue23;
  }

  public void selectIssue23(ValueChangeEvent event)
  { 
    log.info("selectIssue23" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Issue issue23 = em.find(Issue.class, id);
      addIssue3(issue23);
    }
  }

  @DataModel("issue23List") private Map<String, String> issue23List;

  public Map<String, String> getIssue23List()
  { 
    return issue23List;
  }

  @Factory("issue23List") public void initIssue23List()
  { 
    log.info("initIssue23List");
    issue23List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Issue").getResultList())
    { 
      Issue p = (Issue)o;
      issue23List.put(p.getName(), p.getId().toString());
    }
  }

  private String newPerson194;

  public void setNewPerson194(String p)
  { 
    newPerson194 = p;
  }

  public String getNewPerson194()
  { 
    return newPerson194;
  }

  public void selectPerson194(ValueChangeEvent event)
  { 
    log.info("selectPerson194" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Person person194 = em.find(Person.class, id);
      addPerson21(person194);
    }
  }

  @DataModel("person194List") private Map<String, String> person194List;

  public Map<String, String> getPerson194List()
  { 
    return person194List;
  }

  @Factory("person194List") public void initPerson194List()
  { 
    log.info("initPerson194List");
    person194List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Person").getResultList())
    { 
      Person p = (Person)o;
      person194List.put(p.getName(), p.getId().toString());
    }
  }

  @DataModel("person95List") private List<Person> person95List;

  public List<Person> getPerson95List()
  { 
    log.info("getPerson95List");
    return person95List;
  }

  @Factory("person95List") public void initPerson95List()
  { 
    log.info("initPerson95List");
    person95List = em.createQuery("from " + "Person").getResultList();
  }

  @DataModel("project90List") private List<ResearchProject> project90List;

  public List<ResearchProject> getProject90List()
  { 
    log.info("getProject90List");
    return project90List;
  }

  @Factory("project90List") public void initProject90List()
  { 
    log.info("initProject90List");
    project90List = em.createQuery("from " + "ResearchProject").getResultList();
  }
}