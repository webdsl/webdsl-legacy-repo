package semanticdomain;
import java.util.ArrayList;

import semanticdomain.value.VIdentifier;
import semanticdomain.value.VTIdentifier;

import AST.Element;

public class Template extends SemanticVar {
	
	private VTIdentifier tIdentifier;
	private java.util.List<VIdentifier> identifiers = new ArrayList<VIdentifier>();
	private Element body;
	private TemplateEnv templateEnv;
	private Env env;
	
	public Template(VTIdentifier tIdentifier, java.util.List<VIdentifier> identifiers,
			Element body, TemplateEnv templateEnv, Env env)
	{
		this.tIdentifier = tIdentifier;
		this.identifiers = identifiers;
		this.body = body;
		this.templateEnv = templateEnv;
		this.env = env;
	}
	
	public VTIdentifier getTIdentifier() {
		return tIdentifier;
	}
	
	public java.util.List<VIdentifier> getIdentifiers() {
		return identifiers;
	}
	
	public Element getBody() {
		return body;
	}
	
	public TemplateEnv getTemplateEnv() {
		return templateEnv;
	}

	public void setTemplateEnv(TemplateEnv templateEnv) {
		this.templateEnv = templateEnv;
	}

	public Env getEnv() {
		return env;
	}
}
