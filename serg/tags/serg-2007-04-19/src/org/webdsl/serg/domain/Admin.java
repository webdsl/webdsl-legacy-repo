package org.webdsl.serg.domain;

import java.util.*;
import javax.persistence.*;
import org.webdsl.serg.domain.*;

@Entity public class Admin extends Role 
{ 
  public Admin () 
  { }

  public String getName()
  { 
    return getId().toString();
  }
}