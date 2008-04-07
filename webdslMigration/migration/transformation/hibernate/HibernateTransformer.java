package transformation.hibernate;

import java.util.List;

import javax.persistence.EntityManagerFactory;

import org.hibernate.SessionFactory;
import org.hibernate.ejb.HibernateEntityManagerFactory;

import transformation.TransformationException;
import transformation.TransformationScope;
import transformation.TypedTransformation;
import transformation.UntypedTransformation;

// TODO Verify list of persistent attributes before allowing regular getter

public class HibernateTransformer extends TypedTransformation {
	private final Class targetClass; 
	private final EntityManagerFactory targetEMF;
	
	public HibernateTransformer(Class targetClass, EntityManagerFactory targetEMF) {
		this.targetClass = targetClass;
		this.targetEMF = targetEMF;
	}
	
	public Object transform(List<UntypedTransformation> input, TransformationScope scope) throws TransformationException {
		try {
			Object trafoResult = targetClass.newInstance();
			hd(input).transForm(tl(input), scope, trafoResult, getPersistentAttributeNames(targetEMF, targetClass));
			return trafoResult;
		} catch (InstantiationException e) {
			throw new TransformationException("Could not instantiate result of Hibernate transformation", e);
		} catch (IllegalAccessException e) {
			throw new TransformationException("Could not instantiate result of Hibernate transformation (constructor restricted)", e);
		}
	}
	
	/**
	 * Will return a list of field names containing all fileds in the given class that have been declared to 
	 * be persistent.
	 * 
	 * This method falls back to the Hibernate API and will therefore NOT support other javax.persistence 
	 * compliant implementations (in contrast to most other methods in this class). This is the reason that 
	 * this class is called a HibernateTransformer instead of the more generic PersistenceTransformer.  
	 * 
	 * @param emf EntityManagerFactory to get the meta data from. This <b>must</b> be a HibernateEntityManagerFactory instance
	 * @param targetClass Class to find persitent fields in
	 * @return An array of names of persistent fields in the given type, accoring to the given configuration
	 */
	public static String[] getPersistentAttributeNames(EntityManagerFactory emf, Class targetClass)
	{
		// Fall back to the Hibernate API (to use its reflection on the meta data)
		HibernateEntityManagerFactory hibEMF = (HibernateEntityManagerFactory) emf;
		SessionFactory sessionFac = hibEMF.getSessionFactory();

		// The Hibernate specific code to select the fields
		String[] targetPropertyNamesWithoutId = sessionFac.getClassMetadata(targetClass).getPropertyNames();
		String[] targetPropertyNames = new String[targetPropertyNamesWithoutId.length + 1];
		targetPropertyNames[0] = sessionFac.getClassMetadata(targetClass).getIdentifierPropertyName();
		for(int i=0; i<targetPropertyNamesWithoutId.length; i++)
			targetPropertyNames[i+1] = targetPropertyNamesWithoutId[i];
		
		System.out.println("Selected persistent attributes:");
		for(int i=0; i<targetPropertyNames.length; i++)
			System.out.println(targetPropertyNames[i]);
		
		// Fix for WebDSL (remove prefix underscores from names, since getters and setters are named without underscores) 
		for(int i=0; i<targetPropertyNames.length; i++)
			if(targetPropertyNames[i].startsWith("_"))
				targetPropertyNames[i] = targetPropertyNames[i].substring(1);
		
		return targetPropertyNames;
	}

	@Override
	public int getNrInputs(TransformationScope scope, List<UntypedTransformation> inputs) throws TransformationException {
		return 1;
	}
}
