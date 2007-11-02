application org.webdsl.serg

description {
  This application organizes information relevant for a 
  research group, including people, publications, students,
  projects, colloquia, etc.
}

imports templates
imports people
imports access
imports blog
imports colloquium
imports publications
imports projects
imports initdb
imports groups
imports news
imports issues
imports forum
imports shop
imports software

section home

  define page home() {
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
