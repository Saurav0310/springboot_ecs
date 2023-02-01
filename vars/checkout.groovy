def call(Map stageParams) {

    checkout([
        branches: [[ name:"main"  stageParams.branch ]],
        userRemoteConfigs: [[ url:"https://github.com/Saurav0310/springboot_ecs.git" stageParams.url ]]
    ])
  }
