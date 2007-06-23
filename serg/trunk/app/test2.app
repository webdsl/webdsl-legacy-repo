application org.webdsl.test2

section domain. 

  User {
    username :: String (name, unique)
    password :: Secret
    person   -> Person (notnull)
  }
  
  Address {
    street :: String
    city   :: String
    phone  :: String
  }

  Person {
    fullname  :: String (name)
    email     :: Email
    homepage  :: URL
    photo     :: Image
    // birthdate :: Date
    address   <> Address
    user      -> User
    blog      -> Blog
  }
  
  Blog {
    title   :: String (name)
    author  -> Person
    entries <> List<BlogEntry>
  }
  
  BlogEntry {
    blog     -> Blog
    title    :: String (name)
    created  :: Date
    intro    :: Text
    body     :: Text
    comments <> List<BlogComment>
  }
  
  BlogComment {
    author -> Person
    text :: Text
  }

section setup.

  define main() {
    sidebar()
  }
  
  define page home() {}
   
section people pages.

  define blogSidebar(blog : Blog) {
    personSidebar(blog.author)
  }
    
  define page viewBlog(blog : Blog) {
    main()
    define sidebar(){ blogSidebar(blog) }
  }
  
  define personSidebar(p : Person) {
    list {
      listitem{navigate(p.name, viewPerson(p))}
    }
  }
    
  define page viewPerson(person : Person) 
  {    
    main() 
    define sidebar() { 
      personSidebar(person) 
    }
  }
