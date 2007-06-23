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
    Person var24 = new Person();
    person = var24;
    initUser9List();
    initBlog10List();
    initPerson38List();
    initProject30List();
  }

  @Destroy @Remove public void destroy()
  { }

  public void setUser2(User user10)
  { 
    person.setUser(user10);
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

  private String newUser9;

  public void setNewUser9(String p)
  { 
    newUser9 = p;
  }

  public String getNewUser9()
  { 
    return newUser9;
  }

  public void selectUser9(ValueChangeEvent event)
  { 
    log.info("selectUser9" + ": new value = " + " " + event.getNewValue());
    User user9 = (User)event.getNewValue();
  }

  @DataModel("user9List") private Map<String, String> user9List;

  public Map<String, String> getUser9List()
  { 
    return user9List;
  }

  @Factory("user9List") public void initUser9List()
  { 
    log.info("initUser9List");
    user9List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "User").getResultList())
    { 
      User p = (User)o;
      user9List.put(p.getName(), p.getId().toString());
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
    Blog blog10 = (Blog)event.getNewValue();
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

  @DataModel("person38List") private List<Person> person38List;

  public List<Person> getPerson38List()
  { 
    log.info("getPerson38List");
    return person38List;
  }

  @Factory("person38List") public void initPerson38List()
  { 
    log.info("initPerson38List");
    person38List = em.createQuery("from " + "Person").getResultList();
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