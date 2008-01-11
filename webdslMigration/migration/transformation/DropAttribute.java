package transformation;

import java.util.List;

public class DropAttribute extends UntypedTransformation {
	private final String attributeName;
	
	public DropAttribute(String attributeName) {
		this.attributeName = attributeName;
	}

	@Override
	public Object getAttribute(List<UntypedTransformation> input, TransformationScope scope, String attributeName) throws TransformationException {
		if(attributeName.equals(this.attributeName))
			throw new TransformationException("Dropped attribute requested (" + attributeName+")");
		return hd(input).getAttribute(tl(input), scope, attributeName);
	}

	/**
	 * @return the attributeName
	 */
	public String getAttributeName() {
		return attributeName;
	}

	@Override
	public int getNrInputs(TransformationScope scope, List<UntypedTransformation> inputs) throws TransformationException {
		return 1 + hd(inputs).getNrInputs(scope, tl(inputs));
	}
}
