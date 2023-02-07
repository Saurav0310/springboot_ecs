def call() {
  echo "Building docker image"
  sh"docker build -t ${APP_NAME}:${BUILD_NUMBER} ."              
}
