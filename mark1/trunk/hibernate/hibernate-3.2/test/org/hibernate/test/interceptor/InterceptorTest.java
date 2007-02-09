//$Id: InterceptorTest.java 10783 2006-11-11 05:12:37Z steve.ebersole@jboss.com $
package org.hibernate.test.interceptor;

import java.util.List;
import java.io.Serializable;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;
import org.hibernate.test.TestCase;

/**
 * @author Gavin King
 */
public class InterceptorTest extends TestCase {
	
	public InterceptorTest(String str) {
		super(str);
	}

	public void testCollectionIntercept() {
		Session s = openSession( new CollectionInterceptor() );
		Transaction t = s.beginTransaction();
		User u = new User("Gavin", "nivag");
		s.persist(u);
		u.setPassword("vagni");
		t.commit();
		s.close();
		
		s = openSession();
		t = s.beginTransaction();
		u = (User) s.get(User.class, "Gavin");
		assertEquals( 2, u.getActions().size() );
		s.delete(u);
		t.commit();
		s.close();
	}	
	
	public void testPropertyIntercept() {
		Session s = openSession( new PropertyInterceptor() );
		Transaction t = s.beginTransaction();
		User u = new User("Gavin", "nivag");
		s.persist(u);
		u.setPassword("vagni");
		t.commit();
		s.close();
		
		s = openSession();
		t = s.beginTransaction();
		u = (User) s.get(User.class, "Gavin");
		assertNotNull( u.getCreated() );
		assertNotNull( u.getLastUpdated() );
		s.delete(u);
		t.commit();
		s.close();
	}

	/**
	 * Test case from HHH-1921.  Here the interceptor resets the
	 * current-state to the same thing as the current db state; this
	 * causes EntityPersister.findDirty() to return no dirty properties.
	 */
	public void testPropertyIntercept2() {
		Session s = openSession();
		Transaction t = s.beginTransaction();
		User u = new User("Josh", "test");
		s.persist( u );
		t.commit();
		s.close();

		s = openSession(
				new EmptyInterceptor() {
					public boolean onFlushDirty(Object entity, Serializable id, Object[] currentState, Object[] previousState, String[] propertyNames, Type[] types) {
						currentState[0] = "test";
						return true;
					}
				}
		);
		t = s.beginTransaction();
		u = ( User ) s.get( User.class, u.getName() );
		u.setPassword( "nottest" );
		t.commit();
		s.close();

		s = openSession();
		t = s.beginTransaction();
		u = (User) s.get(User.class, "Josh");
		assertEquals("test", u.getPassword());
		s.delete(u);
		t.commit();
		s.close();

	}

	public void testComponentInterceptor() {
		final int checkPerm = 500;
		final String checkComment = "generated from interceptor";

		Session s = openSession(
				new EmptyInterceptor() {
					public boolean onSave(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {
						if ( state[0] == null ) {
							Image.Details detail = new Image.Details();
							detail.setPerm1( checkPerm );
							detail.setComment( checkComment );
							state[0] = detail;
						}
						return true;
					}
				}
		);
		s.beginTransaction();
		Image i = new Image();
		i.setName( "compincomp" );
		i = ( Image ) s.merge( i );
		assertNotNull( i.getDetails() );
		assertEquals( checkPerm, i.getDetails().getPerm1() );
		assertEquals( checkComment, i.getDetails().getComment() );
		s.getTransaction().commit();
		s.close();

		s = openSession();
		s.beginTransaction();
		i = ( Image ) s.get( Image.class, i.getId() );
		assertNotNull( i.getDetails() );
		assertEquals( checkPerm, i.getDetails().getPerm1() );
		assertEquals( checkComment, i.getDetails().getComment() );
		s.delete( i );
		s.getTransaction().commit();
		s.close();
	}

	public void testStatefulIntercept() {
		final StatefulInterceptor statefulInterceptor = new StatefulInterceptor();
		Session s = openSession( statefulInterceptor );
		statefulInterceptor.setSession(s);
		
		Transaction t = s.beginTransaction();
		User u = new User("Gavin", "nivag");
		s.persist(u);
		u.setPassword("vagni");
		t.commit();
		s.close();
		
		s = openSession();
		t = s.beginTransaction();
		List logs = s.createCriteria(Log.class).list();
		assertEquals( 2, logs.size() );
		s.delete(u);
		s.createQuery( "delete from Log" ).executeUpdate();
		t.commit();
		s.close();
	}
	
	protected String[] getMappings() {
		return new String[] { "interceptor/User.hbm.xml", "interceptor/Image.hbm.xml" };
	}

	public static Test suite() {
		return new TestSuite(InterceptorTest.class);
	}

}

