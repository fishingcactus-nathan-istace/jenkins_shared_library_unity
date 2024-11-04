def call() {
  stage('Check parameters') {
      try {
          if (params.ROOT_ARCHIVE_DIRECTORY == null || params.ROOT_ARCHIVE_DIRECTORY == "") {
              error "ROOT_ARCHIVE_DIRECTORY must be set !";
          }
          echo "params.ROOT_ARCHIVE_DIRECTORY = ${params.ROOT_ARCHIVE_DIRECTORY}";
      } catch (Exception err) {
          echo  "ROOT_ARCHIVE_DIRECTORY must be set !";
          currentBuild.result = "FAILURE";
      }
  }
}
