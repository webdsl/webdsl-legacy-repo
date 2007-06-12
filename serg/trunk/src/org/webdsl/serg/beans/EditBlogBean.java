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
    initPerson117List();
    initBlogEntry5List();
    initCategory6List();
    initPerson35List();
    initProject30List();
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

  public void setPerson3(Person person118)
  { 
    blog.setAuthor(person118);
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

  private String newPerson117;

  public void setNewPerson117(String p)
  { 
    newPerson117 = p;
  }

  public String getNewPerson117()
  { 
    return newPerson117;
  }

  public void selectPerson117(ValueChangeEvent event)
  { 
    log.info("selectPerson117" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Person person117 = em.find(Person.class, id);
      setPerson3(person117);
    }
  }

  @DataModel("person117List") private Map<String, String> person117List;

  public Map<String, String> getPerson117List()
  { 
    return person117List;
  }

  @Factory("person117List") public void initPerson117List()
  { 
    log.info("initPerson117List");
    person117List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Person").getResultList())
    { 
      Person p = (Person)o;
      person117List.put(p.getName(), p.getId().toString());
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

  @DataModel("person35List") private List<Person> person35List;

  public List<Person> getPerson35List()
  { 
    log.info("getPerson35List");
    return person35List;
  }

  @Factory("person35List") public void initPerson35List()
  { 
    log.info("initPerson35List");
    person35List = em.createQuery("from " + "Person").getResultList();
  }

  @DataModel("project30List") private List<ResearchProject> project30List;

  public List<ResearchProject> getProject30List()
  { 
    log.info("getProject30List");
    return project30List;
  }

  @Factory("project30List") public void initProject30List()
  { 
    log.info("initProject30List");
    project30List = em.createQuery("from " + "ResearchProject").getResultList();
  }
}