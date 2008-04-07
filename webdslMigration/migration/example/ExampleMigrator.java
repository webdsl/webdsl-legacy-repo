package example;

import java.util.Arrays;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import migration.HibernateMigrator;
import transformation.AddAttribute;
import transformation.AddAttributeDefaultValue;
import transformation.Application;
import transformation.AttributeConversion;
import transformation.AttributeTransformation;
import transformation.BinaryPredicate;
import transformation.DropAttribute;
import transformation.EmptyObject;
import transformation.Identity;
import transformation.Injection;
import transformation.ObjectConversion;
import transformation.PrimitiveTypeConversions;
import transformation.RelatedMerge;
import transformation.Restrict;
import transformation.TransformationException;
import transformation.TransformationScope;
import transformation.TypedApplication;
import transformation.UnaryPredicate;
import transformation.UntypedTransformation;
import transformation.hibernate.HibernateTransformationMapping;
import transformation.hibernate.HibernateTransformer;

public class ExampleMigrator {
	
	public static void main(String[] args) throws TransformationException
	{
		EntityManagerFactory emf0 = Persistence.createEntityManagerFactory("webdslDomain0");
		EntityManagerFactory emf1 = Persistence.createEntityManagerFactory("webdslDomain1");
		EntityManager em0 = emf0.createEntityManager();
		EntityManager em1 = emf1.createEntityManager();
		
		TransformationScope scope = new TransformationScope();
		
		UntypedTransformation entriesTransformation = 
			new AttributeTransformation("entries",
				new HibernateTransformationMapping(
					java.util.List.class,
					new TypedApplication (
						new HibernateTransformer(
							example.domain_1.Blog.class,
							emf1
						),
						new AttributeConversion("key", PrimitiveTypeConversions.selectByName("identity"))
					)
				)
			);
		
		HibernateMigrator[] migs = {
			new HibernateMigrator(em0, em1,
				new TypedApplication(
					new HibernateTransformer(example.domain_1.Blog.class, emf1),
					new Application(
						new AddAttribute("newAttribute", new ObjectConversion<example.domain_0.Blog> () { public Object convert(example.domain_0.Blog i) {return i.getId();}}),
						new Application (
							new Restrict(new UnaryPredicate<example.domain_0.Blog>() {public boolean evaluate(example.domain_0.Blog i1){return true;}}),
							entriesTransformation
						)
					)
				),
				Arrays.asList(new Class[] {example.domain_0.Blog.class})
			),
			new HibernateMigrator(em0, em1,
					new TypedApplication(
						new HibernateTransformer(example.domain_1.Blog.class, emf1),
						new RelatedMerge(
							new BinaryPredicate<example.domain_0.Blog, example.domain_0.Blog>(){public boolean relate(example.domain_0.Blog i1, example.domain_0.Blog i2){return i1 == i2;}}
						)
					),
					Arrays.asList(new Class[] {example.domain_0.Blog.class, example.domain_0.Blog.class})
				),
			
			/* Examples for ASE08 article */	
			new HibernateMigrator(em0, em1,
				new TypedApplication(
					new HibernateTransformer(example.domain_1.Blog.class, emf1),
					new Application(
						new AddAttributeDefaultValue("name", ""),
						new Application(
							new AddAttributeDefaultValue("description", ""),
							new Application(
								new AddAttributeDefaultValue("members", null),
								new EmptyObject()
							)
						)
					)	
				),
				Arrays.asList(new Class[] {example.domain_0.Blog.class})
			),
			new HibernateMigrator(em0, em1,
				new TypedApplication(
					new HibernateTransformer(example.domain_1.Blog.class, emf1),
					new DropAttribute("description")
				),
				Arrays.asList(new Class[] {example.domain_0.Blog.class})
			),
			new HibernateMigrator(em0, em1,
				new TypedApplication(
					new HibernateTransformer(example.domain_1.Blog.class, emf1),
					new AddAttribute(
						"topic",
						new ObjectConversion<example.domain_0.Blog> () { 
							public Object convert(example.domain_0.Blog i) {
								return i.getId(); //.getWeb().getTopic();
							}
						}
					)
				),
				Arrays.asList(new Class[] {example.domain_0.Blog.class})
			)
		};
	
		try {
			for(HibernateMigrator mig: migs)
				mig.migrate(scope);
		} catch (TransformationException e) {
			e.printStackTrace();
		}
		
		emf0.close();
		emf1.close();
	}
}
