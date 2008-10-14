application lyves

imports styles

section pages

define page home() {
    table[align:= "center"] {
      row { 
            image("/images/shadow/shadow_1_1.png")[height:= 20px]
            image("/images/shadow/shadow_1_2.png")[class:=maxwidth, height:= 20px]
            image("/images/shadow/shadow_1_3.png")[height:= 20px]
      }
      row[class:= maxheight] {
            image("/images/shadow/shadow_2_1.png")[class:= maxheight, width:=23px]
            main()
            image("/images/shadow/shadow_2_3.png")[class:= maxheight, width:=25px]
      }
      row {
            image("/images/shadow/shadow_3_1.png")[height:= 24px]
            image("/images/shadow/shadow_3_2.png")[class:= maxwidth, height:= 24px]
            image("/images/shadow/shadow_3_3.png")[height:= 24px]
      }
    }
}

define main() {
    topheader()
    topmenu()
    quickpane()
    body()	
}

define topheader() {
  block("title"){ image("/images/lyves.png") "lyves"}
  block("subtitle"){"get a lyfe; don't hyve!"}
}

define topmenu() {
  menubar {
    menu{
      menuheader{ navigate(home()){"This Group"} }
      menuitem{ "boe" }
      menuspacer{}
      menuitem{ navigate(home()){"Edit this Group"} }
      menuitem{ navigate(home()){"Membership Requests"} }
    }	
     menu {
       menuheader {"MyBlog"}
     }
     menu {
       menuheader {"MeSignOut"}
     } 
     menu {
       menuheader { quicksearch() }
     }
  }
}

define quicksearch() {

}

define quickpane() {

}

define body() {

}
