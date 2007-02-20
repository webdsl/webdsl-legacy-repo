package topics;

import java.util.*;
import java.lang.*;
import org.hibernate.*;

import util.*;
import topics.*;

public class RootTopic
{
    private static final Long roottopicid;

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
	   session.save(topic);
           roottopicid = topic.getId();
	   break;
         case 1 : 
           roottopicid = ((Topic)(topics.get(0))).getId();
	   break;
         default :
	   throw new RuntimeException("too many root objects; fix the database");
	   // alternatively we might choose a root object at random
        }
	transaction.commit();
	session.close();
    }

    public static Long getRootTopicId()
    {
	return roottopicid;
    }

    public static Topic getRootTopic(Session session)
    {
	if (roottopicid == null)
	  throw new RuntimeException("root not initialized");

	if (session == null)
           throw new RuntimeException("session is null");

	Topic topic = (Topic) session.load(Topic.class, roottopicid);

	if (topic == null)
           throw new RuntimeException("root topic is null");

	if (!topic.getIsroot())
	  throw new RuntimeException("root is not root : " + topic.getId() + " : " + topic);

  	return topic;
    }

}
