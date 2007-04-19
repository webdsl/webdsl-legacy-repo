package org.webdsl.serg.domain;

import java.util.*;
import javax.persistence.*;
import org.webdsl.serg.domain.*;

@Entity public class ResearchAssignment extends StudentProject 
{ 
  public ResearchAssignment () 
  { }

  public String getName()
  { 
    return getId().toString();
  }
}