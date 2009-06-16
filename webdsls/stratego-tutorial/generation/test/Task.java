package tasks.domain;

import java.util.*;
import javax.persistence.*;
import tasks.domain.*;
import java.io.Serializable;
import utils.*;
import java.io.PrintWriter;

public @javax.persistence.Entity @Table(name = "_Task") @javax.persistence.Inheritance(strategy = javax.persistence.InheritanceType.SINGLE_TABLE) @javax.persistence.DiscriminatorColumn(name = "DISCRIMINATOR", discriminatorType = javax.persistence.DiscriminatorType.STRING, length = 255) class Task  implements org.webdsl.WebDSLEntity
{ 
  public Task () 
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

  public String getNaturalId()
  { 
    return _id.toString();
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
    return o != null && o instanceof org.webdsl.WebDSLEntity && ((org.webdsl.WebDSLEntity)o).instanceOf("Task") && org.webdsl.tools.Utils.equal((o instanceof org.hibernate.proxy.HibernateProxy ? Task.class.cast(((org.hibernate.proxy.HibernateProxy)o).getHibernateLazyInitializer().getImplementation()) : Task.class.cast(o)).getId(), getId());
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
    return getId().compareTo(((Task)o).getId());
  }

  public boolean isInstance(Class<?> c)
  { 
    return c.isInstance(this);
  }

  public boolean instanceOf(String s)
  { 
    return s.equals("Task") || s.equals("Object");
  }

  @org.hibernate.annotations.AccessType(value = "field") protected String _description = "";

  public String getDescription()
  { 
    return _description;
  }

  public void setDescription(String newitem)
  { 
    _description = newitem;
  }

  @org.hibernate.annotations.AccessType(value = "field") protected Boolean _done = false;

  public Boolean getDone()
  { 
    return _done;
  }

  public void setDone(Boolean newitem)
  { 
    _done = newitem;
  }

  public String getName()
  { 
    if(getDescription() != null)
    { 
      return getDescription().toString();
    }
    else
    { 
      return "";
    }
  }
}