application richfaces

description {
  Testapp for richfaces
}

imports app/people
imports app/publications
imports app/templates
imports app/init

section home

  define page home() {
    main()
    define body() {
      title{"Richfaces testapp"}
      list{
        for(p:User)
        {
          listitem{
            navigate(viewUser(p)){ "View user: " output(p.firstname) }
            navigate(editUser(p)){ " Edit user: " output(p.firstname) }
          }
        }
      }
      
    }
  }
