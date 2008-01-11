package transformation;

import java.util.List;

public class AddAttribute extends UntypedTransformation {
	private final String attributeName;
	private final ObjectConversion valueCalculation;
	
	public AddAttribute(String attributeName, ObjectConversion valueCalculation) {
		this.attributeName = attributeName;
		this.valueCalculation = valueCalculation;
	}

	@Override
	public Object getAttribute(List<UntypedTransformation> input, TransformationScope scope, String attributeName) throws TransformationException {
		shouldBeTypedTransformation(hd(input));
		if(attributeName.equals(this.attributeName))
			return valueCalculation.convert(((TypedTransformation)hd(input)).transform(tl(input), scope));
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
