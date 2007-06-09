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

@Stateful @Name("editUser") public class EditUserBean  implements EditUserBeanInterface
{ 
  @Logger private Log log;

  @PersistenceContext(type = EXTENDED) private EntityManager em;

  @In private FacesMessages facesMessages;

  @Create @Begin public void initialize()
  { 
    log.info("editUser" + ".initalize()");
    if(userId == null)
    { 
      log.info("No " + "userId" + " defined, creating new " + "User");
      user = new User();
    }
    else
    { 
      user = em.find(User.class, userId);
    }
    initPerson5List();
    initPerson107List();
    initProject117List();
  }

  @Destroy @Remove public void destroy()
  { }

  @RequestParameter("user") private Long userId;

  private User user;

  public void setUser(User user)
  { 
    log.info("setUser");
    this.user = user;
  }

  public User getUser()
  { 
    log.info("getUser");
    return user;
  }

  public void setPerson0(Person person6)
  { 
    user.setPerson(person6);
  }

  @End public String cancel()
  { 
    return "/" + "viewUser" + ".seam?" + ("user" + "=" + user.getId() + "");
  }

  @End public String save()
  { 
    em.persist(this.getUser());
    return "/" + "viewUser" + ".seam?" + ("user" + "=" + user.getId() + "");
  }

  private String newPerson5;

  public void setNewPerson5(String p)
  { 
    newPerson5 = p;
  }

  public String getNewPerson5()
  { 
    return newPerson5;
  }

  public void selectPerson5(ValueChangeEvent event)
  { 
    log.info("selectPerson5" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Person person5 = em.find(Person.class, id);
      setPerson0(person5);
    }
  }

  @DataModel("person5List") private Map<String, String> person5List;

  public Map<String, String> getPerson5List()
  { 
    return person5List;
  }

  @Factory("person5List") public void initPerson5List()
  { 
    log.info("initPerson5List");
    person5List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Person").getResultList())
    { 
      Person p = (Person)o;
      person5List.put(p.getName(), p.getId().toString());
    }
  }

  @DataModel("person107List") private List<Person> person107List;

  public List<Person> getPerson107List()
  { 
    log.info("getPerson107List");
    return person107List;
  }

  @Factory("person107List") public void initPerson107List()
  { 
    log.info("initPerson107List");
    person107List = em.createQuery("from " + "Person").getResultList();
  }

  @DataModel("project117List") private List<ResearchProject> project117List;

  public List<ResearchProject> getProject117List()
  { 
    log.info("getProject117List");
    return project117List;
  }

  @Factory("project117List") public void initProject117List()
  { 
    log.info("initProject117List");
    project117List = em.createQuery("from " + "ResearchProject").getResultList();
  }
}