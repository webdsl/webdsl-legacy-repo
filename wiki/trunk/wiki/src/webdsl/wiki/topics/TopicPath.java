package topics;

import java.util.*;

import users.*;

public class TopicPath extends ArrayList
{
   private List elements = new ArrayList();

   public TopicPath(Topic root, String path)
   {
      this(root, path, null, false);
   }

   public TopicPath(Topic root, String path, User user)
   {
      this(root, path, user, true);
   }

   public TopicPath(Topic root, String path, User user, Boolean create)
   {
     super();

     if (path == null || path.equals("")) 
       { path = ""; }
     else while (path.startsWith("/"))
       { path = path.substring(1); }

     Topic topic = root;
     add(topic);

     if (!path.equals(""))
     {
       String xs[] = path.split("/");

       for(int i = 0; i < xs.length; i++) 
       {
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
          elements.add(name);
          topic = subtopic;
        }
      }
   }

   public List getElements()
   {
     return elements;
   }

}

