package org.webdsl.serg.domain;

import java.util.*;
import javax.persistence.*;
import org.webdsl.serg.domain.*;

@Entity public class Project extends Issue 
{ 
  public Project () 
  { }

  public String getName()
  { 
    return getTitle().toString();
  }
}