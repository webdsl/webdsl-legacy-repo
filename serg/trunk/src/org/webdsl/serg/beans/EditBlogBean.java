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
    initPerson136List();
    initBlogEntry6List();
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

  public void setPerson2(Person person137)
  { 
    blog.setAuthor(person137);
  }

  public void removeBlogEntry0(BlogEntry blogEntry5)
  { 
    this.getBlog().getEntries().remove(blogEntry5);
  }

  public void addBlogEntry0(BlogEntry blogEntry5)
  { 
    this.getBlog().getEntries().add(blogEntry5);
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

  private String newPerson136;

  public void setNewPerson136(String p)
  { 
    newPerson136 = p;
  }

  public String getNewPerson136()
  { 
    return newPerson136;
  }

  public void selectPerson136(ValueChangeEvent event)
  { 
    log.info("selectPerson136" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Person person136 = em.find(Person.class, id);
      setPerson2(person136);
    }
  }

  @DataModel("person136List") private Map<String, String> person136List;

  public Map<String, String> getPerson136List()
  { 
    return person136List;
  }

  @Factory("person136List") public void initPerson136List()
  { 
    log.info("initPerson136List");
    person136List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Person").getResultList())
    { 
      Person p = (Person)o;
      person136List.put(p.getName(), p.getId().toString());
    }
  }

  private String newBlogEntry6;

  public void setNewBlogEntry6(String p)
  { 
    newBlogEntry6 = p;
  }

  public String getNewBlogEntry6()
  { 
    return newBlogEntry6;
  }

  public void selectBlogEntry6(ValueChangeEvent event)
  { 
    log.info("selectBlogEntry6" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      BlogEntry blogEntry6 = em.find(BlogEntry.class, id);
      addBlogEntry0(blogEntry6);
    }
  }

  @DataModel("blogEntry6List") private Map<String, String> blogEntry6List;

  public Map<String, String> getBlogEntry6List()
  { 
    return blogEntry6List;
  }

  @Factory("blogEntry6List") public void initBlogEntry6List()
  { 
    log.info("initBlogEntry6List");
    blogEntry6List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "BlogEntry").getResultList())
    { 
      BlogEntry p = (BlogEntry)o;
      blogEntry6List.put(p.getName(), p.getId().toString());
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