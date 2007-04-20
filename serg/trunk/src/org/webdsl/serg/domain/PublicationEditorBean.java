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

@Stateful @Name("publicationEditor") public class PublicationEditorBean  implements PublicationEditor
{ 
  @Logger private Log log;

  @In private FacesMessages facesMessages;

  @PersistenceContext(type = EXTENDED) private EntityManager em;

  private Publication publication;

  public Publication getPublication()
  { 
    return publication;
  }

  public void setPublication(Publication publication)
  { 
    this.publication = publication;
  }

  @Create public void initialize()
  { 
    publication = new Publication();
  }

  private boolean isNew = true;

  public boolean isNew()
  { 
    return isNew;
  }

  @Begin(join = true) public String create()
  { 
    em.persist(publication);
    isNew = false;
    log.info("creating new " + "publication");
    return null;
  }

  @RequestParameter("publicationId") private Long publicationId;

  @Begin(join = true) public String edit()
  { 
    publication = em.find(Publication.class, publicationId);
    isNew = false;
    log.info("loaded new " + "publication" + " for editing " + publication);
    return "editAddress";
  }

  public String save()
  { 
    em.joinTransaction();
    em.flush();
    log.info("saving " + "publication");
    return null;
  }

  @End public String delete()
  { 
    em.remove(publication);
    publication = null;
    log.info("deleting " + "Publication");
    return "find" + "Publication";
  }

  @End public String done()
  { 
    if(!isNew)
      em.refresh(publication);
    return "find" + "Publication";
  }

  @End public String view()
  { 
    if(!isNew)
      em.refresh(publication);
    return "view" + "Publication";
  }

  @Remove @Destroy public void destroy()
  { }
}