package org.strategoxt.imp.metatooling.loading;

import org.eclipse.core.resources.IFile;
import org.eclipse.imp.language.LanguageValidator;

/**
 * Dynamically loaded language validator class.
 * Currently used to launch the <ref>StartupDescriptorLoader</ref>.
 * 
 * @author Lennart Kats <lennart add lclnet.nl>
 */
public class StartupDescriptorValidator extends LanguageValidator {
	
	// HACK: I'm using a validator to hook into the language runtime

	public StartupDescriptorValidator() {
		StartupDescriptorLoader.run();
	}

	@Override
	public boolean validate(IFile file) {
		return false;
	}

	@Override
	public boolean validate(String buffer) {
		return false;
	}
}
