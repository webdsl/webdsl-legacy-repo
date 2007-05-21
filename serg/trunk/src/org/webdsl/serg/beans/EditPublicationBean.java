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
    if(pubId == null)
    { 
      log.debug("No " + "pubId" + " defined, creating new " + "Publication");
      pub = new Publication();
    }
    else
    { 
      pub = em.find(Publication.class, pubId);
    }
    initAuthor1List();
    initProject1List();
  }

  @Destroy @Remove public void destroy()
  { }

  @RequestParameter("pub") private Long pubId;

  private Publication pub;

  public void setPub(Publication pub)
  { 
    this.pub = pub;
  }

  public Publication getPub()
  { 
    return pub;
  }

  @End public String cancel()
  { 
    return "/" + "viewPublication" + ".seam?" + ("pub" + "=" + pub.getId() + "");
  }

  @End public String save()
  { 
    em.persist(this.getPub());
    return "/" + "viewPublication" + ".seam?" + ("pub" + "=" + pub.getId() + "");
  }

  public void addAuthor(Person author)
  { 
    this.getPub().getAuthors().add(author);
  }

  public void removeAuthor(Person author)
  { 
    this.getPub().getAuthors().remove(author);
  }

  public void removeProject(ResearchProject project)
  { 
    this.getPub().getProjects().remove(project);
  }

  public void addProject(ResearchProject project)
  { 
    this.getPub().getProjects().add(project);
  }

  private String newAuthor1;

  public void setNewAuthor1(String p)
  { 
    newAuthor1 = p;
  }

  public String getNewAuthor1()
  { 
    return newAuthor1;
  }

  public String selectAuthor1()
  { 
    log.info("selectAuthor1" + " " + newAuthor1);
    Person author1 = em.find(Person.class, new Long(newAuthor1));
    addAuthor(author1);
    return null;
  }

  @DataModel("author1List") private Map<String, String> author1List;

  public Map<String, String> getAuthor1List()
  { 
    return author1List;
  }

  @Factory("author1List") public void initAuthor1List()
  { 
    log.info("initAuthor1List");
    author1List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Person").getResultList())
    { 
      Person p = (Person)o;
      author1List.put(p.getName(), p.getId().toString());
    }
  }

  private String newProject1;

  public void setNewProject1(String p)
  { 
    newProject1 = p;
  }

  public String getNewProject1()
  { 
    return newProject1;
  }

  public String selectProject1()
  { 
    log.info("selectProject1" + " " + newProject1);
    ResearchProject project1 = em.find(ResearchProject.class, new Long(newProject1));
    addProject(project1);
    return null;
  }

  @DataModel("project1List") private Map<String, String> project1List;

  public Map<String, String> getProject1List()
  { 
    return project1List;
  }

  @Factory("project1List") public void initProject1List()
  { 
    log.info("initProject1List");
    project1List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "ResearchProject").getResultList())
    { 
      ResearchProject p = (ResearchProject)o;
      project1List.put(p.getName(), p.getId().toString());
    }
  }
}