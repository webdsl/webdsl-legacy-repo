module people

section users

  entity User {
    username :: String (name, unique)
    password :: Secret
    person   -> Person (notnull)
  }
  
section persons
  
  entity Address {
    street :: String
    city   :: String
    phone  :: String
  }

  entity Person {
    fullname  :: String (name)
    email     :: Email
    homepage  :: URL
    photo     :: Image
    // birthdate :: Date
    address   <> Address
    user      -> User
    blog      -> Blog
  }
  
  //   publications := select pub from Publication as pub where ~this member of pub.authors
    
  //   rececentPublications :=
  // }
  
  //  query Person p {
  //    publications := Publication pub where pub.authors.has(p)
  //    projects := ResearchProject proj where proj.members.has(p)
  //  }

section person pages

  define personSidebar(p : Person) {
    list {
      listitem{navigate(p.name, viewPerson(p))}
      listitem{navigate("Publications", personPublications(p))}
      // @todo don't show link if field has null value
      listitem{navigate("Blog", viewBlog(p.blog)) blogEntries()}
      listitem { "Projects" listProjectAcronyms(p) }
    }
  }
  
  define blogEntries() {}
    
  define page viewPerson(person : Person) 
  {    
    main() 
    
    title{text(person.name)}
    
    define sidebar() { 
      personSidebar(person) 
    }
    
    define manageMenu() {
      navigate("Edit", editPerson(person))
    }
    
    define body() {
      section{
      	div("photo"){image(person.photo)}
      	
        header{text(person.name)}
    
      	section{
          header{"Coordinates"}
          table {
            row{"homepage" navigate(url(person.homepage))}
            row{"email"    navigate(url(person.email))}
            row{"address"  table{
                             row{text(person.address.street)}
                             row{text(person.address.city)}
                           }}
            row{"phone" text(person.address.phone)}
          }
        }
    
        section{ 
          header{"Publications"}
          publicationTitlesBy(person)
        }
        
        section { 
          header{"Projects"}
          listProjects(person)
        }
      }
    }
  }
  
section basic crud pages

  define page viewPersonSimple(person : Person) {
    div("crudTable"){
    table { 
      row { "Fullname" outputString(person.fullname) }
      row { "Email"    outputString(person.email) }
      row { "Homepage" outputString(person.homepage) }
      row { "Photo"    outputString(person.photo) }
      row { "Address"  "" }
      row { "Street"   outputString(person.address.street) }
      row { "City"     outputString(person.address.city) }
      row { "Phone"    outputString(person.address.phone) }
      row { "user"     output(person.user) }
      row { navigate("Edit", editPersonSimple(person)) "" }
    }
    }
  }

  define page editPersonSimple(person : Person) {
    div("crudTable"){ form { table { 
      row { "Fullname" input(person.fullname) }
      row { "Email"    input(person.email) }
      row { "Homepage" input(person.homepage) }
      row { "Photo"    input(person.photo) }
      row { "Address"  "" }
      row { "Street"   input(person.address.street) }
      row { "City"     input(person.address.city) }
      row { "Phone"    input(person.address.phone) }
      row { "user"     input(person.user) }
      row { action("Save", save()) "" }
    } } }
    action save() {
      person.save();
      return viewPersonSimple(person);
    }
  }
  