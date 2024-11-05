def writeToCsFile( String csFilePath ) {
  def date = new Date((long)currentBuild.startTimeInMillis).format("MM/dd/yyyy HH:mm:ss a") ;
  def sha = get();
  
  def text = readFile(file: fileName);
  def commitText = text.replaceAll("(?s)commitSHA = \".*\"", "commitSHA = \"${sha} ${date}\"");
  
  writeFile( file: fileName, text: commitText );
}

def get() {
  String sha = bat(returnStdout:true , script: '''
    @echo off
    git rev-parse --short HEAD
  ''').trim();

  return sha;
}