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
    initPerson153List();
    initBlogEntry7List();
    initCategory7List();
    initPerson43List();
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

  public void setPerson3(Person person154)
  { 
    blog.setAuthor(person154);
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

  private String newPerson153;

  public void setNewPerson153(String p)
  { 
    newPerson153 = p;
  }

  public String getNewPerson153()
  { 
    return newPerson153;
  }

  public void selectPerson153(ValueChangeEvent event)
  { 
    log.info("selectPerson153" + ": new value = " + " " + event.getNewValue());
    Person person153 = (Person)event.getNewValue();
  }

  @DataModel("person153List") private Map<String, String> person153List;

  public Map<String, String> getPerson153List()
  { 
    return person153List;
  }

  @Factory("person153List") public void initPerson153List()
  { 
    log.info("initPerson153List");
    person153List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Person").getResultList())
    { 
      Person p = (Person)o;
      person153List.put(p.getName(), p.getId().toString());
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
    BlogEntry blogEntry7 = (BlogEntry)event.getNewValue();
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
    Category category7 = (Category)event.getNewValue();
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

  @DataModel("person43List") private List<Person> person43List;

  public List<Person> getPerson43List()
  { 
    log.info("getPerson43List");
    return person43List;
  }

  @Factory("person43List") public void initPerson43List()
  { 
    log.info("initPerson43List");
    person43List = em.createQuery("from " + "Person").getResultList();
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