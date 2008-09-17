application com.example.strategocalls

description {
  This is an automatically generated description
}

section pages

function testSDF() : String {
  // Declare a new variable associated with the WebDSL.tbl parse table, and assign a string to it
  var input : SDFInput<WebDSL>  := "application parseme" as SDFInput<WebDSL>;
  
  // Parse input
  var parsed : ATerm := input.parse();
  var appName : String := parsed.get(0).toString();
  
  return appName;
}

function testSTR() : String {
  // Set-up
  var input : SDFInput<WebDSL>  := "application parseme imports foo" as SDFInput<WebDSL>;
  var parsed : ATerm := input.parse();
  
  var importReader : Stratego := Stratego("read-imports");
  var importName : ATerm := importReader.invoke("read-import", parsed);
  
  return importName.toString();
}

define page home() {

  var sdf : String := testSDF();
  var str : String := testSTR();

  "stratego calls testing"    
  "sdf test appname: "     
  output(sdf)
  "-"
  "str test module name: "
  output(str)
}

entity exampleEntity {
  property :: String
}

