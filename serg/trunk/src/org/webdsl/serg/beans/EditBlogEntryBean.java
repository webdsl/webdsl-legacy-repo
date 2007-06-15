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

@Stateful @Name("editBlogEntry") public class EditBlogEntryBean  implements EditBlogEntryBeanInterface
{ 
  @Logger private Log log;

  @PersistenceContext(type = EXTENDED) private EntityManager em;

  @In private FacesMessages facesMessages;

  @Create @Begin public void initialize()
  { 
    log.info("editBlogEntry" + ".initalize()");
    if(blogEntryId == null)
    { 
      log.info("No " + "blogEntryId" + " defined, creating new " + "BlogEntry");
      blogEntry = new BlogEntry();
    }
    else
    { 
      blogEntry = em.find(BlogEntry.class, blogEntryId);
    }
    initBlog11List();
    initCategory9List();
    initBlogComment6List();
    initPerson39List();
    initProject34List();
  }

  @Destroy @Remove public void destroy()
  { }

  @RequestParameter("blogEntry") private Long blogEntryId;

  private BlogEntry blogEntry;

  public void setBlogEntry(BlogEntry blogEntry)
  { 
    log.info("setBlogEntry");
    this.blogEntry = blogEntry;
  }

  public BlogEntry getBlogEntry()
  { 
    log.info("getBlogEntry");
    return blogEntry;
  }

  public void setBlog2(Blog blog12)
  { 
    blogEntry.setBlog(blog12);
  }

  public void setCategory0(Category category10)
  { 
    blogEntry.setCategory(category10);
  }

  public void removeBlogComment0(BlogComment blogComment5)
  { 
    this.getBlogEntry().getComments().remove(blogComment5);
  }

  public void addBlogComment0(BlogComment blogComment5)
  { 
    this.getBlogEntry().getComments().add(blogComment5);
  }

  @End public String cancel()
  { 
    return "/" + "viewBlogEntry" + ".seam?" + ("entry" + "=" + blogEntry.getId() + "");
  }

  @End public String save()
  { 
    em.persist(this.getBlogEntry());
    return "/" + "viewBlogEntry" + ".seam?" + ("entry" + "=" + blogEntry.getId() + "");
  }

  private String newBlog11;

  public void setNewBlog11(String p)
  { 
    newBlog11 = p;
  }

  public String getNewBlog11()
  { 
    return newBlog11;
  }

  public void selectBlog11(ValueChangeEvent event)
  { 
    log.info("selectBlog11" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Blog blog11 = em.find(Blog.class, id);
      setBlog2(blog11);
    }
  }

  @DataModel("blog11List") private Map<String, String> blog11List;

  public Map<String, String> getBlog11List()
  { 
    return blog11List;
  }

  @Factory("blog11List") public void initBlog11List()
  { 
    log.info("initBlog11List");
    blog11List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Blog").getResultList())
    { 
      Blog p = (Blog)o;
      blog11List.put(p.getName(), p.getId().toString());
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
      setCategory0(category9);
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
      addBlogComment0(blogComment6);
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
}