module init

section global data

  var user1 : User :=
    User {
      username := "zef"
      password := "secret"
    };
  
  var post1 : Post :=
    Post {
      author := user1
      title := "Hello world!"
      text := "This is my first post"
      commentCount := 1
      status := publishedStatus
    };

  var comment1 : Comment :=
    Comment {
      post := post1
      name := "Commenter"
      text := "Hey to you too!"
    };

section pages

  var aboutPage : Page := 
    Page {
      name := "About"
      text := "This blog software is awesome and was written by Zef Hemel."
    };

