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
import org.jboss.seam.annotations.RequestParameter;
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
    log.info("creating new " + "person");
    return null;
  }

  @RequestParameter("personId") private Long personId;

  @Begin(join = true) public String edit()
  { 
    person = em.find(Person.class, personId);
    isNew = false;
    log.info("loaded new " + "person" + " for editing " + person);
    return "editAddress";
  }

  public String save()
  { 
    em.joinTransaction();
    em.flush();
    log.info("saving " + "person");
    return null;
  }

  @End public String delete()
  { 
    em.remove(person);
    person = null;
    log.info("deleting " + "Person");
    return "find" + "Person";
  }

  @End public String done()
  { 
    if(!isNew)
      em.refresh(person);
    return "find" + "Person";
  }

  @End public String view()
  { 
    if(!isNew)
      em.refresh(person);
    return "view" + "Person";
  }

  @Remove @Destroy public void destroy()
  { }
}