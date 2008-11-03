application lyves

imports user
imports security
imports styles
imports menustyle
imports search
imports popup
imports news
imports init


section pages

define page home() {
  main()
  define body() {
    var s: String := ""
    table[class:=maxwidth] {
      <[class:=topalign] 
      |
          group("News") {
            newsitems()
          }
          group("Sign up") {
            groupitem { "Not a member of Lyves yet? Click " ~ signup() : " here " " to sign up!" }
          }
     	|
          group("Quicksearch") {
            groupitem{ "search by name: " input(s)[onclick := { dosearch(s); }] }
          }
          group("Search results")[id:= searchresulttable] {
            "nothing searched..."
          }
      >
    }
  }
}
 

define main() {
  table[align:= "center", width:=1024px] {
    < | image("/images/shadow/shadow_1_1.png")[height:= 20px]
      | image("/images/shadow/shadow_1_2.png")[class:=maxwidth, height:= 20px]
      | image("/images/shadow/shadow_1_3.png")[height:= 20px]
    ><  [class:= maxheight] 
      | image("/images/shadow/shadow_2_1.png")[class:= maxheight, width:=23px]
      | [class:= whitebg]
        topheader()
        topmenu()
        #[id:= body, class:= topalign] {
          body()
        }
      | image("/images/shadow/shadow_2_3.png")[class:= maxheight, width:=25px]
    ><| image("/images/shadow/shadow_3_1.png")[height:= 24px]
      | image("/images/shadow/shadow_3_2.png")[class:= maxwidth, height:= 24px]
      | image("/images/shadow/shadow_3_3.png")[height:= 24px]
    >
  }
  container[id := popuptarget]{
  	//empty
  }
}

define topheader() {
  table[width:= 100%] {
    <| [rowspan := "2"] image("/images/lyves.png") 
     | container[class := title] { "Hyves" }
     | loginlogout()
    >
    <| [colspan := "2"] #[class:=subtitle]{ "get a lyfe; don't hyve!"}>
  }
}

define topmenu() {
  menubar {
    menu{
      menuheader{ ~home():"MyLyves" }
    }	
    menu {
      menuheader {"MyBlog"}
    }
     menu {
       menuheader {"MeSignOut"}
     } 
     quicksearch() 
  }
}
