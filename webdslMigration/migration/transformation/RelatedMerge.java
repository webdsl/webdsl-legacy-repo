package transformation;

import java.util.List;

public class RelatedMerge extends Merge {
	
	private final BinaryRelation relation;
	
	public RelatedMerge(BinaryRelation relation, TypedTransformation slaveTrafo, TypedTransformation inputTrafo) {
		super(slaveTrafo, inputTrafo);
		this.relation = relation;
	}

	@Override
	public Object getAttribute(List<Object> input, String attributeName) throws TransFormationException {
		// Check 
		Object inputResult = ((TypedTransformation)getInputTrafo()).transform(input);
		Object slaveResult = ((TypedTransformation)getSlaveTrafo()).transform(input);
		if(!relation.relate(inputResult, slaveResult))
			throw new PartialTransformationException();
		// Get
		return super.getAttribute(input, attributeName);
	}

	/**
	 * @return the relation
	 */
	public BinaryRelation getRelation() {
		return relation;
	}
}
