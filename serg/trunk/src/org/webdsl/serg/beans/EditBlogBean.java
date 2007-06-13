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

@Stateful @Name("editBlog") public class EditBlogBean  implements EditBlogBeanInterface
{ 
  @Logger private Log log;

  @PersistenceContext(type = EXTENDED) private EntityManager em;

  @In private FacesMessages facesMessages;

  @Create @Begin public void initialize()
  { 
    log.info("editBlog" + ".initalize()");
    if(blogId == null)
    { 
      log.info("No " + "blogId" + " defined, creating new " + "Blog");
      blog = new Blog();
    }
    else
    { 
      blog = em.find(Blog.class, blogId);
    }
    initPerson114List();
    initBlogEntry5List();
    initCategory6List();
    initPerson36List();
    initProject31List();
  }

  @Destroy @Remove public void destroy()
  { }

  @RequestParameter("blog") private Long blogId;

  private Blog blog;

  public void setBlog(Blog blog)
  { 
    log.info("setBlog");
    this.blog = blog;
  }

  public Blog getBlog()
  { 
    log.info("getBlog");
    return blog;
  }

  public void setPerson2(Person person115)
  { 
    blog.setAuthor(person115);
  }

  public void removeBlogEntry0(BlogEntry blogEntry4)
  { 
    this.getBlog().getEntries().remove(blogEntry4);
  }

  public void addBlogEntry0(BlogEntry blogEntry4)
  { 
    this.getBlog().getEntries().add(blogEntry4);
  }

  public void removeCategory0(Category category5)
  { 
    this.getBlog().getCategories().remove(category5);
  }

  public void addCategory0(Category category5)
  { 
    this.getBlog().getCategories().add(category5);
  }

  @End public String cancel()
  { 
    return "/" + "viewBlog" + ".seam?" + ("blog" + "=" + blog.getId() + "");
  }

  @End public String save()
  { 
    em.persist(this.getBlog());
    return "/" + "viewBlog" + ".seam?" + ("blog" + "=" + blog.getId() + "");
  }

  private String newPerson114;

  public void setNewPerson114(String p)
  { 
    newPerson114 = p;
  }

  public String getNewPerson114()
  { 
    return newPerson114;
  }

  public void selectPerson114(ValueChangeEvent event)
  { 
    log.info("selectPerson114" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Person person114 = em.find(Person.class, id);
      setPerson2(person114);
    }
  }

  @DataModel("person114List") private Map<String, String> person114List;

  public Map<String, String> getPerson114List()
  { 
    return person114List;
  }

  @Factory("person114List") public void initPerson114List()
  { 
    log.info("initPerson114List");
    person114List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Person").getResultList())
    { 
      Person p = (Person)o;
      person114List.put(p.getName(), p.getId().toString());
    }
  }

  private String newBlogEntry5;

  public void setNewBlogEntry5(String p)
  { 
    newBlogEntry5 = p;
  }

  public String getNewBlogEntry5()
  { 
    return newBlogEntry5;
  }

  public void selectBlogEntry5(ValueChangeEvent event)
  { 
    log.info("selectBlogEntry5" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      BlogEntry blogEntry5 = em.find(BlogEntry.class, id);
      addBlogEntry0(blogEntry5);
    }
  }

  @DataModel("blogEntry5List") private Map<String, String> blogEntry5List;

  public Map<String, String> getBlogEntry5List()
  { 
    return blogEntry5List;
  }

  @Factory("blogEntry5List") public void initBlogEntry5List()
  { 
    log.info("initBlogEntry5List");
    blogEntry5List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "BlogEntry").getResultList())
    { 
      BlogEntry p = (BlogEntry)o;
      blogEntry5List.put(p.getName(), p.getId().toString());
    }
  }

  private String newCategory6;

  public void setNewCategory6(String p)
  { 
    newCategory6 = p;
  }

  public String getNewCategory6()
  { 
    return newCategory6;
  }

  public void selectCategory6(ValueChangeEvent event)
  { 
    log.info("selectCategory6" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Category category6 = em.find(Category.class, id);
      addCategory0(category6);
    }
  }

  @DataModel("category6List") private Map<String, String> category6List;

  public Map<String, String> getCategory6List()
  { 
    return category6List;
  }

  @Factory("category6List") public void initCategory6List()
  { 
    log.info("initCategory6List");
    category6List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Category").getResultList())
    { 
      Category p = (Category)o;
      category6List.put(p.getName(), p.getId().toString());
    }
  }

  @DataModel("person36List") private List<Person> person36List;

  public List<Person> getPerson36List()
  { 
    log.info("getPerson36List");
    return person36List;
  }

  @Factory("person36List") public void initPerson36List()
  { 
    log.info("initPerson36List");
    person36List = em.createQuery("from " + "Person").getResultList();
  }

  @DataModel("project31List") private List<ResearchProject> project31List;

  public List<ResearchProject> getProject31List()
  { 
    log.info("getProject31List");
    return project31List;
  }

  @Factory("project31List") public void initProject31List()
  { 
    log.info("initProject31List");
    project31List = em.createQuery("from " + "ResearchProject").getResultList();
  }
}