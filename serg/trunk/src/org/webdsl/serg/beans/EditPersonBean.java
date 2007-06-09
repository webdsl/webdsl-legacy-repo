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
    initUser3List();
    initBlog4List();
    initPerson1017List();
    initProject1117List();
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

  public void setUser0(User user4)
  { 
    person.setUser(user4);
  }

  public void setBlog0(Blog blog5)
  { 
    person.setBlog(blog5);
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

  private String newUser3;

  public void setNewUser3(String p)
  { 
    newUser3 = p;
  }

  public String getNewUser3()
  { 
    return newUser3;
  }

  public void selectUser3(ValueChangeEvent event)
  { 
    log.info("selectUser3" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      User user3 = em.find(User.class, id);
      setUser0(user3);
    }
  }

  @DataModel("user3List") private Map<String, String> user3List;

  public Map<String, String> getUser3List()
  { 
    return user3List;
  }

  @Factory("user3List") public void initUser3List()
  { 
    log.info("initUser3List");
    user3List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "User").getResultList())
    { 
      User p = (User)o;
      user3List.put(p.getName(), p.getId().toString());
    }
  }

  private String newBlog4;

  public void setNewBlog4(String p)
  { 
    newBlog4 = p;
  }

  public String getNewBlog4()
  { 
    return newBlog4;
  }

  public void selectBlog4(ValueChangeEvent event)
  { 
    log.info("selectBlog4" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Blog blog4 = em.find(Blog.class, id);
      setBlog0(blog4);
    }
  }

  @DataModel("blog4List") private Map<String, String> blog4List;

  public Map<String, String> getBlog4List()
  { 
    return blog4List;
  }

  @Factory("blog4List") public void initBlog4List()
  { 
    log.info("initBlog4List");
    blog4List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Blog").getResultList())
    { 
      Blog p = (Blog)o;
      blog4List.put(p.getName(), p.getId().toString());
    }
  }

  @DataModel("person1017List") private List<Person> person1017List;

  public List<Person> getPerson1017List()
  { 
    log.info("getPerson1017List");
    return person1017List;
  }

  @Factory("person1017List") public void initPerson1017List()
  { 
    log.info("initPerson1017List");
    person1017List = em.createQuery("from " + "Person").getResultList();
  }

  @DataModel("project1117List") private List<ResearchProject> project1117List;

  public List<ResearchProject> getProject1117List()
  { 
    log.info("getProject1117List");
    return project1117List;
  }

  @Factory("project1117List") public void initProject1117List()
  { 
    log.info("initProject1117List");
    project1117List = em.createQuery("from " + "ResearchProject").getResultList();
  }
}