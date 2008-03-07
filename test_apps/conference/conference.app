application com.example.conference

description {
  Conference application
}

imports templates
imports data

section pages

define page home() {
  main()
  define body() {
    list {
      listitem { navigate(createUser()) { "Create user" } }
      listitem { navigate(allConference()) { "All conferences" } }
      listitem { navigate(createConference()) { "Create conference" } }
      listitem { navigate(createPaper()) { "Submit paper" } }
    }                        
  }
}
