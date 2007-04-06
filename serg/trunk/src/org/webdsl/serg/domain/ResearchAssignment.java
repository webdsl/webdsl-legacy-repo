package org.webdsl.serg.domain;

import java.util.*;
import javax.persistence.*;

@Entity public class ResearchAssignment extends Project 
{ 
  public ResearchAssignment () 
  { }

  @Id @GeneratedValue private Long id;
}