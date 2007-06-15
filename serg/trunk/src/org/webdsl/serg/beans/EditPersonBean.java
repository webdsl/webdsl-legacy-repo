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
    initBlog7List();
    initPerson30List();
    initProject29List();
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

  public void setBlog0(Blog blog8)
  { 
    person.setBlog(blog8);
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

  private String newBlog7;

  public void setNewBlog7(String p)
  { 
    newBlog7 = p;
  }

  public String getNewBlog7()
  { 
    return newBlog7;
  }

  public void selectBlog7(ValueChangeEvent event)
  { 
    log.info("selectBlog7" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Blog blog7 = em.find(Blog.class, id);
      setBlog0(blog7);
    }
  }

  @DataModel("blog7List") private Map<String, String> blog7List;

  public Map<String, String> getBlog7List()
  { 
    return blog7List;
  }

  @Factory("blog7List") public void initBlog7List()
  { 
    log.info("initBlog7List");
    blog7List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Blog").getResultList())
    { 
      Blog p = (Blog)o;
      blog7List.put(p.getName(), p.getId().toString());
    }
  }

  @DataModel("person30List") private List<Person> person30List;

  public List<Person> getPerson30List()
  { 
    log.info("getPerson30List");
    return person30List;
  }

  @Factory("person30List") public void initPerson30List()
  { 
    log.info("initPerson30List");
    person30List = em.createQuery("from " + "Person").getResultList();
  }

  @DataModel("project29List") private List<ResearchProject> project29List;

  public List<ResearchProject> getProject29List()
  { 
    log.info("getProject29List");
    return project29List;
  }

  @Factory("project29List") public void initProject29List()
  { 
    log.info("initProject29List");
    project29List = em.createQuery("from " + "ResearchProject").getResultList();
  }
}