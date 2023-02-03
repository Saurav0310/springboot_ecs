
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
    <p>Check console output at &QUOT;<a href='${env.BUILD_URL}'>${JOB_NAME} [${BUILD_NUMBER}]</a>&QUOT;</p>"""

  // Override default values based on build status
  if (buildStatus == 'STARTED') {
    color = 'YELLOW'
    colorCode = '#DAAD4F'
  } else if (buildStatus == 'SUCCESS') {
    color = 'GREEN'
    colorCode = '#5CB589'
  } else {
    color = 'RED'
    colorCode = '#BD3855'
  }

  // Send notifications
//   slackSend (color: colorCode, message: summary)

  emailext (
      to: 'thakursaurav339@gmail.com',
      subject: subject,
      body: details,
      recipientProviders: [[$class: 'DevelopersRecipientProvider']]
    )

}
