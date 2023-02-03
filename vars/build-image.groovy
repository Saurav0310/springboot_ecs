def call(){
 echo "Building java project"
  sh '''
  set +x
  cd ${JOB_DIR}
  ls
  docker build -t ${DOCKER_REGISTRY}/nw-social-${DOCKER_SERVICE}:${ENV}-${env.BUILD_NUMBER} Dockerfile .
  docker tag ${DOCKER_REGISTRY}/nw-social-${DOCKER_SERVICE}:${ENV}-${env.BUILD_NUMBER} ${DOCKER_REGISTRY}/nw-social-${DOCKER_SERVICE}:${ENV}-latest  
  '''
}
