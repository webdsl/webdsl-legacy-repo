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

  public void setPerson15(Person person211);

  public void setDiscussion2(Discussion discussion15);

  public String cancel();

  public String save();

  public void setNewPerson210(String p);

  public String getNewPerson210();

  public void selectPerson210(ValueChangeEvent event);

  public Map<String, String> getPerson210List();

  public void initPerson210List();

  public void setNewDiscussion14(String p);

  public String getNewDiscussion14();

  public void selectDiscussion14(ValueChangeEvent event);

  public Map<String, String> getDiscussion14List();

  public void initDiscussion14List();

  public List<Person> getPerson120List();

  public void initPerson120List();

  public List<ResearchProject> getProject120List();

  public void initProject120List();
}