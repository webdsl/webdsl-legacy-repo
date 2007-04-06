package org.webdsl.serg.domain;

import java.util.*;
import javax.persistence.*;

@Entity public class Thesis extends MasterStatus 
{ 
  public Thesis () 
  { }

  @Id @GeneratedValue private Long id;
}