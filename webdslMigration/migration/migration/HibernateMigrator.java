package migration;

import java.util.List;
import java.util.Vector;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import transformation.HibernateTransformer;
import transformation.Injection;
import transformation.TransFormationException;

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
			// Load
			targetEM.merge(result);	
			// TODO Merge of 'new' (persistent in different storage) object, will an ID be kept the same, even when this is set to be generated (wasn't able to find this in docs)??
			// TODO Could cause multiple merges of same object (depends on merge implementation) when other objects refer to this one, make more efficient? 
			//targetSession.replicate(result, ReplicationMode.IGNORE);
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
