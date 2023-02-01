def call(Map stageParams) {

    checkout([
        $class: 'GitSCM',
        branches: [[ name:"main"  stageParams.branch ]],
        userRemoteConfigs: [[ url:"https://github.com/Saurav0310/springboot_ecs.git" stageParams.url ]]
    ])
  }
