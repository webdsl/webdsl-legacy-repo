//$Id: AllTests.java 10807 2006-11-14 23:18:34Z steve.ebersole@jboss.com $
package org.hibernate.test;

import junit.framework.Test;
import junit.framework.TestSuite;
import junit.textui.TestRunner;

import org.hibernate.dialect.Dialect;
import org.hibernate.test.abstractembeddedcomponents.cid.AbstractCompositeIdTest;
import org.hibernate.test.abstractembeddedcomponents.propertyref.AbstractComponentPropertyRefTest;
import org.hibernate.test.any.AnyTypeTest;
import org.hibernate.test.array.ArrayTest;
import org.hibernate.test.ast.ASTIteratorTest;
import org.hibernate.test.ast.ASTUtilTest;
import org.hibernate.test.batchfetch.BatchFetchTest;
import org.hibernate.test.bidi.AuctionTest;
import org.hibernate.test.bidi.AuctionTest2;
import org.hibernate.test.bytecode.BytecodeSuite;
import org.hibernate.test.cache.CacheSuite;
import org.hibernate.test.cascade.RefreshTest;
import org.hibernate.test.cid.CompositeIdTest;
import org.hibernate.test.collection.CollectionSuite;
import org.hibernate.test.component.ComponentTest;
import org.hibernate.test.compositeelement.CompositeElementTest;
import org.hibernate.test.comppropertyref.ComponentPropertyRefTest;
import org.hibernate.test.connections.ConnectionsSuite;
import org.hibernate.test.criteria.CriteriaQueryTest;
import org.hibernate.test.cuk.CompositePropertyRefTest;
import org.hibernate.test.cut.CompositeUserTypeTest;
import org.hibernate.test.discriminator.DiscriminatorTest;
import org.hibernate.test.dom4j.Dom4jAccessorTest;
import org.hibernate.test.dom4j.Dom4jManyToOneTest;
import org.hibernate.test.dom4j.Dom4jTest;
import org.hibernate.test.dynamic.DynamicClassTest;
import org.hibernate.test.dynamicentity.interceptor.InterceptorDynamicEntityTest;
import org.hibernate.test.dynamicentity.tuplizer.TuplizerDynamicEntityTest;
import org.hibernate.test.ecid.EmbeddedCompositeIdTest;
import org.hibernate.test.entity.MultiRepresentationTest;
import org.hibernate.test.exception.SQLExceptionConversionTest;
import org.hibernate.test.extralazy.ExtraLazyTest;
import org.hibernate.test.filter.DynamicFilterTest;
import org.hibernate.test.formulajoin.FormulaJoinTest;
import org.hibernate.test.generated.TimestampGeneratedValuesWithCachingTest;
import org.hibernate.test.generated.TriggerGeneratedValuesWithCachingTest;
import org.hibernate.test.generated.TriggerGeneratedValuesWithoutCachingTest;
import org.hibernate.test.generatedkeys.select.SelectGeneratorTest;
import org.hibernate.test.generatedkeys.seqidentity.SequenceIdentityTest;
import org.hibernate.test.hql.HQLSuite;
import org.hibernate.test.id.MultipleHiLoPerTableGeneratorTest;
import org.hibernate.test.idbag.IdBagTest;
import org.hibernate.test.idclass.IdClassTest;
import org.hibernate.test.immutable.ImmutableTest;
import org.hibernate.test.instrument.buildtime.InstrumentTest;
import org.hibernate.test.instrument.runtime.CGLIBInstrumentationTest;
import org.hibernate.test.instrument.runtime.JavassistInstrumentationTest;
import org.hibernate.test.interceptor.InterceptorTest;
import org.hibernate.test.interfaceproxy.InterfaceProxyTest;
import org.hibernate.test.iterate.IterateTest;
import org.hibernate.test.join.JoinTest;
import org.hibernate.test.joinedsubclass.JoinedSubclassTest;
import org.hibernate.test.joinfetch.JoinFetchTest;
import org.hibernate.test.jpa.JPAComplianceSuite;
import org.hibernate.test.lazycache.InstrumentCacheTest;
import org.hibernate.test.lazycache.InstrumentCacheTest2;
import org.hibernate.test.lazyonetoone.LazyOneToOneTest;
import org.hibernate.test.legacy.ABCProxyTest;
import org.hibernate.test.legacy.ABCTest;
import org.hibernate.test.legacy.CacheTest;
import org.hibernate.test.legacy.ComponentNotNullTest;
import org.hibernate.test.legacy.ConfigurationPerformanceTest;
import org.hibernate.test.legacy.FooBarTest;
import org.hibernate.test.legacy.FumTest;
import org.hibernate.test.legacy.IJ2Test;
import org.hibernate.test.legacy.IJTest;
import org.hibernate.test.legacy.MapTest;
import org.hibernate.test.legacy.MasterDetailTest;
import org.hibernate.test.legacy.MultiTableTest;
import org.hibernate.test.legacy.NonReflectiveBinderTest;
import org.hibernate.test.legacy.OneToOneCacheTest;
import org.hibernate.test.legacy.ParentChildTest;
import org.hibernate.test.legacy.QueryByExampleTest;
import org.hibernate.test.legacy.SQLFunctionsTest;
import org.hibernate.test.legacy.SQLLoaderTest;
import org.hibernate.test.legacy.StatisticsTest;
import org.hibernate.test.manytomany.ManyToManyTest;
import org.hibernate.test.map.MapIndexFormulaTest;
import org.hibernate.test.mapcompelem.MapCompositeElementTest;
import org.hibernate.test.mapelemformula.MapElementFormulaTest;
import org.hibernate.test.mapping.PersistentClassVisitorTest;
import org.hibernate.test.mapping.ValueVisitorTest;
import org.hibernate.test.mappingexception.MappingExceptionTest;
import org.hibernate.test.mixed.MixedTest;
import org.hibernate.test.naturalid.NaturalIdTest;
import org.hibernate.test.ondelete.OnDeleteTest;
import org.hibernate.test.onetomany.OneToManyTest;
import org.hibernate.test.onetoone.joined.OneToOneTest;
import org.hibernate.test.onetooneformula.OneToOneFormulaTest;
import org.hibernate.test.ops.OpsSuite;
import org.hibernate.test.optlock.OptimisticLockTest;
import org.hibernate.test.ordered.OrderByTest;
import org.hibernate.test.orphan.OrphanIdRollbackTest;
import org.hibernate.test.orphan.OrphanTest;
import org.hibernate.test.pagination.PaginationTest;
import org.hibernate.test.propertyref.PropertyRefTest;
import org.hibernate.test.proxy.ProxyTest;
import org.hibernate.test.querycache.QueryCacheTest;
import org.hibernate.test.readonly.ReadOnlyTest;
import org.hibernate.test.rowid.RowIdTest;
import org.hibernate.test.sorted.SortTest;
import org.hibernate.test.sql.NativeSqlSupportSuite;
import org.hibernate.test.stats.SessionStatsTest;
import org.hibernate.test.stats.StatsTest;
import org.hibernate.test.subclassfilter.DiscrimSubclassFilterTest;
import org.hibernate.test.subclassfilter.JoinedSubclassFilterTest;
import org.hibernate.test.subclassfilter.UnionSubclassFilterTest;
import org.hibernate.test.subclasspropertyref.SubclassPropertyRefTest;
import org.hibernate.test.subselect.SubselectTest;
import org.hibernate.test.subselectfetch.SubselectFetchTest;
import org.hibernate.test.ternary.TernaryTest;
import org.hibernate.test.timestamp.TimestampTest;
import org.hibernate.test.tm.CMTTest;
import org.hibernate.test.typedmanytoone.TypedManyToOneTest;
import org.hibernate.test.typedonetoone.TypedOneToOneTest;
import org.hibernate.test.typeparameters.TypeParameterTest;
import org.hibernate.test.unconstrained.UnconstrainedTest;
import org.hibernate.test.unidir.BackrefTest;
import org.hibernate.test.unionsubclass.UnionSubclassTest;
import org.hibernate.test.util.UtilSuite;
import org.hibernate.test.version.VersionTest;
import org.hibernate.test.version.db.DbVersionTest;
import org.hibernate.test.version.sybase.SybaseTimestampVersioningTest;
import org.hibernate.test.where.WhereTest;
import org.hibernate.test.dialect.cache.SQLFunctionsInterSystemsTest;
import org.hibernate.test.lob.LobTest;
import org.hibernate.test.lob.ClobTest;

/**
 * @author Gavin King
 */
public class AllTests {

	/**
	 * Returns the entire test suite (both legacy and new
	 *
	 * @return the entire test suite
	 */
	public static Test suite() {
		TestSuite suite = new TestSuite();
		suite.addTest( NewTests.suite() );
		suite.addTest( LegacyTests.suite() );
		return suite;
	}

	/**
	 * Runs the entire test suite.
	 * <p/>
	 * @see #suite
	 * @param args n/a
	 */
	public static void main(String args[]) {
		TestRunner.run( suite() );
	}

	/**
	 * An inner class representing the new test suite.
	 */
	public static class NewTests {

		/**
		 * Returns the new test suite
		 *
		 * @return the new test suite
		 */
		public static Test suite() {
			TestSuite suite = new TestSuite("New tests suite");
			suite.addTest( OpsSuite.suite() );
			suite.addTest( NaturalIdTest.suite() );
			suite.addTest( ComponentTest.suite() );
			suite.addTest( ProxyTest.suite() );
			suite.addTest( VersionTest.suite() );
			suite.addTest( TimestampTest.suite() );
			suite.addTest( InterceptorTest.suite() );
			suite.addTest( EmbeddedCompositeIdTest.suite() );
			suite.addTest( ImmutableTest.suite() );
			suite.addTest( ReadOnlyTest.suite() );
			suite.addTest( IdClassTest.suite() );
			suite.addTest( ArrayTest.suite() );
			suite.addTest( TernaryTest.suite() );
			suite.addTest( CollectionSuite.suite() );
			suite.addTest( IdBagTest.suite() );
			suite.addTest( MapCompositeElementTest.suite() );
			suite.addTest( MapIndexFormulaTest.suite() );
			suite.addTest( MapElementFormulaTest.suite() );
			suite.addTest( BackrefTest.suite() );
			suite.addTest( BatchFetchTest.suite() );
			suite.addTest( CompositeIdTest.suite() );
			suite.addTest( CompositeElementTest.suite() );
			suite.addTest( CompositePropertyRefTest.suite() );
			suite.addTest( FormulaJoinTest.suite() );
			suite.addTest( DiscriminatorTest.suite() );
			suite.addTest( DynamicClassTest.suite() );
			suite.addTest( DynamicFilterTest.suite() );
			suite.addTest( InterfaceProxyTest.suite() );
			suite.addTest( OrphanTest.suite() );
			suite.addTest( OrphanIdRollbackTest.suite() );
			suite.addTest( JoinTest.suite() );
			suite.addTest( JoinedSubclassTest.suite() );
			suite.addTest( org.hibernate.test.unionsubclass2.UnionSubclassTest.suite() );
			suite.addTest( MixedTest.suite() );
			suite.addTest( OneToManyTest.suite() );
			suite.addTest( ManyToManyTest.suite() );
			suite.addTest( OneToOneFormulaTest.suite() );
			suite.addTest( OneToOneTest.suite() );
			suite.addTest( org.hibernate.test.onetoone.singletable.OneToOneTest.suite() );
			suite.addTest( org.hibernate.test.onetoonelink.OneToOneTest.suite() );
			suite.addTest( OptimisticLockTest.suite() );
			suite.addTest( PropertyRefTest.suite() );
			suite.addTest( ComponentPropertyRefTest.suite() );
			suite.addTest( org.hibernate.test.joineduid.PropertyRefTest.suite() );
			suite.addTest( org.hibernate.test.orphan.PropertyRefTest.suite() );
			suite.addTest( SubclassPropertyRefTest.suite() );
			suite.addTest( NativeSqlSupportSuite.suite() );
			suite.addTest( CriteriaQueryTest.suite() );
			suite.addTest( SubselectTest.suite() );
			suite.addTest( SubselectFetchTest.suite() );
			suite.addTest( JoinFetchTest.suite() );
			suite.addTest( UnionSubclassTest.suite() );
			suite.addTest( ASTIteratorTest.suite() );
			suite.addTest( HQLSuite.suite() );
			suite.addTest( ASTUtilTest.suite() );
			suite.addTest( CacheSuite.suite() );
			suite.addTest( QueryCacheTest.suite() );
			suite.addTest( CompositeUserTypeTest.suite() );
			suite.addTest( TypeParameterTest.suite() );
			suite.addTest( TypedOneToOneTest.suite() );
			suite.addTest( TypedManyToOneTest.suite() );
			suite.addTest( CMTTest.suite() );
			suite.addTest( MultipleHiLoPerTableGeneratorTest.suite() );
			suite.addTest( MultiRepresentationTest.suite() );
			suite.addTest( Dom4jAccessorTest.suite() );
			suite.addTest( Dom4jTest.suite() );
			suite.addTest( Dom4jManyToOneTest.suite() );
			suite.addTest( UnionSubclassFilterTest.suite() );
			suite.addTest( JoinedSubclassFilterTest.suite() );
			suite.addTest( DiscrimSubclassFilterTest.suite() );
			suite.addTest( UnconstrainedTest.suite() );
			suite.addTest( RowIdTest.suite() );
			suite.addTest( OnDeleteTest.suite() );
			suite.addTest( OrderByTest.suite() );
			suite.addTest( SortTest.suite() );
			suite.addTest( WhereTest.suite() );
			suite.addTest( IterateTest.suite() );
			suite.addTest( RefreshTest.suite() );
			suite.addTest( ExtraLazyTest.suite() );
			suite.addTest( StatsTest.suite() );
			suite.addTest( SessionStatsTest.suite() );
			suite.addTest( ConnectionsSuite.suite() );
			suite.addTest( SQLExceptionConversionTest.suite() );
			suite.addTest( ValueVisitorTest.suite() );
			suite.addTest( PersistentClassVisitorTest.suite() );
			suite.addTest( AuctionTest.suite() );
			suite.addTest( AuctionTest2.suite() );
			suite.addTest( PaginationTest.suite() );
			suite.addTest( MappingExceptionTest.suite() );
			if ( InstrumentTest.isRunnable() ) {
				suite.addTest( InstrumentTest.suite() );
			}
			if ( LazyOneToOneTest.isRunnable() ) {
				suite.addTest( LazyOneToOneTest.suite() );
			}
			if ( InstrumentCacheTest.isRunnable() ) {
				suite.addTest( InstrumentCacheTest.suite() );
			}
			if ( InstrumentCacheTest2.isRunnable() ) {
				suite.addTest( InstrumentCacheTest2.suite() );
			}
			suite.addTest( CGLIBInstrumentationTest.suite() );
			suite.addTest( JavassistInstrumentationTest.suite() );
			suite.addTest( SybaseTimestampVersioningTest.suite() );
			suite.addTest( DbVersionTest.suite() );
			suite.addTest( TimestampGeneratedValuesWithCachingTest.suite() );
			suite.addTest( TriggerGeneratedValuesWithCachingTest.suite() );
			suite.addTest( TriggerGeneratedValuesWithoutCachingTest.suite() );
			suite.addTest( SelectGeneratorTest.suite() );
			suite.addTest( SequenceIdentityTest.suite() );
			suite.addTest( InterceptorDynamicEntityTest.suite() );
			suite.addTest( TuplizerDynamicEntityTest.suite() );
			suite.addTest( BytecodeSuite.suite() );
			suite.addTest( JPAComplianceSuite.suite() );
			suite.addTest( AbstractComponentPropertyRefTest.suite() );
			suite.addTest( AbstractCompositeIdTest.suite() );
			suite.addTest( UtilSuite.suite() );
			suite.addTest( AnyTypeTest.suite() );
			suite.addTest( SQLFunctionsInterSystemsTest.suite() );
			suite.addTest( LobTest.suite() );
			suite.addTest( ClobTest.suite() );

			return filter( suite );
			//return suite;
		}

		/**
		 * Runs the new test suite
		 *
		 * @param args n/a
		 */
		public static void main(String[] args) {
			TestRunner.run( suite() );
		}
	}

	/**
	 * An inner class representing the legacy test suite.
	 */
	public static class LegacyTests {

		/**
		 * Returns the legacy test suite
		 *
		 * @return the legacy test suite
		 */
		public static Test suite() {
			TestSuite suite = new TestSuite("Legacy tests suite");
			suite.addTest( FumTest.suite() );
			suite.addTest( MasterDetailTest.suite() );
			suite.addTest( ParentChildTest.suite() );
			suite.addTest( ABCTest.suite() );
			suite.addTest( ABCProxyTest.suite() );
			suite.addTest( SQLFunctionsTest.suite() );
			suite.addTest( SQLLoaderTest.suite() );
			suite.addTest( MultiTableTest.suite() );
			suite.addTest( MapTest.suite() );
			suite.addTest( QueryByExampleTest.suite() );
			suite.addTest( ComponentNotNullTest.suite() );
			suite.addTest( IJTest.suite() );
			suite.addTest( IJ2Test.suite() );
			suite.addTest( FooBarTest.suite() );
			suite.addTest( StatisticsTest.suite() );
			suite.addTest( CacheTest.suite() );
			suite.addTest( OneToOneCacheTest.suite() );
			suite.addTest( NonReflectiveBinderTest.suite() );
			suite.addTest( ConfigurationPerformanceTest.suite() ); // Added to ensure we can utilize the recommended performance tips ;)
			return filter( suite );
//			return suite;
		}

		/**
		 * Run the legacy test suite
		 *
		 * @param args n/a
		 */
		public static void main(String[] args) {
			TestRunner.run( suite() );
		}
	}

	private static TestSuite filter(TestSuite testSuite) {
		FilterHandler handler = new FilterHandler();
		TestSuiteVisitor visitor = new TestSuiteVisitor( handler );
		visitor.visit( testSuite );
		return handler.getFilteredTestSuite();
	}

	private static class TestSuiteStackEntry {
		public final TestSuite testSuite;
		public final TestSuiteStackEntry parentEntry;

		public TestSuiteStackEntry(TestSuite testSuite, TestSuiteStackEntry parentEntry) {
			this.testSuite = testSuite;
			this.parentEntry = parentEntry;
			if ( parentEntry != null ) {
				parentEntry.testSuite.addTest( testSuite );
			}
		}
	}

	private static class FilterHandler implements TestSuiteVisitor.Handler {
		private TestSuiteStackEntry topStackElement;
		private TestSuiteStackEntry currentStackElement;
		private Dialect dialect = Dialect.getDialect();

		public void handleTestCase(Test test) {
			if ( test instanceof TestCase ) {
				TestCase hibernateTestCase = ( TestCase ) test;
				if ( ! hibernateTestCase.appliesTo( dialect ) ) {
					System.out.println( "skipping test [" + hibernateTestCase.fullTestName() + "] for dialect [" + dialect.getClass().getName() + "]" );
				}
				else {
					currentStackElement.testSuite.addTest( test );
				}
			}
			else {
				currentStackElement.testSuite.addTest( test );
			}
		}

		public void startingTestSuite(TestSuite suite) {
			currentStackElement = new TestSuiteStackEntry(
					new TestSuite( suite.getName() ),
					currentStackElement
			);
			if ( topStackElement == null ) {
				topStackElement = currentStackElement;
			}
		}

		public void completedTestSuite(TestSuite suite) {
			if ( currentStackElement != null ) {
				currentStackElement = currentStackElement.parentEntry;
			}
		}

		public TestSuite getFilteredTestSuite() {
			return topStackElement.testSuite;
		}
	}
}