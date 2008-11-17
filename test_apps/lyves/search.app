module search

section pages

//quick search from the bar
define quicksearch() {
  var s: String := ""
  container{ "search " input(s)[onkeyup := action { dosearch(s);} ] }
}

function dosearch(s: String) {
  var users : List<User> := searchHelper(s);
  replace popuptarget << userListPopup(users);
}

define userListPopup(users: List<User>) {
	popup("Found users")
	define popupBody() {
		group("click a user to view its profile") {
			for(u: User in users) {
				quickviewUser(u)
			}
		}
		container[class:=right] { action("cancel", action { visibility this << hide; } )}
	}
}

//search function from the home page
function dosearchInHome(s: String) {
	var users: List<User> := searchHelper(s);
	clear searchresulttable <<;
	for(u: User in users) {
		append searchresulttable << quickviewUser(u);
	}
}

function searchHelper(s: String):  List<User> {
	var users : List<User> :=
       select u from User as u 
       where (u._name = ~s) or (u._nick = ~s);  
  return users;
}
