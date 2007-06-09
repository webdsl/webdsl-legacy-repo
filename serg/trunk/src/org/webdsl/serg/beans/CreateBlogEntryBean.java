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

@Stateful @Name("createBlogEntry") public class CreateBlogEntryBean  implements CreateBlogEntryBeanInterface
{ 
  @Logger private Log log;

  @PersistenceContext(type = EXTENDED) private EntityManager em;

  @In private FacesMessages facesMessages;

  @Create @Begin public void initialize()
  { 
    log.info("createBlogEntry" + ".initalize()");
    BlogEntry var20 = new BlogEntry();
    blogEntry = var20;
    initBlog12List();
    initCategory12List();
    initBlogComment6List();
    initPerson1020List();
    initProject1120List();
  }

  @Destroy @Remove public void destroy()
  { }

  public void setBlog4(Blog blog13)
  { 
    blogEntry.setBlog(blog13);
  }

  public void setCategory1(Category category13)
  { 
    blogEntry.setCategory(category13);
  }

  public void removeBlogComment1(BlogComment blogComment5)
  { 
    this.getBlogEntry().getComments().remove(blogComment5);
  }

  public void addBlogComment1(BlogComment blogComment5)
  { 
    this.getBlogEntry().getComments().add(blogComment5);
  }

  @End public String cancel()
  { 
    return "/" + "home" + ".seam?" + "";
  }

  @End public String save()
  { 
    em.persist(this.getBlogEntry());
    return "/" + "viewBlogEntry" + ".seam?" + ("entry" + "=" + blogEntry.getId() + "");
  }

  private String newBlog12;

  public void setNewBlog12(String p)
  { 
    newBlog12 = p;
  }

  public String getNewBlog12()
  { 
    return newBlog12;
  }

  public void selectBlog12(ValueChangeEvent event)
  { 
    log.info("selectBlog12" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Blog blog12 = em.find(Blog.class, id);
      setBlog4(blog12);
    }
  }

  @DataModel("blog12List") private Map<String, String> blog12List;

  public Map<String, String> getBlog12List()
  { 
    return blog12List;
  }

  @Factory("blog12List") public void initBlog12List()
  { 
    log.info("initBlog12List");
    blog12List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Blog").getResultList())
    { 
      Blog p = (Blog)o;
      blog12List.put(p.getName(), p.getId().toString());
    }
  }

  private String newCategory12;

  public void setNewCategory12(String p)
  { 
    newCategory12 = p;
  }

  public String getNewCategory12()
  { 
    return newCategory12;
  }

  public void selectCategory12(ValueChangeEvent event)
  { 
    log.info("selectCategory12" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Category category12 = em.find(Category.class, id);
      setCategory1(category12);
    }
  }

  @DataModel("category12List") private Map<String, String> category12List;

  public Map<String, String> getCategory12List()
  { 
    return category12List;
  }

  @Factory("category12List") public void initCategory12List()
  { 
    log.info("initCategory12List");
    category12List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Category").getResultList())
    { 
      Category p = (Category)o;
      category12List.put(p.getName(), p.getId().toString());
    }
  }

  private String newBlogComment6;

  public void setNewBlogComment6(String p)
  { 
    newBlogComment6 = p;
  }

  public String getNewBlogComment6()
  { 
    return newBlogComment6;
  }

  public void selectBlogComment6(ValueChangeEvent event)
  { 
    log.info("selectBlogComment6" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      BlogComment blogComment6 = em.find(BlogComment.class, id);
      addBlogComment1(blogComment6);
    }
  }

  @DataModel("blogComment6List") private Map<String, String> blogComment6List;

  public Map<String, String> getBlogComment6List()
  { 
    return blogComment6List;
  }

  @Factory("blogComment6List") public void initBlogComment6List()
  { 
    log.info("initBlogComment6List");
    blogComment6List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "BlogComment").getResultList())
    { 
      BlogComment p = (BlogComment)o;
      blogComment6List.put(p.getName(), p.getId().toString());
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

  private BlogEntry blogEntry;

  public BlogEntry getBlogEntry()
  { 
    log.info("getBlogEntry");
    return blogEntry;
  }

  public void setBlogEntry(BlogEntry blogEntry)
  { 
    log.info("setBlogEntry");
    this.blogEntry = blogEntry;
  }
}