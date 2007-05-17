package org.webdsl.serg.test;

import org.testng.annotations.Test;
import org.jboss.seam.mock.SeamTest;

public class RegisterTest extends SeamTest {

	@Test
	public void test() throws Exception {
		new FacesRequest() {
			@Override
			protected void updateModelValues() throws Exception {				
				//set form input to model attributes
            setValue("#{register.value}", "seam");
			}
			@Override
			protected void invokeApplication() {
				//call action methods here
				invokeMethod("#{register.register}");
			}
			@Override
			protected void renderResponse() {
				//check model attributes if needed
            assert getValue("#{register.value}").equals("seam");
			}
		}.run();
	}	
}
