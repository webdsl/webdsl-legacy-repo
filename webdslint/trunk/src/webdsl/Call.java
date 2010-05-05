package webdsl;

import java.util.ArrayList;

import semanticdomain.*;
import semanticdomain.value.*;

public class Call extends Element implements Cloneable {

	String name;
	List<Exp> args;
	Sequence body;

	public Call(String name, List<Exp> args, Sequence body) {
		super();
		this.name = name;
		this.args = args;
		this.body = body;
	}

	public Value evalA(EvalState state, Request request, Context context,
			TemplateEnv templateEnv, Env env) {

		Value result = null;

		VTIdentifier tId = new VTIdentifier(name);
		Template template = templateEnv.get(tId);

		if (template == null) {
			// pass
		} else {
			// evaluate parameters
			java.util.List<VIdentifier> ids = template.getIdentifiers();

			for (int i = 0; i < args.size(); i++) {
				Exp arg = args.get(i);
				Value v = arg.evalE(state, request, context, templateEnv, env);
				VIdentifier id = ids.get(i);
				env = env.extend(id, v);
			}

			// create new Template for elements
			VTIdentifier elements = new VTIdentifier("elements");
			Template newTemplate = new Template(tId,
					new ArrayList<VIdentifier>(), body, new TemplateEnv(),
					env);
			// add it to the template environment
			templateEnv = templateEnv.extend(elements, newTemplate);

			// evaluate original template's body
			Element body = template.getBody();
			result = body.evalA(state, request, context, templateEnv, env);
		}
		return result;
	}

	// Declared in EvalR.jadd at line 74

	public String evalR(EvalState state, Request request, Context context,
			TemplateEnv templateEnv, Env env) {

		String result = "";
		// result += super.evalR(state, request, context, templateEnv, env);

		VTIdentifier tId = new VTIdentifier(name);
		Template template = templateEnv.get(tId);

		if (template == null) {
			result += "Missing template: " + tId;
		} else {
			// evaluate parameters
			java.util.List<VIdentifier> ids = template.getIdentifiers();

			for (int i = 0; i < args.size(); i++) {
				Exp arg = args.get(i);
				Value v = arg.evalE(state, request, context, templateEnv, env);
				VIdentifier id = ids.get(i);
				env = env.extend(id, v);
			}

			// create new Template for elements
			VTIdentifier elements = new VTIdentifier("elements");
			Template newTemplate = new Template(tId,
					new ArrayList<VIdentifier>(), body, new TemplateEnv(),
					env);
			// add it to the template environment
			templateEnv = templateEnv.extend(elements, newTemplate);

			// evaluate original template's body
			Element body = template.getBody();
			result += body.evalR(state, request, context, templateEnv, env);
		}
		return result;
	}

	public String pp(String indent) {
		return name + " " + args.pp(indent, ", ", "(", ") ")
				+ body.pp(indent);
	}

}
