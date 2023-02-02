def call(var1,var2,folder){
pipeline {
    agent any
    stages {
        stage('Git checkout') {
           steps{
                git branch: 'qa_release', credentialsId: '', url: 'git@github.com:Saurav0310/springboot_ecs.git'
            }
        }
        stage('build image') {
           steps{
               script {
                   sh script:'''
                   #!/bin/bash
                   set +x
                   cd ${folder}/
                   ls
                   docker build -t 991037556739.dkr.ecr.us-east-1.amazonaws.com/nw-social-${var1}:${var2}-${BUILD_NUMBER} Dockerfile .
                   docker tag 991037556739.dkr.ecr.us-east-1.amazonaws.com/nw-social-${var1}:${var2}-${BUILD_NUMBER} 743930152640.dkr.ecr.us-east-1.amazonaws.com/nw-social-${var1}:${var2}-latest
                   aws ecr get-login-password --region us-east-1 | docker login --username AWS --password-stdin 991037556739.dkr.ecr.us-east-1.amazonaws.com
                   docker push 991037556739.dkr.ecr.us-east-1.amazonaws.com/nw-social-${var1}:${var2}-${BUILD_NUMBER}
                   docker push 991037556739.dkr.ecr.us-east-1.amazonaws.com/nw-social-${var1}:${var2}-latest
                   docker rmi -f 991037556739.dkr.ecr.us-east-1.amazonaws.com/nw-social-${var1}:${var2}-${BUILD_NUMBER}
                   docker rmi -f 991037556739.dkr.ecr.us-east-1.amazonaws.com/nw-social-${var1}:${var2}-latest
                   '''
               }
            }
        }

    //     stage('kubernetes deploy') {
    //        steps{
    //             sh 'kubectl config set-context --namespace=default --current'
    //             sh 'kubectl set image deployment/nw-social-${var1}-${var2} nw-social-${var1}-${var2}=743930152640.dkr.ecr.us-east-1.amazonaws.com/nw-social-${var1}:${var2}-${BUILD_NUMBER}'
    //             sh 'kubectl rollout status deployment.v1.apps/nw-social-${var1}-${var2}'
    //         }
    //     }
    }
 }
}
