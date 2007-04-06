package org.webdsl.serg.domain;

import java.util.*;
import javax.persistence.*;

@Entity public class Started extends MasterStatus 
{ 
  public Started () 
  { }

  @Id @GeneratedValue private Long id;
}