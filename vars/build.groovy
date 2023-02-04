def call(Map config = [:]) {
  echo "Building docker image"
  sh '''
  cd post
  ls
  docker build -t new-${env.BUILD_NUMBER} Dockerfile .
  docker tag new-${env.BUILD_NUMBER}:latest  
  '''
}
