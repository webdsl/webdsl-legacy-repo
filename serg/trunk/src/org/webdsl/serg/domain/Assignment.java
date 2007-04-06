package org.webdsl.serg.domain;

import java.util.*;
import javax.persistence.*;

@Entity public class Assignment extends MasterStatus 
{ 
  public Assignment () 
  { }

  @Id @GeneratedValue private Long id;
}