application com.example.aterm

description {
  This is an automatically generated description
}

section pages

function show(a:ATerm,prefix:String):String{
  
  if(a.length()>0)
  {
    var children:List<ATerm> := a.subterms();
    var s:String := a.constructor() + "\n\n";
  
    for(child:ATerm in children){
      
      s := s + prefix + " * " + show(child,prefix+"   ");
      s := s + "\n";
    }
  
    return s;
  }
  else
  {
    return a.toString();
  }
}


function testSDF(s:SDFInput<WebDSL>) : String {
  // Declare a new variable associated with the WebDSL.tbl parse table, and assign a string to it
  var input : SDFInput<WebDSL>  := s;
  
  // Parse input
  var parsed : ATerm := input.parse();
  var moduleName : String := show(parsed,""); //parsed.get(0).toString();
  
  return moduleName;
}



define page home() {
  var sdf : SDFInput<WebDSL>;



  "sdf test"
  
  form{
    inputText(sdf)
    action("convert",convert())
  }
  action convert(){
    sdf := testSDF(sdf);
  }      
  outputText(sdf)

}

entity exampleEntity {
  property :: String
}

