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
    initBlog14List();
    initCategory14List();
    initBlogComment8List();
    initPerson39List();
    initProject34List();
  }

  @Destroy @Remove public void destroy()
  { }

  public void setBlog4(Blog blog15)
  { 
    blogEntry.setBlog(blog15);
  }

  public void setCategory1(Category category15)
  { 
    blogEntry.setCategory(category15);
  }

  public void removeBlogComment1(BlogComment blogComment7)
  { 
    this.getBlogEntry().getComments().remove(blogComment7);
  }

  public void addBlogComment1(BlogComment blogComment7)
  { 
    this.getBlogEntry().getComments().add(blogComment7);
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
      setBlog4(blog14);
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

  private String newCategory14;

  public void setNewCategory14(String p)
  { 
    newCategory14 = p;
  }

  public String getNewCategory14()
  { 
    return newCategory14;
  }

  public void selectCategory14(ValueChangeEvent event)
  { 
    log.info("selectCategory14" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Category category14 = em.find(Category.class, id);
      setCategory1(category14);
    }
  }

  @DataModel("category14List") private Map<String, String> category14List;

  public Map<String, String> getCategory14List()
  { 
    return category14List;
  }

  @Factory("category14List") public void initCategory14List()
  { 
    log.info("initCategory14List");
    category14List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Category").getResultList())
    { 
      Category p = (Category)o;
      category14List.put(p.getName(), p.getId().toString());
    }
  }

  private String newBlogComment8;

  public void setNewBlogComment8(String p)
  { 
    newBlogComment8 = p;
  }

  public String getNewBlogComment8()
  { 
    return newBlogComment8;
  }

  public void selectBlogComment8(ValueChangeEvent event)
  { 
    log.info("selectBlogComment8" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      BlogComment blogComment8 = em.find(BlogComment.class, id);
      addBlogComment1(blogComment8);
    }
  }

  @DataModel("blogComment8List") private Map<String, String> blogComment8List;

  public Map<String, String> getBlogComment8List()
  { 
    return blogComment8List;
  }

  @Factory("blogComment8List") public void initBlogComment8List()
  { 
    log.info("initBlogComment8List");
    blogComment8List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "BlogComment").getResultList())
    { 
      BlogComment p = (BlogComment)o;
      blogComment8List.put(p.getName(), p.getId().toString());
    }
  }

  @DataModel("person39List") private List<Person> person39List;

  public List<Person> getPerson39List()
  { 
    log.info("getPerson39List");
    return person39List;
  }

  @Factory("person39List") public void initPerson39List()
  { 
    log.info("initPerson39List");
    person39List = em.createQuery("from " + "Person").getResultList();
  }

  @DataModel("project34List") private List<ResearchProject> project34List;

  public List<ResearchProject> getProject34List()
  { 
    log.info("getProject34List");
    return project34List;
  }

  @Factory("project34List") public void initProject34List()
  { 
    log.info("initProject34List");
    project34List = em.createQuery("from " + "ResearchProject").getResultList();
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