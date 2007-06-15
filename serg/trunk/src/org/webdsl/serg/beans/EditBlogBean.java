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
    initPerson132List();
    initBlogEntry7List();
    initCategory6List();
    initPerson37List();
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

  public void setPerson3(Person person133)
  { 
    blog.setAuthor(person133);
  }

  public void removeBlogEntry0(BlogEntry blogEntry6)
  { 
    this.getBlog().getEntries().remove(blogEntry6);
  }

  public void addBlogEntry0(BlogEntry blogEntry6)
  { 
    this.getBlog().getEntries().add(blogEntry6);
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

  private String newPerson132;

  public void setNewPerson132(String p)
  { 
    newPerson132 = p;
  }

  public String getNewPerson132()
  { 
    return newPerson132;
  }

  public void selectPerson132(ValueChangeEvent event)
  { 
    log.info("selectPerson132" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Person person132 = em.find(Person.class, id);
      setPerson3(person132);
    }
  }

  @DataModel("person132List") private Map<String, String> person132List;

  public Map<String, String> getPerson132List()
  { 
    return person132List;
  }

  @Factory("person132List") public void initPerson132List()
  { 
    log.info("initPerson132List");
    person132List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Person").getResultList())
    { 
      Person p = (Person)o;
      person132List.put(p.getName(), p.getId().toString());
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

  @DataModel("person37List") private List<Person> person37List;

  public List<Person> getPerson37List()
  { 
    log.info("getPerson37List");
    return person37List;
  }

  @Factory("person37List") public void initPerson37List()
  { 
    log.info("initPerson37List");
    person37List = em.createQuery("from " + "Person").getResultList();
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