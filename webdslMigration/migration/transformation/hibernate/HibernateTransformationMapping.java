package transformation.hibernate;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import transformation.Injection;
import transformation.PartialTransformationException;
import transformation.TransformationException;
import transformation.TransformationScope;
import transformation.TypedTransformation;
import transformation.UntypedTransformation;

public class HibernateTransformationMapping extends TypedTransformation {
	private TypedTransformation trafo;
	private Class type;
	
	public HibernateTransformationMapping(Class type, TypedTransformation trafo) {
		this.trafo = trafo;
		this.type = type;
	}
	
	@Override
	public Object getAttribute(List<UntypedTransformation> input, TransformationScope scope, String attributeName) throws TransformationException {
		throw new TransformationException("Cannot get any attributes from a hibernate transformation mapping (from a Hibernate Collection)");
	}
	
	@Override
	public Object transform(List<UntypedTransformation> input, TransformationScope scope) throws TransformationException {
		// Verify trafo and type (this could be done at construction, but then the constructor would throw an exception, which is not very useful in generation)
		if(trafo.getNrInputs(null, null) != 1)
			throw new TransformationException("Transformation to be mapped over a Hibernate collection (list, set, etc) can only rely on a single parameter");
		if(!Collection.class.isAssignableFrom(type))
			throw new TransformationException("The collection class ("+type.getName()+") used to construct a HibernateTransformationMapping is not an instance of java.util.Collection");

		// Verify input
		if(!(hd(input) instanceof Injection))
			throw new TransformationException("Cannot apply a Hibernate mapping transformation to anything other than injections");
		
		Injection inj = (Injection) hd(input);
		try {
			Collection result = (Collection)type.newInstance(); 			// type cast verified at constructor
			Collection source = (Collection)inj.getInjected();				// type cast not verified, exception caught below
			
			Iterator sourceIt = source.iterator();
			while(sourceIt.hasNext())
			{
				Object so = sourceIt.next();
				// Add source as transformation (Injection) to new input
				Vector<UntypedTransformation> newInput = new Vector<UntypedTransformation>();
				newInput.add(new Injection(so));
				// Do transformation and add result (if not partial) to result collection
				try {
					Object trafoResult = trafo.transform(newInput, scope);
					result.add(trafoResult);
				} catch(PartialTransformationException e) {}
			}
			return result;
			
		} catch (InstantiationException e) {
			throw new TransformationException("Unable to construct instance of type "+type.getName()+" to be used in hibernate transformation mapping", e);
		} catch (IllegalAccessException e) {
			throw new TransformationException("Unable to construct instance of type "+type.getName()+" to be used in hibernate transformation mapping", e);
		} catch(ClassCastException e) {
			throw new TransformationException("Input provided to hibernate transformationmapping was not a collection", e);
		}
	}

	@Override
	public int getNrInputs(TransformationScope scope, List<UntypedTransformation> inputs) throws TransformationException {
		return 1;
	}
}
