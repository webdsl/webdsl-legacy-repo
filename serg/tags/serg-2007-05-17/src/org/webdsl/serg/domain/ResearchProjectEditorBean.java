package org.webdsl.serg.domain;

import static javax.persistence.PersistenceContextType.EXTENDED;
import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.ejb.TransactionAttribute;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.Create;
import org.jboss.seam.annotations.Destroy;
import org.jboss.seam.annotations.End;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.RequestParameter;
import org.jboss.seam.core.FacesMessages;
import org.jboss.seam.log.Log;

@Stateful @Name("researchProjectEditor") public class ResearchProjectEditorBean  implements ResearchProjectEditor
{ 
  @Logger private Log log;

  @In private FacesMessages facesMessages;

  @PersistenceContext(type = EXTENDED) private EntityManager em;

  private ResearchProject researchProject;

  public ResearchProject getResearchProject()
  { 
    return researchProject;
  }

  public void setResearchProject(ResearchProject researchProject)
  { 
    this.researchProject = researchProject;
  }

  @Create public void initialize()
  { 
    researchProject = new ResearchProject();
  }

  private boolean isNew = true;

  public boolean isNew()
  { 
    return isNew;
  }

  @Begin(join = true) public String create()
  { 
    em.persist(researchProject);
    isNew = false;
    log.info("creating new " + "researchProject");
    return null;
  }

  @RequestParameter("researchProjectId") private Long researchProjectId;

  @Begin(join = true) public String edit()
  { 
    researchProject = em.find(ResearchProject.class, researchProjectId);
    isNew = false;
    log.info("loaded new " + "researchProject" + " for editing " + researchProject);
    return "editAddress";
  }

  public String save()
  { 
    em.joinTransaction();
    em.flush();
    log.info("saving " + "researchProject");
    return null;
  }

  @End public String delete()
  { 
    em.remove(researchProject);
    researchProject = null;
    log.info("deleting " + "ResearchProject");
    return "find" + "ResearchProject";
  }

  @End public String done()
  { 
    if(!isNew)
      em.refresh(researchProject);
    return "find" + "ResearchProject";
  }

  @End public String view()
  { 
    if(!isNew)
      em.refresh(researchProject);
    return "view" + "ResearchProject";
  }

  @Remove @Destroy public void destroy()
  { }
}