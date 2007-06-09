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

@Stateful @Name("createPerson") public class CreatePersonBean  implements CreatePersonBeanInterface
{ 
  @Logger private Log log;

  @PersistenceContext(type = EXTENDED) private EntityManager em;

  @In private FacesMessages facesMessages;

  @Create @Begin public void initialize()
  { 
    log.info("createPerson" + ".initalize()");
    Person var33 = new Person();
    person = var33;
    initUser5List();
    initBlog6List();
    initPerson1014List();
    initProject1114List();
  }

  @Destroy @Remove public void destroy()
  { }

  public void setUser1(User user6)
  { 
    person.setUser(user6);
  }

  public void setBlog1(Blog blog7)
  { 
    person.setBlog(blog7);
  }

  @End public String cancel()
  { 
    return "/" + "home" + ".seam?" + "";
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
      setUser1(user5);
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
      setBlog1(blog6);
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

  @DataModel("person1014List") private List<Person> person1014List;

  public List<Person> getPerson1014List()
  { 
    log.info("getPerson1014List");
    return person1014List;
  }

  @Factory("person1014List") public void initPerson1014List()
  { 
    log.info("initPerson1014List");
    person1014List = em.createQuery("from " + "Person").getResultList();
  }

  @DataModel("project1114List") private List<ResearchProject> project1114List;

  public List<ResearchProject> getProject1114List()
  { 
    log.info("getProject1114List");
    return project1114List;
  }

  @Factory("project1114List") public void initProject1114List()
  { 
    log.info("initProject1114List");
    project1114List = em.createQuery("from " + "ResearchProject").getResultList();
  }

  private Person person;

  public Person getPerson()
  { 
    log.info("getPerson");
    return person;
  }

  public void setPerson(Person person)
  { 
    log.info("setPerson");
    this.person = person;
  }
}