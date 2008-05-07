
package org.example.dsl.wizards;

import org.openarchitectureware.xtext.LanguageUtilities;
import org.openarchitectureware.xtext.editor.wizards.AbstractNewFileWizard;

import org.example.dsl.MydslEditorPlugin;

public class NewMydslFileWizard extends AbstractNewFileWizard {

	@Override
	protected LanguageUtilities getUtilities() {
		return MydslEditorPlugin.getDefault().getUtilities();
	}
}
