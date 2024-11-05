// These functions take care of gathering the SHA from the latest commit, and writing it into a CS file. In order to work, the file must exist at the given path and have line that looks like 'commitSHA = "<whatever>"'

def get()
{
   String sha = bat( returnStdout: true, script: '''
    @echo off
    git rev-parse --short HEAD
   ''' ).trim()

   return sha
}

def writeToCsFile( String csFilePath )
{
   String date = new Date( ( long ) currentBuild.startTimeInMillis ).format( "MM/dd/yyyy HH:mm:ss a" )
   String sha = get()

   String text = readFile( file: csFilePath )
   String commitText = text.replaceAll( "(?s)commitSHA *= *\".*\"", "commitSHA = \"${sha} ${date}\"" )

   writeFile( file: csFilePath, text: commitText )
}
