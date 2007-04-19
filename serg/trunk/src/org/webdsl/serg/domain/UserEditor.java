package org.webdsl.serg.domain;

import org.jboss.annotation.ejb.Local;

@Local public interface UserEditor 
{ 
  public User getUser();

  public void setUser(User user);

  public void initialize();

  public boolean isNew();

  public String create();

  public String update();

  public String delete();

  public String done();

  public String view();

  public void destroy();
}