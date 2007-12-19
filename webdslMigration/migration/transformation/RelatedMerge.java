package transformation;

import java.util.List;

/**
 * Like a merge, execpt that the output from the master and slave transformation is 
 * verified by means of a given relation. If successful, the input will be used to
 * merge, if not, the transformation ends by means of a partial transformation exception.
 * @author sander
 *
 */
public class RelatedMerge extends Merge {
	
	private final BinaryPredicate relation;
	
	public RelatedMerge(BinaryPredicate relation, TypedTransformation slaveTrafo, TypedTransformation inputTrafo) {
		super(slaveTrafo, inputTrafo);
		this.relation = relation;
	}

	@Override
	public Object getAttribute(List<Object> input, String attributeName) throws TransformationException {
		List<List<Object>> splitInput = divideAndCheckInputs(input);

		// Check 
		Object inputResult = ((TypedTransformation)getInputTrafo()).transform(splitInput.get(0));
		Object slaveResult = ((TypedTransformation)getSlaveTrafo()).transform(splitInput.get(1));
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
