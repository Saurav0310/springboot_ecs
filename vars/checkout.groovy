def call(Map stageParams) {

    pipeline {
    agent any
    stages('Clean workspace'){
        stage{
                echo "Cleaning up ${WORKSPACE}"
                // clean up our workspace
                deleteDir()
                // clean up tmp directory
                dir("${workspace}@tmp") {
                deleteDir()          
                }
            }
        }
        stage('Git checkout') {
           steps{
                git branch: 'main', credentialsId: '', url: 'git@github.com:Saurav0310/springboot_ecs.git'
                sh 'ls post/'
                sh 'pwd'
            }
        }
    }
}
