package transformation;

import java.util.List;

public class AddAttribute extends Transformation {
	private final Transformation inputTrafo;
	private final String attributeName;
	private final Object defaultValue;
	
	public AddAttribute(String attributeName, Object defaultValue, Transformation inputTrafo) {
		this.inputTrafo = inputTrafo;
		this.attributeName = attributeName;
		this.defaultValue = defaultValue;
	}

	@Override
	public Object getAttribute(List<Object> input, String attributeName) throws TransFormationException {
		if(attributeName.equals(this.attributeName))
			return defaultValue;
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
	public Transformation getInputTrafo() {
		return inputTrafo;
	}

	/**
	 * @return the defaultValue
	 */
	public Object getDefaultValue() {
		return defaultValue;
	}
	
	@Override
	public List<Injection> getInjections() {
		return inputTrafo.getInjections();
	}
}
