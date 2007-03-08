package org.webdsl.wiki.domain;

import java.util.*;
import org.hibernate.*;

import org.webdsl.wiki.domain.*;
import org.webdsl.wiki.xml.*;
import org.webdsl.wiki.utilities.*;

public class RootTopic {
	private static final Long roottopicid;

	static {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		List topics = session.createQuery("from Topic as topic where topic.isroot = true").list();
		switch (topics.size()) {
		case 0:
			Topic topic;
			try {

				// use getRealPath(path) from ServletContext to get file from
				// WAR
				// or maybe not
				// initialization of the database should probably be done
				// manually
				// (or using a script) using the web interface
				// maybe should switch to this mode already during testing

				FromXML h = FromXML.parse("/home/eelco/webdsl/mark1/wiki1/data/dbinit.xml");
				topic = h.getTopic();
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
			topic.setIsroot(true);
			session.save(topic);
			roottopicid = topic.getId();
			break;
		case 1:
			roottopicid = ((Topic) (topics.get(0))).getId();
			break;
		default:
			throw new RuntimeException("too many root objects; fix the database");
		// alternatively we might choose a root object at random
		}
		transaction.commit();
		session.close();
	}

	public static Long getRootTopicId() {
		return roottopicid;
	}

	public static Topic getRootTopic(Session session) {
		if (roottopicid == null)
			throw new RuntimeException("root not initialized");

		if (session == null)
			throw new RuntimeException("session is null");

		Topic topic = (Topic) session.load(Topic.class, roottopicid);

		if (topic == null)
			throw new RuntimeException("root topic is null");

		if (!topic.getIsroot())
			throw new RuntimeException("root is not root : " + topic.getId()
					+ " : " + topic);

		return topic;
	}

}
