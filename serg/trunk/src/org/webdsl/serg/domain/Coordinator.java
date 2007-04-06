package org.webdsl.serg.domain;

import java.util.*;
import javax.persistence.*;

@Entity public class Coordinator extends Role 
{ 
  public Coordinator () 
  { }

  @Id @GeneratedValue private Long id;
}