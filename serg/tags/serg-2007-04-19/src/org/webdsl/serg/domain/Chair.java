package org.webdsl.serg.domain;

import java.util.*;
import javax.persistence.*;
import org.webdsl.serg.domain.*;

@Entity public class Chair extends Role 
{ 
  public Chair () 
  { }

  public String getName()
  { 
    return getId().toString();
  }
}