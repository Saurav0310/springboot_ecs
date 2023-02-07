def call() {
  echo "Building docker image"
  sh "cd post/"
  sh "docker build -t ${APP_NAME}:${BUILD_NUMBER} ."
}
