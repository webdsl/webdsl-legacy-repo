package org.webdsl.serg.domain;

import java.util.*;
import javax.persistence.*;
import org.webdsl.serg.domain.*;

@Entity public class ResearchAssignment extends Project 
{ 
  public ResearchAssignment () 
  { }

  public String getName()
  { 
    return getId().toString();
  }
}