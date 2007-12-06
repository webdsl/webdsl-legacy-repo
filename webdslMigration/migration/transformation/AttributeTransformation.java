package transformation;

import java.util.List;
import java.util.Vector;

public class AttributeTransformation extends UntypedTransformation {
	private final UntypedTransformation inputTrafo;
	private final String attributeName;
	private final TypedTransformation attTrans;
	
	public AttributeTransformation(String attributeName, TypedTransformation attTrans, UntypedTransformation inputTrafo) {
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
	public UntypedTransformation getInputTrafo() {
		return inputTrafo;
	}

	/**
	 * @return the attTrans
	 */
	public TypedTransformation getAttTrans() {
		return attTrans;
	}

	@Override
	public List<Injection> getInjections() {
		return inputTrafo.getInjections();
	}
}
