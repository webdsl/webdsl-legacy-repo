package semanticdomain;
import java.util.ArrayList;

import semanticdomain.value.Identifier;
import semanticdomain.value.TIdentifier;

import AST.Element;

public class Template extends SemanticVar {
	
	private TIdentifier tIdentifier;
	private java.util.List<Identifier> identifiers = new ArrayList<Identifier>();
	private Element body;
	private TemplateEnv templateEnv;
	private Env env;
	
	public Template(TIdentifier tIdentifier, java.util.List<Identifier> identifiers,
			Element body, TemplateEnv templateEnv, Env env)
	{
		this.tIdentifier = tIdentifier;
		this.identifiers = identifiers;
		this.body = body;
		this.templateEnv = templateEnv;
		this.env = env;
	}
	
	public TIdentifier getTIdentifier() {
		return tIdentifier;
	}
	
	public java.util.List<Identifier> getIdentifiers() {
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
