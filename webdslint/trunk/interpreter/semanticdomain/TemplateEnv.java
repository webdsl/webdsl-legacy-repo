package semanticdomain;

import semanticdomain.value.VTIdentifier;

public class TemplateEnv extends SemanticVar {
	
	private TemplateEnv prev = null;
	
	private VTIdentifier key;
	private Template value;

	public TemplateEnv(VTIdentifier key, Template value, TemplateEnv prev) {
		this.key = key;
		this.value = value;
		this.prev = prev;
	}
	
	public TemplateEnv() {
	}
	
	public Template get(VTIdentifier key) {
		if ((this.key != null) && this.key.equals(key))
			return value;
		else if (prev != null)
			return prev.get(key);
		else
			return null;
	}
	
	public void setInnerTemplateEnv(TemplateEnv newTemplateEnv) {
		if (key != null) {
			value.setTemplateEnv(newTemplateEnv);
			prev.setInnerTemplateEnv(newTemplateEnv);
		}
	}
	
	public TemplateEnv extend(VTIdentifier key, Template value) {
		return new TemplateEnv(key, value, this);
	}
}
