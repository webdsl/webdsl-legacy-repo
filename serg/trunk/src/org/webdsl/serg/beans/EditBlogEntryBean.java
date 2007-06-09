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
    initBlog10List();
    initCategory10List();
    initBlogComment4List();
    initPerson1019List();
    initProject1119List();
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

  public void setBlog3(Blog blog11)
  { 
    blogEntry.setBlog(blog11);
  }

  public void setCategory0(Category category11)
  { 
    blogEntry.setCategory(category11);
  }

  public void removeBlogComment0(BlogComment blogComment3)
  { 
    this.getBlogEntry().getComments().remove(blogComment3);
  }

  public void addBlogComment0(BlogComment blogComment3)
  { 
    this.getBlogEntry().getComments().add(blogComment3);
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

  private String newBlog10;

  public void setNewBlog10(String p)
  { 
    newBlog10 = p;
  }

  public String getNewBlog10()
  { 
    return newBlog10;
  }

  public void selectBlog10(ValueChangeEvent event)
  { 
    log.info("selectBlog10" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Blog blog10 = em.find(Blog.class, id);
      setBlog3(blog10);
    }
  }

  @DataModel("blog10List") private Map<String, String> blog10List;

  public Map<String, String> getBlog10List()
  { 
    return blog10List;
  }

  @Factory("blog10List") public void initBlog10List()
  { 
    log.info("initBlog10List");
    blog10List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Blog").getResultList())
    { 
      Blog p = (Blog)o;
      blog10List.put(p.getName(), p.getId().toString());
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

  private String newBlogComment4;

  public void setNewBlogComment4(String p)
  { 
    newBlogComment4 = p;
  }

  public String getNewBlogComment4()
  { 
    return newBlogComment4;
  }

  public void selectBlogComment4(ValueChangeEvent event)
  { 
    log.info("selectBlogComment4" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      BlogComment blogComment4 = em.find(BlogComment.class, id);
      addBlogComment0(blogComment4);
    }
  }

  @DataModel("blogComment4List") private Map<String, String> blogComment4List;

  public Map<String, String> getBlogComment4List()
  { 
    return blogComment4List;
  }

  @Factory("blogComment4List") public void initBlogComment4List()
  { 
    log.info("initBlogComment4List");
    blogComment4List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "BlogComment").getResultList())
    { 
      BlogComment p = (BlogComment)o;
      blogComment4List.put(p.getName(), p.getId().toString());
    }
  }

  @DataModel("person1019List") private List<Person> person1019List;

  public List<Person> getPerson1019List()
  { 
    log.info("getPerson1019List");
    return person1019List;
  }

  @Factory("person1019List") public void initPerson1019List()
  { 
    log.info("initPerson1019List");
    person1019List = em.createQuery("from " + "Person").getResultList();
  }

  @DataModel("project1119List") private List<ResearchProject> project1119List;

  public List<ResearchProject> getProject1119List()
  { 
    log.info("getProject1119List");
    return project1119List;
  }

  @Factory("project1119List") public void initProject1119List()
  { 
    log.info("initProject1119List");
    project1119List = em.createQuery("from " + "ResearchProject").getResultList();
  }
}