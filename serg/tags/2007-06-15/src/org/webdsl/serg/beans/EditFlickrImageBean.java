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

@Stateful @Name("editFlickrImage") public class EditFlickrImageBean  implements EditFlickrImageBeanInterface
{ 
  @Logger private Log log;

  @PersistenceContext(type = EXTENDED) private EntityManager em;

  @In private FacesMessages facesMessages;

  @Create @Begin public void initialize()
  { 
    log.info("editFlickrImage" + ".initalize()");
    if(flickrImageId == null)
    { 
      log.info("No " + "flickrImageId" + " defined, creating new " + "FlickrImage");
      flickrImage = new FlickrImage();
    }
    else
    { 
      flickrImage = em.find(FlickrImage.class, flickrImageId);
    }
    initPerson1025List();
    initProject1125List();
  }

  @Destroy @Remove public void destroy()
  { }

  @RequestParameter("flickrImage") private Long flickrImageId;

  private FlickrImage flickrImage;

  public void setFlickrImage(FlickrImage flickrImage)
  { 
    log.info("setFlickrImage");
    this.flickrImage = flickrImage;
  }

  public FlickrImage getFlickrImage()
  { 
    log.info("getFlickrImage");
    return flickrImage;
  }

  @End public String cancel()
  { 
    return "/" + "viewFlickrImage" + ".seam?" + ("flickrImage" + "=" + flickrImage.getId() + "");
  }

  @End public String save()
  { 
    em.persist(this.getFlickrImage());
    return "/" + "viewFlickrImage" + ".seam?" + ("flickrImage" + "=" + flickrImage.getId() + "");
  }

  @DataModel("person1025List") private List<Person> person1025List;

  public List<Person> getPerson1025List()
  { 
    log.info("getPerson1025List");
    return person1025List;
  }

  @Factory("person1025List") public void initPerson1025List()
  { 
    log.info("initPerson1025List");
    person1025List = em.createQuery("from " + "Person").getResultList();
  }

  @DataModel("project1125List") private List<ResearchProject> project1125List;

  public List<ResearchProject> getProject1125List()
  { 
    log.info("getProject1125List");
    return project1125List;
  }

  @Factory("project1125List") public void initProject1125List()
  { 
    log.info("initProject1125List");
    project1125List = em.createQuery("from " + "ResearchProject").getResultList();
  }
}