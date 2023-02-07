def call() {
  echo "Building docker image"
                   sh script:'''
                   #!/bin/bash
                   set +x
                   pwd
                   ls
                   cd post
                   ls
                   docker build -t ${APP_NAME}:${BUILD_NUMBER} .
                   '''
}
