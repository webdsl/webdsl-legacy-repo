// $Id: AbstractGeneratedPropertyTest.java 10435 2006-09-01 23:53:40Z steve.ebersole@jboss.com $
package org.hibernate.test.generated;

import org.hibernate.test.DatabaseSpecificTestCase;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.Hibernate;

/**
 * Implementation of AbstractGeneratedPropertyTest.
 *
 * @author Steve Ebersole
 */
public abstract class AbstractGeneratedPropertyTest extends DatabaseSpecificTestCase {
	public AbstractGeneratedPropertyTest(String x) {
		super( x );
	}

	public final void testGeneratedProperty() {
		GeneratedPropertyEntity entity = new GeneratedPropertyEntity();
		entity.setName( "entity-1" );
		Session s = openSession();
		Transaction t = s.beginTransaction();
		s.save( entity );
		s.flush();
		assertNotNull( "no timestamp retrieved", entity.getLastModified() );
		t.commit();
		s.close();

		byte[] bytes = entity.getLastModified();

		s = openSession();
		t = s.beginTransaction();
		entity = ( GeneratedPropertyEntity ) s.get( GeneratedPropertyEntity.class, entity.getId() );
		assertTrue( Hibernate.BINARY.isEqual( bytes, entity.getLastModified() ) );
		t.commit();
		s.close();

		assertTrue( Hibernate.BINARY.isEqual( bytes, entity.getLastModified() ) );

		s = openSession();
		t = s.beginTransaction();
		s.delete( entity );
		t.commit();
		s.close();
	}
}
