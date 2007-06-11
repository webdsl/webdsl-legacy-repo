package org.webdsl.serg.beans;

import java.util.*;
import java.io.Serializable;
import static javax.persistence.PersistenceContextType.EXTENDED;
import javax.persistence.PersistenceContext;
import javax.persistence.EntityManager;
import javax.faces.event.ValueChangeEvent;
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

@Stateful @Name("createUser") public class CreateUserBean  implements CreateUserBeanInterface
{ 
  @Logger private Log log;

  @PersistenceContext(type = EXTENDED) private EntityManager em;

  @In private FacesMessages facesMessages;

  @Create @Begin public void initialize()
  { 
    log.info("createUser" + ".initalize()");
    User var16 = new User();
    user = var16;
    initPerson15List();
    initPerson1012List();
    initProject1112List();
  }

  @Destroy @Remove public void destroy()
  { }

  public void setPerson1(Person person16)
  { 
    user.setPerson(person16);
  }

  @End public String cancel()
  { 
    return "/" + "home" + ".seam?" + "";
  }

  @End public String save()
  { 
    em.persist(this.getUser());
    return "/" + "viewUser" + ".seam?" + ("user" + "=" + user.getId() + "");
  }

  private String newPerson15;

  public void setNewPerson15(String p)
  { 
    newPerson15 = p;
  }

  public String getNewPerson15()
  { 
    return newPerson15;
  }

  public void selectPerson15(ValueChangeEvent event)
  { 
    log.info("selectPerson15" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Person person15 = em.find(Person.class, id);
      setPerson1(person15);
    }
  }

  @DataModel("person15List") private Map<String, String> person15List;

  public Map<String, String> getPerson15List()
  { 
    return person15List;
  }

  @Factory("person15List") public void initPerson15List()
  { 
    log.info("initPerson15List");
    person15List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Person").getResultList())
    { 
      Person p = (Person)o;
      person15List.put(p.getName(), p.getId().toString());
    }
  }

  @DataModel("person1012List") private List<Person> person1012List;

  public List<Person> getPerson1012List()
  { 
    log.info("getPerson1012List");
    return person1012List;
  }

  @Factory("person1012List") public void initPerson1012List()
  { 
    log.info("initPerson1012List");
    person1012List = em.createQuery("from " + "Person").getResultList();
  }

  @DataModel("project1112List") private List<ResearchProject> project1112List;

  public List<ResearchProject> getProject1112List()
  { 
    log.info("getProject1112List");
    return project1112List;
  }

  @Factory("project1112List") public void initProject1112List()
  { 
    log.info("initProject1112List");
    project1112List = em.createQuery("from " + "ResearchProject").getResultList();
  }

  private User user;

  public User getUser()
  { 
    log.info("getUser");
    return user;
  }

  public void setUser(User user)
  { 
    log.info("setUser");
    this.user = user;
  }
}