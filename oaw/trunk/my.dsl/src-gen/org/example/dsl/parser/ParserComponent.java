package org.example.dsl.parser;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.openarchitectureware.workflow.issues.Issues;
import org.openarchitectureware.xtext.parser.impl.AbstractParserComponent;
import org.openarchitectureware.xtext.resource.IXtextResource;

import org.example.dsl.resource.mydslResourceFactory;

public class ParserComponent extends AbstractParserComponent {
	static {
		mydslResourceFactory.register();
	}

	protected String getFileExtension() {
		return "dsl";
	}

}
