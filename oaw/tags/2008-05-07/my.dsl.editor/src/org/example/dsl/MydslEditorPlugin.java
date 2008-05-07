package org.example.dsl;

import org.openarchitectureware.xtext.AbstractXtextEditorPlugin;
import org.openarchitectureware.xtext.LanguageUtilities;
import org.osgi.framework.BundleContext;

public class MydslEditorPlugin extends AbstractXtextEditorPlugin {
   private static MydslEditorPlugin plugin;
   public static MydslEditorPlugin getDefault() {
      return plugin;
   }

   private MydslUtilities utilities = new MydslUtilities();
   public LanguageUtilities getUtilities() {
      return utilities;
   }

   public MydslEditorPlugin() {
      plugin = this;
   }

   public void stop(BundleContext context) throws Exception {
      super.stop(context);
      plugin = null;
   }
}
