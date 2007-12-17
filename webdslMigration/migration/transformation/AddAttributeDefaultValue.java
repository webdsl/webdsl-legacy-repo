package transformation;

import java.util.List;

public class AddAttributeDefaultValue extends UntypedTransformation {
	private final UntypedTransformation inputTrafo;
	private final String attributeName;
	private final Object defValue;
	
	public AddAttributeDefaultValue(String attributeName, Object defValue, TypedTransformation inputTrafo) {
		this.inputTrafo = inputTrafo;
		this.attributeName = attributeName;
		this.defValue = defValue;
	}

	@Override
	public Object getAttribute(List<Object> input, String attributeName) throws TransformationException {
		if(attributeName.equals(this.attributeName))
			return defValue;
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
	
	/**
	 * @return the defValue
	 */
	public Object getDefValue() {
		return defValue;
	}

	@Override
	public List<Injection> getInjections() {
		return inputTrafo.getInjections();
	}
}
