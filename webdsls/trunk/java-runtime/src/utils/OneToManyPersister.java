package utils;

import java.io.Serializable;

import org.hibernate.FetchMode;
import org.hibernate.MappingException;
import org.hibernate.cache.CacheException;
import org.hibernate.cache.access.CollectionRegionAccessStrategy;
import org.hibernate.cfg.Configuration;
import org.hibernate.engine.LoadQueryInfluencers;
import org.hibernate.engine.SessionFactoryImplementor;
import org.hibernate.engine.SessionImplementor;
import org.hibernate.loader.collection.CollectionInitializer;
import org.hibernate.loader.collection.OneToManyLoader;
import org.hibernate.mapping.Collection;

public class OneToManyPersister extends org.hibernate.persister.collection.OneToManyPersister implements BatchCollectionPersister {
	protected BatchingCollectionInitializer batchInitializer = null;

	public OneToManyPersister(Collection collection,
			CollectionRegionAccessStrategy cacheAccessStrategy,
			Configuration cfg, SessionFactoryImplementor factory)
			throws MappingException, CacheException {
		super(collection, cacheAccessStrategy, cfg, factory);
	}

	protected CollectionInitializer createCollectionInitializer(LoadQueryInfluencers loadQueryInfluencers) throws MappingException {
		if(utils.QueryOptimization.optimizationMode == 3) {
			batchInitializer = BatchingCollectionInitializer.createBatchingOneToManyInitializer( this, batchSize, getFactory(), loadQueryInfluencers );
			return batchInitializer;
		}
		return super.createCollectionInitializer(loadQueryInfluencers);
	}

	@Override
	public void initializeBatch(Serializable[] batch, SessionImplementor session, java.util.List<String> joins) {
		//batchInitializer.initializeBatch(batch, session);
		if(batch.length == 0) return;
		if(java.lang.reflect.Proxy.isProxyClass( session.getClass() ) ) {
			if(session instanceof org.hibernate.Session) {
				Object realSession = ((org.hibernate.Session)session).getSession(session.getEntityMode());
				if(realSession instanceof SessionImplementor) {
					session = (SessionImplementor)realSession;
				}
				else {
					return;
				}
			}
			else {
				return;
			}
		}
		class OneToManyJoinCollectionLoader extends org.hibernate.loader.collection.CollectionLoader {

			OneToManyJoinCollectionLoader(
					org.hibernate.persister.collection.QueryableCollection collectionPersister, 
					int batchSize, 
					SessionFactoryImplementor factory, 
					LoadQueryInfluencers loadQueryInfluencers, java.util.List<String> joins) throws MappingException {
				this( collectionPersister, batchSize, null, factory, loadQueryInfluencers, joins );
			}
			
			protected OneToManyJoinCollectionLoader(
					org.hibernate.persister.collection.QueryableCollection collectionPersister, 
					int batchSize, 
					String subquery, 
					SessionFactoryImplementor factory, 
					LoadQueryInfluencers loadQueryInfluencers, java.util.List<String> joins) throws MappingException {
				super( collectionPersister, factory, loadQueryInfluencers );
				final java.util.List<String> joinpaths = joins;
				org.hibernate.loader.JoinWalker walker = new org.hibernate.loader.collection.OneToManyJoinWalker(
						collectionPersister, 
						batchSize, 
						subquery, 
						factory, 
						loadQueryInfluencers
				){
					protected int getJoinType(
							org.hibernate.type.AssociationType associationType,
							org.hibernate.FetchMode config,
							org.hibernate.loader.PropertyPath path,
							String lhsTable,
							String[] lhsColumns,
							boolean nullable,
							int currentDepth,
							org.hibernate.engine.CascadeStyle cascadeStyle) throws MappingException {
						if  ( !isJoinedFetchEnabled( associationType, config, cascadeStyle ) && (joinpaths == null || !joinpaths.contains(path.getFullPath())) ) {
							return -1;
						}
						if ( isTooDeep(currentDepth) || ( associationType.isCollectionType() && isTooManyCollections() ) ) {
							return -1;
						}
						if ( isDuplicateAssociation( lhsTable, lhsColumns, associationType ) ) {
							return -1;
						}
						int rtn = getJoinType( nullable, currentDepth );
						return rtn;
					}
				};				
				initFromWalker( walker );

				postInstantiate();

			}
			
		}
		if(utils.QueryOptimization.optimizationMode == 3) { // Guided batching with fixed batch size
			batchInitializer.initializeBatch(batch, session);
		} else {
			OneToManyJoinCollectionLoader loader = new OneToManyJoinCollectionLoader( this, batch.length, session.getFactory(), session.getLoadQueryInfluencers(), joins );
			loader.loadCollectionBatch(session, batch, getKeyType());
		}
	}

}
