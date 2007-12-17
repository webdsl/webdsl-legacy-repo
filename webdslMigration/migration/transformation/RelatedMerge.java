package transformation;

import java.util.List;
import java.util.Vector;

public class RelatedMerge extends Merge {
	
	private final BinaryPredicate relation;
	
	public RelatedMerge(BinaryPredicate relation, TypedTransformation slaveTrafo, TypedTransformation inputTrafo) {
		super(slaveTrafo, inputTrafo);
		this.relation = relation;
	}

	@Override
	public Object getAttribute(List<Object> input, String attributeName) throws TransformationException {
		Vector<Object> i1 = new Vector<Object>(); i1.addAll(input);	// TODO Fix
		Vector<Object> i2 = new Vector<Object>(); i2.addAll(input);
		
		// Check 
		Object inputResult = ((TypedTransformation)getInputTrafo()).transform(i1);
		Object slaveResult = ((TypedTransformation)getSlaveTrafo()).transform(i2);
		if(!relation.relate(inputResult, slaveResult))
			throw new PartialTransformationException();
		// Get
		return super.getAttribute(input, attributeName);
	}

	/**
	 * @return the relation
	 */
	public BinaryPredicate getRelation() {
		return relation;
	}
}
