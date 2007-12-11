package transformation;

import java.util.List;

public class Merge extends UntypedTransformation {
	private final UntypedTransformation inputTrafo;
	private final UntypedTransformation slaveTrafo;
	
	public Merge(UntypedTransformation slaveTrafo, UntypedTransformation inputTrafo) {
		this.inputTrafo = inputTrafo;
		this.slaveTrafo = slaveTrafo;
	}

	@Override
	public Object getAttribute(List<Object> input, String attributeName) throws TransformationException {
		// Try master, if not succesful, use slave (possibly throws exception)
		try {
			return inputTrafo.getAttribute(input, attributeName);}
		catch(TransformationException e){}
		return slaveTrafo.getAttribute(input, attributeName);
	}

	@Override
	public List<Injection> getInjections() {
		List <Injection> masterInjections = inputTrafo.getInjections();
		List <Injection> slaveInjections = slaveTrafo.getInjections();
		masterInjections.addAll(slaveInjections);
		return masterInjections;
	}

	/**
	 * @return the inputTrafo
	 */
	public UntypedTransformation getInputTrafo() {
		return inputTrafo;
	}

	/**
	 * @return the slaveTrafo
	 */
	public UntypedTransformation getSlaveTrafo() {
		return slaveTrafo;
	}
}
