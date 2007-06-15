package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface CreateFlickrImageBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public String cancel();

  public String save();

  public List<Person> getPerson1026List();

  public void initPerson1026List();

  public List<ResearchProject> getProject1126List();

  public void initProject1126List();

  public FlickrImage getFlickrImage();

  public void setFlickrImage(FlickrImage flickrImage);
}