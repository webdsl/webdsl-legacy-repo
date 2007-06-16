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
    initPerson148List();
    initBlogEntry7List();
    initCategory7List();
    initPerson42List();
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

  public void setPerson3(Person person149)
  { 
    blog.setAuthor(person149);
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

  private String newPerson148;

  public void setNewPerson148(String p)
  { 
    newPerson148 = p;
  }

  public String getNewPerson148()
  { 
    return newPerson148;
  }

  public void selectPerson148(ValueChangeEvent event)
  { 
    log.info("selectPerson148" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Person person148 = em.find(Person.class, id);
      setPerson3(person148);
    }
  }

  @DataModel("person148List") private Map<String, String> person148List;

  public Map<String, String> getPerson148List()
  { 
    return person148List;
  }

  @Factory("person148List") public void initPerson148List()
  { 
    log.info("initPerson148List");
    person148List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Person").getResultList())
    { 
      Person p = (Person)o;
      person148List.put(p.getName(), p.getId().toString());
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

  @DataModel("person42List") private List<Person> person42List;

  public List<Person> getPerson42List()
  { 
    log.info("getPerson42List");
    return person42List;
  }

  @Factory("person42List") public void initPerson42List()
  { 
    log.info("initPerson42List");
    person42List = em.createQuery("from " + "Person").getResultList();
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