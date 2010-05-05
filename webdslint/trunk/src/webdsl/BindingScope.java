package webdsl;

import semanticdomain.*;

public class BindingScope extends Element implements Cloneable {

	Exp exp;
	Element element;

	public BindingScope(Exp exp, Element element) {
		super();
		this.exp = exp;
		this.element = element;
	}

	public String evalR(EvalState state, Request request, Context context,
			TemplateEnv templateEnv, Env env) {
		return element.evalR(state, request, context, templateEnv, env);
	}

	public String pp(String indent) {
		return "binding_scope (" + exp.pp(indent) + ") "
				+ element.pp(indent);
	}

}
