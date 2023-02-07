def call() {
  echo "Pushing docker image to ECR"
  sh "aws ecr get-login-password --region us-east-1 | docker login --username AWS --password-stdin 991037556739.dkr.ecr.us-east-1.amazonaws.com"
  sh "docker tag springboot:${env.BUILD_NUMBER} 991037556739.dkr.ecr.us-east-1.amazonaws.com/springboot:${env.BUILD_NUMBER}"
  sh "docker push 991037556739.dkr.ecr.us-east-1.amazonaws.com/springboot:${env.BUILD_NUMBER}"
  sh "docker push 991037556739.dkr.ecr.us-east-1.amazonaws.com/springboot:latest"
  sh "rm -r /var/lib/jenkins/.docker/config.json" 
}
