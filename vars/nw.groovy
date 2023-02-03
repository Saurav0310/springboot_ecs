def call(){
pipeline {
    stages {
        
        stage('Clean workspace') {
            steps {
                cleanWs()
            }
        }
        stage('Git checkout') {
           steps{
                git branch: '${BRANCH}', credentialsId: '${GIT_CRENDENTIAL_ID}', url: '${GIT_URL}'
                }
            }
        stage('Build Image') {
           steps{
                   sh """
                   #!/bin/bash
                   set +x
                   cd ${JOB_DIR}
                   ls
                   docker build -t ${DOCKER_REGISTRY}/nw-social-${DOCKER_SERVICE}:${ENV}-${env.BUILD_NUMBER} Dockerfile .
                   docker tag ${DOCKER_REGISTRY}/nw-social-${DOCKER_SERVICE}:${ENV}-${env.BUILD_NUMBER} ${DOCKER_REGISTRY}/nw-social-${DOCKER_SERVICE}:${ENV}-latest
                   """
                }
            }
        stage('Login to ECR  image and Push image') {
            steps {
                sh """
                   aws ecr get-login-password --region ${REGION} | docker login --usernadocker build -t ${DOCKER_REGISTRY}/nw-social-${DOCKER_SERVICE}:${ENV}-${env.BUILD_NUMBER} Dockerfile .
                   docker tag ${DOCKER_REGISTRY}/nw-social-${DOCKER_SERVICE}:${ENV}-${env.BUILD_NUMBER} ${DOCKER_REGISTRY}/nw-social-${DOCKER_SERVICE}:${ENV}-latestme AWS --password-stdin ${DOCKER_REGISTRY}
                   docker push ${DOCKER_REGISTRY}/nw-social-${DOCKER_SERVICE}:${ENV}-${env.BUILD_NUMBER}
                   docker push ${DOCKER_REGISTRY}/nw-social-${DOCKER_SERVICE}:${ENV}-latest
                   docker rmi -f ${DOCKER_REGISTRY}/nw-social-${DOCKER_SERVICE}:${ENV}-${env.BUILD_NUMBER}
                   docker rmi -f ${DOCKER_REGISTRY}/nw-social-${DOCKER_SERVICE}:${ENV}-latest
                   rm -r /var/lib/jenkins/.docker/config.json
                   """
               }
            }
        stage('assume role') {
          steps{
                sh """
                        temp_role=$(aws sts assume-role \
                            --role-arn $ROLE_ARN \
                            --role-session-name $ROLE_SESSION_ARN)
                        export AWS_ACCESS_KEY_ID=$(echo $temp_role | jq -r .Credentials.AccessKeyId)
                        export AWS_SECRET_ACCESS_KEY=$(echo $temp_role | jq -r .Credentials.SecretAccessKey)
                        export AWS_SESSION_TOKEN=$(echo $temp_role | jq -r .Credentials.SessionToken)
                        aws sts get-caller-identity
                        aws eks update-kubeconfig --region ${REGION} --name social-dev-eks-cluster
                        kubectl config set-context --namespace=default --current
                        kubectl get deployment
                        kubectl set image deployment/nw-social-${DOCKER_SERVICE}-${ENV} nw-social-${DOCKER_SERVICE}-${ENV}=${DOCKER_REGISTRY}/nw-social-${DOCKER_SERVICE}:${ENV}-${BUILD_NUMBER}
                        kubectl rollout status deployment.v1.apps/nw-social-${DOCKER_SERVICE}-${ENV}
                        rm -r /var/lib/jenkins/.kube/config
                    """
                }
            }
                                

        stage('kubernetes deploy') {
           steps{
                sh """
                kubectl config set-context --namespace=default --current
                kubectl set image deployment/nw-social-${DOCKER_SERVICE}-${ENV} nw-social-${DOCKER_SERVICE}-${ENV}=${DOCKER_REGISTRY}/nw-social-${DOCKER_SERVICE}:${ENV}-${env.BUILD_NUMBER}'
                kubectl rollout status deployment.v1.apps/nw-social-${DOCKER_SERVICE}-${ENV}
                """
            }
        } 
    }
        /*post {
        always 
        {   
            googlechatnotification message: 'Build_check of job ${JOB_NAME} - ${BUILD_NUMBER} with ${BUILD_URL} is ${BUILD_STATUS}', notifyAborted: true, notifyBackToNormal: true, notifyFailure: true, notifyNotBuilt: true, notifySuccess: true, notifyUnstable: true, suppressInfoLoggers: true, url: 'https://chat.googleapis.com/v1/spaces/AAAAMtiDwVM/messages?key=AIzaSyDdI0hCZtE6vySjMm-WEfRq3CPzqKqqsHI&token=KCqrVb7JZqQwG3l3MuJbaVxUoo6HW5_SxlFFfVtyzjs%3D'
        }
            }*/
    }
}
