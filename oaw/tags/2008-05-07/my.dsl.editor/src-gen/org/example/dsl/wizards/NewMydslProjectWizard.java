package org.example.dsl.wizards;

import org.openarchitectureware.xtext.LanguageUtilities;
import org.openarchitectureware.xtext.editor.wizards.AbstractNewProjectWizard;

import org.example.dsl.MydslEditorPlugin;

public class NewMydslProjectWizard extends AbstractNewProjectWizard {

	public NewMydslProjectWizard() {
		super();
		setLangName("mydsl");
		setGeneratorProjectName("my.dsl.generator");
		setDslProjectName("my.dsl");
		setFileExtension("dsl");
		setPackageName("org/example/dsl/");
	}
	
	@Override
	protected LanguageUtilities getUtilities() {
		return MydslEditorPlugin.getDefault().getUtilities();
	}
}

