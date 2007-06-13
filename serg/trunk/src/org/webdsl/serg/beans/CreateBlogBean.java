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
    initPerson116List();
    initBlogEntry7List();
    initCategory8List();
    initPerson37List();
    initProject32List();
  }

  @Destroy @Remove public void destroy()
  { }

  public void setPerson3(Person person117)
  { 
    blog.setAuthor(person117);
  }

  public void removeBlogEntry1(BlogEntry blogEntry6)
  { 
    this.getBlog().getEntries().remove(blogEntry6);
  }

  public void addBlogEntry1(BlogEntry blogEntry6)
  { 
    this.getBlog().getEntries().add(blogEntry6);
  }

  public void removeCategory1(Category category7)
  { 
    this.getBlog().getCategories().remove(category7);
  }

  public void addCategory1(Category category7)
  { 
    this.getBlog().getCategories().add(category7);
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

  private String newPerson116;

  public void setNewPerson116(String p)
  { 
    newPerson116 = p;
  }

  public String getNewPerson116()
  { 
    return newPerson116;
  }

  public void selectPerson116(ValueChangeEvent event)
  { 
    log.info("selectPerson116" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Person person116 = em.find(Person.class, id);
      setPerson3(person116);
    }
  }

  @DataModel("person116List") private Map<String, String> person116List;

  public Map<String, String> getPerson116List()
  { 
    return person116List;
  }

  @Factory("person116List") public void initPerson116List()
  { 
    log.info("initPerson116List");
    person116List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Person").getResultList())
    { 
      Person p = (Person)o;
      person116List.put(p.getName(), p.getId().toString());
    }
  }

  private String newBlogEntry7;

  public void setNewBlogEntry7(String p)
  { 
    newBlogEntry7 = p;
  }

  public String getNewBlogEntry7()
  { 
    return newBlogEntry7;
  }

  public void selectBlogEntry7(ValueChangeEvent event)
  { 
    log.info("selectBlogEntry7" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      BlogEntry blogEntry7 = em.find(BlogEntry.class, id);
      addBlogEntry1(blogEntry7);
    }
  }

  @DataModel("blogEntry7List") private Map<String, String> blogEntry7List;

  public Map<String, String> getBlogEntry7List()
  { 
    return blogEntry7List;
  }

  @Factory("blogEntry7List") public void initBlogEntry7List()
  { 
    log.info("initBlogEntry7List");
    blogEntry7List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "BlogEntry").getResultList())
    { 
      BlogEntry p = (BlogEntry)o;
      blogEntry7List.put(p.getName(), p.getId().toString());
    }
  }

  private String newCategory8;

  public void setNewCategory8(String p)
  { 
    newCategory8 = p;
  }

  public String getNewCategory8()
  { 
    return newCategory8;
  }

  public void selectCategory8(ValueChangeEvent event)
  { 
    log.info("selectCategory8" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Category category8 = em.find(Category.class, id);
      addCategory1(category8);
    }
  }

  @DataModel("category8List") private Map<String, String> category8List;

  public Map<String, String> getCategory8List()
  { 
    return category8List;
  }

  @Factory("category8List") public void initCategory8List()
  { 
    log.info("initCategory8List");
    category8List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Category").getResultList())
    { 
      Category p = (Category)o;
      category8List.put(p.getName(), p.getId().toString());
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

  @DataModel("project32List") private List<ResearchProject> project32List;

  public List<ResearchProject> getProject32List()
  { 
    log.info("getProject32List");
    return project32List;
  }

  @Factory("project32List") public void initProject32List()
  { 
    log.info("initProject32List");
    project32List = em.createQuery("from " + "ResearchProject").getResultList();
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