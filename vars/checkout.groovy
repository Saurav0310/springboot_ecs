def call() {
    checkout([
	                $class: 'GitSCM', 
	                branches: [[name: '*/main']],  
	                userRemoteConfigs: [[credentialsId: 'fdb7af1b-09f2-40fd-a3dc-39c69fd02ee4', url: REPO_URL]]
	                ])
}
