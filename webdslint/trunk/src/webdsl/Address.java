package webdsl;

import semanticdomain.*;
import semanticdomain.value.*;

public class Address extends Exp implements Cloneable {

	Exp exp;

	public Address(Exp exp) {
		this.exp = exp;
	}

	public Value evalE(EvalState state, Request request, Context context,
			TemplateEnv templateEnv, Env env) {
		Value result = null;
		Value value = exp.evalE(state, request, context, templateEnv, env);
		if (value instanceof VRef) {
			VRef vRef = (VRef) value;
			result = vRef.getAddress();
		} else
			result = new VString("Can not get address of " + value.toString());
		return result;
	}

	public String pp(String indent) {
		return "address(" + exp.pp(indent) + ")";
	}

}
