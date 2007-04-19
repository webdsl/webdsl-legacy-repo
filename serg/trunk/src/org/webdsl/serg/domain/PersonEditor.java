package org.webdsl.serg.domain;

import org.jboss.annotation.ejb.Local;

@Local public interface PersonEditor 
{ 
  public Person getPerson();

  public void setPerson(Person person);

  public void initialize();

  public boolean isNew();

  public String create();

  public String update();

  public String delete();

  public String done();

  public String view();

  public void destroy();
}