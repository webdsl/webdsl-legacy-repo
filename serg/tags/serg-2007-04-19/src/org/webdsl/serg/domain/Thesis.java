package org.webdsl.serg.domain;

import java.util.*;
import javax.persistence.*;
import org.webdsl.serg.domain.*;

@Entity public class Thesis extends MasterStatus 
{ 
  public Thesis () 
  { }

  public String getName()
  { 
    return getId().toString();
  }
}