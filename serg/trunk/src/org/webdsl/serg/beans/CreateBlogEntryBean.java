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
    initBlog13List();
    initCategory11List();
    initBlogComment8List();
    initPerson40List();
    initProject35List();
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