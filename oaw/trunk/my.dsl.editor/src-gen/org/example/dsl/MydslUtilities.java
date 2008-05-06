package org.example.dsl;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.jface.text.rules.IPartitionTokenScanner;
import org.openarchitectureware.xtext.AbstractLanguageUtilities;
import org.openarchitectureware.xtext.XtextFile;
import org.openarchitectureware.xtext.parser.IXtextParser;
import org.osgi.framework.Bundle;

import org.example.dsl.parser.XtextParser;

public class MydslUtilities extends AbstractLanguageUtilities {

   @Override
	protected IXtextParser internalParse(InputStream inputStream) {
		return new XtextParser(inputStream);
	}

   public String getFileExtension() {
      return "dsl";
   }

	public EPackage getEPackage() {
		return org.example.dsl.MetaModelRegistration.getEPackage();
	}
   
   List<String> r = new ArrayList<String>();
   {
   r.add("define");
   r.add("application");
   r.add("page");
   r.add("entity");
   r.add("section");
   }
   public List<String> allKeywords() {
      return r;
   }

   protected ClassLoader getClassLoader() {
      return this.getClass().getClassLoader();
   }
   
   public IPartitionTokenScanner getPartitionScanner() {
      return new GeneratedPartitionScanner();
   }
   
   @Override
	public Bundle getPluginBundle() {
		return MydslEditorPlugin.getDefault().getBundle();
	}
	
	@Override
	public String getPackageForExtensions() {
		return "org::example::dsl";
	}
	
	public XtextFile getXtextFile() {
		return MetaModelRegistration.getXtextFile();
	}
}
