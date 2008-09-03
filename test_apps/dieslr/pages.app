module pages

section pages

define page user(user : User) {
  main()
  title { output(user.username) }
  define body() {
    for(u : Message in user.sentMessages where u.original = true order by u.date desc per 20) {
      displayMessage(u)
    }
    section {
      header{ "Following "}
      output(user.following)
      header{ "Followers "}
      output(user.followers)
      form {
        if(securityContext.loggedIn) {
          par {
            if(securityContext.principal in user.followers) {
              action("Unfollow", unfollow())
              action unfollow() {
                securityContext.principal.following.remove(user);
                securityContext.principal.save();
                return user(user);
              }
            } else {
              action("Follow", follow())
              action follow() {
                securityContext.principal.following.add(user);
                securityContext.principal.save();
                return user(user);
              }
            }
          }
        }
      }
    }
  }
}

define page message(m : Message) {
  derive viewPage from m
}

