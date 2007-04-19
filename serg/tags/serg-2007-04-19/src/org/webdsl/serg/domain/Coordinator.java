package org.webdsl.serg.domain;

import java.util.*;
import javax.persistence.*;
import org.webdsl.serg.domain.*;

@Entity public class Coordinator extends Role 
{ 
  public Coordinator () 
  { }

  public String getName()
  { 
    return getId().toString();
  }
}