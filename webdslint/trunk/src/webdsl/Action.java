package webdsl;

import semanticdomain.*;

public class Action extends ASTNode {

	String name;
	List<Param> optParamList;
	List<Statement> optStatementList;

	public Action(String name, List<Param> optParamList, List<Statement> optStatementList) {
		super();
		this.name = name;
		this.optParamList = optParamList;
		this.optStatementList = optStatementList;
	}

	public String evalR(EvalState state, Request request, Context context,
			TemplateEnv templateEnv, Env env) {
		return "";
	}

	public String pp(String indent) {
		return "action " + name + "(" + optParamList.pp(indent, ", ")
				+ ")" + " {\n" + indent + "  "
				+ optStatementList.pp(indent + "  ", "\n" + indent + "  ") + "\n"
				+ indent + "}";
	}

}
