//#1 no property noname in entity User
application test


	
  entity User {
  	name2 :: String
    webservice mapping {
   		
    	toplevel with name property : noname 
    }  
  }
 

  define page root(){}