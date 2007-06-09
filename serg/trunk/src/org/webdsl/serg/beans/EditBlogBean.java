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
    initPerson13List();
    initBlogEntry3List();
    initCategory4List();
    initPerson1019List();
    initProject1119List();
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

  public void setPerson3(Person person14)
  { 
    blog.setAuthor(person14);
  }

  public void removeBlogEntry0(BlogEntry blogEntry2)
  { 
    this.getBlog().getEntries().remove(blogEntry2);
  }

  public void addBlogEntry0(BlogEntry blogEntry2)
  { 
    this.getBlog().getEntries().add(blogEntry2);
  }

  public void removeCategory0(Category category3)
  { 
    this.getBlog().getCategories().remove(category3);
  }

  public void addCategory0(Category category3)
  { 
    this.getBlog().getCategories().add(category3);
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

  private String newPerson13;

  public void setNewPerson13(String p)
  { 
    newPerson13 = p;
  }

  public String getNewPerson13()
  { 
    return newPerson13;
  }

  public void selectPerson13(ValueChangeEvent event)
  { 
    log.info("selectPerson13" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Person person13 = em.find(Person.class, id);
      setPerson3(person13);
    }
  }

  @DataModel("person13List") private Map<String, String> person13List;

  public Map<String, String> getPerson13List()
  { 
    return person13List;
  }

  @Factory("person13List") public void initPerson13List()
  { 
    log.info("initPerson13List");
    person13List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Person").getResultList())
    { 
      Person p = (Person)o;
      person13List.put(p.getName(), p.getId().toString());
    }
  }

  private String newBlogEntry3;

  public void setNewBlogEntry3(String p)
  { 
    newBlogEntry3 = p;
  }

  public String getNewBlogEntry3()
  { 
    return newBlogEntry3;
  }

  public void selectBlogEntry3(ValueChangeEvent event)
  { 
    log.info("selectBlogEntry3" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      BlogEntry blogEntry3 = em.find(BlogEntry.class, id);
      addBlogEntry0(blogEntry3);
    }
  }

  @DataModel("blogEntry3List") private Map<String, String> blogEntry3List;

  public Map<String, String> getBlogEntry3List()
  { 
    return blogEntry3List;
  }

  @Factory("blogEntry3List") public void initBlogEntry3List()
  { 
    log.info("initBlogEntry3List");
    blogEntry3List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "BlogEntry").getResultList())
    { 
      BlogEntry p = (BlogEntry)o;
      blogEntry3List.put(p.getName(), p.getId().toString());
    }
  }

  private String newCategory4;

  public void setNewCategory4(String p)
  { 
    newCategory4 = p;
  }

  public String getNewCategory4()
  { 
    return newCategory4;
  }

  public void selectCategory4(ValueChangeEvent event)
  { 
    log.info("selectCategory4" + ": new value = " + " " + event.getNewValue());
    Long id = new Long((String)event.getNewValue());
    if(id > 0)
    { 
      Category category4 = em.find(Category.class, id);
      addCategory0(category4);
    }
  }

  @DataModel("category4List") private Map<String, String> category4List;

  public Map<String, String> getCategory4List()
  { 
    return category4List;
  }

  @Factory("category4List") public void initCategory4List()
  { 
    log.info("initCategory4List");
    category4List = new HashMap<String, String>();
    for(Object o : em.createQuery("from " + "Category").getResultList())
    { 
      Category p = (Category)o;
      category4List.put(p.getName(), p.getId().toString());
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