application ajax1

imports init
imports data

description {
  
}

section the pages

  define page home(){
    block("top"){ top() }
    block("body"){
      block("left_innerbody"){ sidebar() }
      block("main_innerbody"){ 
		    section{ "Welcome to the example application which manages users and documents." }
		    section{ "The supported operations are creation, viewing, and editing." }
		    login()
		    logout()
      }
    }
  }


	define page editDocument(document:Document){
		derive editPage from document
	}
	
	define page document(document: Document){
	  block("top"){ top() }
    block("body"){
      block("left_innerbody"){ sidebar() }
      block("main_innerbody"){ 
				section
				{
					header{
		      	  output(document.title)
		    	}
		    	table
		    	{
		        row{ "Author:" 
               output(document.author.name)
               event onclick { left_innerbody user(document.author)}
		        }
		        row{ "" 
		        		navigate(editDocument(document)){"[edit this using the cool new ajax feature]"} 
		        }
		    	}     
		  	}
		  }
    }
	}//page doc
	
	
	define page createDocument(){
		var document: Document := Document {}
		derive createPage from document
	}

  define page user(u:User) {
  	derive viewPage from u
  }
  
  define page editUser(u: User){
  	derive editPage from u
  }
  
  define page createUser() {
  	var u: User := User {}
  	derive createPage from u
  }
   
  define main() 
  {
    block("top"){ top() }
    block("body"){
      block("left_innerbody"){ sidebar() }
      block("main_innerbody"){ body() }
    }
    block("footer"){ footer() }
  }
  
   
  define footer(){ output("Cool, ajax") }
  
  define top(){
    block("header"){}
    block("menubar"){ menubar{ applicationmenu() }}
  }
  
  define sidebar(){
    list{
      listitem{ navigate(home()){ output("home") }}
    }
  }
  
  define applicationmenu()
  {
    menu{
      menuheader{ "View Document" }
      for(d:Document){
        menuitem{ navigate(document(d)){ output(d.title) }}
      }
    }
    menu{
      menuheader{ "Edit Document" }
      for(d:Document){
        menuitem{ navigate(editDocument(d)){ output(d.title) }}
      }
    }    
    menu{ menuheader{ navigate(createDocument()){ output("New document") }}}  
    menu{
      menuheader{ "View User" }
      for(u:User){
        menuitem{ navigate(user(u)){ output(u.name) }}
      }
    }
    menu{
      menuheader{"Edit User"}
      for(u:User){
        menuitem{ navigate(editUser(u)){ output(u.name) }}
      }
    }    
    menu{ menuheader{ navigate(createUser()){ output("New user") }}}  
  }

  
  
  

