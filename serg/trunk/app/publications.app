module publications

description

  Publications are published documents. There is a large variety of publication mediums,
  each of which is cited in different ways. BibTeX provides a good domain model, which 
  should be modeled here. 
  
end

section publications.
    
  Publication {
    title    :: String (name)
    subtitle :: String
    authors  -> List<Person>
    year     :: Int // use Year defined type
    abstract :: Text // note: abstract is a reserved word in java!
    projects -> Set<ResearchProject>
    pdf      :: URL
  }
    
  TechnicalReport : Publication {
    number     :: Int
 // code       :: String := "TUD-SERG-" + year + "-" + number
    document   :: Text // should be Document or PDF or similar
    preprintof -> Publication
  }
  
section publication pages.

  define page personPublications(p : Person) {
    title{"Publications by " text(p.name)}
    
    define sidebar() { personSidebar(p) }
    
    define body() {
      header{"Publications by " text(p.name)}
      //publicationsPage(p.publications)
    }
    main()
    
  }
  
  define publicationsBy(person : Person) {
  
    var publications : List<Publication> :=
      select pub from Publication as pub, Person as pers 
       where (pers.id = ~person.id) and (pers member of pub._authors); 
           
    var orderedPublications : List<Publication> :=
      select pub from Publication as pub, Person as pers 
       where (pers.id = ~person.id) and (pers member of pub._authors)
       order by pub._year descending; 
                 
    for(pub : Publication in orderedPublications) {
      div("line") {
        navigate(pub.name, viewPublication(pub))
        " (" text(pub.year) ")"
      }
    }
  }
  
  define publicationsForYear(pers : Person, year : Int) {
  }
  
     //    publicationsPage(pers.publications where p.year == year)

  define publicationsPage(publications : List<Publication>) {
    list{
      for(publication : Publication in publications) { 
        listitem{
          for(author : Person in publication.authors) {navigate(author.name, viewPerson(author)) ","}
          navigate(publication.name, viewPublication(publication))
        }
     }
    }
  }
  
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
