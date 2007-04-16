application org.webdsl.serg

description

  This application supports the tracking of the progress
  of students in a master's program by students and faculty.

end

section people. 

  Address {
    street : String
    number : String
    city   : String
  }
  // should phone numbers be part of address or separate?
  
  URL {
    url : String
  }

  Person {
    fullname  : String (name)
    addresses : Map<String,Address>
                // home, work, family, ...
    homepages : Set<String> // should be URLs
    photo     : Image
    user      : User
  }

  User {
    username : String (name, unique)
    email    : String (unique)
    password : String
    person   : Person // (secret)
    roles     : Set<Role>
  }

  // probably attach additional information to (some) roles
  // e.g., student has student number
  // is enum then a good model?

  //enum Role {
  //  coordinator
  //  student
  //  supervisor
  //  lecturer
  //  chair
  //  admin
  //}

  // when creating UI, infer names for roles from names for subclasses
  // of role

  Role {
    user : User
  }

  Admin : Role {}

  Chair : Role {}

  Coordinator : Role {}

  Student : Role {
    number : String
    year   : Int
  }

  Lecturer : Role {
    courses : Set<Course>
  }

  Supervisor : Role {
    students : Set<Student>
  }
  
  // Session {}
  
  //menu {
  //  menu {
  //    if (session.loggedin) { editUser() }
  //  }
  //}

  action createUser
  {
    user        := create User;   // create input form to get data
    person      := create Person;
    person.user := user;
    user.person := person;
  }

  action login {
    user := enter User.username, User.password, User.role;
    if (find User user)
      session.loggedin := user;
    else
      createUser();
  }

section courses or educational units.

  EduUnit {
    title   : String (name)
    code    : String
    credits : Int
  }

  Course {
    // name  := unit.name
    unit     : EduUnit
    year     : Int
    period   : Int
    lecturer : Person
  }

  StudentCourse {
    course  : Course
    student : Person // (.user.role has student)
    exam    : Date
    grade   : Int   // (0 .. 10)
  }

  StudentProject {
    unit        : EduUnit
    topic       : String
    description : Text
    student     : Set<Person>
    supervisor  : Set<Person>
    start       : Date
    finish      : Date
  }

  ResearchAssignment : StudentProject {
  }

  ThesisProject : StudentProject {
    milestones : List<Document>
    website    : URL
    subversion : URL
    // include a pointer to the research projects a thesis project
    // is affiliated with
  }

  Document {
    doc      : Text // PDF?
    date     : Date
    comments : Text
  }

section tracking students in a master program.

  StudentMaster {
    student        : User
    specialization : String
    courses        : Set<Course>
    research       : ResearchAssignment
    thesis         : ThesisProject
    start          : Date
    ending         : Date 
    status         : MasterStatus
  }

  //Specialization : String {}

  //enum MasterStatus {
  //  started
  //  assignment
  //  thesis
  //  graduated
  //}

  MasterStatus {}
  Started : MasterStatus {}
  Assignment : MasterStatus {}
  Thesis : MasterStatus {}
  Graduated : MasterStatus {}

  rules  

    sum(Master.courses.credits) = 60

    sum(Master.courses.credits) 
    + Master.research.credits 
    + Master.thesis = 120

    // courses.credits is an implicit map; maybe adopt an xpath 
    // style projection syntax

  //note 
  //  we want all kinds of views of the student population; it would
  //  be nice to generate a table that can be reordered interactively
  //  by selecting a particular column
  //end

  action activeStudentPopulation {
    for (x in StudentMaster where x.status != graduated ordered by start ascending)
      {
        show x.student.person.fullname, x.start, x.end, x.status;
      }
  }
  
section online documents . // wiki pages

  Topic {
    title    : String (name)
    text     : String // formatted text
    subtopic : Map<String, Topic>
    authors  : Set<User>
  }
  
section images .

  // @todo model images stored in application and external images (flickr, url)
  
section publications.

  TechnicalReport {
    title      : String (name)
    authors    : List<Person>
    year       : Int // use Year defined type
    number     : Int
 // code       : String := "TUD-SERG-" + year + "-" + number
    document   : Text // should be Document or PDF or similar
    trabstract : Text // note: abstract is a reserved word in java!
    project    : Set<ResearchProject>
    preprintof : Publication
  }
  
  Publication {
    title       : String (name)
    authors     : List<Person>
    year        : Int // use Year defined type
    pubabstract : Text
    // ehm encode variability of bibtex here
  }
  
section research.

  ResearchProject {
    name         : String
    members      : Map<String, Person>
    publications : Set<TechnicalReport>
  }

section init database .

  action initDB {
  
    Mekelweg4 := 
      Address {
        street := "Mekelweg"
        number := "4"
        city   := "Delft"
      }
  
    EelcoVisser := 
      Person {
        fullname := "Eelco Visser"
        addresses := ["work" -> Mekelweg4]
        homepages := ["http://www.eelcovisser.net"]
        photo := "http://static.flickr.com/56/141569082_372ea07ea9_m.jpg"
        user := User {
                  username := "Eelco Visser"
                  email := "visser@acm.org"
                  password := "foo"
                  person := EelcoVisser
                }
      }

  }