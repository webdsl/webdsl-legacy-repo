package utils;

import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.webdsl.lang.Environment;

public abstract class TemplateServlet {
    
    public static TemplateServlet getCurrentTemplate(){
        return ThreadLocalTemplate.get();
    }
    
    protected boolean validated=true;
    protected String uniqueid;
    public String getUniqueId(){
      if(uniqueIdOverride.isEmpty()){
        return this.uniqueid;
      }
      else{
        return uniqueIdOverride.peek();
      }
    }
    public void pushUniqueIdOverride(String s){
        uniqueIdOverride.push(s);
    }
    public void popUniqueIdOverride(){
        uniqueIdOverride.pop();
    }
    protected ArrayDeque<String> uniqueIdOverride = new ArrayDeque<String>(); 
    protected Environment env;
    public Environment getEnv(){
        return env;
    }
    private java.util.Map<String, Object> templatecalls = null;
    protected java.util.Map<String, Object> getTemplatecalls(){
      if(templatecalls == null){
        templatecalls = new java.util.HashMap<String, Object>();
      }
      return templatecalls;
    }
    protected boolean onlyPerformingRenderPhase(){
        return templatecalls == null;
    }
    protected org.hibernate.Session hibSession;
    protected HttpServletRequest request;
    protected HttpServletResponse response;
    protected boolean initialized = false;
    protected utils.TemplateCall templateArg;
    protected Map<String,String> attrs = null;
    protected String[] pageArguments = null;

    // cancels further handling of this template, e.g. when validation error occurs in init
    protected boolean skipThisTemplate = false;
    
    protected boolean initializeLocalVarsOnceExecuted = false;
    protected void tryInitializeVarsOnce(){
      if(!initializeLocalVarsOnceExecuted){
        initializeLocalVarsOnceExecuted = true;  
        initializeLocalVarsOnce();
      }
    }
    
    public abstract void prefetchFor(int i, java.util.Collection<? extends org.webdsl.WebDSLEntity> elems);
    
    public void storeInputs(String calledName, Object[] args, Environment env, Map<String,String> attrs) {
        if(!skipThisTemplate){
          tryInitializeTemplate(calledName, args, env, attrs);
          tryInitializeVarsOnce(); //this phase could be skipped, so performed in render as well
          storeInputsInternal();
        }
      }  
    public void validateInputs(String calledName, Object[] args, Environment env,  Map<String,String> attrs) {
        if(!skipThisTemplate){
          tryInitializeTemplate(calledName, args, env, attrs);
          validateInputsInternal();
        }
      } 
    public void handleActions(String calledName, Object[] args, Environment env, Map<String,String> attrs) {          
        if(!skipThisTemplate){
          tryInitializeTemplate(calledName, args, env, attrs);
          handleActionsInternal();
        }
      }  

    public void render(String calledName, Object[] args, Environment env, Map<String,String> attrs) { 
      if(!skipThisTemplate){
        tryInitializeTemplate(calledName, args, env, attrs);
        tryInitializeVarsOnce();
     
        java.io.StringWriter s = new java.io.StringWriter();

        PrintWriter out = new java.io.PrintWriter(s);
        ThreadLocalOut.push(out);
        beforeRender();
        renderInternal();
        afterRender();
        ThreadLocalOut.popChecked(out);
        out = ThreadLocalOut.peek();
        
        tryWriteSpanOpen(out);
        out.write(s.toString());
        tryWriteSpanClose(out);
      }
    }
    
    protected abstract void storeInputsInternal();
    protected abstract void validateInputsInternal();
    protected abstract void handleActionsInternal();
    protected abstract void renderInternal();
    
    protected abstract boolean isAjaxTemplate();
    private boolean alreadyPassedThroughAjaxTemplate = false;
    protected void beforeRender(){
        if(ThreadLocalPage.get().passedThroughAjaxTemplate()){
            alreadyPassedThroughAjaxTemplate = true;
        }
        else if(isAjaxTemplate()){
            ThreadLocalPage.get().setPassedThroughAjaxTemplate(true);
        }
    }
    protected void afterRender(){
        if(!alreadyPassedThroughAjaxTemplate && isAjaxTemplate()){
            ThreadLocalPage.get().setPassedThroughAjaxTemplate(false);
        }    	
    }
    
    protected abstract void tryWriteSpanOpen(PrintWriter outtemp);
    protected abstract void tryWriteSpanClose(PrintWriter outtemp);
    protected abstract void putLocalDefinesInEnv();
    protected abstract void storeArguments(Object[] args);
    
    protected abstract void initialize();
    protected abstract void initSubmitActions();
    protected abstract void initActions();
    protected abstract void initializeLocalVars();
    protected abstract void initializeLocalVarsOnce();
    
    public abstract String getUniqueName();
    public abstract String getTemplateClassName();
    public abstract String getTemplateSignature();
    public abstract String getStateEncodingOfArgument();
    public String getTemplateContext(){
      return ThreadLocalPage.get().getTemplateContextString();
    } 
    
    private Map<String,Object> actions = null;
    public Map<String,Object> getActions(){
        return actions;
    }
    public Object getAction(String key) {
        if (actions != null && actions.containsKey(key)){
            return actions.get(key);
        }
        else{
            throw new RuntimeException("Action with name "+key+" was not found in template "+getUniqueName());
        }
    }
    public void putAction(String key, Object value) {
        if(actions == null){
            actions = new HashMap<String,Object>();
        }
        actions.put(key, value);
    }
    
    //template name used to call it might be different due to override/local redefine
    //null means use the regular unique template name
    protected String calledName;
    
    private void tryInitializeTemplate(String calledName, Object[] args, Environment env, Map<String,String> attrs){
        //always set ThreadLocalTemplate
        ThreadLocalTemplate.set(this);
        //always store env and arguments, values might change between phases
        // We ensure that there is an env, because prefetching in storeArguments() may use env.getTemplate()
        this.env = env;
        storeArguments(args);
        
        if(!initialized || ThreadLocalPage.get().hibernateCacheCleared)
        {
              //System.out.println("template init "+"~x_Page"+"init: "+initialized+ " hibcache: "+ThreadLocalPage.get().hibernateCacheCleared);
              initialized=true;
              
              this.calledName = calledName;
              this.env = env;
              putLocalDefinesInEnv();
              this.request = ThreadLocalPage.get().getRequest();
              this.response = ThreadLocalPage.get().getResponse();
              //if(request != null){ //calling rendertemplate within background task
              //  this.session = request.getSession(true);
              //}
              this.hibSession = utils.HibernateUtil.getCurrentSession();
              this.attrs = attrs;
              try {
                this.uniqueid = Encoders.encodeTemplateId(getTemplateClassName()/*, getStateEncodingOfArgument()*/, getTemplateContext());
                initialize();
                initializeLocalVars();
                initSubmitActions();
                initActions();
              }
              catch(utils.ValidationException ve){
                ThreadLocalPage.get().getValidationExceptions().add(ve.setName(ThreadLocalPage.get().getValidationContext()));
                ThreadLocalPage.get().setValidated(false);
                utils.Warning.warn("Validation failed in initialization of "+getTemplateSignature()+": "+ve.getErrorMessage());  
            skipThisTemplate = true;
          }
          catch(utils.MultipleValidationExceptions ve){
            for(utils.ValidationException vex : ve.getValidationExceptions()){
              ThreadLocalPage.get().getValidationExceptions().add(vex.setName(ThreadLocalPage.get().getValidationContext()));
              utils.Warning.warn("Validation failed in initialization of "+getTemplateSignature()+": "+vex.getErrorMessage());  
            }
            ThreadLocalPage.get().setValidated(false); 
            skipThisTemplate = true;
          }
        } 
    } 
    
    protected boolean isAjaxSubmitRequired(){
        return isAjaxSubmitRequired(false);
    }
    protected boolean isAjaxSubmitRequired(boolean ajaxmod){
      return ThreadLocalPage.get().isServingAsAjaxResponse //template is rendered in an action, e.g. with replace(placeholder,templatecall())
        || ThreadLocalPage.get().isAjaxRuntimeRequest() //current request came from ajax runtime
        || ThreadLocalPage.get().passedThroughAjaxTemplate() // passed through template defined with ajax modifier 'define ajax'
        || ajaxmod //submit buttons is defined with ajax modifier '[ajax]'
        || ThreadLocalPage.get().getFormIdent().equals(""); //submit is not in a form (normal browser submit won't work)
    }
}
