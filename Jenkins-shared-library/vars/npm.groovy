def call(){

pipeline {
    stages {
        
        stage('npm install') { 
            steps {
                sh 'npm install' 
            }
        }

        stage('npm run test') { 
            steps {
                sh 'npm run test' 
            }
        }

        stage('npm Publish') {
            steps {
                sh 'npm publish'
            }
        }

        stage('Clean workspace'){
            steps{
                echo "Cleaning up ${WORKSPACE}"
                // clean up our workspace
                deleteDir()
                // clean up tmp directory
                dir("${workspace}@tmp") {
                deleteDir()          
                }
            }
        }
    }
}
}
