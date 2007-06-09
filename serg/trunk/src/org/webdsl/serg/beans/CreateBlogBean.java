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
    Blog var19 = new Blog();
    blog = var19;
    initPerson15List();
    initBlogEntry5List();
    initCategory6List();
    initPerson1020List();
    initProject1120List();
  }

  @Destroy @Remove public void destroy()
  { }

  public void setPerson4(Person person16)
  { 
    blog.setAuthor(person16);
  }

  public void removeBlogEntry1(BlogEntry blogEntry4)
  { 
    this.getBlog().getEntries().remove(blogEntry4);
  }

  public void addBlogEntry1(BlogEntry blogEntry4)
  { 
    this.getBlog().getEntries().add(blogEntry4);
  }

  public void removeCategory1(Category category5)
  { 
    this.getBlog().getCategories().remove(category5);
  }

  public void addCategory1(Category category5)
  { 
    this.getBlog().getCategories().add(category5);
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

  private String newPerson15;

  public void setNewPerson15(String p)
  { 
    newPerson15 = p;
  }

  public String getNewPerson15()
  { 
    return newPerson15;
  }

  public void selectPerson15(ValueChangeEvent event)
  { 
    log.info("selectPerson15" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Person person15 = em.find(Person.class, id);
      setPerson4(person15);
    }
  }

  @DataModel("person15List") private Map<String, String> person15List;

  public Map<String, String> getPerson15List()
  { 
    return person15List;
  }

  @Factory("person15List") public void initPerson15List()
  { 
    log.info("initPerson15List");
    person15List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Person").getResultList())
    { 
      Person p = (Person)o;
      person15List.put(p.getName(), p.getId().toString());
    }
  }

  private String newBlogEntry5;

  public void setNewBlogEntry5(String p)
  { 
    newBlogEntry5 = p;
  }

  public String getNewBlogEntry5()
  { 
    return newBlogEntry5;
  }

  public void selectBlogEntry5(ValueChangeEvent event)
  { 
    log.info("selectBlogEntry5" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      BlogEntry blogEntry5 = em.find(BlogEntry.class, id);
      addBlogEntry1(blogEntry5);
    }
  }

  @DataModel("blogEntry5List") private Map<String, String> blogEntry5List;

  public Map<String, String> getBlogEntry5List()
  { 
    return blogEntry5List;
  }

  @Factory("blogEntry5List") public void initBlogEntry5List()
  { 
    log.info("initBlogEntry5List");
    blogEntry5List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "BlogEntry").getResultList())
    { 
      BlogEntry p = (BlogEntry)o;
      blogEntry5List.put(p.getName(), p.getId().toString());
    }
  }

  private String newCategory6;

  public void setNewCategory6(String p)
  { 
    newCategory6 = p;
  }

  public String getNewCategory6()
  { 
    return newCategory6;
  }

  public void selectCategory6(ValueChangeEvent event)
  { 
    log.info("selectCategory6" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Category category6 = em.find(Category.class, id);
      addCategory1(category6);
    }
  }

  @DataModel("category6List") private Map<String, String> category6List;

  public Map<String, String> getCategory6List()
  { 
    return category6List;
  }

  @Factory("category6List") public void initCategory6List()
  { 
    log.info("initCategory6List");
    category6List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Category").getResultList())
    { 
      Category p = (Category)o;
      category6List.put(p.getName(), p.getId().toString());
    }
  }

  @DataModel("person1020List") private List<Person> person1020List;

  public List<Person> getPerson1020List()
  { 
    log.info("getPerson1020List");
    return person1020List;
  }

  @Factory("person1020List") public void initPerson1020List()
  { 
    log.info("initPerson1020List");
    person1020List = em.createQuery("from " + "Person").getResultList();
  }

  @DataModel("project1120List") private List<ResearchProject> project1120List;

  public List<ResearchProject> getProject1120List()
  { 
    log.info("getProject1120List");
    return project1120List;
  }

  @Factory("project1120List") public void initProject1120List()
  { 
    log.info("initProject1120List");
    project1120List = em.createQuery("from " + "ResearchProject").getResultList();
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