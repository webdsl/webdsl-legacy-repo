package org.webdsl.serg.domain;

import java.util.*;
import javax.persistence.*;
import org.webdsl.serg.domain.*;

@Entity public class Task extends Issue 
{ 
  public Task () 
  { }

  public String getName()
  { 
    return getTitle().toString();
  }
}