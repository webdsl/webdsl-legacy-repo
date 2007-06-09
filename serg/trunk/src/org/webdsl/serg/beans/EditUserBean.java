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
    initPerson6List();
    initPerson1011List();
    initProject1111List();
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

  public void setPerson0(Person person7)
  { 
    user.setPerson(person7);
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

  private String newPerson6;

  public void setNewPerson6(String p)
  { 
    newPerson6 = p;
  }

  public String getNewPerson6()
  { 
    return newPerson6;
  }

  public void selectPerson6(ValueChangeEvent event)
  { 
    log.info("selectPerson6" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Person person6 = em.find(Person.class, id);
      setPerson0(person6);
    }
  }

  @DataModel("person6List") private Map<String, String> person6List;

  public Map<String, String> getPerson6List()
  { 
    return person6List;
  }

  @Factory("person6List") public void initPerson6List()
  { 
    log.info("initPerson6List");
    person6List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Person").getResultList())
    { 
      Person p = (Person)o;
      person6List.put(p.getName(), p.getId().toString());
    }
  }

  @DataModel("person1011List") private List<Person> person1011List;

  public List<Person> getPerson1011List()
  { 
    log.info("getPerson1011List");
    return person1011List;
  }

  @Factory("person1011List") public void initPerson1011List()
  { 
    log.info("initPerson1011List");
    person1011List = em.createQuery("from " + "Person").getResultList();
  }

  @DataModel("project1111List") private List<ResearchProject> project1111List;

  public List<ResearchProject> getProject1111List()
  { 
    log.info("getProject1111List");
    return project1111List;
  }

  @Factory("project1111List") public void initProject1111List()
  { 
    log.info("initProject1111List");
    project1111List = em.createQuery("from " + "ResearchProject").getResultList();
  }
}