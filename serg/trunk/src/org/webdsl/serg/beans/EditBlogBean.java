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
    initPerson167List();
    initBlogEntry7List();
    initCategory7List();
    initPerson45List();
    initProject32List();
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

  public void setPerson3(Person person168)
  { 
    blog.setAuthor(person168);
  }

  public void removeBlogEntry0(BlogEntry blogEntry6)
  { 
    this.getBlog().getEntries().remove(blogEntry6);
  }

  public void addBlogEntry0(BlogEntry blogEntry6)
  { 
    this.getBlog().getEntries().add(blogEntry6);
  }

  public void removeCategory0(Category category6)
  { 
    this.getBlog().getCategories().remove(category6);
  }

  public void addCategory0(Category category6)
  { 
    this.getBlog().getCategories().add(category6);
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

  private String newPerson167;

  public void setNewPerson167(String p)
  { 
    newPerson167 = p;
  }

  public String getNewPerson167()
  { 
    return newPerson167;
  }

  public void selectPerson167(ValueChangeEvent event)
  { 
    log.info("selectPerson167" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Person person167 = em.find(Person.class, id);
      setPerson3(person167);
    }
  }

  @DataModel("person167List") private Map<String, String> person167List;

  public Map<String, String> getPerson167List()
  { 
    return person167List;
  }

  @Factory("person167List") public void initPerson167List()
  { 
    log.info("initPerson167List");
    person167List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Person").getResultList())
    { 
      Person p = (Person)o;
      person167List.put(p.getName(), p.getId().toString());
    }
  }

  private String newBlogEntry7;

  public void setNewBlogEntry7(String p)
  { 
    newBlogEntry7 = p;
  }

  public String getNewBlogEntry7()
  { 
    return newBlogEntry7;
  }

  public void selectBlogEntry7(ValueChangeEvent event)
  { 
    log.info("selectBlogEntry7" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      BlogEntry blogEntry7 = em.find(BlogEntry.class, id);
      addBlogEntry0(blogEntry7);
    }
  }

  @DataModel("blogEntry7List") private Map<String, String> blogEntry7List;

  public Map<String, String> getBlogEntry7List()
  { 
    return blogEntry7List;
  }

  @Factory("blogEntry7List") public void initBlogEntry7List()
  { 
    log.info("initBlogEntry7List");
    blogEntry7List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "BlogEntry").getResultList())
    { 
      BlogEntry p = (BlogEntry)o;
      blogEntry7List.put(p.getName(), p.getId().toString());
    }
  }

  private String newCategory7;

  public void setNewCategory7(String p)
  { 
    newCategory7 = p;
  }

  public String getNewCategory7()
  { 
    return newCategory7;
  }

  public void selectCategory7(ValueChangeEvent event)
  { 
    log.info("selectCategory7" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Category category7 = em.find(Category.class, id);
      addCategory0(category7);
    }
  }

  @DataModel("category7List") private Map<String, String> category7List;

  public Map<String, String> getCategory7List()
  { 
    return category7List;
  }

  @Factory("category7List") public void initCategory7List()
  { 
    log.info("initCategory7List");
    category7List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Category").getResultList())
    { 
      Category p = (Category)o;
      category7List.put(p.getName(), p.getId().toString());
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

  @DataModel("project32List") private List<ResearchProject> project32List;

  public List<ResearchProject> getProject32List()
  { 
    log.info("getProject32List");
    return project32List;
  }

  @Factory("project32List") public void initProject32List()
  { 
    log.info("initProject32List");
    project32List = em.createQuery("from " + "ResearchProject").getResultList();
  }
}