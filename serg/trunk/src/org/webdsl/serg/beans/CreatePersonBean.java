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
    initUser7List();
    initBlog8List();
    initPerson31List();
    initProject29List();
  }

  @Destroy @Remove public void destroy()
  { }

  public void setUser1(User user8)
  { 
    person.setUser(user8);
  }

  public void setBlog1(Blog blog9)
  { 
    person.setBlog(blog9);
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
      setBlog1(blog8);
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

  @DataModel("person31List") private List<Person> person31List;

  public List<Person> getPerson31List()
  { 
    log.info("getPerson31List");
    return person31List;
  }

  @Factory("person31List") public void initPerson31List()
  { 
    log.info("initPerson31List");
    person31List = em.createQuery("from " + "Person").getResultList();
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