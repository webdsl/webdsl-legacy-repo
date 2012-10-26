package utils;

import utils.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.InputStream;
import java.io.Serializable;
import javax.persistence.*;

import java.sql.SQLException;
import java.util.*;
import org.hibernate.Session;

@Entity public class File
{ 
  public File () 
  { }

  @Id @GeneratedValue private Long id;

  public Long getId()
  { 
    return id;
  }

  public void setId(Long id)
  { 
    this.id = id;
  }

  public boolean equals(Object o)
  { 
    return o != null && org.webdsl.tools.Utils.isInstance(o, File.class) && org.webdsl.tools.Utils.equal(((File)o).getId(), getId());
  }

  public int hashCode()
  { 
    if(getId() == null)
      return super.hashCode();
    else
      return getId().hashCode();
  }
  
  private java.sql.Blob	content	= null;

  public java.sql.Blob getContent(){
    return content;
  }

  private void setContent(java.sql.Blob content ){
    this.content = content;
  }

  public java.io.InputStream getContentStream()
            throws java.sql.SQLException
  {
    if (getContent() == null)
      return null;
    return getContent().getBinaryStream();
  }

  public void setContentStream( java.io.InputStream sourceStream )
            throws java.io.IOException
  {
    setContent( org.hibernate.Hibernate.createBlob( sourceStream ) );
  }
  
  public String getContentAsString(){
    try {
        InputStream inputStream = getContent().getBinaryStream();
        int expected = inputStream.available();
        byte[] contents = new byte[expected];
        int length = inputStream.read(contents);
        if(length != expected)
            return "";
        return new String(contents);
    } catch (SQLException e) {
        org.webdsl.logging.Logger.error("EXCEPTION",e);
        return "";
    } catch (IOException e) {
        org.webdsl.logging.Logger.error("EXCEPTION",e);
        return "";
    }
  }

  @org.hibernate.annotations.AccessType(value = "field") protected String fileName = "";

  public String getFileName()
  { 
    return fileName;
  }

  public void setFileName(String newitem)
  { 
    fileName = newitem;
  }

  @org.hibernate.annotations.AccessType(value = "field") protected long sizeInBytes = 0;

  public long getSizeInBytes()
  { 
    return sizeInBytes;
  }

  public void setSizeInBytes(long newitem)
  { 
      sizeInBytes = newitem;
  }
    
  @org.hibernate.annotations.AccessType(value = "field") protected String contentType = "";

  public String getContentType() {
    return contentType;
  }
    
  public void setContentType(String contentType) {
    this.contentType = contentType;
  }
  
  public utils.File makeClone() {
      utils.File newF = new utils.File();
      newF.setContent(content);
      newF.setFileName(fileName);
      newF.setSizeInBytes(sizeInBytes);
      newF.setContentType(contentType);
      return newF;
  }
      
}
