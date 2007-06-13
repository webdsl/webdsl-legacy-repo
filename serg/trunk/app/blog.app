module app/blog

description

  A blog is a journal-like sequence of time-stamped entries. The
  main page of a blog shows the n most recent entries. Entries also
  have their own page.

end

section domain.

  Blog {
    title      :: String (name)
    author     -> Person
    entries    <> List<BlogEntry>
    categories -> List<Category> // share categories between blogs?
  }
  
  BlogEntry {
    blog     -> Blog
    title    :: String (name)
    created  :: Date
    category -> Category // select from categories defined in blog
    intro    :: Text
    body     :: Text
    comments <> List<BlogComment>
  }
  
  Category {
    name :: String
  }
  
  BlogComment {
    author -> Person
    text :: Text
  }

section pages.

  define blogSidebar(blog : Blog) {
    personSidebar(blog.author)
  }
    
  define page viewBlog(blog : Blog) {
    main()
    
    define blogEntries() {
      list{
        for(entry : BlogEntry in blog.entries) {
          listitem { navigate(entry.name, viewBlogEntry(entry)) }
        }
      }
    }
    
    define sidebar(){ blogSidebar(blog) }
    
    define manageMenu() { 
       navigate("Edit", editBlog(blog))
       form{actionLink("New Blog", createNewBlogEntry())} 
       action createNewBlogEntry() {
         var entry : BlogEntry := 
           BlogEntry{
             blog := blog
             title := "title here"
           };
         blog.entries.add(entry);
         blog.persist();
         return editBlogEntry(entry);
       }
    }
    
    define body() {
      title{text(blog.title)}
      section{ 
        header{ text(blog.title) }
        for(entry : BlogEntry in blog.entries) {
          section{ 
            header{ text(entry.title) }
            div("blogIntro"){outputText(entry.intro)} " "
            navigate("read more ...", viewBlogEntry(entry))
          }
        }
      }
    }
  }

  define page viewBlogEntry(entry : BlogEntry) {
    main()
    define sidebar(){ blogSidebar(entry.blog) }
    define manageMenu() { navigate("Edit", editBlogEntry(entry)) }
    define body() {
      title{text(entry.title)}
      section{header{text(entry.title)}
        div("blogDate"){outputDate(entry.created)}
        div("blogIntro"){outputText(entry.intro)}
        div("blogBody"){outputText(entry.body)}
      }
    }
    define blogEntries() {
      list{
        for(entry : BlogEntry in entry.blog.entries) {
          listitem { navigate(entry.name, viewBlogEntry(entry)) }
        }
      }
    }
  }
