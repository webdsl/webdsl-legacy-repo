package example;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import migration.CombinedHibernateMigrator;
import migration.HibernateTransFormerClassPair;
import transformation.AddAttribute;
import transformation.AddAttributeDefaultValue;
import transformation.AttributeConversion;
import transformation.AttributeTransformation;
import transformation.BeanTransformer;
import transformation.BinaryPredicate;
import transformation.Injection;
import transformation.ObjectConversion;
import transformation.PrimitiveTypeConversions;
import transformation.RelatedMerge;
import transformation.Restrict;
import transformation.TransformationException;
import transformation.UnaryPredicate;
import transformation.hibernate.HibernateTransformationMapping;
import transformation.hibernate.HibernateTransformer;

public class ExampleMigrator {
	
	public static void main(String[] args) throws TransformationException
	{
		EntityManagerFactory emf0 = Persistence.createEntityManagerFactory("webdslDomain0");
		EntityManagerFactory emf1 = Persistence.createEntityManagerFactory("webdslDomain1");
		
		HibernateTransFormerClassPair[] transformers = {
				new HibernateTransFormerClassPair(
					new AttributeTransformation("entries",
						new HibernateTransformationMapping(
							java.util.List.class,
							new HibernateTransformer(
								new AttributeConversion("key", PrimitiveTypeConversions.selectByName("identity"),
								new Injection(example.domain_0.Blog.class)),
								example.domain_1.Blog.class,
								emf1
							)
						),
					new AddAttribute("newAttribute", 
							new ObjectConversion<example.domain_0.Blog> () { public Object convert(example.domain_0.Blog i) {return i.getId();}}, 
					new Restrict(
							new UnaryPredicate<example.domain_0.Blog>() {public boolean evaluate(example.domain_0.Blog i1){return true;}},
					new Injection(example.domain_0.Blog.class)))),
					example.domain_1.Blog.class
				),
				new HibernateTransFormerClassPair(
					new AddAttributeDefaultValue("newAttribute", "newAttributeValue", new Injection(example.domain_0.Blog.class)),
					example.domain_1.Blog.class
				),
				new HibernateTransFormerClassPair(
					new RelatedMerge(
						new BinaryPredicate<example.domain_0.Blog, example.domain_0.Blog>(){public boolean relate(example.domain_0.Blog i1, example.domain_0.Blog i2){return i1 == i2;}},
						new BeanTransformer(new Injection(example.domain_0.Blog.class), example.domain_0.Blog.class),
						new BeanTransformer(new Injection(example.domain_0.Blog.class), example.domain_0.Blog.class)),
					example.domain_1.Blog.class
				)
			};
		
		CombinedHibernateMigrator hmig = new CombinedHibernateMigrator(emf0, emf1, transformers);
		
		try {
			hmig.migrate();
		} catch (TransformationException e) {
			e.printStackTrace();
		}
		
		emf0.close();
		emf1.close();
		
	}
}
