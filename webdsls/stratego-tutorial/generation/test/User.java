package tasks.domain;

import java.util.*;
import javax.persistence.*;
import tasks.domain.*;
import java.io.Serializable;
import utils.*;
import java.io.PrintWriter;

public @javax.persistence.Entity @Table(name = "_User", uniqueConstraints = @UniqueConstraint(columnNames = {"_username"})) @javax.persistence.Inheritance(strategy = javax.persistence.InheritanceType.SINGLE_TABLE) @javax.persistence.DiscriminatorColumn(name = "DISCRIMINATOR", discriminatorType = javax.persistence.DiscriminatorType.STRING, length = 255) class User  implements org.webdsl.WebDSLEntity
{ 
  public User () 
  { }

  @javax.persistence.Id @org.hibernate.annotations.Type(type = "utils.UUIDUserType") @javax.persistence.Column(name = "id", length = 16) protected java.util.UUID _id = java.util.UUID.randomUUID();

  public java.util.UUID getId()
  { 
    return _id;
  }

  public void setId(java.util.UUID id)
  { 
    this._id = _id;
  }

  @Version @Column(name = "version_opt_lock") protected Integer _version = 0;

  public Integer getVersion()
  { 
    return _version;
  }

  public void setVersion(Integer version)
  { 
    this._version = _version;
  }

  public boolean equals(Object o)
  { 
    return o != null && o instanceof org.webdsl.WebDSLEntity && ((org.webdsl.WebDSLEntity)o).instanceOf("User") && org.webdsl.tools.Utils.equal((o instanceof org.hibernate.proxy.HibernateProxy ? User.class.cast(((org.hibernate.proxy.HibernateProxy)o).getHibernateLazyInitializer().getImplementation()) : User.class.cast(o)).getId(), getId());
  }

  public int hashCode()
  { 
    if(getId() == null)
      return super.hashCode();
    else
      return getId().hashCode();
  }

  public int compareTo(org.webdsl.WebDSLEntity o)
  { 
    return getId().compareTo(((User)o).getId());
  }

  public boolean isInstance(Class<?> c)
  { 
    return c.isInstance(this);
  }

  public boolean instanceOf(String s)
  { 
    return s.equals("User") || s.equals("Object");
  }

  @org.hibernate.annotations.AccessType(value = "field") protected String _username = "";

  public String getUsername()
  { 
    return _username;
  }

  public void setUsername(String newitem)
  { 
    _username = newitem;
  }

  public String getNaturalId()
  { 
    return utils.IdAnnoURLFilter.filter(_username.toString());
  }

  @ManyToMany(fetch = javax.persistence.FetchType.LAZY) @JoinTable(name = "User_tasks_Task") @org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.PERSIST, org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.MERGE}) protected java.util.Set<tasks.domain.Task> _tasks = new java.util.LinkedHashSet<tasks.domain.Task>();

  public java.util.Set<tasks.domain.Task> getTasks()
  { 
    return _tasks;
  }

  public void setTasks(java.util.Set<tasks.domain.Task> newitem)
  { 
    _tasks = newitem;
  }

  public java.util.List<tasks.domain.Task> getTasksList()
  { 
    return new ArrayList(getTasks());
  }

  public void setTasksList(java.util.List<tasks.domain.Task> list2)
  { 
    setTasks(new java.util.LinkedHashSet<tasks.domain.Task>(list2));
  }

  public int getTasksLength()
  { 
    return getTasks().size();
  }

  public void removeFromTasks(tasks.domain.Task item)
  { 
    getTasks().remove(item);
  }

  public void removeAllFromTasks()
  { 
    org.hibernate.Hibernate.initialize(getTasks());
    while(!getTasks().isEmpty())
    { 
      removeFromTasks(getTasks().iterator().next());
    }
  }

  public void addToTasks(tasks.domain.Task item)
  { 
    getTasks().add(item);
  }

  public java.util.Set<tasks.domain.Task> addAllToTasks(java.util.Collection<tasks.domain.Task> col)
  { 
    Iterator<tasks.domain.Task> it = col.iterator();
    while(it.hasNext())
    { 
      addToTasks(it.next());
    }
    return getTasks();
  }

  @ManyToMany(fetch = javax.persistence.FetchType.LAZY) @JoinTable(name = "User_log_Task") @org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.PERSIST, org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.MERGE}) protected java.util.Set<tasks.domain.Task> _log = new java.util.LinkedHashSet<tasks.domain.Task>();

  public java.util.Set<tasks.domain.Task> getLog()
  { 
    return _log;
  }

  public void setLog(java.util.Set<tasks.domain.Task> newitem)
  { 
    _log = newitem;
  }

  public java.util.List<tasks.domain.Task> getLogList()
  { 
    return new ArrayList(getLog());
  }

  public void setLogList(java.util.List<tasks.domain.Task> list3)
  { 
    setLog(new java.util.LinkedHashSet<tasks.domain.Task>(list3));
  }

  public int getLogLength()
  { 
    return getLog().size();
  }

  public void removeFromLog(tasks.domain.Task item)
  { 
    getLog().remove(item);
  }

  public void removeAllFromLog()
  { 
    org.hibernate.Hibernate.initialize(getLog());
    while(!getLog().isEmpty())
    { 
      removeFromLog(getLog().iterator().next());
    }
  }

  public void addToLog(tasks.domain.Task item)
  { 
    getLog().add(item);
  }

  public java.util.Set<tasks.domain.Task> addAllToLog(java.util.Collection<tasks.domain.Task> col)
  { 
    Iterator<tasks.domain.Task> it = col.iterator();
    while(it.hasNext())
    { 
      addToLog(it.next());
    }
    return getLog();
  }

  public String getName()
  { 
    if(getUsername() != null)
    { 
      return getUsername().toString();
    }
    else
    { 
      return "";
    }
  }
}