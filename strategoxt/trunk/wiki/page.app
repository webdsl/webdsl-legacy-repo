module wiki/page

section main page for wiki

  define page wiki()
  {
    main()
    title{output(config.wikistartpage.title)}
    define body() {
      output(config.wikistartpage.content)
    }
  }

  define page wikiIndex()
  {
    main()
    title{"Wiki Topic Index"}
    define body() {
      section { 
        header{"Wiki Webs"}
        list { for(web : Web order by web.name desc) { 
          listitem { output(web) }
        } }
      }
      section { 
        header{"Wiki Topic Index"}
        list { for(p : Topic) { 
          listitem { output(p) }
        } }
      }
      section { 
        header{"TWiki Topic Index"}
        list { for(p : TwikiPage) { 
          listitem { output(p) }
        } }
      }
    }
  }
  
section web

  define page web(web : Web)
  {
    main()
    title{"Web " output(web.name)}
    define sidebar() {
      output(web.sidebar.content)
    }
    define thisMenu() {
      thisWebMenu(web)
    }
    define body() {
      section { 
        header{output(web.title)}
        par{output(web.home.content)}
      }
    }
  }

  define page webIndex(web : Web)
  {
    main()
    title{"Index of Web " output(web.name)}
    define thisMenu() {
      thisWebMenu(web)
    }
    define sidebar() {
      output(web.sidebar.content)
    }
    define body() {
      section { 
        header{"Index of " output(web) " Web"}
        list { for(p : Topic in web.topicsList) { 
          listitem { output(p) }
        } }
      }
    }
  }
  
  define page editWeb(web : Web)
  {
    main()
    title{"Edit Web " output(web.name)}
    define thisMenu() {
      thisWebMenu(web)
    }
    define sidebar() {
      output(web.sidebar.content)
    }
    define body() {
      section{
        header{"Edit Web " output(web)}
        form{
          table{
            row{ "Title"   input(web.title) }
            row{ "Home"    input(web.home) }
            row{ "    "    input(web.home.content) }
            row{ "Sidebar" input(web.sidebar) }
            row{ ""        input(web.sidebar.content) }
          }
          action("Save", saveWeb())
          action saveWeb() {
            web.save();
            return web(web);
          }
        }
      }
    }
  }
  
  define page editWebPermissions(web : Web)
  {
    main() 
    title{"Edit Permissions of Web: " output(web)}
    define sidebar() {
      output(web.sidebar.content)
    }
    define thisMenu() {
      thisWebMenu(web)
    }
    define body() {
      block("twikiEditTopic") {
        section {
          header{"Edit Permissions of Web: " output(web)}
          editPermissions(web.acl, web.acl)
        }
      }
    }
  }
  
  define page newWeb()
  {
    main()
    title{"New Web"}
    define body() {
      var name    : String;
      var viewers : Set<UserGroup>;
      var editors : Set<UserGroup>;
      section {
        header { "Create New Web" }
        form {
          table{
            row{ "Name"    input(name) }
            row{ "Viewers" input(viewers) }
            row{ "Editors" input(editors) }
          }
          action("Create",  createWeb())
          action createWeb() {
            var web : Web := newWeb(name, viewers, editors, securityContext.principal);
            web.persist();
            return web(web);
          }
        }
      }
    }
  }

section wiki topic
  
  define page topic(topic : Topic)
  {
    init {
      if(topic.authors.length = 0) {
        // This is a new topic
        goto accessDenied(); 
      }
    }
    main()
    title{output(topic.title)}
    define sidebar() {
      output(topic.web.sidebar.content)
    }
    define thisMenu() {
      thisWebMenu(topic.web)
      thisTopicMenu(topic)
    }
    define body() {
      section {
        block("twikiTopicTitle") {
          header{ output(topic.title) }
        }
    par{ output(topic.content) }
    block("wikiTopicByLine") {
      par{"Contributions by " 
        for(author : User in topic.authorsList) {
          output(author) " "
        }
      }
        }
      }
    }
  }
  
section wiki topic editing

  define page editTopic(topic : Topic)
  {
    var newTitle   : String   := topic.title;
    var newContent : WikiText := topic.content;
    main() 
    title{"Edit Topic: " output(topic.name)}
    define sidebar() {
      output(topic.web.sidebar.content)
    }
    define thisMenu() {
      thisWebMenu(topic.web)
      thisTopicMenu(topic)
    }
    define body() {
      block("twikiEditTopic") {
        section {
          header{"Edit Topic: " output(topic.name)}
          form { 
            par{ input(newTitle) }
            par{ input(newContent) }
            par{ action("Save changes", saveTopic()) }
            action saveTopic() {
              makeChange(topic,newTitle, newContent, securityContext.principal);
              topic.persist();
              return topic(topic);
            }
          }
        }
        editPermissions(topic.acl, topic.web.acl)
      }
    }
  }
  
  define page editTopicPermissions(topic : Topic)
  {
    main() 
    title{"Edit Permissions of Topic: " output(topic.name)}
    define sidebar() {
      output(topic.web.sidebar.content)
    }
    define thisMenu() {
      thisWebMenu(topic.web)
      thisTopicMenu(topic)
    }
    define body() {
      block("twikiEditTopic") {
        section {
          header{"Edit Permissions of Topic: " output(topic.name)}
          editPermissions(topic.acl, topic.web.acl)
        }
      }
    }
  }
    
  define page newTopic(web : Web)
  {
    var newName    : String;
    var newTitle   : String;
    var newContent : WikiText;
    var viewers    : Set<UserGroup>;
    var editors    : Set<UserGroup>;
    main() 
    title{"Create New Topic"}
    define sidebar() {
      output(web.sidebar.content)
    }
    define thisMenu() {
      thisWebMenu(web)
    }
    define body() {
      block("twikiEditTopic") {
        section {
          header{"Create New Topic"}
          form { 
            table {
              row{ "Name"  input(newName) }
              row{ ""      "The name of a topic is the key that is used to refer to it and cannot be changed after creation. " }
              row{ "Title" input(newTitle) }
              row{ ""      input(newContent) }
              row{ "Viewers" input(viewers) }
              row{ "Editors" input(editors) }
           }
           action("Save changes", saveTopic())
          }
          action saveTopic() {
            var topics : List<Topic> := select t from Topic as t where (t._key = ~newName);
            
            for (t : Topic in topics) {
              // give error message: topic already exists
              return topic(t);
            }            
            
            var topic : Topic
                := newTopic(web, newName, newTitle, newContent, securityContext.principal);
            topic.acl.view := viewers;
            topic.acl.edit := editors;
            topic.persist();
            return topic(topic);
          }
        }
      }
    }
  }

section wiki topic history
  
  // TODO
  
  // show specific version
  
  // show change history
  
section topic operations

  define wikiMenu()
  {
    menu{
      menuheader{ navigate(wiki()){"Wiki"} }
      menuitem{ navigate(wikiIndex()){"All Topics"} }
      menuspacer{}
      for(p : Topic in config.starttopicsList) {
        menuitem{ output(p) }
      }
    }
  }
  
  define thisWikiMenu() {
    menu{
      menuheader{ navigate(wiki()){ "This Wiki" }}
      menuitem{ navigate(newWeb()){"New Web"} }
      menuitem{ navigate(configuration(config)){"Configuration"} }
      menuitem{ navigate(editConfiguration(config)){"Edit Configuration"} }
      menuitem{ navigate(pendingRegistrations()){"Pending Registrations"} }
    }
  }
  
  define thisWebMenu(web : Web)
  {
    menu{
      menuheader{ navigate(web(web)){"This Web"} }
      menuitem{ navigate(web(web)){output(web.name) " WebHome"} }
      menuitem{ navigate(webIndex(web)){output(web.name) " Index"} }
      menuspacer{}
      menuitem{ navigate(newTopic(web)){"New Topic"} }
      menuitem{ navigate(editWeb(web)){"Edit " output(web.name) " Web"} }
    }
  }
  
  define thisTopicMenu(t : Topic)
  {
    menu{
      menuheader{ navigate(topic(t)){"This Topic"} }
      menuitem{ navigate(editTopic(t)) { "Edit Content" } }
      menuitem{ navigate(editTopicPermissions(t)) { "Edit Permissions" } }
      menuitem{ 
        if (t in config.starttopics) {
          form {
            actionLink("Remove as Starttopic", unmakeStarttopic())
            action unmakeStarttopic() {
              config.starttopics.remove(t);
              config.persist();
            }
          }
        }
        if (!(t in config.starttopics)) {
          form{
            actionLink("Add to Starttopics", makeStarttopic())
            action makeStarttopic() {
              config.starttopics.add(t);
              config.persist();
            }
          }
        }
      }
    }
  }
  
  
  
  