package utils;

import java.io.Serializable;

import org.hibernate.FetchMode;
import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.LockOptions;
import org.hibernate.MappingException;
import org.hibernate.cache.access.EntityRegionAccessStrategy;
import org.hibernate.engine.LoadQueryInfluencers;
import org.hibernate.engine.Mapping;
import org.hibernate.engine.SessionFactoryImplementor;
import org.hibernate.engine.SessionImplementor;
import org.hibernate.loader.entity.EntityLoader;
import org.hibernate.mapping.PersistentClass;

public class SingleTableEntityPersister extends org.hibernate.persister.entity.SingleTableEntityPersister {

	protected BatchingEntityLoader batchLoader = null;

	public SingleTableEntityPersister(PersistentClass persistentClass,
			EntityRegionAccessStrategy cacheAccessStrategy,
			SessionFactoryImplementor factory,
			Mapping mapping) throws HibernateException {
		super(persistentClass, cacheAccessStrategy, factory, mapping);
	}
	@Override
	protected void createLoaders() {
		super.createLoaders();
		if(utils.QueryOptimization.optimizationMode == 3) {
			batchLoader = BatchingEntityLoader.createBatchingEntityLoader(this, getFactory());
		}
	}

	public void loadBatch(Serializable[] batch, SessionImplementor session, java.util.List<String> joins) {
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

		if(utils.QueryOptimization.optimizationMode == 1 || utils.QueryOptimization.optimizationMode == 5 || utils.QueryOptimization.optimizationMode == 6 || utils.QueryOptimization.optimizationMode == 8) { // Normal or at arguments
			JoinEntityLoader loader = new JoinEntityLoader(this, batch.length, LockMode.NONE, session.getFactory(), session.getLoadQueryInfluencers(), joins);
			loader.loadEntityBatch(session, batch, getIdentifierType(), null, getEntityName(), null, this, LockOptions.NONE);
		} else if(utils.QueryOptimization.optimizationMode == 3) { // Guided batch
			batchLoader.loadBatch(batch, session);
		} else if(utils.QueryOptimization.optimizationMode == 4) { // All joins
			org.hibernate.Criteria criteria = ((org.hibernate.Session)session).createCriteria(this.getEntityName());
			if(joins != null) {
				for(String str : joins) {
					criteria.setFetchMode(str, FetchMode.JOIN);
				}
			}
			criteria.add(org.hibernate.criterion.Restrictions.in("id", batch));
			criteria.list();
		} else {
			throw new UnsupportedOperationException("loadBatch undefined for optimizationmode " + utils.QueryOptimization.optimizationMode);
		}
	}

	public void loadLazyBatch(String propertyName, Serializable[] batch, SessionImplementor session, java.util.List<String> joins) {
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

        org.hibernate.EntityMode entityMode = session.getEntityMode();
        org.hibernate.engine.SessionFactoryImplementor factory = session.getFactory();
        org.hibernate.engine.PersistenceContext context = session.getPersistenceContext();
		org.hibernate.type.EntityType type = (org.hibernate.type.EntityType) getPropertyType(propertyName);
		String associatedEntity = type.getAssociatedEntityName();
		String uniqueKeyPropertyName = type.getRHSUniqueKeyPropertyName();
		utils.SingleTableEntityPersister associatedPersister = (utils.SingleTableEntityPersister)factory.getEntityPersister(associatedEntity);
		String[] columns = associatedPersister.getPropertyColumnNames(uniqueKeyPropertyName);
        org.hibernate.type.Type ukType = associatedPersister.getPropertyType(uniqueKeyPropertyName);
		if(utils.QueryOptimization.optimizationMode == 1 || utils.QueryOptimization.optimizationMode == 3 || utils.QueryOptimization.optimizationMode == 5 || utils.QueryOptimization.optimizationMode == 8) {
			java.util.Set<Serializable> all = new java.util.HashSet<Serializable>(java.util.Arrays.asList(batch));
	        JoinEntityLoader loader = new JoinEntityLoader(associatedPersister, columns, ukType, batch.length, LockMode.NONE, factory, session.getLoadQueryInfluencers(), utils.QueryOptimization.optimizationMode == 3 ? null : joins);
			java.util.List lst = loader.loadEntityBatch(session, batch, getIdentifierType(), null, associatedEntity, null, associatedPersister, LockOptions.NONE);
			// We register the EntityUniqueKeys with the persistence context, so the we can look them up later
			for(Object object : lst) {
				Serializable id = getIdentifier(associatedPersister.getPropertyValue(object, uniqueKeyPropertyName, entityMode), session); // Returns the identifier that was used to fetch the object (from batch)
				org.hibernate.engine.EntityUniqueKey euk = new org.hibernate.engine.EntityUniqueKey(
						associatedEntity, 
						uniqueKeyPropertyName,
						id,
						ukType,
						entityMode, 
						factory
				);
				context.addEntity( euk, object );
				all.remove(id);
			}
			// All remaining identifiers have a null value for the lazy property.
			// We need to initialize them directly, because we cannot register a euk for a null value.
			// The PersistenceContext uses a Map to store objects by euk, and the get() method returning null means the context has no object with that euk
			int propertyIndex = getEntityMetamodel().getPropertyIndex(propertyName);
			for(Serializable id : all) {
				org.hibernate.engine.EntityKey key = new org.hibernate.engine.EntityKey( id, this, entityMode );
				Object entity = context.getEntity(key);
				if(entity == null || !(entity instanceof org.webdsl.WebDSLEntity)) continue;
				if(((org.webdsl.WebDSLEntity)entity).removeUninitializedLazyProperty(propertyName)) {
					org.hibernate.engine.EntityEntry entry = context.getEntry(entity);
					if(entry == null) continue;
					final Object[] snapshot = entry.getLoadedState();
					if(snapshot == null) continue;
					setPropertyValue(entity, propertyIndex, null, session.getEntityMode());
					if(snapshot != null) {
						snapshot[ propertyIndex ] = type.deepCopy( null, session.getEntityMode(), factory );
					}
				}
			}
		} else if(utils.QueryOptimization.optimizationMode == 4) { // All joins
			org.hibernate.Criteria criteria = ((org.hibernate.Session)session).createCriteria(this.getEntityName());
			if(joins != null) {
				for(String str : joins) {
					criteria.setFetchMode(str, FetchMode.JOIN);
				}
			}
			criteria.add(org.hibernate.criterion.Restrictions.in("id", batch));
			criteria.list();
		} else {
			throw new UnsupportedOperationException("loadLazyBatch undefined for optimizationmode " + utils.QueryOptimization.optimizationMode);
		}
	}

	class JoinEntityLoader extends org.hibernate.loader.entity.AbstractEntityLoader {

		private final boolean batchLoader;
		private final int[][] compositeKeyManyToOneTargetIndices;
		private final LockOptions lockOptions = new LockOptions();

		JoinEntityLoader(
				org.hibernate.persister.entity.OuterJoinLoadable persister,
				int batchSize,
				LockMode lockMode,
				SessionFactoryImplementor factory,
				LoadQueryInfluencers loadQueryInfluencers, java.util.List<String> joins) throws MappingException {
			this(
					persister,
					persister.getIdentifierColumnNames(),
					persister.getIdentifierType(),
					batchSize,
					lockMode,
					factory,
					loadQueryInfluencers,
					joins
				);
		}
		public JoinEntityLoader(
				org.hibernate.persister.entity.OuterJoinLoadable persister,
				String[] uniqueKey,
				org.hibernate.type.Type uniqueKeyType, 
				int batchSize, 
				LockMode lockMode,
				SessionFactoryImplementor factory, 
				LoadQueryInfluencers loadQueryInfluencers, java.util.List<String> joins) throws MappingException {
			super( persister, uniqueKeyType, factory, loadQueryInfluencers );
			this.lockOptions.setLockMode(lockMode);
			final java.util.List<String> joinpaths = joins;
			org.hibernate.loader.entity.EntityJoinWalker walker = new org.hibernate.loader.entity.EntityJoinWalker(
					persister, 
					uniqueKey, 
					batchSize, 
					lockMode, 
					factory, 
					loadQueryInfluencers
			){
				protected int getJoinType(
						org.hibernate.persister.entity.OuterJoinLoadable persister,
						org.hibernate.loader.PropertyPath path,
						int propertyNumber,
						org.hibernate.type.AssociationType associationType,
						org.hibernate.FetchMode metadataFetchMode,
						org.hibernate.engine.CascadeStyle metadataCascadeStyle,
						String lhsTable,
						String[] lhsColumns,
						boolean nullable,
						int currentDepth) throws MappingException {
					if ( lockOptions.getLockMode().greaterThan( LockMode.READ ) ) {
						return -1;
					}
					if ( isTooDeep( currentDepth )
							|| ( associationType.isCollectionType() && isTooManyCollections() ) ) {
						return -1;
					}
					if ( !isJoinedFetchEnabledInMapping( metadataFetchMode, associationType )
							&& !isJoinFetchEnabledByProfile( persister, path, propertyNumber )
							&& (joinpaths == null || !joinpaths.contains(path.getFullPath()))) {
						return -1;
					}
					if ( isDuplicateAssociation( lhsTable, lhsColumns, associationType ) ) {
						return -1;
					}
					return getJoinType( nullable, currentDepth );
				}
			};
			initFromWalker( walker );
			this.compositeKeyManyToOneTargetIndices = walker.getCompositeKeyManyToOneTargetIndices();
			postInstantiate();
			batchLoader = batchSize > 1;

		}

		@Override
		protected boolean isSingleRowLoader() {
			return !batchLoader;
		}

		@Override
		public int[][] getCompositeKeyManyToOneTargetIndices() {
			return compositeKeyManyToOneTargetIndices;
		}
	}
}
