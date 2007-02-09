package org.hibernate.test.collection.idbag;

import java.util.ArrayList;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.hibernate.test.TestCase;
import org.hibernate.Session;
import org.hibernate.collection.PersistentIdentifierBag;

/**
 * Tests related to operations on a PersistentIdentifierBag
 *
 * @author Steve Ebersole
 */
public class PersistentIdBagTest extends TestCase {
	public PersistentIdBagTest(String name) {
		super( name );
	}

	protected String[] getMappings() {
		return new String[] { "collection/idbag/Mappings.hbm.xml" };
	}

	public static Test suite() {
		return new TestSuite( PersistentIdBagTest.class );
	}

	public void testWriteMethodDirtying() {
		IdbagOwner parent = new IdbagOwner( "root" );
		IdbagOwner child = new IdbagOwner( "c1" );
		parent.getChildren().add( child );
		IdbagOwner otherChild = new IdbagOwner( "c2" );

		Session session = openSession();
		session.beginTransaction();
		session.save( parent );
		session.flush();
		// at this point, the list on parent has now been replaced with a PersistentBag...
		PersistentIdentifierBag children = ( PersistentIdentifierBag ) parent.getChildren();

		assertFalse( children.remove( otherChild ) );
		assertFalse( children.isDirty() );

		ArrayList otherCollection = new ArrayList();
		otherCollection.add( child );
		assertFalse( children.retainAll( otherCollection ) );
		assertFalse( children.isDirty() );

		otherCollection = new ArrayList();
		otherCollection.add( otherChild );
		assertFalse( children.removeAll( otherCollection ) );
		assertFalse( children.isDirty() );

		children.clear();
		session.delete( child );
		assertTrue( children.isDirty() );

		session.flush();

		children.clear();
		assertFalse( children.isDirty() );

		session.delete( parent );
		session.getTransaction().commit();
		session.close();
	}
}
