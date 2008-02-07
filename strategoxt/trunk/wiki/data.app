module wiki/data

section definition

  entity Web {
    name    :: String    (id, name)
    title   :: String
    home    -> Topic
    sidebar -> Topic
    topics  -> Set<Topic>
  }

  entity Topic {
    key      :: String (id)
    web      -> Web    (inverse=Web.topics)
    name     :: String
    title    :: String 
    content  :: WikiText
    authors  -> Set<User> 
  }

  extend entity User {
    authored -> Set<Topic> (inverse=Topic.authors)
  }

section versioning

  extend entity Topic {
    diffs    -> List<TopicDiff> (order by version asc)
    created  :: Date
    modified :: Date
    version  :: Int
  }

  entity TopicDiff {
    topic    -> Topic   (inverse=Topic.diffs)
    version  :: Int     // primary key with topic
    title    :: String
    patch    :: Patch   // patch to create content of this version from next
    created  :: Date
    author   -> User
  }

section content of a topic diff
    
  extend entity Topic {
  
    function contentOfVersion(vs : Int) : WikiText {
      var cont : WikiText := content;
      var cur  : Int := version;
      while (cur > vs) {
        cont := diffs.get(cur).patch.applyPatch(content);
        cur  := cur - 1;
      }
      return cont;
    }
    
  }
  
section creating new topics

  globals {

    function newWeb(webname : String, viewers : UserGroup, editors : UserGroup) : Web {
      var moderators : UserGroup := 
        UserGroup {
          groupname  := webname + "Moderators"
          fullname   := webname + " Moderators Group"
          moderators := {securityContext.principal}
          members    := {securityContext.principal}
        };
      var web : Web :=
        Web{
          name := webname
          acl := ACL{ 
                   view := {viewers} 
                   edit := {editors} 
                   moderate := {moderators}
                   admin := {adminGroup} }
        };
      web.home    := newTopic(web, "WebHome");
      web.sidebar := newTopic(web, "SideBar");
      return web;
    }

    function newTopic(newWeb : Web, newTopic : String, newTitle : String, text : WikiText) : Topic {
      var diff : TopicDiff :=
        TopicDiff {
          version := 0
          title   := newTitle
          patch   := "".makePatch(text)
          author  := securityContext.principal
        };
      var topic : Topic := 
        Topic {
          key     := newWeb.name + "/" + newTopic
          web     := newWeb
          name    := newTopic
          title   := newTitle
          content := text
          version := 0
          acl     := ACL{ view := {} edit := {} moderate := {} admin := {} }
        };
      topic.authors.add(securityContext.principal);
      return topic;
    }

  }
  
section making change to a topic

  extend entity Topic {
  
    function makeChange(newTitle : String, newText : WikiText, newAuthor : User) : Topic 
    {
      var diff : TopicDiff := 
        TopicDiff {
          topic     := this
          version  := this.version + 1
          title    := newTitle
          patch    := newText.makePatch(this.content)
          author   := newAuthor
        };
      this.version := this.version + 1;
      this.title   := newTitle;
      this.content := newText;
      this.authors.add(newAuthor);
      return this;
    }
    
  }
