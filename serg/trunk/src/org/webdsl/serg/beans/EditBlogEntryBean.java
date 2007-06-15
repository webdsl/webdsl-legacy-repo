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
    initBlogComment7List();
    initPerson40List();
    initProject35List();
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

  public void removeBlogComment0(BlogComment blogComment6)
  { 
    this.getBlogEntry().getComments().remove(blogComment6);
  }

  public void addBlogComment0(BlogComment blogComment6)
  { 
    this.getBlogEntry().getComments().add(blogComment6);
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

  private String newBlogComment7;

  public void setNewBlogComment7(String p)
  { 
    newBlogComment7 = p;
  }

  public String getNewBlogComment7()
  { 
    return newBlogComment7;
  }

  public void selectBlogComment7(ValueChangeEvent event)
  { 
    log.info("selectBlogComment7" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      BlogComment blogComment7 = em.find(BlogComment.class, id);
      addBlogComment0(blogComment7);
    }
  }

  @DataModel("blogComment7List") private Map<String, String> blogComment7List;

  public Map<String, String> getBlogComment7List()
  { 
    return blogComment7List;
  }

  @Factory("blogComment7List") public void initBlogComment7List()
  { 
    log.info("initBlogComment7List");
    blogComment7List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "BlogComment").getResultList())
    { 
      BlogComment p = (BlogComment)o;
      blogComment7List.put(p.getName(), p.getId().toString());
    }
  }

  @DataModel("person40List") private List<Person> person40List;

  public List<Person> getPerson40List()
  { 
    log.info("getPerson40List");
    return person40List;
  }

  @Factory("person40List") public void initPerson40List()
  { 
    log.info("initPerson40List");
    person40List = em.createQuery("from " + "Person").getResultList();
  }

  @DataModel("project35List") private List<ResearchProject> project35List;

  public List<ResearchProject> getProject35List()
  { 
    log.info("getProject35List");
    return project35List;
  }

  @Factory("project35List") public void initProject35List()
  { 
    log.info("initProject35List");
    project35List = em.createQuery("from " + "ResearchProject").getResultList();
  }
}