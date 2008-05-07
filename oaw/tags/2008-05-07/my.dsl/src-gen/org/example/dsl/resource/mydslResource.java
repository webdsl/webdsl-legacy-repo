package org.example.dsl.resource;

import java.io.InputStream;

import org.eclipse.emf.common.util.URI;
import org.openarchitectureware.xtext.parser.IXtextParser;
import org.openarchitectureware.xtext.resource.AbstractXtextResource;

import org.example.dsl.parser.XtextParser;

public class mydslResource extends AbstractXtextResource {
	public mydslResource(URI uri) {
		super(uri);
	}

	@Override
	protected IXtextParser createParser(InputStream inputStream) {
		return new XtextParser(inputStream);
	}

}

