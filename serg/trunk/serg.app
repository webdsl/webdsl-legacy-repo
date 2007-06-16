application org.webdsl.serg

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

section home.

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
