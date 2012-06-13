package webdsl;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.wst.server.core.IServer;

public final class WebDSLProjectBuilderWTPJ2EEPreview extends WebDSLProjectBuilder{

    @Override
    protected void initWtpServerConfig(String plugindir, IProject project, String projectname, IProgressMonitor monitor) throws CoreException{
        WTPJ2EEPreviewSetup.initWtpServerConfig(plugindir,project,projectname,monitor);
    }
    
    @Override
    public String getAppUrl(IProject project){
        return getUrlRoot(project)+"root/"; 
        //without root/ this servers shows deployed application file list
    }
    
    @Override
    public void tryStartServer(IProject project, IProgressMonitor monitor, final ChainedJob cj, int delay) throws CoreException{
        final IServer server = getProjectServer(project,monitor);
        //publish, clean, restart are all not supported on this server type, so just stop and start
        addStopServerJob(
            server, 
            new ChainedJob() {
                @Override
                public void run() {
                    addStartServerJob(server, cj, 0);
                }
            },
            0);        
    }
    
}
