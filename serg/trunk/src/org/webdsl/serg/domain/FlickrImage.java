package org.webdsl.serg.domain;

import java.util.*;
import javax.persistence.*;
import org.webdsl.serg.domain.*;

@Entity public class FlickrImage  
{ 
  public FlickrImage () 
  { }

  @Id @GeneratedValue private Long id;

  public Long getId()
  { 
    return id;
  }

  private void setId(Long id)
  { 
    this.id = id;
  }

  private String _photoid = "";

  public String getPhotoid()
  { 
    return _photoid;
  }

  public void setPhotoid(String _photoid)
  { 
    this._photoid = _photoid;
  }

  private String _title = "";

  public String getTitle()
  { 
    return _title;
  }

  public void setTitle(String _title)
  { 
    this._title = _title;
  }

  private String _username = "";

  public String getUsername()
  { 
    return _username;
  }

  public void setUsername(String _username)
  { 
    this._username = _username;
  }

  private String _photourl = "";

  public String getPhotourl()
  { 
    return _photourl;
  }

  public void setPhotourl(String _photourl)
  { 
    this._photourl = _photourl;
  }

  private String _square = "";

  public String getSquare()
  { 
    return _square;
  }

  public void setSquare(String _square)
  { 
    this._square = _square;
  }

  private String _thumbnail = "";

  public String getThumbnail()
  { 
    return _thumbnail;
  }

  public void setThumbnail(String _thumbnail)
  { 
    this._thumbnail = _thumbnail;
  }

  private String _small = "";

  public String getSmall()
  { 
    return _small;
  }

  public void setSmall(String _small)
  { 
    this._small = _small;
  }

  private String _medium = "";

  public String getMedium()
  { 
    return _medium;
  }

  public void setMedium(String _medium)
  { 
    this._medium = _medium;
  }

  private String _large = "";

  public String getLarge()
  { 
    return _large;
  }

  public void setLarge(String _large)
  { 
    this._large = _large;
  }

  private String _original = "";

  public String getOriginal()
  { 
    return _original;
  }

  public void setOriginal(String _original)
  { 
    this._original = _original;
  }

  public String getName()
  { 
    return getTitle().toString();
  }
}