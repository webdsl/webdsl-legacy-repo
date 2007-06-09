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

section home.

  define page home() {
    for(group : ResearchGroup) {
      navigate(group.name, viewResearchGroup(group))
    }
  }

  
