application smalltest2

imports templates
imports data

section fdsfsd
entity UsrList{

 thelist -> List<User>
}

globals 
{
var bla:UsrList:=UsrList{thelist := [User{name:="bla"}] };

}

  define page home(){
    main()
    define body()
    {

      header{"My Friends"}

      for(myf : User in bla.thelist) 
      { 
        section{navigate(user(myf)){output(myf.name)}}      
      }
     }
   }
   
 //  principal is User with credentials name
   
   
   
 // access control rules 
   
   //  rule page user(*)
     //{
      // true  
    // }
   