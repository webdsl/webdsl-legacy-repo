// TODO Supertype change

package migration;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import transformation.HibernateTransformer;
import transformation.TransFormationException;

public class CombinedHibernateMigrator 
{
	private final HibernateMigrator[] migrators;
	
	public CombinedHibernateMigrator(EntityManagerFactory emf0, EntityManagerFactory emf1, HibernateTransFormerClassPair[] transformers)
	{
        migrators = new HibernateMigrator[transformers.length];
        for(int i=0; i<transformers.length; i++)
        {
        	HibernateTransformer transformer = new HibernateTransformer(transformers[i].getTransformation(), transformers[i].getTargetClass(), emf1);
        	EntityManager em0 = emf0.createEntityManager();
        	EntityManager em1 = emf1.createEntityManager();
        	migrators[i] = new HibernateMigrator(em0, em1, transformer);
        }
	}
	
	public CombinedHibernateMigrator(EntityManagerFactory emf0, EntityManagerFactory emf1, HibernateTransformer[] transformers)
	{
        migrators = new HibernateMigrator[transformers.length];
        for(int i=0; i<transformers.length; i++)
        {
        	EntityManager em0 = emf0.createEntityManager();
        	EntityManager em1 = emf1.createEntityManager();
        	migrators[i] = new HibernateMigrator(em0, em1, transformers[i]);
        }
	}
	
	public void migrate() throws TransFormationException
	{
		for(HibernateMigrator migrator : migrators)
			migrator.migrate();
	}
}
