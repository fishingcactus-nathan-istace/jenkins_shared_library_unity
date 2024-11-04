def call() {
  if (params.TEST_PARAM2 == null){
    echo "${env.PROJECT_NAME} has no param named TEST_PARAM2"
  } else {
    echo "${env.PROJECT_NAME} ${params.TEST_PARAM2}"
  } 
}
