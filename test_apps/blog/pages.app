module pages

section pages

  define page user(user : User) {
    derive viewPage from user
  }

  define page post(p : Post) {
    title { output(p.title) }
    main()
    define body() {
      displayPost(p)
      section {
        header { "Comments" }
        for(c : Comment in p.comments order by c.date asc) {
          displayComment(c)
        }
      }
      section {
        header { "Add a comment" }
        form {
          var c : Comment := Comment{}
          table {
            row { "Your name:" input(c.name) }
            row { "Comment:" input(c.text) }
          }
          action("Post", postComment())

          action postComment() {
            c.post := p;
            c.save();
            p.commentCount := p.commentCount + 1;
            p.save();
            return post(p);
          }
        }
      }
    }
  }

  define page newPost() {
    title { "New post" }
    main()
    define body() {
      form {
        var p : Post := Post{ title := "New post" }

        header { input(p.title) }
        par { input(p.text) }

        action("Save draft", save())
        action("Publish", publish())

        action save() {
          p.author := securityContext.principal;
          p.status := draftStatus;
          p.save();
          return editPost(p);
        }
        action publish() {
          p.author := securityContext.principal;
          p.status := publishedStatus;
          p.save();
          return post(p);
        }
      }
    }
  }

  define page editPost(p : Post) {
    title { "Edit " output(p.title) }
    main()
    define body() {
      form {
        header { input(p.title) }
        par { input(p.text) }

        action("Save draft", save())
        action("Publish", publish())

        action save() {
          p.status := draftStatus;
          p.save();
          return editPost(p);
        }
        action publish() {
          p.status := publishedStatus;
          p.save();
          return post(p);
        }
      }
    }
  }

  define page comment(c : Comment) {
    derive viewPage from c
  }

section page pages

  define page page(p : Page) {
    title { output(p.name) }
    main()
    define body() {
      section {
        header { output(p.name) }
        output(p.text)
        navigate(editPage(p)) { "[ Edit ]" }
      }
    }
  }

  define page editPage(p : Page) {
    title { "Edit " output(p.name) }
    derive editPage from p
  }
