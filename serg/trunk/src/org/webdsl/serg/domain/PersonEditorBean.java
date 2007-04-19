package org.webdsl.serg.domain;

import static javax.persistence.PersistenceContextType.EXTENDED;
import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.ejb.TransactionAttribute;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.Create;
import org.jboss.seam.annotations.Destroy;
import org.jboss.seam.annotations.End;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.core.FacesMessages;
import org.jboss.seam.log.Log;

@Stateful @Name("personEditor") public class PersonEditorBean  implements PersonEditor
{ 
  @Logger private Log log;

  @In private FacesMessages facesMessages;

  @PersistenceContext(type = EXTENDED) private EntityManager em;

  private Person person;

  public Person getPerson()
  { 
    return person;
  }

  public void setPerson(Person person)
  { 
    this.person = person;
  }

  @Create public void initialize()
  { 
    person = new Person();
  }

  private boolean isNew = true;

  public boolean isNew()
  { 
    return isNew;
  }

  @Begin(join = true) public String create()
  { 
    em.persist(person);
    isNew = false;
    log.info("creating new x_class");
    return null;
  }

  public String update()
  { 
    em.joinTransaction();
    em.flush();
    log.info("updating x_class");
    return null;
  }

  @End public String delete()
  { 
    em.remove(person);
    person = null;
    log.info("deleting x_Class and going to PersonList");
    return "/find" + "Person" + ".xhtml";
  }

  @End public String done()
  { 
    if(!isNew)
      em.refresh(person);
    return "/find" + "Person" + ".xhtml";
  }

  @End public String view()
  { 
    if(!isNew)
      em.refresh(person);
    return "/view" + "Person" + ".xhtml";
  }

  @Remove @Destroy public void destroy()
  { }
}