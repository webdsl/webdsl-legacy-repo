//$Id: StatisticsTest.java 8894 2005-12-21 13:22:50Z steveebersole $
package org.hibernate.test.legacy;


import junit.framework.Test;
import junit.framework.TestSuite;
import junit.textui.TestRunner;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.stat.Statistics;

/**
 * @author Emmanuel Bernard
 */
public class StatisticsTest extends LegacyTestCase {

	public StatisticsTest(String x) {
		super(x);
	}
	
	public void testSessionStats() throws Exception {
		
		SessionFactory sf = getSessions();
		Statistics stats = sf.getStatistics();
		boolean isStats = stats.isStatisticsEnabled();
		stats.clear();
		stats.setStatisticsEnabled(true);
		Session s = sf.openSession();
		assertEquals( 1, stats.getSessionOpenCount() );
		s.close();
		assertEquals( 1, stats.getSessionCloseCount() );
		s = sf.openSession();
		Transaction tx = s.beginTransaction();
		A a = new A();
		a.setName("mya");
		s.save(a);
		a.setName("b");
		tx.commit();
		s.close();
		assertEquals( 1, stats.getFlushCount() );
		s = sf.openSession();
		tx = s.beginTransaction();
		String hql = "from " + A.class.getName();
		Query q = s.createQuery(hql);
		q.list();
		tx.commit();
		s.close();
		assertEquals(1, stats.getQueryExecutionCount() );
		assertEquals(1, stats.getQueryStatistics(hql).getExecutionCount() );
		
		stats.setStatisticsEnabled(isStats);
	}
	
	public String[] getMappings() {
		return new String[] { "legacy/ABC.hbm.xml", "legacy/ABCExtends.hbm.xml" };
	}

	public static Test suite() {
		return new TestSuite(StatisticsTest.class);
	}
	
	/**
	 * @see org.hibernate.test.TestCase#getMappings()
	 */
	public static void main(String[] args) throws Exception {
		TestRunner.run( suite() );
	}

}
