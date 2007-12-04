package transformation;

import java.util.List;
import java.util.Vector;

public class AttributeTransformation extends Transformation {
	private final Transformation inputTrafo;
	private final String attributeName;
	private final Transformer attTrans;
	
	public AttributeTransformation(String attributeName, Transformer attTrans, Transformation inputTrafo) {
		this.inputTrafo = inputTrafo;
		this.attributeName = attributeName;
		this.attTrans = attTrans;
	}

	@Override
	public Object getAttribute(List<Object> input, String attributeName) throws TransFormationException {
		if(attributeName.equals(this.attributeName))
		{
			List<Object> transformInput = new Vector<Object>();
			transformInput.add(inputTrafo.getAttribute(input, attributeName));
			return attTrans.transform(transformInput);
		}
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
	 * @return the attTrans
	 */
	public Transformer getAttTrans() {
		return attTrans;
	}

	@Override
	public List<Injection> getInjections() {
		return inputTrafo.getInjections();
	}
}
