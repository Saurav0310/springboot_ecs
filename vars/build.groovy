def call(Map config = [:]) {
  echo "Building docker image"
  #!/usr/bin/env bash
  sh """
  ls
  docker build -t new-${env.BUILD_NUMBER} Dockerfile .
  docker tag new-${env.BUILD_NUMBER}:latest  
  """
}
