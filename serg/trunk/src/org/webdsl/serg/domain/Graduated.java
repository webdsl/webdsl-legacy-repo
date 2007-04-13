package org.webdsl.serg.domain;

import java.util.*;
import javax.persistence.*;
import org.webdsl.serg.domain.*;

@Entity public class Graduated extends MasterStatus 
{ 
  public Graduated () 
  { }

  public String getName()
  { 
    return getId().toString();
  }
}