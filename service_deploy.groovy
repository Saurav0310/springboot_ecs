def call(){
  sh "kubectl config set-context --namespace=default --current"
  sh "kubectl set image deployment/nw-social-${DOCKER_SERVICE}-${ENV} nw-social-${DOCKER_SERVICE}-${ENV}=${DOCKER_REGISTRY}/nw-social-${DOCKER_SERVICE}:${ENV}-${env.BUILD_NUMBER}"
  sh "kubectl rollout status deployment.v1.apps/nw-social-${DOCKER_SERVICE}-${ENV}"
}
