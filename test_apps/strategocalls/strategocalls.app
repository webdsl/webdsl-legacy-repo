application com.example.strategocalls

description {
  This is an automatically generated description
}

section pages

function testSDF() : String {
  // Declare a new variable associated with the WebDSL.tbl parse table, and assign a string to it
  var input : SDFInput<WebDSL>  := "application parseme";
  
  // Parse input
  var parsed : ATerm := input.parse();
  var moduleName : String := parsed.get(0).toString();
  
  return moduleName;
}

function testSTR() : String {
  // Set-up
  var input : SDFInput<WebDSL>  := "application parseme imports foo";
  var parsed : ATerm := input.parse();
  
  var importReader : Stratego := Stratego("read-imports");
  var importName : ATerm := importReader.invoke("read-import", parsed);
  
  return importName.toString();
}

define page home() {

  var sdf : String := testSDF();
  var str : String := testSTR();

  "stratego calls testing"    
        
  output(sdf)
  "-"
  output(str)
}

entity exampleEntity {
  property :: String
}

