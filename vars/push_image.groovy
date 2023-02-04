def call() {
  echo "Pushing docker image to ECR"
  sh "aws ecr get-login-password --region \${REGION} | docker login --usernadocker build -t \${DOCKER_REGISTRY}/nw-social-\${DOCKER_SERVICE}:\${ENV}-\${env.BUILD_NUMBER} Dockerfile ."
  sh "docker tag \${DOCKER_REGISTRY}/nw-social-\${DOCKER_SERVICE}:\${ENV}-\${env.BUILD_NUMBER} \${DOCKER_REGISTRY}/nw-social-\${DOCKER_SERVICE}:\${ENV}-latestme AWS --password-stdin ${DOCKER_REGISTRY}"
  sh "docker push \${DOCKER_REGISTRY}/nw-social-\${DOCKER_SERVICE}:\${ENV}-\${env.BUILD_NUMBER}"
  sh "docker push \${DOCKER_REGISTRY}/nw-social-\${DOCKER_SERVICE}:\${ENV}-latest"
  sh "docker rmi -f \${DOCKER_REGISTRY}/nw-social-\${DOCKER_SERVICE}:\${ENV}-\${env.BUILD_NUMBER}"
  sh "docker rmi -f \${DOCKER_REGISTRY}/nw-social-\${DOCKER_SERVICE}:\${ENV}-latest"
  sh "rm -r /var/lib/jenkins/.docker/config.json"
}
