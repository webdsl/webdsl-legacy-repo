package org.webdsl.serg.domain;

import java.util.*;
import javax.persistence.*;
import org.webdsl.serg.domain.*;

@Entity public class Assignment extends MasterStatus 
{ 
  public Assignment () 
  { }

  public String getName()
  { 
    return getId().toString();
  }
}