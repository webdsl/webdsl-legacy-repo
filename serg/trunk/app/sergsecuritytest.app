application org.webdsl.sergsecuritytest

description 

  This application organizes information relevant for a 
  research group, including people, publications, students,
  projects, colloquia, etc.

end

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

section home.
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

            
          }
          
          action("logout", logout())
          action logout() {
            		securityContext.loggedIn:=false;
          }
        }
}
access control rules {
	principal is User with credentials username, password
	//roles: admin normal special
	
	rules page viewPerson {
		securityContext.loggedIn
	}
	
	rules page viewUser {
		securityContext.loggedIn
		
	}
	
	rules page viewP* {
		securityContext.loggedIn
		
	}
	
	rules page home{
	securityContext.loggedIn
		rules action dgdfg{
		securityContext.loggedIn
		}
	}
}

extend entity User {
	nmff :: String
	dfssgsdg :: String
}

extend entity User {
	nmf55f :: String
	dfs55sgsdg :: String
	
	function fdg() : Bool{
		return true;
	}
}

  define page home() {
  action dgdfg(){
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
