def call(Map stageParams) {

    pipeline {
    agent any
    stages {
        stage('Clean workspace') {
            steps {
                cleanWs()
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
}
