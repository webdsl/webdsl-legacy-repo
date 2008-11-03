module user

section entities

entity User {
  name :: String
  nick :: String
  password :: Secret
  friends -> Set<User>
  ignores -> Set<User>
  public  :: Bool
  moderator:: Bool
}

section pagesh

define page viewUser(u: User) {
  quickviewUser(u)
}

define quickviewUser(u: User) {
  output(u.nick)
}

define loginlogout() {
  if(securityContext.loggedIn) {
    actionLink("logout")[onclick:=	{
                         securityContext.loggedIn := false;
                         securityContext.principal := null;
                         relocate << home();
                        }]
  } 
  else {
		actionLink("login")[onclick:= {replace popuptarget << login();}]
  }
}

define login(){
	popup("Please provide your credentials")
	define popupBody() {
	  var usr: User := User{}
    form {
		  group("Enter credentials to login"){ 
		    < label("user ")		{ input(usr.nick) } 	>
		    < label("password")	{ input(usr.password) }	>		    
		  }
 			--
			#[class:= right] {
				container[id := loginerror, class:= error]{""}  //to display errors
				action("Login",  login())
				action("Cancel", { visibility this << hide; })
			}
			//the login action
			action login(){
			  var users : List<User> :=
			     select u from User as u 
			     where (u._nick = ~usr.nick);

			  for (us : User in users ){
			    if (us.password.check(usr.password)){
			      securityContext.principal := us;
			      securityContext.loggedIn := true;
			      return home();
			    }
			  }
			  securityContext.loggedIn := false;
			  securityContext.principal := null;
			  clear loginerror <<;
			 	append loginerror << template{ "incorrect credentials provided" } ;
			}			
		}
  }
}
  
  define page signup(){
    main()
    define body() {
      table {
      	row[class:=topalign] {
      		group("Sign up for a lyves account"){
      			"Welcome" "We are glad you choose to use our services. Sign up today!"
      		}
      		var u: User := User{}
      		var s: Secret := ""
      		form {
	      		group("Request new account"){
							< label("your desired nickname: "){input(u.nick)}>
							< label("your desired password: "){input(u.password)}>
							< label("repeat your password: "){ input(s)}>
							< label("please enter the text of the image:") { captcha }>
							< container[id:= signuperror, class:= red]{""} action("sign up", dosignup()) >
      			}
      		}
      		action dosignup() {
      			if (s == u.password) {
      				u.name 			:= u.nick;
      				u.public 		:= false;
      				u.moderator := false;
      				u.password	:= u.password.digest();
	      			u.save();
  	    			return home();
  	    		}
  	    		else {
  	    			clear signuperror <<;
  	    			append signuperror << template { "the passwords didn't match!" };
  	    		}
      		}
      	}
      }
    }  
  }
  
  
