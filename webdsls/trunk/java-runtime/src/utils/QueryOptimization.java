package utils;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Map;

public class QueryOptimization {
	public static int optimizationMode = -1;

	public static org.hibernate.Criteria addQueryOptimization(org.hibernate.Criteria criteria, String[] curjoins, String curgen, boolean ismain, String[] joins, String[][] queries, org.hibernate.criterion.Criterion criterion, String[] condjoins) {
		org.hibernate.Criteria ret = criteria;
		java.util.ArrayList<String> tojoin = new java.util.ArrayList<String>(); 

		// Add joins needed for the conditions first
		if(condjoins != null && condjoins.length > 0) {
			tojoin.addAll(Arrays.asList(condjoins));
		}

		// If this is the first queries we need to add all non-generic joins
		if(ismain && joins != null && joins.length > 0) {
			tojoin.addAll(Arrays.asList(joins));
		}

		// Add joins starting with the generic property curgen
		if(curgen != null && queries != null) {
			for(int i = 0; i < queries.length; i++) {
				if(queries[i].length > 0 && curgen.equals(queries[i][0])) {
					tojoin.addAll(Arrays.asList(queries[i]));
					break;
				}
			}
		}

		if(curjoins != null && tojoin.size() > 0) {
			tojoin.removeAll(Arrays.asList(curjoins)); // Remove all joins that are already added to the criteria
		}

		if(tojoin.size() > 0) {
			ret = QueryOptimization.addJoins(ret, tojoin).setCacheable(false); // Add the selected joins to the criteria
			// We added joins, which are not executed if the query is cached, so that is why we disable caching here
		}

		if(criterion != null) { // Add the condition aswell
			ret = ret.add((org.hibernate.criterion.Criterion)criterion);
		}
		return ret;
	}

	public static org.hibernate.Criteria addJoins(org.hibernate.Criteria criteria, java.util.ArrayList<String> joins) {
		org.hibernate.Criteria ret = criteria;
		for(int i = 0; i < joins.size(); i++) {
			String join = joins.get(i);
			String[] varList = join.split("\\.");
			if(varList.length < 1) continue;
			String joinProp = varList[varList.length - 1];
			String assaciation = joinProp;
			if(varList.length > 1) {
				assaciation = "_" + join.substring(0, join.length() - joinProp.length() - 1).replace(".", "_") + "." + joinProp;
			}
			String alias = "_" + join.replace(".", "_");
			ret = ret.createAlias(assaciation, alias, org.hibernate.criterion.CriteriaSpecification.LEFT_JOIN);
		}
		return ret;
	}

	public static org.hibernate.criterion.Criterion getNotEqCondition(String prop, Object val) {
		if(val == null) {
			return org.hibernate.criterion.Restrictions.isNotNull(prop);
		}
		return org.hibernate.criterion.Restrictions.ne(prop, val);
	}

	public static org.hibernate.criterion.Criterion getEqCondition(String prop, Object val, boolean naturalId) {
		if(val == null) {
			return org.hibernate.criterion.Restrictions.isNull(prop);
		}
		if(naturalId) {
			return org.hibernate.criterion.Restrictions.naturalId().set(prop, val);
		}
		return org.hibernate.criterion.Restrictions.eq(prop, val);
	}

	public static void prefetchProperties(org.hibernate.Criteria criteria, java.io.Serializable id, String[] props) {
		prefetchProperties(criteria, id, java.util.Arrays.asList(props));
	}

	public static void prefetchProperties(org.hibernate.Criteria criteria, java.io.Serializable id, Iterable<String> props) {
		for(String prop : props) {
			criteria.setFetchMode(prop, org.hibernate.FetchMode.JOIN);
		}
		criteria.add(org.hibernate.criterion.Restrictions.idEq(id));
	}

	public static java.util.List loadByUUID(org.hibernate.Session hibSession, String entity, java.io.Serializable id, String[] props) {
		return loadByUUID(hibSession, entity, id, java.util.Arrays.asList(props));
	}

	public static java.util.List loadByUUID(org.hibernate.Session hibSession, String entity, java.io.Serializable id, java.util.List<String> props) {
		org.hibernate.Criteria criteria = hibSession.createCriteria(entity);
		for(String prop : props) {
			criteria.setFetchMode(prop, org.hibernate.FetchMode.JOIN);
		}
		criteria.add(org.hibernate.criterion.Restrictions.idEq(id));
		return criteria.list();
	}

	public static java.util.List loadByNaturalId(org.hibernate.Session hibSession, String entity, String idProp, Object id, String[] props) {
		return loadByNaturalId(hibSession, entity, idProp, id, java.util.Arrays.asList(props));
	}

	public static java.util.List loadByNaturalId(org.hibernate.Session hibSession, String entity, String idProp, Object id, java.util.List<String> props) {
		org.hibernate.Criteria criteria = hibSession.createCriteria(entity);
		for(String prop : props) {
			criteria.setFetchMode(prop, org.hibernate.FetchMode.JOIN);
		}
		criteria.add(org.hibernate.criterion.Restrictions.naturalId().set(idProp, id));
		return criteria.list();
	}

    public static java.util.Collection prefetchCollection(org.hibernate.Session hibSession, java.util.Collection col, String[] joins) {
    	if(col instanceof org.hibernate.collection.PersistentCollection && joins != null && joins.length > 0 && QueryOptimization.optimizationMode == 2) {
    		org.hibernate.collection.PersistentCollection persistentCol = (org.hibernate.collection.PersistentCollection) col;
    		if(!persistentCol.wasInitialized() && persistentCol.getOwner() instanceof org.webdsl.WebDSLEntity && hibSession instanceof org.hibernate.engine.SessionImplementor && hibSession.getTransaction().isActive()) { // A transaction needs to be active or getFactory() will throw an HibernateException
	    		org.hibernate.engine.SessionImplementor session = (org.hibernate.engine.SessionImplementor)hibSession;
	    		org.hibernate.engine.SessionFactoryImplementor sessionFactory = session.getFactory();
				org.hibernate.persister.collection.CollectionPersister persister = sessionFactory.getCollectionPersister(persistentCol.getRole());
				if(persister instanceof utils.BatchCollectionPersister) {
					java.io.Serializable[] ownerId = { ((org.webdsl.WebDSLEntity)persistentCol.getOwner()).getId() };
					((utils.BatchCollectionPersister)persister).initializeBatch(ownerId, session, java.util.Arrays.asList(joins));
				}
    			
    		}
    	}
    	return col;
    }

    public static void prefetchCollections(org.hibernate.Session hibSession, String role, java.util.Collection<java.io.Serializable> ownerIds, String[] joins) {
    	if( ownerIds != null && ownerIds.size() > 0 && hibSession instanceof org.hibernate.engine.SessionImplementor && hibSession.getTransaction().isActive()) { // A transaction needs to be active or getFactory() will throw an HibernateException
    		org.hibernate.engine.SessionImplementor session = (org.hibernate.engine.SessionImplementor)hibSession;
    		org.hibernate.engine.SessionFactoryImplementor sessionFactory = session.getFactory();
			org.hibernate.persister.collection.CollectionPersister persister = sessionFactory.getCollectionPersister(role);
			if(persister instanceof utils.BatchCollectionPersister) {
				java.util.List<String> joinslist = null;
				if(joins != null) joinslist = java.util.Arrays.asList(joins);
				((utils.BatchCollectionPersister)persister).initializeBatch(ownerIds.toArray(new Serializable[ownerIds.size()]), session, joinslist);
			}
    	}
    }

	public static void prefetchEntity(org.hibernate.Session hibSession, String entityName, org.hibernate.proxy.HibernateProxy proxy, String[] joins) {
		org.hibernate.proxy.LazyInitializer init = proxy.getHibernateLazyInitializer();
		if (!init.isUninitialized() || joins == null || !QueryOptimization.isOptimizationEnabled()) {
			return;
		}
		java.io.Serializable[] ids = { init.getIdentifier() };
		if (hibSession instanceof org.hibernate.engine.SessionImplementor && hibSession.getTransaction().isActive()) { // A transaction needs to be active or getFactory() will throw an HibernateException
			org.hibernate.engine.SessionImplementor session = (org.hibernate.engine.SessionImplementor) hibSession;
			org.hibernate.engine.SessionFactoryImplementor sessionFactory = session.getFactory();
			org.hibernate.persister.entity.EntityPersister persister = sessionFactory.getEntityPersister(entityName);
			if (persister instanceof utils.SingleTableEntityPersister) {
				try {
					java.util.List<String> joinslist = null;
					joinslist = java.util.Arrays.asList(joins); // Also for optimizationMode == 1, because we want to join fetch when fetching a single entity 
					((utils.SingleTableEntityPersister) persister).loadBatch(ids, session, joinslist);
					init.initialize();
				} catch (Exception ex) {
					org.webdsl.logging.Logger.error("EXCEPTION",ex);
				}
			}
		}
	}

	public static int prefetchEntities(org.hibernate.Session hibSession, String entityName, java.util.Collection<? extends org.webdsl.WebDSLEntity> objs, String[] joins, boolean clearOnEmptyBatch) {
		if (hibSession instanceof org.hibernate.engine.SessionImplementor && hibSession.getTransaction().isActive()) { // A transaction needs to be active or getFactory() will throw an HibernateException
			org.hibernate.engine.SessionImplementor session = (org.hibernate.engine.SessionImplementor) hibSession;
			org.hibernate.engine.SessionFactoryImplementor sessionFactory = session.getFactory();
			org.hibernate.persister.entity.EntityPersister persister = sessionFactory.getEntityPersister(entityName);
			if (persister instanceof utils.SingleTableEntityPersister) {
				java.util.Set<java.io.Serializable> ids = new java.util.HashSet<java.io.Serializable>();
				for (Object obj : objs) {
					if (obj instanceof org.hibernate.proxy.HibernateProxy) {
						org.hibernate.proxy.LazyInitializer init = ((org.hibernate.proxy.HibernateProxy)obj).getHibernateLazyInitializer();
						if (init.isUninitialized()) {
							ids.add(init.getIdentifier());
						}
					}
				}
				if (ids.size() > 1) {
					try {
						java.util.List<String> joinslist = null;
						if(joins != null) joinslist = java.util.Arrays.asList(joins);
						((utils.SingleTableEntityPersister) persister).loadBatch(ids.toArray(new java.io.Serializable[ids.size()]), session, joinslist);
						for (final Object obj : objs) {
							if (obj instanceof org.hibernate.proxy.HibernateProxy) {
								org.hibernate.proxy.LazyInitializer init = ((org.hibernate.proxy.HibernateProxy)obj).getHibernateLazyInitializer();
								if (init.isUninitialized()) {
									init.initialize();
								}
							}
						}
					} catch (Exception ex) {
						org.webdsl.logging.Logger.error("EXCEPTION",ex);
					}
				} else if(clearOnEmptyBatch && ids.size() == 0) {
					objs.clear();
				}
				return ids.size();
			}
		} else {
			objs.clear(); // Might as well clear the batch if the session is not active
		}
		return 0;
	}

	public static int prefetchEntities(org.hibernate.Session hibSession, String entityName, java.util.Collection<? extends org.webdsl.WebDSLEntity>[] batches, boolean[] clearOnEmptyBatch) {
		if (hibSession instanceof org.hibernate.engine.SessionImplementor && hibSession.getTransaction().isActive()) { // A transaction needs to be active or getFactory() will throw an HibernateException
			org.hibernate.engine.SessionImplementor session = (org.hibernate.engine.SessionImplementor) hibSession;
			org.hibernate.engine.SessionFactoryImplementor sessionFactory = session.getFactory();
			org.hibernate.persister.entity.EntityPersister persister = sessionFactory.getEntityPersister(entityName);
			if (persister instanceof utils.SingleTableEntityPersister) {
				java.util.Set<java.io.Serializable> ids = new java.util.HashSet<java.io.Serializable>();
				for(int b = 0; b < batches.length; b++) {
					java.util.Collection<? extends org.webdsl.WebDSLEntity> objs = batches[b];
					boolean emptyBatch = true;
					for (Object obj : objs) {
						if (obj instanceof org.hibernate.proxy.HibernateProxy) {
							org.hibernate.proxy.LazyInitializer init = ((org.hibernate.proxy.HibernateProxy)obj).getHibernateLazyInitializer();
							if (init.isUninitialized()) {
								emptyBatch = false;
								ids.add(init.getIdentifier());
							}
						}
					}
					if(emptyBatch && clearOnEmptyBatch[b]) {
						objs.clear();
					}
				}
				if (ids.size() > 1) {
					try {
						((utils.SingleTableEntityPersister) persister).loadBatch(ids.toArray(new java.io.Serializable[ids.size()]), session, null);
						for(java.util.Collection<? extends org.webdsl.WebDSLEntity> objs : batches) {
							for(Object obj : objs) {
								if (obj instanceof org.hibernate.proxy.HibernateProxy) {
									org.hibernate.proxy.LazyInitializer init = ((org.hibernate.proxy.HibernateProxy)obj).getHibernateLazyInitializer();
									if (init.isUninitialized()) {
										init.initialize();
									}
								}
							}
						}
					} catch (Exception ex) {
						org.webdsl.logging.Logger.error("EXCEPTION",ex);
					}
				}
				return ids.size();
			}
		} else { // Might as well clear the batches if the session is not active
			for(java.util.Collection<? extends org.webdsl.WebDSLEntity> objs : batches) {
				objs.clear();
			}
		}
		return 0;
	}

	public static void prefetchLazyProperties(org.hibernate.Session hibSession, String entityName, String fieldName, java.util.Set<java.io.Serializable> ids, String[] joins) {
		if (hibSession instanceof org.hibernate.engine.SessionImplementor && hibSession.getTransaction().isActive()) { // A transaction needs to be active or getFactory() will throw an HibernateException
			org.hibernate.engine.SessionImplementor session = (org.hibernate.engine.SessionImplementor) hibSession;
			org.hibernate.engine.SessionFactoryImplementor sessionFactory = session.getFactory();
			org.hibernate.persister.entity.EntityPersister persister = sessionFactory.getEntityPersister(entityName);
			if (persister instanceof utils.SingleTableEntityPersister) {
				if (ids.size() > 1) {
					try {
						java.util.List<String> joinslist = null;
						((utils.SingleTableEntityPersister) persister).loadLazyBatch(fieldName, ids.toArray(new java.io.Serializable[ids.size()]), session, joinslist);
					} catch (Exception ex) {
						org.webdsl.logging.Logger.error("EXCEPTION",ex);
					}
				}
			}
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static boolean equalFilters(org.hibernate.impl.FilterImpl oldFilter, org.hibernate.impl.FilterImpl newFilter) {
		if(oldFilter == null && newFilter == null) return true;
		if(oldFilter == null || newFilter == null) return false;
		if(!oldFilter.getName().equals(newFilter.getName())) return false;
		java.util.Set params = new java.util.HashSet();
		Map oldParams = oldFilter.getParameters();
		Map newParams = newFilter.getParameters();
		params.addAll(oldParams.keySet());
		params.addAll(newParams.keySet());
		for(Object param : params) {
			Object oldParam = oldParams.get(param);
			Object newParam = newParams.get(param);
			if(oldParam == null || newParam == null || !oldParam.equals(newParam)) {
				return false;
			}
		}
		return true;
	}

	/*public static boolean equalFilterParams(final java.util.Map oldParams, final java.util.Map newParams, final int start, final int end) {
		for(int i = start; i < end; i++) {
			final Object oldParam = oldParams.get("param" + i);
			final Object newParam = newParams.get("param" + (i - start));
			if(oldParam == null || newParam == null || !oldParam.equals(newParam)) {
				return false;
			}
		}
		return true;
	}*/
	@SuppressWarnings("rawtypes")
	public static boolean equalFilterParams(Map oldParams, int oldStart, Map newParams, int newStart, int len) {
		for(int i = 0; i < len; i++) {
			final Object oldParam = oldParams.get("param" + (oldStart + i));
			final Object newParam = newParams.get("param" + (newStart + i));
			if(oldParam == null || newParam == null || !oldParam.equals(newParam)) {
				return false;
			}
		}
		return true;
	}

	public static String filterToString(org.hibernate.impl.FilterImpl fltr) {
		if(fltr == null) return null;
		StringBuilder sb = new StringBuilder();
		sb.append(fltr.getName());
		sb.append("(");
		java.util.Map params = fltr.getParameters();
		for(int i = 0; i < params.size(); i++) {
			if(i > 1) sb.append(',');
			sb.append(params.get("param" + i));
		}
		sb.append(")");
		return sb.toString();
	}

	public static org.hibernate.Filter restoreFilter(org.hibernate.engine.LoadQueryInfluencers lqi, org.hibernate.impl.FilterImpl filter) { 
		org.hibernate.Filter newFilter = lqi.enableFilter(filter.getName());
		if(filter.getParameters() != null) {
			for(Object entry : filter.getParameters().entrySet()) {
				if(!(entry instanceof java.util.Map.Entry)) continue;
				Object key = ((java.util.Map.Entry)entry).getKey();
				Object value = ((java.util.Map.Entry)entry).getValue();
				newFilter.setParameter(key.toString(), value);
			}
		}
		return newFilter;
	}

	// Checks if an EntityUniqueKey exists within the PersistenceContext for a lazy no-proxy property, without initializing the property
	public static boolean uniqueKeyInContext(org.webdsl.WebDSLEntity entity, org.hibernate.bytecode.javassist.FieldHandler fieldHandler, String fieldName) {
    	if(!(fieldHandler instanceof org.hibernate.intercept.javassist.FieldInterceptorImpl)) return false;
    	org.hibernate.intercept.javassist.FieldInterceptorImpl fieldInterceptor = (org.hibernate.intercept.javassist.FieldInterceptorImpl)fieldHandler;
		org.hibernate.engine.SessionImplementor session = fieldInterceptor.getSession();
		org.hibernate.engine.SessionFactoryImplementor factory = session.getFactory();
    	org.hibernate.persister.entity.EntityPersister persister = factory.getEntityPersister(fieldInterceptor.getEntityName());
    	org.hibernate.type.EntityType type = (org.hibernate.type.EntityType)persister.getPropertyType(fieldName);
    	String associatedEntityName = type.getAssociatedEntityName();
    	String uniqueKeyPropertyName = type.getRHSUniqueKeyPropertyName();

    	org.hibernate.engine.EntityUniqueKey euk = new org.hibernate.engine.EntityUniqueKey(
				associatedEntityName, 
				uniqueKeyPropertyName, 
				entity.getId(), 
				type.getIdentifierOrUniqueKeyType( factory ),
				session.getEntityMode(), 
				factory
		);

		org.hibernate.engine.PersistenceContext persistenceContext = session.getPersistenceContext();
		Object result = persistenceContext.getEntity( euk );
		return result != null;
	}

	public static org.hibernate.Criteria addJoinsIfOptimizationEnabled(org.hibernate.Criteria criteria, String[] props) {
		if(criteria != null && props != null && QueryOptimization.isOptimizationEnabled()) {
			for(String prop : props) {
				criteria.setFetchMode(prop, org.hibernate.FetchMode.JOIN);
			}
		}
		return criteria;
	}

	public static boolean isOptimizationEnabled() {
		AbstractPageServlet page = ThreadLocalPage.get();
		return page == null || page.isOptimizationEnabled();
	}
}
