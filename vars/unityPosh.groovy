def getVersion() {
    return powershell(
        label: "Check Unity Version",
        returnStdout: true,
        script: getBuildScript("Check-Unity-Version.ps1 -ProjectPath ${getProjectFolder()}")
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

def deactivateLicense(String unity_install_path) {
    powershell(
        label: "Desactivate Unity License",
        returnStdout: false,
        script: getBuildScript("Desactivate-Unity.ps1 -UnityPath ${unity_install_path}")
    );
}
