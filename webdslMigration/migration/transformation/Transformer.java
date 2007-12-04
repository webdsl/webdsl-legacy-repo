package transformation;

import java.util.List;

public interface Transformer {
	public Object transform(List<Object> input) throws TransFormationException;
	public List<Injection> getInjections();
}
