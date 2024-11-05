def call( String name ) {
  def workspace = env.WORKSPACE;

  if ( workspace == null ) { 
    workspace = getWorkspace();
  }
  
   return "${workspace}/BuildScripts/Posh/${name}";
}
