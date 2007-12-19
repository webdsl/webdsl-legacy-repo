package transformation;

import java.util.List;
import java.util.Vector;

public class Merge extends UntypedTransformation {
	private final UntypedTransformation inputTrafo;
	private final UntypedTransformation slaveTrafo;
	
	public Merge(UntypedTransformation slaveTrafo, UntypedTransformation inputTrafo) {
		this.inputTrafo = inputTrafo;
		this.slaveTrafo = slaveTrafo;
	}

	@Override
	public Object getAttribute(List<Object> input, String attributeName) throws TransformationException {
		List<List<Object>> splitInput = divideAndCheckInputs(input);
		
		// Try master, if not succesful, use slave (possibly throws exception)
		try {
			return inputTrafo.getAttribute(splitInput.get(0), attributeName);}
		catch(TransformationException e){}
		
		return slaveTrafo.getAttribute(splitInput.get(1), attributeName);
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
	
	protected List<List<Object>> divideAndCheckInputs(List<Object> input) throws TransformationException {
		// Split input over input part and slave part
		int nrMasterInputs = inputTrafo.getInjections().size();
		int nrSlaveInputs = slaveTrafo.getInjections().size();
		if(input.size() != nrMasterInputs + nrSlaveInputs)
			throw new TransformationException("Incorrect number of inputs in merge: got "+input.size()+" but required "+nrMasterInputs + nrSlaveInputs);
		List<Object> masterInput = input.subList(0, nrMasterInputs);
		List<Object> slaveInput = input.subList(nrMasterInputs, nrSlaveInputs);
		
		Vector<List<Object>> result = new Vector<List<Object>>();
		result.add(masterInput);
		result.add(slaveInput);
		return result;		
	}
}
