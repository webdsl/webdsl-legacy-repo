package semanticdomain;

import semanticdomain.value.TIdentifier;

public class TemplateEnv extends SemanticVar {
	
	private TemplateEnv prev = null;
	
	private TIdentifier key;
	private Template value;

	public TemplateEnv(TIdentifier key, Template value, TemplateEnv prev) {
		this.key = key;
		this.value = value;
		this.prev = prev;
	}
	
	public TemplateEnv() {
	}
	
	public Template get(TIdentifier key) {
		if ((this.key != null) && this.key.equals(key))
			return value;
		else if (prev != null)
			return prev.get(key);
		else
			return null;
	}
	
	public TemplateEnv extend(TIdentifier key, Template value) {
		return new TemplateEnv(key, value, this);
	}
}
