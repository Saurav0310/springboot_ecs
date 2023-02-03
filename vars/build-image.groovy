def call(Map config = [:]) {
  echo "Building docker image"
  sh '''
  set +x
  cd ${config.JOB_DIR}
  ls
  docker build -t ${config.DOCKER_REGISTRY}/nw-social-${config.DOCKER_SERVICE}:${config.ENV}-${env.BUILD_NUMBER} Dockerfile .
  docker tag ${config.DOCKER_REGISTRY}/nw-social-${config.DOCKER_SERVICE}:${config.ENV}-${env.BUILD_NUMBER} ${config.DOCKER_REGISTRY}/nw-social-${config.DOCKER_SERVICE}:${config.ENV}-latest  
  '''
}
