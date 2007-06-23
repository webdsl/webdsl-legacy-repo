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
    Blog var25 = new Blog();
    blog = var25;
    initPerson155List();
    initBlogEntry9List();
    initCategory9List();
    initPerson44List();
    initProject33List();
  }

  @Destroy @Remove public void destroy()
  { }

  public void setPerson4(Person person156)
  { 
    blog.setAuthor(person156);
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

  private String newPerson155;

  public void setNewPerson155(String p)
  { 
    newPerson155 = p;
  }

  public String getNewPerson155()
  { 
    return newPerson155;
  }

  public void selectPerson155(ValueChangeEvent event)
  { 
    log.info("selectPerson155" + ": new value = " + " " + event.getNewValue());
    Person person155 = (Person)event.getNewValue();
  }

  @DataModel("person155List") private Map<String, String> person155List;

  public Map<String, String> getPerson155List()
  { 
    return person155List;
  }

  @Factory("person155List") public void initPerson155List()
  { 
    log.info("initPerson155List");
    person155List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Person").getResultList())
    { 
      Person p = (Person)o;
      person155List.put(p.getName(), p.getId().toString());
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
    BlogEntry blogEntry9 = (BlogEntry)event.getNewValue();
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
    Category category9 = (Category)event.getNewValue();
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

  @DataModel("person44List") private List<Person> person44List;

  public List<Person> getPerson44List()
  { 
    log.info("getPerson44List");
    return person44List;
  }

  @Factory("person44List") public void initPerson44List()
  { 
    log.info("initPerson44List");
    person44List = em.createQuery("from " + "Person").getResultList();
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