application richfaces

description {
  Testapp for richfaces
}

imports app/people
imports app/publications
imports app/templates

section home

  define page home() {
    main()
    define sidebar() {}
    define body() {
      title{"Richfaces testapp"}
    }
  }
