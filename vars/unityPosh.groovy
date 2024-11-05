def getVersion( String project_folder ) {
    return powershell(
        label: "Check Unity Version",
        returnStdout: true,
        script: getBuildScript("Check-Unity-Version.ps1 -ProjectPath ${project_folder}")
    ).trim();
}

def getInstallPath( String unity_version ) {
    string unity_install_path = powershell(
        label: "Get Unity Install Path",
        returnStdout: true,
        script: getBuildScript("Get-Unity-Install-Path.ps1 -ExpectedVersion ${unity_version}")
    ).trim();
        
    // make sure it's quoted, there can be spaces inside
    unity_install_path = '"' + unity_install_path + '"';
  
    return unity_install_path;
}

def activateLicense( String serial, String platform, String project_folder, String base_arguments ) {
    withCredentials([
        string(credentialsId: serial, variable: 'SERIAL'),
        string(credentialsId: 'UnityUsername', variable: 'USERNAME'),
        string(credentialsId: 'UnityPassword', variable: 'PASSWORD')
    ]) {
        timeout(time: 30, unit: 'MINUTES') {
            stage('Get And Activate Unity Version') {
                String unity_version = getVersion( project_folder );
                String unity_install_path = getInstallPath( unity_version );
                
                powershell(
                    label: "Activate Unity License",
                    returnStdout: false,
                    script: getBuildScript("Activate-Unity.ps1 -UnityPath $unity_install_path -Username $USERNAME -Password $PASSWORD -Serial $SERIAL -Arguments @($base_arguments)")
                )

                return unity_install_path
            }
        }
    }
}

def deactivateLicense( String unity_install_path ) {
    powershell(
        label: "Desactivate Unity License",
        returnStdout: false,
        script: getBuildScript("Desactivate-Unity.ps1 -UnityPath ${unity_install_path}")
    );
}
