package org.webdsl.serg.beans;

import org.jboss.annotation.ejb.Local;
import java.util.*;
import org.webdsl.serg.domain.*;

@Local public interface ViewPublicationBeanInterface 
{ 
  public void initialize();

  public void destroy();

  public void setPublication(Publication publication);

  public Publication getPublication();
}