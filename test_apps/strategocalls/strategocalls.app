application com.example.strategocalls

description {
	This is an automatically generated description
}

section pages

define page home() {
	main()
	define body() {
		"stratego calls testing"
		
		var input : SDFInput<WebDSL> := "application readme"
		var parsed : ATerm := input.parse()
		var moduleName : String := parsed.get(0).toString()
		
		"I just parsed" text(moduleName)
	}
}

entity exampleEntity {
	property :: String
}

