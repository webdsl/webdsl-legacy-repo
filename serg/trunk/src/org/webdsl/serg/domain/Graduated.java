package org.webdsl.serg.domain;

import java.util.*;
import javax.persistence.*;

@Entity public class Graduated extends MasterStatus 
{ 
  public Graduated () 
  { }

  @Id @GeneratedValue private Long id;
}