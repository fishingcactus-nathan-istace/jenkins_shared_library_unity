// Executes a powershell script stored in the workspace, in the folder BuildScripts/Posh
// Assumes the workspace path is stored in env.WORKSPACE, otherwise that the shared library https://github.com/TheEmidee/JenkinsUtilsLibrary/blob/master/vars/getWorkspace.groovy is imported in the JenkinsFile.

def call( String name )
{
   def workspace = env.WORKSPACE

   if( workspace == null )
   {
      workspace = getWorkspace()
   }

   return "${workspace}/BuildScripts/Posh/${name}"
}
