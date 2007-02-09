//$Id: MultipleHiLoPerTableGeneratorTest.java 9914 2006-05-09 09:37:18Z max.andersen@jboss.com $
package org.hibernate.test.id;

import org.hibernate.test.TestCase;
import org.hibernate.Session;
import org.hibernate.Transaction;
import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * @author Emmanuel Bernard
 */
public class MultipleHiLoPerTableGeneratorTest extends TestCase {
	public MultipleHiLoPerTableGeneratorTest(String x) {
		super(x);
	}

	protected String[] getMappings() {
		return new String[]{
			"id/Car.hbm.xml",
			"id/Plane.hbm.xml",
			"id/Radio.hbm.xml"
		};
	}

	public void testDistinctId() throws Exception {
		Session s = openSession();
		Transaction tx = s.beginTransaction();
		int testLength = 8;
		Car[] cars = new Car[testLength];
		Plane[] planes = new Plane[testLength];
		for (int i = 0; i < testLength ; i++) {
			cars[i] = new Car();
			cars[i].setColor("Color" + i);
			planes[i] = new Plane();
			planes[i].setNbrOfSeats(i);
			s.persist(cars[i]);
			//s.persist(planes[i]);
		}
		tx.commit();
		s.close();
		for (int i = 0; i < testLength ; i++) {
			assertEquals(i+1, cars[i].getId().intValue());
			//assertEquals(i+1, planes[i].getId().intValue());
		}
		
		s = openSession();
		tx = s.beginTransaction();
		s.createQuery( "delete from Car" ).executeUpdate();
		tx.commit();
		s.close();
	}

	public void testRollingBack() throws Throwable {
		Session s = openSession();
		Transaction tx = s.beginTransaction();
		int testLength = 3;
		Long lastId = null;
		for (int i = 0; i < testLength ; i++) {
			Car car = new Car();
			car.setColor( "color " + i );
			s.save( car );
			lastId = car.getId();
		}
		tx.rollback();
		s.close();

		s = openSession();
		tx = s.beginTransaction();
		Car car = new Car();
		car.setColor( "blue" );
		s.save( car );
		s.flush();
		tx.commit();
		s.close();

		assertEquals( "id generation was rolled back", lastId.longValue() + 1, car.getId().longValue() );

		s = openSession();
		tx = s.beginTransaction();
		s.createQuery( "delete Car" ).executeUpdate();
		tx.commit();
		s.close();
	}

	public void testAllParams() throws Exception {
		Session s = openSession();
		Transaction tx = s.beginTransaction();
		Radio radio = new Radio();
		radio.setFrequency("32 MHz");
		s.persist(radio);
		assertEquals( new Integer(1), radio.getId() );
		radio = new Radio();
		radio.setFrequency("32 MHz");
		s.persist(radio);
		assertEquals( new Integer(2), radio.getId() );
		tx.commit();
		s.close();
		
		s = openSession();
		tx = s.beginTransaction();
		s.createQuery( "delete from Radio" ).executeUpdate();
		tx.commit();
		s.close();
	}

	public static Test suite() {
		return new TestSuite(MultipleHiLoPerTableGeneratorTest.class);
	}
}
