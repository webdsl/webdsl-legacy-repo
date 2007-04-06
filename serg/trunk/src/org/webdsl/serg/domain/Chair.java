package org.webdsl.serg.domain;

import java.util.*;
import javax.persistence.*;

@Entity public class Chair extends Role 
{ 
  public Chair () 
  { }

  @Id @GeneratedValue private Long id;
}