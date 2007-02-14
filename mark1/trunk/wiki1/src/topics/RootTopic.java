package topics;

import java.util.*;
import java.lang.*;
import org.hibernate.*;

import util.*;
import topics.*;

public class RootTopic
{
    private static final Topic roottopic;

    static {
	Session session = HibernateUtil.getSessionFactory().openSession();
	Transaction transaction = session.beginTransaction();
        List topics = 
          session.createQuery("from Topic as topic where topic.isroot = true")
                 .list();
        switch (topics.size()) {
         case 0 : 
           Topic topic = new Topic();
           topic.setIsroot(true);
           roottopic = topic;
	   session.save(topic);
	   break;
         case 1 : 
           roottopic = (Topic)topics.get(0);
	   break;
         default :
	   throw new RuntimeException("too many root objects; fix the database");
	   // alternatively we might choose a root object at random
        }
	transaction.commit();
	session.close();
    }

    public static Topic getRootTopic()
    {
	return roottopic;
    }

}
