module search

section pages

define quicksearch() {
  var s: String := ""
  container{ "search " input(s)[onkeyup := { dosearch(s);} ] }
}

function dosearch(s: String) {
  var users : List<User> :=
       select u from User as u 
       where ((u.name = ~s) or (u.nick = ~s));  
  clear searchresulttable <<;
  for(u : User in users) {
      append searchresulttable << quickviewUser(u);
  }
}
