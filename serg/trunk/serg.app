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

section home.

  define page home() {
    main()
  }

  define sidebar() {
    list { for(group : ResearchGroup) {
      listitem{ navigate(group.name, viewResearchGroup(group)) }
    }}
  }
