package transformation.hibernate;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import transformation.Injection;
import transformation.PartialTransformationException;
import transformation.TransformationException;
import transformation.TypedTransformation;

public class HibernateTransformationMapping extends TypedTransformation {
	private TypedTransformation trafo;
	private Class type;
	
	// TODO verify type using first injection of trafo, before injecting
	public HibernateTransformationMapping(Class type, TypedTransformation trafo) throws TransformationException
	{
		this.type = type;
		this.trafo = trafo;
		if(!Collection.class.isAssignableFrom(type))
			throw new TransformationException("The collection class ("+type.getName()+") used to construct a HibernateTransformationMapping is not an instance of java.util.Collection");
	}
	
	@Override
	public Object getAttribute(List<Object> input, String attributeName) throws TransformationException {
		throw new TransformationException("Cannot get any attributes from a hibernate transformation mapping (from a Hibernate Collection)");
	}
	
	@Override
	public List<Injection> getInjections() {
		// Get injections from transformation, but remove first, this will be the injection point for the mapping
		List<Injection> trafoInjections = trafo.getInjections();
		trafoInjections = trafoInjections.subList(1, trafoInjections.size());
		
		// Add the collection injection point
		Vector<Injection> result = new Vector<Injection>();
		result.add(new Injection(type));
		// Add trafo injections
		result.addAll(trafoInjections);
		return result;
	}
	@Override
	public Class getType() {
		return type;
	}
	
	public Class getElementType() {
		return trafo.getType();
	}
	
	@Override
	public Object transform(List<Object> input) throws TransformationException {
		try {
			Collection result = (Collection)type.newInstance(); 		// type cast verified at constructor
			Collection sourceCollection = (Collection)input.remove(0);	// type cast not verified, exception caught below
			
			Iterator sourceIt = sourceCollection.iterator();
			while(sourceIt.hasNext())
			{
				Object source = sourceIt.next();
				// Add source to input; Add remaining inputs to input
				Vector<Object> newInput = new Vector<Object>();
				newInput.add(source);
				newInput.addAll(input);	// First has already been removed
				// Do transformation and add result (if not partial) to result collection
				try {
					Object trafoResult = trafo.transform(newInput);
					result.add(trafoResult);
				} catch(PartialTransformationException e) {}
			}
			
			// Fix input list (remove trafo injections)
			int nrInjectionsToremove = trafo.getInjections().size() - 1; // One (the mapping injection) has already been removed above;
			for(int i=0; i<nrInjectionsToremove; i++)
				input.remove(0);
			
			return result;
			
		} catch (InstantiationException e) {
			throw new TransformationException("Unable to construct instance of type "+type.getName()+" to be used in hibernate transformation mapping", e);
		} catch (IllegalAccessException e) {
			throw new TransformationException("Unable to construct instance of type "+type.getName()+" to be used in hibernate transformation mapping", e);
		} catch(ClassCastException e) {
			throw new TransformationException("Input provided to hibernate transformationmapping was not of type "+type.getName(), e);
		}
		
	}
}
