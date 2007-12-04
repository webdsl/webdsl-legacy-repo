package example;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import migration.CombinedHibernateMigrator;
import migration.HibernateTransFormerClassPair;
import transformation.AddAttribute;
import transformation.Injection;
import transformation.TransFormationException;

public class ExampleMigrator {
	
	public static void main(String[] args)
	{
		EntityManagerFactory emf0 = Persistence.createEntityManagerFactory("webdslDomain0");
		EntityManagerFactory emf1 = Persistence.createEntityManagerFactory("webdslDomain1");
		
		HibernateTransFormerClassPair[] transformers = {
				new HibernateTransFormerClassPair(
						new AddAttribute("newAttribute", "newAttributeValue", new Injection(example.domain_0.Blog.class)), example.domain_1.Blog.class)
			};
		CombinedHibernateMigrator hmig = new CombinedHibernateMigrator(emf0, emf1, transformers);
		
		try {
			hmig.migrate();
		} catch (TransFormationException e) {
			e.printStackTrace();
		}
		
		emf0.close();
		emf1.close();
		
	}
}
