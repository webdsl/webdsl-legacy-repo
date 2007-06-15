package org.webdsl.serg.domain;

import java.util.*;
import javax.persistence.*;
import org.webdsl.serg.domain.*;

@Entity public class Bug extends Issue 
{ 
  public Bug () 
  { }

  public String getName()
  { 
    return getTitle().toString();
  }
}