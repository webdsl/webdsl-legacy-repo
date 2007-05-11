package org.webdsl.serg.test;

import org.testng.annotations.Test;
import org.jboss.seam.mock.SeamTest;

public class UsersTest extends SeamTest {

	@Test
	public void test() throws Exception {
		new FacesRequest() {
			@Override
			protected void updateModelValues() throws Exception {				
				//set form input to model attributes
            setValue("#{users.value}", "seam");
			}
			@Override
			protected void invokeApplication() {
				//call action methods here
				invokeMethod("#{users.users}");
			}
			@Override
			protected void renderResponse() {
				//check model attributes if needed
            assert getValue("#{users.value}").equals("seam");
			}
		}.run();
	}	
}
