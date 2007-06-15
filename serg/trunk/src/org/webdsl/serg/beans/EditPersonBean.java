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
    initBlog8List();
    initPerson35List();
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

  public void setBlog0(Blog blog9)
  { 
    person.setBlog(blog9);
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

  private String newBlog8;

  public void setNewBlog8(String p)
  { 
    newBlog8 = p;
  }

  public String getNewBlog8()
  { 
    return newBlog8;
  }

  public void selectBlog8(ValueChangeEvent event)
  { 
    log.info("selectBlog8" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Blog blog8 = em.find(Blog.class, id);
      setBlog0(blog8);
    }
  }

  @DataModel("blog8List") private Map<String, String> blog8List;

  public Map<String, String> getBlog8List()
  { 
    return blog8List;
  }

  @Factory("blog8List") public void initBlog8List()
  { 
    log.info("initBlog8List");
    blog8List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Blog").getResultList())
    { 
      Blog p = (Blog)o;
      blog8List.put(p.getName(), p.getId().toString());
    }
  }

  @DataModel("person35List") private List<Person> person35List;

  public List<Person> getPerson35List()
  { 
    log.info("getPerson35List");
    return person35List;
  }

  @Factory("person35List") public void initPerson35List()
  { 
    log.info("initPerson35List");
    person35List = em.createQuery("from " + "Person").getResultList();
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