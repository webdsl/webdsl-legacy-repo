package transformation;

import java.util.List;

public class AddAttributeDefaultValue extends UntypedTransformation {
	private final String attributeName;
	private final Object defValue;
	
	public AddAttributeDefaultValue(String attributeName, Object defValue) {
		this.attributeName = attributeName;
		this.defValue = defValue;
	}

	@Override
	public Object getAttribute(List<UntypedTransformation> input, TransformationScope scope,  String attributeName) throws TransformationException {
		if(attributeName.equals(this.attributeName))
			return defValue;
		return hd(input).getAttribute(tl(input), scope, attributeName);
	}

	/**
	 * @return the attributeName
	 */
	public String getAttributeName() {
		return attributeName;
	}

	/**
	 * @return the defValue
	 */
	public Object getDefValue() {
		return defValue;
	}

	@Override
	public int getNrInputs(TransformationScope scope, List<UntypedTransformation> inputs) throws TransformationException {
		return 1 + hd(inputs).getNrInputs(scope, tl(inputs));
	}
}
