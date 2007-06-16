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
    Person var22 = new Person();
    person = var22;
    initUser7List();
    initBlog10List();
    initPerson37List();
    initProject30List();
  }

  @Destroy @Remove public void destroy()
  { }

  public void setUser1(User user8)
  { 
    person.setUser(user8);
  }

  public void setBlog1(Blog blog11)
  { 
    person.setBlog(blog11);
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

  private String newUser7;

  public void setNewUser7(String p)
  { 
    newUser7 = p;
  }

  public String getNewUser7()
  { 
    return newUser7;
  }

  public void selectUser7(ValueChangeEvent event)
  { 
    log.info("selectUser7" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      User user7 = em.find(User.class, id);
      setUser1(user7);
    }
  }

  @DataModel("user7List") private Map<String, String> user7List;

  public Map<String, String> getUser7List()
  { 
    return user7List;
  }

  @Factory("user7List") public void initUser7List()
  { 
    log.info("initUser7List");
    user7List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "User").getResultList())
    { 
      User p = (User)o;
      user7List.put(p.getName(), p.getId().toString());
    }
  }

  private String newBlog10;

  public void setNewBlog10(String p)
  { 
    newBlog10 = p;
  }

  public String getNewBlog10()
  { 
    return newBlog10;
  }

  public void selectBlog10(ValueChangeEvent event)
  { 
    log.info("selectBlog10" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Blog blog10 = em.find(Blog.class, id);
      setBlog1(blog10);
    }
  }

  @DataModel("blog10List") private Map<String, String> blog10List;

  public Map<String, String> getBlog10List()
  { 
    return blog10List;
  }

  @Factory("blog10List") public void initBlog10List()
  { 
    log.info("initBlog10List");
    blog10List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Blog").getResultList())
    { 
      Blog p = (Blog)o;
      blog10List.put(p.getName(), p.getId().toString());
    }
  }

  @DataModel("person37List") private List<Person> person37List;

  public List<Person> getPerson37List()
  { 
    log.info("getPerson37List");
    return person37List;
  }

  @Factory("person37List") public void initPerson37List()
  { 
    log.info("initPerson37List");
    person37List = em.createQuery("from " + "Person").getResultList();
  }

  @DataModel("project30List") private List<ResearchProject> project30List;

  public List<ResearchProject> getProject30List()
  { 
    log.info("getProject30List");
    return project30List;
  }

  @Factory("project30List") public void initProject30List()
  { 
    log.info("initProject30List");
    project30List = em.createQuery("from " + "ResearchProject").getResultList();
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