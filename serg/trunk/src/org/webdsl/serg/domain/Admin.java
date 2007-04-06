package org.webdsl.serg.domain;

import java.util.*;
import javax.persistence.*;

@Entity public class Admin extends Role 
{ 
  public Admin () 
  { }

  @Id @GeneratedValue private Long id;
}