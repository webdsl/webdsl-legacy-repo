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
    initPerson7List();
    initPerson1010List();
    initProject1110List();
  }

  @Destroy @Remove public void destroy()
  { }

  public void setPerson1(Person person8)
  { 
    user.setPerson(person8);
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

  private String newPerson7;

  public void setNewPerson7(String p)
  { 
    newPerson7 = p;
  }

  public String getNewPerson7()
  { 
    return newPerson7;
  }

  public void selectPerson7(ValueChangeEvent event)
  { 
    log.info("selectPerson7" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Person person7 = em.find(Person.class, id);
      setPerson1(person7);
    }
  }

  @DataModel("person7List") private Map<String, String> person7List;

  public Map<String, String> getPerson7List()
  { 
    return person7List;
  }

  @Factory("person7List") public void initPerson7List()
  { 
    log.info("initPerson7List");
    person7List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Person").getResultList())
    { 
      Person p = (Person)o;
      person7List.put(p.getName(), p.getId().toString());
    }
  }

  @DataModel("person1010List") private List<Person> person1010List;

  public List<Person> getPerson1010List()
  { 
    log.info("getPerson1010List");
    return person1010List;
  }

  @Factory("person1010List") public void initPerson1010List()
  { 
    log.info("initPerson1010List");
    person1010List = em.createQuery("from " + "Person").getResultList();
  }

  @DataModel("project1110List") private List<ResearchProject> project1110List;

  public List<ResearchProject> getProject1110List()
  { 
    log.info("getProject1110List");
    return project1110List;
  }

  @Factory("project1110List") public void initProject1110List()
  { 
    log.info("initProject1110List");
    project1110List = em.createQuery("from " + "ResearchProject").getResultList();
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