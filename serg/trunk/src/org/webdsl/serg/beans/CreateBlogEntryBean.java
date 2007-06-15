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
    BlogEntry var22 = new BlogEntry();
    blogEntry = var22;
    initBlog13List();
    initCategory11List();
    initBlogComment9List();
    initPerson41List();
    initProject36List();
  }

  @Destroy @Remove public void destroy()
  { }

  public void setBlog3(Blog blog14)
  { 
    blogEntry.setBlog(blog14);
  }

  public void setCategory1(Category category12)
  { 
    blogEntry.setCategory(category12);
  }

  public void removeBlogComment1(BlogComment blogComment8)
  { 
    this.getBlogEntry().getComments().remove(blogComment8);
  }

  public void addBlogComment1(BlogComment blogComment8)
  { 
    this.getBlogEntry().getComments().add(blogComment8);
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

  private String newBlog13;

  public void setNewBlog13(String p)
  { 
    newBlog13 = p;
  }

  public String getNewBlog13()
  { 
    return newBlog13;
  }

  public void selectBlog13(ValueChangeEvent event)
  { 
    log.info("selectBlog13" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Blog blog13 = em.find(Blog.class, id);
      setBlog3(blog13);
    }
  }

  @DataModel("blog13List") private Map<String, String> blog13List;

  public Map<String, String> getBlog13List()
  { 
    return blog13List;
  }

  @Factory("blog13List") public void initBlog13List()
  { 
    log.info("initBlog13List");
    blog13List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Blog").getResultList())
    { 
      Blog p = (Blog)o;
      blog13List.put(p.getName(), p.getId().toString());
    }
  }

  private String newCategory11;

  public void setNewCategory11(String p)
  { 
    newCategory11 = p;
  }

  public String getNewCategory11()
  { 
    return newCategory11;
  }

  public void selectCategory11(ValueChangeEvent event)
  { 
    log.info("selectCategory11" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Category category11 = em.find(Category.class, id);
      setCategory1(category11);
    }
  }

  @DataModel("category11List") private Map<String, String> category11List;

  public Map<String, String> getCategory11List()
  { 
    return category11List;
  }

  @Factory("category11List") public void initCategory11List()
  { 
    log.info("initCategory11List");
    category11List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Category").getResultList())
    { 
      Category p = (Category)o;
      category11List.put(p.getName(), p.getId().toString());
    }
  }

  private String newBlogComment9;

  public void setNewBlogComment9(String p)
  { 
    newBlogComment9 = p;
  }

  public String getNewBlogComment9()
  { 
    return newBlogComment9;
  }

  public void selectBlogComment9(ValueChangeEvent event)
  { 
    log.info("selectBlogComment9" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      BlogComment blogComment9 = em.find(BlogComment.class, id);
      addBlogComment1(blogComment9);
    }
  }

  @DataModel("blogComment9List") private Map<String, String> blogComment9List;

  public Map<String, String> getBlogComment9List()
  { 
    return blogComment9List;
  }

  @Factory("blogComment9List") public void initBlogComment9List()
  { 
    log.info("initBlogComment9List");
    blogComment9List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "BlogComment").getResultList())
    { 
      BlogComment p = (BlogComment)o;
      blogComment9List.put(p.getName(), p.getId().toString());
    }
  }

  @DataModel("person41List") private List<Person> person41List;

  public List<Person> getPerson41List()
  { 
    log.info("getPerson41List");
    return person41List;
  }

  @Factory("person41List") public void initPerson41List()
  { 
    log.info("initPerson41List");
    person41List = em.createQuery("from " + "Person").getResultList();
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