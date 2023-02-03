def call(Map params) {
    checkout([
	                $class: 'GitSCM', 
	                branches: [[name: params.branch]],  
	                userRemoteConfigs: [[credentialsId: params.cred_id, url: params.scm_url]]
	                ])
}
