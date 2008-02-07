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
    define wikiOperationsMenuItems() {
      webOperationsMenuItems(web)
    }
    define sidebar() {
      output(web.sidebar.content)
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
    define wikiOperationsMenuItems() {
      webOperationsMenuItems(web)
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
        editPermissions(web.acl)
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
    define wikiOperationsMenuItems() {
      topicOperationsMenuItems(topic)
      webOperationsMenuItems(topic.web)
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
    define body() {
      block("twikiEditTopic") {
        section {
          header{"Edit Topic: " output(topic.name)}
          form { 
            par{ input(newTitle) }
	    par{ input(newContent) }
	    par{ action("Save changes", saveTopic()) }
            action saveTopic() {
              topic.makeChange(newTitle, newContent, securityContext.principal);
              topic.persist();
	      return topic(topic);
            }
	  }
        }
        editPermissions(topic.acl)
      }
    }
  }
  
  define page newTopic(web : Web)
  {
    var newName    : String;
    var newTitle   : String;
    var newContent : WikiText;
    main() 
    title{"Create New Topic"}
    define body() {
      block("twikiEditTopic") {
        section {
          header{"Create New Topic"}
          form { 
            table {
              row{ "Name"  input(newName) }
              row{ ""      "The name of a topic is the key that is used to refer to it "
                           "and cannot be changed after creation. " }
              row{ "Title" input(newTitle) }
	      row{ ""      input(newContent) }
	    }
	    action("Save changes", saveTopic()) 
	  }
          action saveTopic() {
            var topic : Topic := newTopic(web, newName, newTitle, newContent, securityContext.principal);
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
      wikiOperationsMenuItems()
      menuitem{ navigate(wikiIndex()){"Topic Index"} }
      menuitem{ navigate(newWeb()){"New Web"} }
      menuspacer{}
      for(p : Topic in config.starttopicsList) {
        menuitem{ output(p) }
      }
    }
  }
  
  define wikiOperationsMenuItems() {
  }
    
  define webOperationsMenuItems(web : Web)
  {
    menuitem{ navigate(newTopic(web)){"New Topic"} }
    menuitem{ navigate(web(web)){"Web Index"} }
  }
  
  define topicOperationsMenuItems(p : Topic)
  {
    menuitem{ navigate(editTopic(p)) { "Edit This Topic" } }
    menuitem{ 
      if (p in config.starttopics) {
        form {
          actionLink("Remove as Starttopic", unmakeStarttopic())
          action unmakeStarttopic() {
            config.starttopics.remove(p);
            config.persist();
          }
        }
      }
      if (!(p in config.starttopics)) {
        form{
          actionLink("Add to Starttopics", makeStarttopic())
          action makeStarttopic() {
            config.starttopics.add(p);
            config.persist();
          }
        }
      }
    }
    menuspacer{}
  }
  
  
  
  