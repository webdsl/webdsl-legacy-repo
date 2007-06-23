package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import javax.faces.event.ValueChangeEvent;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface EditPostBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void setPost(Post post);

  public Post getPost();

  public void setPerson15(Person person238);

  public void setDiscussion2(Discussion discussion16);

  public String cancel();

  public String save();

  public void setNewPerson237(String p);

  public String getNewPerson237();

  public void selectPerson237(ValueChangeEvent event);

  public Map<String, String> getPerson237List();

  public void initPerson237List();

  public void setNewDiscussion15(String p);

  public String getNewDiscussion15();

  public void selectDiscussion15(ValueChangeEvent event);

  public Map<String, String> getDiscussion15List();

  public void initDiscussion15List();

  public List<Person> getPerson131List();

  public void initPerson131List();

  public List<ResearchProject> getProject124List();

  public void initProject124List();
}