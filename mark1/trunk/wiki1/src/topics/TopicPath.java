package topics;

import java.util.*;

import users.*;

public class TopicPath extends ArrayList
{
   public TopicPath(String path, User user)
   {
      this(path, user, true);
   }

   public TopicPath(String path, User user, Boolean create)
   {
     super();

     if (path == null || path.equals("")) { path = "/"; }

     String xs[] = path.split("/");

     Topic topic = RootTopic.getRootTopic();
     add(topic);

     for(int i = 0; i < xs.length; i++) {
	String name = xs[i];
	Topic subtopic = topic.getSubtopic(name);
	if (subtopic == null) {
          if (create)
            {
	      subtopic = new Topic();
              subtopic.setTitle(name);
	      subtopic.addAuthor(user);
              topic.addSubtopic(name, subtopic);
            }
          else
            { 
              throw new TopicDoesNotExistException(path);
            }
        }
	add(subtopic);
        topic = subtopic;
     }
   }

}

