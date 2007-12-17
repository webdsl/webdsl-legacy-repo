package transformation;

import java.util.List;

public class AddAttribute extends UntypedTransformation {
	private final TypedTransformation inputTrafo;
	private final String attributeName;
	private final ObjectConversion valueCalculation;
	
	public AddAttribute(String attributeName, ObjectConversion valueCalculation, TypedTransformation inputTrafo) {
		this.inputTrafo = inputTrafo;
		this.attributeName = attributeName;
		this.valueCalculation = valueCalculation;
	}

	@Override
	public Object getAttribute(List<Object> input, String attributeName) throws TransformationException {
		if(attributeName.equals(this.attributeName))
			return valueCalculation.convert(inputTrafo.transform(input));
		return inputTrafo.getAttribute(input, attributeName);
	}

	/**
	 * @return the attributeName
	 */
	public String getAttributeName() {
		return attributeName;
	}

	/**
	 * @return the input transformation
	 */
	public UntypedTransformation getInputTrafo() {
		return inputTrafo;
	}
	
	@Override
	public List<Injection> getInjections() {
		return inputTrafo.getInjections();
	}
}
