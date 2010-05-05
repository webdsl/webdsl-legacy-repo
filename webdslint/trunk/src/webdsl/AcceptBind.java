package webdsl;

import semanticdomain.*;

public class AcceptBind extends Element implements Cloneable {

	Exp exp;

	public AcceptBind(Exp exp) {
		this.exp = exp;
	}

	public String evalR(EvalState state, Request request, Context context,
			TemplateEnv templateEnv, Env env) {
		return "";
	}

	public String pp(String indent) {
		return "accept_bind " + exp.pp(indent);
	}

}
