application richfaces

description {
  Testapp for richfaces
}

imports init
imports people
imports publications
imports templates
imports emails

section home

  define page home() {
    main()
    define body() {
      title{"Richfaces testapp"}
      list{
        for(p:User)
        {
          listitem{
           navigate(user(p)){ "View user: " output(p.firstname) }
            navigate(editUser(p)){ " Edit user: " output(p.firstname) }
          }
        }
      }

      div("bla") {
        table( pub : Publication ) {
          header() {"Publication"}
          text(pub.title)
          subtable(author : User in pub.authors) {
            header() {"Firstname" "Lastname" "Username"}
            text(author.firstname)
            text(author.lastname)
            text(author.username)
          }
        }
      }
    }
  }

section home2

  define page home2(u : User, u1 : User) {
    main()
    define body() {
      title{"Richfaces testapp home2"}
      list{
        for(p:User)
        {
          listitem{
           navigate(user(p)){ "View user: " output(p.firstname) }
            navigate(editUser(p)){ " Edit user: " output(p.firstname) }
          }
        }
      }
    }
  }
