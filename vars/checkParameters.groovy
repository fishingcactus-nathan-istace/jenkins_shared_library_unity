def call() {
  stage('Check parameters') {
      try {
          if (params.ROOT_ARCHIVE_DIRECTORY == null || params.ROOT_ARCHIVE_DIRECTORY == "") {
              error "ROOT_ARCHIVE_DIRECTORY must be set !";
          }
      } catch (Exception err) {
          currentBuild.result = "FAILURE";
      }
  }
}
