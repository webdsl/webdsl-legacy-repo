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

@Stateful @Name("viewFlickrImage") public class ViewFlickrImageBean  implements ViewFlickrImageBeanInterface
{ 
  @Logger private Log log;

  @PersistenceContext(type = EXTENDED) private EntityManager em;

  @In private FacesMessages facesMessages;

  @Create @Begin public void initialize()
  { 
    log.info("viewFlickrImage" + ".initalize()");
    if(flickrImageId == null)
    { 
      log.info("No " + "flickrImageId" + " defined, creating new " + "FlickrImage");
      flickrImage = new FlickrImage();
    }
    else
    { 
      flickrImage = em.find(FlickrImage.class, flickrImageId);
    }
    initPerson1027List();
    initProject1127List();
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

  @DataModel("person1027List") private List<Person> person1027List;

  public List<Person> getPerson1027List()
  { 
    log.info("getPerson1027List");
    return person1027List;
  }

  @Factory("person1027List") public void initPerson1027List()
  { 
    log.info("initPerson1027List");
    person1027List = em.createQuery("from " + "Person").getResultList();
  }

  @DataModel("project1127List") private List<ResearchProject> project1127List;

  public List<ResearchProject> getProject1127List()
  { 
    log.info("getProject1127List");
    return project1127List;
  }

  @Factory("project1127List") public void initProject1127List()
  { 
    log.info("initProject1127List");
    project1127List = em.createQuery("from " + "ResearchProject").getResultList();
  }
}