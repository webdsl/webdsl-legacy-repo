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

@Stateful @Name("createBlog") public class CreateBlogBean  implements CreateBlogBeanInterface
{ 
  @Logger private Log log;

  @PersistenceContext(type = EXTENDED) private EntityManager em;

  @In private FacesMessages facesMessages;

  @Create @Begin public void initialize()
  { 
    log.info("createBlog" + ".initalize()");
    Blog var21 = new Blog();
    blog = var21;
    initPerson139List();
    initBlogEntry9List();
    initCategory9List();
    initPerson43List();
    initProject33List();
  }

  @Destroy @Remove public void destroy()
  { }

  public void setPerson4(Person person140)
  { 
    blog.setAuthor(person140);
  }

  public void removeBlogEntry1(BlogEntry blogEntry8)
  { 
    this.getBlog().getEntries().remove(blogEntry8);
  }

  public void addBlogEntry1(BlogEntry blogEntry8)
  { 
    this.getBlog().getEntries().add(blogEntry8);
  }

  public void removeCategory1(Category category8)
  { 
    this.getBlog().getCategories().remove(category8);
  }

  public void addCategory1(Category category8)
  { 
    this.getBlog().getCategories().add(category8);
  }

  @End public String cancel()
  { 
    return "/" + "home" + ".seam?" + "";
  }

  @End public String save()
  { 
    em.persist(this.getBlog());
    return "/" + "viewBlog" + ".seam?" + ("blog" + "=" + blog.getId() + "");
  }

  private String newPerson139;

  public void setNewPerson139(String p)
  { 
    newPerson139 = p;
  }

  public String getNewPerson139()
  { 
    return newPerson139;
  }

  public void selectPerson139(ValueChangeEvent event)
  { 
    log.info("selectPerson139" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Person person139 = em.find(Person.class, id);
      setPerson4(person139);
    }
  }

  @DataModel("person139List") private Map<String, String> person139List;

  public Map<String, String> getPerson139List()
  { 
    return person139List;
  }

  @Factory("person139List") public void initPerson139List()
  { 
    log.info("initPerson139List");
    person139List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Person").getResultList())
    { 
      Person p = (Person)o;
      person139List.put(p.getName(), p.getId().toString());
    }
  }

  private String newBlogEntry9;

  public void setNewBlogEntry9(String p)
  { 
    newBlogEntry9 = p;
  }

  public String getNewBlogEntry9()
  { 
    return newBlogEntry9;
  }

  public void selectBlogEntry9(ValueChangeEvent event)
  { 
    log.info("selectBlogEntry9" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      BlogEntry blogEntry9 = em.find(BlogEntry.class, id);
      addBlogEntry1(blogEntry9);
    }
  }

  @DataModel("blogEntry9List") private Map<String, String> blogEntry9List;

  public Map<String, String> getBlogEntry9List()
  { 
    return blogEntry9List;
  }

  @Factory("blogEntry9List") public void initBlogEntry9List()
  { 
    log.info("initBlogEntry9List");
    blogEntry9List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "BlogEntry").getResultList())
    { 
      BlogEntry p = (BlogEntry)o;
      blogEntry9List.put(p.getName(), p.getId().toString());
    }
  }

  private String newCategory9;

  public void setNewCategory9(String p)
  { 
    newCategory9 = p;
  }

  public String getNewCategory9()
  { 
    return newCategory9;
  }

  public void selectCategory9(ValueChangeEvent event)
  { 
    log.info("selectCategory9" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Category category9 = em.find(Category.class, id);
      addCategory1(category9);
    }
  }

  @DataModel("category9List") private Map<String, String> category9List;

  public Map<String, String> getCategory9List()
  { 
    return category9List;
  }

  @Factory("category9List") public void initCategory9List()
  { 
    log.info("initCategory9List");
    category9List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Category").getResultList())
    { 
      Category p = (Category)o;
      category9List.put(p.getName(), p.getId().toString());
    }
  }

  @DataModel("person43List") private List<Person> person43List;

  public List<Person> getPerson43List()
  { 
    log.info("getPerson43List");
    return person43List;
  }

  @Factory("person43List") public void initPerson43List()
  { 
    log.info("initPerson43List");
    person43List = em.createQuery("from " + "Person").getResultList();
  }

  @DataModel("project33List") private List<ResearchProject> project33List;

  public List<ResearchProject> getProject33List()
  { 
    log.info("getProject33List");
    return project33List;
  }

  @Factory("project33List") public void initProject33List()
  { 
    log.info("initProject33List");
    project33List = em.createQuery("from " + "ResearchProject").getResultList();
  }

  private Blog blog;

  public Blog getBlog()
  { 
    log.info("getBlog");
    return blog;
  }

  public void setBlog(Blog blog)
  { 
    log.info("setBlog");
    this.blog = blog;
  }
}