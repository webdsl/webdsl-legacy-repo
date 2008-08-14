application com.example.strategocalls

description {
  This is an automatically generated description
}

section pages

function testIt() {
  var input : SDFInput<WebDSL>  := "application parseme";
  var parsed : ATerm := input.parse();
  var moduleName : String := parsed.get(0).toString();
}

define page home() {
  main()
  define body() {
    "stratego calls testing"    
        
    "I just parsed" text(testIt())
  }
}

entity exampleEntity {
  property :: String
}

