package transformation;

import java.util.List;

public class AttributeNameChange extends UntypedTransformation {
	private final String oldAttributeName;
	private final String newAttributeName;
	
	public AttributeNameChange(String oldAttributeName, String newAttributeName) {
		this.oldAttributeName = oldAttributeName;
		this.newAttributeName = newAttributeName;
	}

	@Override
	public Object getAttribute(List<UntypedTransformation> input, TransformationScope scope, String attributeName) throws TransformationException {
		if(attributeName.equals(newAttributeName))
			return hd(input).getAttribute(tl(input), scope, oldAttributeName);
		return hd(input).getAttribute(tl(input), scope, attributeName);
	}

	/**
	 * @return the newAttributeName
	 */
	public String getNewAttributeName() {
		return newAttributeName;
	}

	/**
	 * @return the oldAttributeName
	 */
	public String getOldAttributeName() {
		return oldAttributeName;
	}

	@Override
	public int getNrInputs(TransformationScope scope, List<UntypedTransformation> inputs) throws TransformationException {
		return 1 + hd(inputs).getNrInputs(scope, tl(inputs));
	}
}
