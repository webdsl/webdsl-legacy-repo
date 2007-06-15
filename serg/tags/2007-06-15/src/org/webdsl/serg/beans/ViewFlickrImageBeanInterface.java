package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface ViewFlickrImageBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void setFlickrImage(FlickrImage flickrImage);

  public FlickrImage getFlickrImage();

  public List<Person> getPerson1027List();

  public void initPerson1027List();

  public List<ResearchProject> getProject1127List();

  public void initProject1127List();
}