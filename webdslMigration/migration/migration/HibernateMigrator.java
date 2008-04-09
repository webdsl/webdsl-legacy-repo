package migration;

import java.util.List;
import java.util.Vector;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.hibernate.ReplicationMode;
import org.hibernate.Session;
import org.hibernate.ejb.HibernateEntityManager;

import transformation.Injection;
import transformation.TransformationException;
import transformation.TransformationScope;
import transformation.TypedTransformation;
import transformation.UntypedTransformation;

/**
 * The Hibernate migrator holds a source, target and transformation. Using these, it will extract objects from 
 * the source, transform them and load the into the new database. This process is automated and can be started 
 * using 'migrate'.
 * @author Sander Vermolen
 */
public class HibernateMigrator
{
	private final TypedTransformation transformer;
	private final EntityManager sourceEM;
	private final EntityManager targetEM;
	private final List<Class> inputTypes;
	
	public HibernateMigrator(EntityManager sourceEM, EntityManager targetEM, TypedTransformation transformer, List<Class> inputTypes) {
		this.transformer = transformer;
		this.sourceEM = sourceEM;
		this.targetEM = targetEM;
		this.inputTypes = inputTypes;
	}
	
	/**
	 * Will extract old objects from source database, transform using transformer and load objects into 
	 * the new database. The source objects used are restricted by the injections defined in the transformer.
	 * The target objects loaded are restricted by the result of the transformation and by the objects already
	 * available in the database. Object ID's will not be altered (even when these are set to be generated). If 
	 * an id is already available in the database, the object containing it will not be overwritten. Any 
	 * transformation result with this id will be ignored.
	 * @throws TransformationException When the transformation breaks down.
	 */
	public void migrate(TransformationScope scope) throws TransformationException
	{
		EntityTransaction sourceTrans = sourceEM.getTransaction();
		EntityTransaction targetTrans = targetEM.getTransaction();
		sourceTrans.begin(); targetTrans.begin();
		
		if(inputTypes.size() > 0)
			migrate(inputTypes, new Vector<UntypedTransformation>(), scope);
		
		sourceTrans.commit();
		targetTrans.commit();
	}
	
	protected void migrate(List<Class> unProcessedTypes, List<UntypedTransformation> input, TransformationScope scope) throws TransformationException
	{
		// If all inputs are processed, start transformer
		if(unProcessedTypes.size() == 0)
		{
			// Transform
			Object result = transformer.transform(input, scope);
						
			// Load (falling back to Hibernate to use replication)
			//targetEM.persist(result);
			HibernateEntityManager hibEM = (HibernateEntityManager) targetEM;
			Session session = hibEM.getSession();
			session.replicate(result, ReplicationMode.IGNORE);
		}
		
		// If not, get more data from the database
		else
		{
			Class firstType = unProcessedTypes.get(0);
			System.out.println("Loading objects for type "+firstType.getName());
			List sources = sourceEM.createQuery("from "+firstType.getName()).getResultList();
			System.out.println(sources.size()+" objects loaded");
			for(Object s : sources)
			{
				input.add(new Injection(s));
				migrate(unProcessedTypes.subList(1, unProcessedTypes.size()), input, scope);
				input.remove(input.size() - 1);
			}
		}
	}
}
