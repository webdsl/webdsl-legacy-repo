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

section pages

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
  container[id := popuptarget, class:= error]{
  	//empty
  }
}

define login(){
 form {
	 var usr : User := User{};
 		popup("Please provide your credentials")
 		define popupBody() {
		  group("Enter credentials to login"){ 
		    < label("user ")			{ input(usr.nick) } 	>
		    < label("password")	{ input(usr.password) }	>		    
		  }
		}
		define popupFooter() {
			container[id := loginerror, class:= error]{""}  //to display errors
			action("Log In", login())
		  action login(){
		    var users : List<User> :=
		       select u from User as u 
		       where (u._name = ~usr.name);

		    for (us : User in users ){
		      if (us.password.check(usr.password)){
		        securityContext.principal := us;
		        securityContext.loggedIn := true;
		        return home();
		      }
		    }
		    securityContext.loggedIn := false;
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
      				u.name 	:= u.nick;
      				u.public 		:= false;
      				u.moderator := false;
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
  
  
