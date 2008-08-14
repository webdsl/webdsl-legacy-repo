application com.example.strategocalls

description {
  This is an automatically generated description
}

section pages

function testIt():String {
  var input : SDFInput<WebDSL>  := "application parseme";
  var parsed : ATerm := input.parse();
  var moduleName : String := parsed.get(0).toString();
  return moduleName;
}

define page home() {

  var s:String := testIt();

  "stratego calls testing"    
        
  output(s)
  
  
  //output(testIt())
}

entity exampleEntity {
  property :: String
}

