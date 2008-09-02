module pages

section pages

define page user(user : User) {
  main()
  title { output(user.username) }
  define body() {
    for(u : Update in user.updates order by u.date desc limit 20) {
      viewUpdate(u)
    }
  }
}

define page update(u : Update) {
  derive viewPage from u
}

