application com.example.petclinic

description {
  This is an automatically generated description
}

imports templates
imports datamodel
imports init
imports pages
imports style

section pages

define page home() {
  title { "PetClinic Home Page" }
  
  main()
  
  define body() {
    header { "Welcome" }
    
    div("find_owner_div") { navigate(findOwner()) { "Find owner" } }
    div("display_vets_div") { navigate(vets()) { "Display veterinarians" } }
  }
}
