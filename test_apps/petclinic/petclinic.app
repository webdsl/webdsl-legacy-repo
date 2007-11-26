application com.example.petclinic

description {
  This is an automatically generated description
}

imports templates
imports datamodel
imports init
imports pages

section pages

define page home() {
  main()
  define body() {
    header { "Welcome" }
    
    div("find_owner_div") { navigate(findOwner()) { "Find owner" } }
    div("display_vets_div") { navigate(vets()) { "Display veterinarians" } }
  }
}
