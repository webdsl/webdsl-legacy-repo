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

@Stateful @Name("editPerson") public class EditPersonBean  implements EditPersonBeanInterface
{ 
  @Logger private Log log;

  @PersistenceContext(type = EXTENDED) private EntityManager em;

  @In private FacesMessages facesMessages;

  @Create @Begin public void initialize()
  { 
    log.info("editPerson" + ".initalize()");
    if(personId == null)
    { 
      log.info("No " + "personId" + " defined, creating new " + "Person");
      person = new Person();
    }
    else
    { 
      person = em.find(Person.class, personId);
    }
    initUser5List();
    initBlog6List();
    initPerson29List();
    initProject28List();
  }

  @Destroy @Remove public void destroy()
  { }

  @RequestParameter("person") private Long personId;

  private Person person;

  public void setPerson(Person person)
  { 
    log.info("setPerson");
    this.person = person;
  }

  public Person getPerson()
  { 
    log.info("getPerson");
    return person;
  }

  public void setUser0(User user6)
  { 
    person.setUser(user6);
  }

  public void setBlog0(Blog blog7)
  { 
    person.setBlog(blog7);
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

  private String newUser5;

  public void setNewUser5(String p)
  { 
    newUser5 = p;
  }

  public String getNewUser5()
  { 
    return newUser5;
  }

  public void selectUser5(ValueChangeEvent event)
  { 
    log.info("selectUser5" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      User user5 = em.find(User.class, id);
      setUser0(user5);
    }
  }

  @DataModel("user5List") private Map<String, String> user5List;

  public Map<String, String> getUser5List()
  { 
    return user5List;
  }

  @Factory("user5List") public void initUser5List()
  { 
    log.info("initUser5List");
    user5List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "User").getResultList())
    { 
      User p = (User)o;
      user5List.put(p.getName(), p.getId().toString());
    }
  }

  private String newBlog6;

  public void setNewBlog6(String p)
  { 
    newBlog6 = p;
  }

  public String getNewBlog6()
  { 
    return newBlog6;
  }

  public void selectBlog6(ValueChangeEvent event)
  { 
    log.info("selectBlog6" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Blog blog6 = em.find(Blog.class, id);
      setBlog0(blog6);
    }
  }

  @DataModel("blog6List") private Map<String, String> blog6List;

  public Map<String, String> getBlog6List()
  { 
    return blog6List;
  }

  @Factory("blog6List") public void initBlog6List()
  { 
    log.info("initBlog6List");
    blog6List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Blog").getResultList())
    { 
      Blog p = (Blog)o;
      blog6List.put(p.getName(), p.getId().toString());
    }
  }

  @DataModel("person29List") private List<Person> person29List;

  public List<Person> getPerson29List()
  { 
    log.info("getPerson29List");
    return person29List;
  }

  @Factory("person29List") public void initPerson29List()
  { 
    log.info("initPerson29List");
    person29List = em.createQuery("from " + "Person").getResultList();
  }

  @DataModel("project28List") private List<ResearchProject> project28List;

  public List<ResearchProject> getProject28List()
  { 
    log.info("getProject28List");
    return project28List;
  }

  @Factory("project28List") public void initProject28List()
  { 
    log.info("initProject28List");
    project28List = em.createQuery("from " + "ResearchProject").getResultList();
  }
}