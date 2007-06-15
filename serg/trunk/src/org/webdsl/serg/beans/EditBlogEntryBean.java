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
    initBlog12List();
    initCategory10List();
    initBlogComment7List();
    initPerson45List();
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

  public void setBlog2(Blog blog13)
  { 
    blogEntry.setBlog(blog13);
  }

  public void setCategory0(Category category11)
  { 
    blogEntry.setCategory(category11);
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
      setBlog2(blog12);
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

  private String newCategory10;

  public void setNewCategory10(String p)
  { 
    newCategory10 = p;
  }

  public String getNewCategory10()
  { 
    return newCategory10;
  }

  public void selectCategory10(ValueChangeEvent event)
  { 
    log.info("selectCategory10" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Category category10 = em.find(Category.class, id);
      setCategory0(category10);
    }
  }

  @DataModel("category10List") private Map<String, String> category10List;

  public Map<String, String> getCategory10List()
  { 
    return category10List;
  }

  @Factory("category10List") public void initCategory10List()
  { 
    log.info("initCategory10List");
    category10List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Category").getResultList())
    { 
      Category p = (Category)o;
      category10List.put(p.getName(), p.getId().toString());
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

  @DataModel("person45List") private List<Person> person45List;

  public List<Person> getPerson45List()
  { 
    log.info("getPerson45List");
    return person45List;
  }

  @Factory("person45List") public void initPerson45List()
  { 
    log.info("initPerson45List");
    person45List = em.createQuery("from " + "Person").getResultList();
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