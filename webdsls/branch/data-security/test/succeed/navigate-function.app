application test

  define page root(){
    output(testString(navigate(testpage(432,"3wnrof4twh"))))
  }
  
  function testString(s : String):String{ return s; }
  
  define page testpage(i:Int,s:String)
  {
    output(i)
    output(s)
  }
