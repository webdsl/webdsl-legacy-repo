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
    Person var18 = new Person();
    person = var18;
    initUser5List();
    initBlog6List();
    initPerson1016List();
    initProject1116List();
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

  @DataModel("person1016List") private List<Person> person1016List;

  public List<Person> getPerson1016List()
  { 
    log.info("getPerson1016List");
    return person1016List;
  }

  @Factory("person1016List") public void initPerson1016List()
  { 
    log.info("initPerson1016List");
    person1016List = em.createQuery("from " + "Person").getResultList();
  }

  @DataModel("project1116List") private List<ResearchProject> project1116List;

  public List<ResearchProject> getProject1116List()
  { 
    log.info("getProject1116List");
    return project1116List;
  }

  @Factory("project1116List") public void initProject1116List()
  { 
    log.info("initProject1116List");
    project1116List = em.createQuery("from " + "ResearchProject").getResultList();
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