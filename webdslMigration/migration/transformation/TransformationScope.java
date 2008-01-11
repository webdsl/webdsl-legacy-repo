package transformation;

import java.util.HashMap;
import java.util.Map;

public class TransformationScope 
{
	private final Map<String, UntypedTransformation> definitions;
	
	public TransformationScope() {
		definitions = new HashMap<String, UntypedTransformation>();
	}
	
	public TransformationScope(TransformationScope s) {
		definitions = new HashMap(s.definitions);
	}
	
	public void setDefinition(String id, UntypedTransformation trafo) {
		definitions.put(id, trafo);
	}
	
	public UntypedTransformation getDefinition(String id) throws TransformationException {
		UntypedTransformation result = definitions.get(id);
		if(result == null)
			throw new TransformationException("Non existent transformation ("+id+") requested from scope");
		return result;
	}
}
