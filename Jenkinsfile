pipeline{
    agent:any

    parameters{
        string(name:"BRANCH",defaultValue:"main",description:"Branch Name")
        choice(name:"Environment",choices:['dev','prod','staging'],description:"Choose The Environment")
        booleanParam(name:"Run_Docker",defaultValue:false)
        booleanParam(name:"Run_UnitTest",defaultValue:false,description:"Want to run Unit test cases")
        booleanParam(name:"Run_IntegrationTest",defaultValue:false,description:"Want to run integration test cases")
    }
     environment {
        IMAGE_NAME = "my-spring-app"
        IMAGE_TAG = "${BUILD_NUMBER}"
     }
    stages{
        stage('checkout from GIT'){
            steps{
                echo "Pulling the resources from git"
                sh "git checkout ${params.BRANCH}"
            }
        }
        stage('test cases'){
            parallel{
                stage("Running Unit testing"){
                    when{
                        expression{params.Run_UnitTest}
                    }
                    steps{
                        sh "mvn test -Dtest=*UnitTest"
                    }
                }
                stage("Running Integration Test"){
                    steps{
                        when{
                            expression{params.RUN_IntegrationTest}
                        }
                        steps{
                            sh "mvn test -Dskip=*IntegrationTest"
                        }
                    }
                }
            }
        }
        stage('Building jar file'){
            steps{
                echo "Building project for environment  ${params.Environment}"
                sh "mvn clean install"
            }
        }
        stage('Archive Artifact') {
          steps {
            echo '📦 Archiving target/*.jar'
            archiveArtifacts artifacts: 'target/*.jar', fingerprint: true
          }
        }
        stage("Running Docker"){
            steps{
                echo "Copying jar file in home directory"
                sh "cp target/*.jar app.jar"
                sh "docker stop ${IMAGE_NAME}"
                sh "docker built -t ${IMAGE_NAME}:${IMAGE_TAG}"
                sh "docker run --name ${IMAGE_NAME} -p 8082:8082 ${IMAGE_NAME}:${IMAGE_TAG}"
            }
        }
      post {
        always {
          echo '🧹 [Post] Always runs'
        }
        success {
          echo '✅ [Post] Runs on SUCCESS only'
        }
        unstable {
          echo '⚠️ [Post] Runs if build is UNSTABLE'
        }
        changed {
          echo '🔁 [Post] Runs if build result changed from last time'
        }

      }
    }
}