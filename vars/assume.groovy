def call() {
  echo "assume role"
  sh "temp_role=$(aws sts assume-role \
                            --role-arn $ROLE_ARN \
                            --role-session-name $ROLE_SESSION_ARN)"
  sh "export AWS_ACCESS_KEY_ID=$(echo $temp_role | jq -r .Credentials.AccessKeyId)"
  sh "export AWS_SECRET_ACCESS_KEY=$(echo $temp_role | jq -r .Credentials.SecretAccessKey)"
  sh "export AWS_SESSION_TOKEN=$(echo $temp_role | jq -r .Credentials.SessionToken)"
  sh "aws sts get-caller-identity"
  sh "aws eks update-kubeconfig --region ${REGION} --name social-dev-eks-cluster"
  sh "kubectl config set-context --namespace=default --current"
  sh "kubectl get deployment"
  sh "kubectl set image deployment/nw-social-${DOCKER_SERVICE}-${ENV} nw-social-${DOCKER_SERVICE}-${ENV}=${DOCKER_REGISTRY}/nw-social-${DOCKER_SERVICE}:${ENV}-${BUILD_NUMBER}"
  sh "kubectl rollout status deployment.v1.apps/nw-social-${DOCKER_SERVICE}-${ENV}"
  sh "rm -r /var/lib/jenkins/.kube/config"
}
