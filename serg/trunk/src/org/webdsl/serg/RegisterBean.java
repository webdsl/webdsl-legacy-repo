package org.webdsl.serg;

import javax.ejb.Remove;
import javax.ejb.Stateful;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Destroy;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.log.Log;
import org.jboss.seam.core.FacesMessages;
import org.hibernate.validator.Length;

@Stateful 
@Name("register")
public class RegisterBean implements Register {

    @Logger private Log log;
    
    @In
    FacesMessages facesMessages;
    
    private String value;
	
	//seam-gen method
	public String register()
	{
		//implement your business logic here
		log.info("register.register() action called with: #{register.value}");
		facesMessages.add("register #{register.value}");
		return "success";
	}
	
	//add additional action methods
	
	@Length(max=10)
	public String getValue()
	{
		return value;
	}
	
	public void setValue(String value)
	{
		this.value = value;
	}
	
	@Destroy @Remove                                                                      
	public void destroy() {}	
}
