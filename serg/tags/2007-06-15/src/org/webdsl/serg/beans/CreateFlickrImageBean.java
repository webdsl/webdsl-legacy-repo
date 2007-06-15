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

@Stateful @Name("createFlickrImage") public class CreateFlickrImageBean  implements CreateFlickrImageBeanInterface
{ 
  @Logger private Log log;

  @PersistenceContext(type = EXTENDED) private EntityManager em;

  @In private FacesMessages facesMessages;

  @Create @Begin public void initialize()
  { 
    log.info("createFlickrImage" + ".initalize()");
    FlickrImage var38 = new FlickrImage();
    flickrImage = var38;
    initPerson1026List();
    initProject1126List();
  }

  @Destroy @Remove public void destroy()
  { }

  @End public String cancel()
  { 
    return "/" + "home" + ".seam?" + "";
  }

  @End public String save()
  { 
    em.persist(this.getFlickrImage());
    return "/" + "viewFlickrImage" + ".seam?" + ("flickrImage" + "=" + flickrImage.getId() + "");
  }

  @DataModel("person1026List") private List<Person> person1026List;

  public List<Person> getPerson1026List()
  { 
    log.info("getPerson1026List");
    return person1026List;
  }

  @Factory("person1026List") public void initPerson1026List()
  { 
    log.info("initPerson1026List");
    person1026List = em.createQuery("from " + "Person").getResultList();
  }

  @DataModel("project1126List") private List<ResearchProject> project1126List;

  public List<ResearchProject> getProject1126List()
  { 
    log.info("getProject1126List");
    return project1126List;
  }

  @Factory("project1126List") public void initProject1126List()
  { 
    log.info("initProject1126List");
    project1126List = em.createQuery("from " + "ResearchProject").getResultList();
  }

  private FlickrImage flickrImage;

  public FlickrImage getFlickrImage()
  { 
    log.info("getFlickrImage");
    return flickrImage;
  }

  public void setFlickrImage(FlickrImage flickrImage)
  { 
    log.info("setFlickrImage");
    this.flickrImage = flickrImage;
  }
}