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
    authors  -> Set<User> (inverse=User.authored)
  }

  extend entity User {
    authored -> Set<Topic> 
  }

section versioning

  extend entity Topic {
    diffs    -> List<TopicDiff> // (order by version asc)
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
    
  globals {
  
    function contentOfVersion(topic:Topic,vs : Int) : WikiText {
      var cont : WikiText := topic.content;
      var cur  : Int := topic.version;
      //while (cur > vs) {
      //  cont := diffs.get(cur).patch.applyPatch(content);
      //  cur  := cur - 1;
      //}
      return cont;
    }
    
  }
  
section creating new topics

  globals {

    function newWeb(webname : String, viewers : Set<UserGroup>, 
                    editors : Set<UserGroup>, author : User) : Web 
    {
      var moderators : UserGroup := 
        UserGroup {
          name       := webname + "Moderators"
          //fullname   := webname + " Moderators Group"
          moderators := {author}
          members    := {author}
        };
      author.groups.add(moderators);
      var web : Web :=
        Web{
          name := webname
          acl := ACL{ 
                   view     := viewers 
                   edit     := editors 
                   moderate := {moderators}
                   admin    := {adminGroup} }
        };
      web.home    := newTopic(web, "WebHome", "WebHome", "", author);
      web.sidebar := newTopic(web, "SideBar", "SideBar", 
                       "* **[[topic(" + webname + "/WebHome)|" +  webname + "]]**", author);
      return web;
    }

    function newTopic(newWeb : Web, newTopic : String, 
                      newTitle : String, text : WikiText, author : User) : Topic 
    {
      var diff : TopicDiff :=
        TopicDiff {
          version := 0
          title   := newTitle
          // patch   := "".makePatch(text)
          author  := author
        };
      var topic : Topic := 
        Topic {
          key     := newWeb.name + "/" + newTopic
          web     := newWeb
          name    := newTopic
          title   := newTitle
          content := text
          version := 0
          acl     := ACL{ }
        };
      topic.authors.add(author);
      author.authored.add(topic);
      return topic;
    }

  }
  
section making change to a topic

  globals {
  
    function makeChange(topic:Topic, newTitle : String, newText : WikiText, newAuthor : User) : Topic 
    {
      var diff : TopicDiff := 
        TopicDiff {
          topic     := topic
          version  := topic.version + 1
          title    := newTitle
          patch    := newText.makePatch(topic.content)
          author   := newAuthor
        };
      topic.version := topic.version + 1;
      topic.title   := newTitle;
      topic.content := newText;
      topic.authors.add(newAuthor);
      return topic;
    }
    
  }
