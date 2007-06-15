package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface EditFlickrImageBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void setFlickrImage(FlickrImage flickrImage);

  public FlickrImage getFlickrImage();

  public String cancel();

  public String save();

  public List<Person> getPerson1025List();

  public void initPerson1025List();

  public List<ResearchProject> getProject1125List();

  public void initProject1125List();
}