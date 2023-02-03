#!/usr/bin/env groovy

/**
 * Send notifications based on build status string
 */
def call(String buildStatus = 'STARTED') {
  // build status of null means successful
  buildStatus = buildStatus ?: 'SUCCESS'

  // Default values
  def colorName = 'RED'
  def colorCode = '#BD3855'
  def subject = "${buildStatus}: Job '${JOB_NAME} [${BUILD_NUMBER}]'"
  def summary = "${subject} (${BUILD_URL})"
  def details = """<p>${buildStatus}: Job '${JOB_NAME} [${BUILD_NUMBER}]':</p>
    <p>Check console output at &QUOT;<a href='${BUILD_URL}'>${JOB_NAME} [${BUILD_NUMBER}]</a>&QUOT;</p>"""

  // Override default values based on build status
  if ( buildResult == "SUCCESS" ) {
    slackSend color: "good", message: "Job: ${JOB_NAME} with buildnumber ${BUILD_NUMBER} was successful"
  }
  else if( buildResult == "FAILURE" ) { 
    slackSend color: "danger", message: "Job: ${JOB_NAME} with buildnumber ${BUILD_NUMBER} was failed"
  }
  else if( buildResult == "UNSTABLE" ) { 
    slackSend color: "warning", message: "Job: ${JOB_NAME} with buildnumber ${BUILD_NUMBER} was unstable"
  }
  else {
    slackSend color: "danger", message: "Job: ${JOB_NAME} with buildnumber ${BUILD_NUMBER} its resulat was unclear"	
  }
}
