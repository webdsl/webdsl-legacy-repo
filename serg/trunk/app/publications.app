module app/publications

description

  Publications are published documents. There is a large variety of publication mediums,
  each of which is cited in different ways. BibTeX provides a good domain model, which 
  should be modeled here. 
  
end

section publications.
    
  Publication {
    title    :: String (name)
    subtitle :: String
    year     :: Int // use Year defined type
    pdf      :: URL
    authors  -> List<Person>
    abstract :: Text // note: abstract is a reserved word in java!
    projects -> Set<ResearchProject>
  }
    
  PublicationFoo {
    title    :: String (name)
    subtitle :: String
    year     :: Int // use Year defined type
    pdf      :: URL
    authors  -> List<Person>
    abstract :: Text // note: abstract is a reserved word in java!
    projects -> Set<ResearchProject>
  }
  
  TechnicalReport : Publication {
    number     :: Int
 // code       :: String := "TUD-SERG-" + year + "-" + number
    document   :: Text // should be Document or PDF or similar
    preprintof -> Publication
  }
  
  InProceedings : Publication {
    conference -> Conference
    pages      :: String
  }
  
  Conference {
    fullname   :: String
    acronym    :: String (name)
    booktitle  :: String
    editors    -> List<Person>
    place      :: String
    year       :: Int
    month      :: String
    url        :: URL
    acceptance :: Int // 0 .. 100 percentage
  }
    
  Article : Publication {
    journal -> Journal
    pages   :: String
    impact  :: Int // average number of citations?
  }
  
  Journal {
    fullname :: String
    acronym  :: String (name)
  }
  
section presenting publications.

 define showPublication(pub : Publication) {
    for(author : Person in pub.authors){ 
      navigate(author.name, viewPerson(author)) ", "
    }
    navigate(pub.name, viewPublication(pub)) ", "
    text(pub.year) "."
  }

  define publicationsPage(publications : List<Publication>) {
    list {
      for(publication : Publication in publications) { 
        listitem{ showPublication(publication) }
      }
    }
  }
    
section looking up publications.

  define publicationsBy(person : Person) {
           
    var orderedPublications : List<Publication> :=
      select pub from Publication as pub, Person as pers 
       where (pers.id = ~person.id) and (pers member of pub._authors)
       order by pub._year descending;   
         
    list { for(pub : Publication in orderedPublications) {
      listitem { showPublication(pub) }
    } }
  }
   
  define publicationTitlesBy(person : Person) { 
           
    var orderedPublications : List<Publication> :=
      select pub from Publication as pub, Person as pers 
       where (pers.id = ~person.id) and (pers member of pub._authors)
       order by pub._year descending;   
         
    list { for(pub : Publication in orderedPublications) {
      listitem { output(pub) " (" text(pub.year) ")" }
    } }
  }
  
section publication pages.

  define page personPublications(person : Person) {
    main()
    title{"Publications by " text(person.name)}
    
    define sidebar() { personSidebar(person) }
    
    define body() {
      header{"Publications by " text(person.name)}
      publicationsBy(person)
    }
  }
  
section editing publications.
  
  define editRowsPublication (publication : Publication) {
    row(){
      "Title"
        input(publication.title){
      }
    }
    row(){
      "Subtitle"
        input(publication.subtitle){
      }
    }
    row(){
      "Authors"
      input(publication.authors)
    }
    row {
      ""
      table {
        var newAuthor : Person := Person{};
        row { "Fullname" input(newAuthor.fullname) }
        row { "Email"    input(newAuthor.email) }
        row { "" action("Add new author", addNewAuthor()) }
        action addNewAuthor() { 
          publication.authors.add(newAuthor);
          newAuthor := Person{};
        }
      }
    }
    row(){
      "Year"
      input(publication.year){
      }
    }
    row(){
      "Abstract"
      input(publication.abstract){
      }
    }
    row(){
      "Projects"
      input(publication.projects){
      }
    }
    row(){
      "Pdf"
      input(publication.pdf){
      }
    }
  }
