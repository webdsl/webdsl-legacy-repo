package topics;
import org.hibernate.Session;

import java.util.Date;
import java.util.*;

import util.HibernateUtil;

import users.User;

public class TopicManager {

    public static void main(String[] args) {
        TopicManager mgr = new TopicManager();

        if (args[0].equals("store")) {
            mgr.createAndStoreTopic("Topic 1", "Topic 1 text", new Date());
            mgr.createAndStoreTopic("Topic 2", "Topic 2 text", new Date());
            mgr.createAndStoreTopic("Topic 3", "Topic 3 text", new Date());
        }
	else if (args[0].equals("list")) {
	    List topics = mgr.listTopics();
	    for (int i = 0; i < topics.size(); i++) {
	        Topic theTopic = (Topic) topics.get(i);
	        System.out.println("Topic:   " + theTopic.getName() +
	                           "\nCreated: " + theTopic.getCreated());
	    }
	}
        else if (args[0].equals("addauthortotopic")) {
          Long topicId = mgr.createAndStoreTopic("My Topic", "the event ...", new Date());
          Long userId = mgr.createAndStoreUser("Foo", "Bar", "foo@net", "www.foo.net");
          mgr.addAuthorToTopic(userId, topicId);
          System.out.println("Added author " + userId + " to topic " + topicId);
        }

        HibernateUtil.getSessionFactory().close();
    }

    private Long createAndStoreTopic(String name, String text, Date created) 
    {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();

        session.beginTransaction();

        Topic theTopic = new Topic();
        theTopic.setName(name);
        theTopic.setText(text);
        theTopic.setCreated(created);
        theTopic.setModified(created);

        Long id = (Long)session.save(theTopic);

        session.getTransaction().commit();

 	return id;
    }

    private Long createAndStoreUser(String username, String fullname, String email, String url) 
    {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();

        session.beginTransaction();

        User theUser = new User();
        theUser.setUsername(username);
        theUser.setFullname(fullname);
        theUser.setEmail(email);
        theUser.setUrl(url);

        Long id = (Long)session.save(theUser);

        session.getTransaction().commit();

	return id;
    }

    private List listTopics() {

    	Session session = HibernateUtil.getSessionFactory().getCurrentSession();

    	session.beginTransaction();

    	List result = session.createQuery("from Topic").list();

    	session.getTransaction().commit();

    	return result;
    }

    private void addAuthorToTopic(Long userId, Long topicId) {

       Session session = HibernateUtil.getSessionFactory().getCurrentSession();
       session.beginTransaction();

       User aUser = (User) session.load(User.class, userId);
       Topic aTopic = (Topic) session.load(Topic.class, topicId);

       aUser.getTopics().add(aTopic);

       session.getTransaction().commit();
   }

}
