package migration;

import java.util.List;
import java.util.Vector;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.hibernate.ReplicationMode;
import org.hibernate.Session;
import org.hibernate.ejb.HibernateEntityManager;

import transformation.HibernateTransformer;
import transformation.Injection;
import transformation.TransFormationException;

/**
 * The Hibernate migrator holds a source, target and transformation. Using these, it will extract objects from 
 * the source, transform them and load the into the new database. This proces is automated and can be started 
 * using 'migrate'.
 * @author Sander Vermolen
 */
public class HibernateMigrator
{
	private final HibernateTransformer transformer;
	EntityManager sourceEM;
	EntityManager targetEM;
	
	public HibernateMigrator(EntityManager sourceEM, EntityManager targetEM, HibernateTransformer transformer) {
		this.transformer = transformer;
		this.sourceEM = sourceEM;
		this.targetEM = targetEM;
	}
	
	/**
	 * Will extract old objects from source database, transform using transformer and load objects into 
	 * the new database. The source objects used are restricted by the injections defined in the transformer.
	 * The traget objects loaded are restricted by the result of the transformation and by the objects already
	 * available in the database. Object ID's will not be altered (even when these are set to be generated). If 
	 * an id is already available in the database, the object containing it will not be overwritten. Any 
	 * transformation result with this id will be ignored.
	 * @throws TransFormationException When the transformation breaks down.
	 */
	public void migrate() throws TransFormationException
	{
		List<Injection> injections = transformer.getInjections();
		
		EntityTransaction sourceTrans = sourceEM.getTransaction();
		EntityTransaction targetTrans = targetEM.getTransaction();
		sourceTrans.begin(); targetTrans.begin();
		
		migrate(injections, new Vector<Object>());
		
		sourceTrans.commit();
		targetTrans.commit();	// TODO Improve
	}
	
	protected void migrate(List<Injection> unProcessedInjections, List<Object> input) throws TransFormationException
	{
		// If all injections are processed, start transformer
		if(unProcessedInjections.size() == 0)
		{			
			// Transform
			Object result = transformer.transform(input);
			
			// Load (falling back to Hibernate to use replication)
			HibernateEntityManager hibEM = (HibernateEntityManager) targetEM;
			Session session = hibEM.getSession();
			session.replicate(result, ReplicationMode.IGNORE);
			// TODO Could cause multiple merges of same object (depends on merge implementation) when other objects refer to this one, make more efficient? 
		}
		
		// If not, get more data from the database
		else
		{
			Injection inj = unProcessedInjections.remove(0);
			List sources = sourceEM.createQuery("from "+inj.getInjectionType().getName()).getResultList();
			for(Object s : sources)
			{
				input.add(s);
				migrate(unProcessedInjections, input);
				input.remove(input.size() - 1);
			}
		}
	}
}
