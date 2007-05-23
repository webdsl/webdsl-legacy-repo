package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface EditPublicationMyBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void setPub(Publication pub);

  public Publication getPub();

  public String cancel();

  public String save();

  public void addAuthor(Person author);

  public void removeAuthor(Person author);

  public void removeProject(ResearchProject project);

  public void addProject(ResearchProject project);

  public void setNewAuthor1(String p);

  public String getNewAuthor1();

  public String selectAuthor1();

  public Map<String, String> getAuthor1List();

  public void initAuthor1List();

  public void setNewProject1(String p);

  public String getNewProject1();

  public String selectProject1();

  public Map<String, String> getProject1List();

  public void initProject1List();
}