package org.webdsl.serg;

import javax.ejb.Stateless;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.log.Log;
import org.jboss.seam.core.FacesMessages;

@Stateless
@Name("people")
public class PeopleBean implements People {
	
    @Logger private Log log;
	
    @In 
    FacesMessages facesMessages;
    
    //seam-gen method
    public String people()
    {
        //implement your business logic here
        log.info("people.people() action called");
        facesMessages.add("people");
        return "success";
    }
    
    //add additional action methods
    
}
