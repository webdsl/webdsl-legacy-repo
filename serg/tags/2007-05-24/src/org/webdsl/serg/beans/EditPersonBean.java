package org.webdsl.serg.beans;

import java.util.*;
import java.io.Serializable;
import static javax.persistence.PersistenceContextType.EXTENDED;
import javax.persistence.PersistenceContext;
import javax.persistence.EntityManager;
import javax.ejb.Stateless;
import javax.ejb.Stateful;
import javax.ejb.Remove;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.End;
import org.jboss.seam.annotations.Destroy;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.Create;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.RequestParameter;
import org.jboss.seam.annotations.datamodel.DataModel;
import org.jboss.seam.annotations.datamodel.DataModelSelection;
import org.jboss.seam.core.FacesMessages;
import org.jboss.seam.log.Log;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Factory;
import org.webdsl.serg.domain.*;

@Stateful @Name("editPerson") public class EditPersonBean  implements EditPersonBeanInterface
{ 
  @Logger private Log log;

  @PersistenceContext(type = EXTENDED) private EntityManager em;

  @In private FacesMessages facesMessages;

  @Create @Begin public void initialize()
  { 
    if(personId == null)
    { 
      log.debug("No " + "personId" + " defined, creating new " + "Person");
      person = new Person();
    }
    else
    { 
      person = em.find(Person.class, personId);
    }
    initUser0List();
  }

  @Destroy @Remove public void destroy()
  { }

  @RequestParameter("person") private Long personId;

  private Person person;

  public void setPerson(Person person)
  { 
    this.person = person;
  }

  public Person getPerson()
  { 
    return person;
  }

  public void setUser0(User user1)
  { 
    person.setUser(user1);
  }

  @End public String cancel()
  { 
    return "/" + "viewPerson" + ".seam?" + ("person" + "=" + person.getId() + "");
  }

  @End public String save()
  { 
    em.persist(this.getPerson());
    return "/" + "viewPerson" + ".seam?" + ("person" + "=" + person.getId() + "");
  }

  private String newUser0;

  public void setNewUser0(String p)
  { 
    newUser0 = p;
  }

  public String getNewUser0()
  { 
    return newUser0;
  }

  public String selectUser0()
  { 
    log.info("selectUser0" + " " + newUser0);
    User user0 = em.find(User.class, new Long(newUser0));
    setUser0(user0);
    return null;
  }

  @DataModel("user0List") private Map<String, String> user0List;

  public Map<String, String> getUser0List()
  { 
    return user0List;
  }

  @Factory("user0List") public void initUser0List()
  { 
    log.info("initUser0List");
    user0List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "User").getResultList())
    { 
      User p = (User)o;
      user0List.put(p.getName(), p.getId().toString());
    }
  }
}