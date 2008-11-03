module init

section init

init {
	var s : Secret := "1";

	var admin : User := User {
		name := "admin"
		nick:= "admin"
		password := s.digest()
		moderator := true
	};
	admin.save(); 
	
	var michel : User := User {
		name := "michel"
		nick	:= "michel"
		password:= s.digest()
		public := true
	};
	michel.save();
	
 var n1 : NewsItem := NewsItem {
 		name := "Welcome"
 		text := "Welcom at lyves. Sign up today!"
 		datetime := now()
 		public := true
 		author := admin
 };
 n1.save();
 
 var n2 : NewsItem := NewsItem {
 		name := "New features"
 		text := "are implemented"
 		datetime := now()
 		public := false
 		author := michel
 };
 n2.save();
}
