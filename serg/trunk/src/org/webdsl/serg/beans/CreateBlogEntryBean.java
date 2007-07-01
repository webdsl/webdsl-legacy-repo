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
    BlogEntry var26 = new BlogEntry();
    blogEntry = var26;
    initBlog14List();
    initCategory12List();
    initBlogComment10List();
    initPerson49List();
    initProject36List();
  }

  @Destroy @Remove public void destroy()
  { }

  public void setBlog3(Blog blog15)
  { 
    blogEntry.setBlog(blog15);
  }

  public void setCategory1(Category category13)
  { 
    blogEntry.setCategory(category13);
  }

  public void removeBlogComment1(BlogComment blogComment9)
  { 
    this.getBlogEntry().getComments().remove(blogComment9);
  }

  public void addBlogComment1(BlogComment blogComment9)
  { 
    this.getBlogEntry().getComments().add(blogComment9);
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

  private String newBlog14;

  public void setNewBlog14(String p)
  { 
    newBlog14 = p;
  }

  public String getNewBlog14()
  { 
    return newBlog14;
  }

  public void selectBlog14(ValueChangeEvent event)
  { 
    log.info("selectBlog14" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Blog blog14 = em.find(Blog.class, id);
      setBlog3(blog14);
    }
  }

  @DataModel("blog14List") private Map<String, String> blog14List;

  public Map<String, String> getBlog14List()
  { 
    return blog14List;
  }

  @Factory("blog14List") public void initBlog14List()
  { 
    log.info("initBlog14List");
    blog14List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Blog").getResultList())
    { 
      Blog p = (Blog)o;
      blog14List.put(p.getName(), p.getId().toString());
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

  private String newBlogComment10;

  public void setNewBlogComment10(String p)
  { 
    newBlogComment10 = p;
  }

  public String getNewBlogComment10()
  { 
    return newBlogComment10;
  }

  public void selectBlogComment10(ValueChangeEvent event)
  { 
    log.info("selectBlogComment10" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      BlogComment blogComment10 = em.find(BlogComment.class, id);
      addBlogComment1(blogComment10);
    }
  }

  @DataModel("blogComment10List") private Map<String, String> blogComment10List;

  public Map<String, String> getBlogComment10List()
  { 
    return blogComment10List;
  }

  @Factory("blogComment10List") public void initBlogComment10List()
  { 
    log.info("initBlogComment10List");
    blogComment10List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "BlogComment").getResultList())
    { 
      BlogComment p = (BlogComment)o;
      blogComment10List.put(p.getName(), p.getId().toString());
    }
  }

  @DataModel("person49List") private List<Person> person49List;

  public List<Person> getPerson49List()
  { 
    log.info("getPerson49List");
    return person49List;
  }

  @Factory("person49List") public void initPerson49List()
  { 
    log.info("initPerson49List");
    person49List = em.createQuery("from " + "Person").getResultList();
  }

  @DataModel("project36List") private List<ResearchProject> project36List;

  public List<ResearchProject> getProject36List()
  { 
    log.info("getProject36List");
    return project36List;
  }

  @Factory("project36List") public void initProject36List()
  { 
    log.info("initProject36List");
    project36List = em.createQuery("from " + "ResearchProject").getResultList();
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