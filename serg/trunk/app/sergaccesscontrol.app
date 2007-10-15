application org.webdsl.serg

description {
  This application organizes information relevant for a 
  research group, including people, publications, students,
  projects, colloquia, etc.
}

imports app/templates
imports app/people
imports app/access
imports app/blog
imports app/colloquium
imports app/publications
imports app/projects
imports app/initdb
imports app/groups
imports app/news
imports app/issues
imports app/forum
imports app/shop
imports app/software

section home

define page login()
{
		var user : User := User{};

        form { 
          table {
            row{ "username: " input(user.username) }
            row{ "password: " input(user.password) }
         
          }
          action("login", login())
          action login() {
      
          var users : List<User> :=
          select u from User as u 
           where (u._username = ~user.username) and (u._password = ~user.password);
          	
          	for (usr : User in users )
          	{
          		securityContext.principal:=usr;
            	securityContext.loggedIn:=true;
            	return home();
          	}
          	return login();
            
          }
          
          action("logout", logout())
          action logout() {
            		securityContext.loggedIn:=false;
            		return login();
          }
        }
}

access control rules {
	principal is User with credentials username, password
	
	rules page viewPerson(*) {
		securityContext.loggedIn
		true
		true
	}
	
	rules page viewUser(u: User) {
		securityContext.loggedIn	
	}
	
	rules page home() {
		true
		rules action fdfd() {
			true
		}
	}
	rules page viewPe*() {
		securityContext.loggedIn
		true
	}
	rules page viewP*(*) {
		true
		true
		true
	}
	rules page login(){
		true
		
		rules action *()
		{
			true
		}
	}	
	
	rules page initDatabase(*){
		true
		rules action * (*)
		{
			true
		}
	}
}

  define page home() {
  	action fdfd() {
  	  true;
  	}
    main()
    define sidebar() {}
    define body() {
      title{"SERG Starting Points"}
      section{
        header{"Starting Points"}
        section{
          header{"Research Groups"}
          list { for(x : ResearchGroup) { listitem{ output(x) } } }
        }
        section{
          header{"Research Projects"}
          list { for(x : ResearchProject) { listitem{ output(x) } } }
        }
        section{
          header{"Forums"}
          list { for(x : Forum) { listitem{ output(x) } } }
        }   
        section{
          header{"Blogs"}
          list { for(x : Blog) { listitem{ output(x) } } }
        }
        section{
          header{"Projects"}
          list { for(x : Project) { listitem{ output(x) } } }
        }    
        section{
          header{"People"}
          list { for(x : Person) { listitem{ output(x) } } }
        }
      }
    }
  }
