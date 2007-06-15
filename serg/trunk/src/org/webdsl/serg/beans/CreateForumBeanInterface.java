package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface CreateForumBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void removeDiscussion1(Discussion discussion7);

  public void addDiscussion1(Discussion discussion7);

  public String cancel();

  public String save();

  public void setNewDiscussion8(String p);

  public String getNewDiscussion8();

  public void selectDiscussion8(ValueChangeEvent event);

  public Map<String, String> getDiscussion8List();

  public void initDiscussion8List();

  public List<Person> getPerson110List();

  public void initPerson110List();

  public List<ResearchProject> getProject110List();

  public void initProject110List();

  public Forum getForum();

  public void setForum(Forum forum);
}