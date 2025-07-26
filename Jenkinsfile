pipeline {
    agent any

    parameters {
        string(name: "BRANCH", defaultValue: "main", description: "Branch Name")
        choice(name: "Environment", choices: ['dev', 'prod', 'staging'], description: "Choose The Environment")
        booleanParam(name: "Run_Docker", defaultValue: false)
        booleanParam(name: "Run_UnitTest", defaultValue: false, description: "Want to run Unit test cases")
        booleanParam(name: "Run_IntegrationTest", defaultValue: false, description: "Want to run integration test cases")
    }

    environment {
        IMAGE_NAME = "my-spring-app"
        IMAGE_TAG = "${BUILD_NUMBER}"
    }

    stages {
        stage('Checkout from GIT') {
            steps {
                echo "Pulling the resources from git"
                checkout scm
            }
        }

        stage('Test Cases') {
            parallel {
                stage("Running Unit Test") {
                    when {
                        expression { params.Run_UnitTest }
                    }
                    steps {
                        sh "mvn test -Dtest=*UnitTest"
                    }
                }

                stage("Running Integration Test") {
                    when {
                        expression { params.Run_IntegrationTest }
                    }
                    steps {
                        sh "mvn test -Dtest=*IntegrationTest"
                    }
                }
            }
        }

        stage('Build JAR File') {
            steps {
                echo "Building project for environment ${params.Environment}"
                sh "mvn clean install"
            }
        }

        stage('Archive Artifact') {
            steps {
                echo '📦 Archiving target/*.jar'
                archiveArtifacts artifacts: 'target/*.jar', fingerprint: true
            }
        }

        stage("Run Docker") {
            when {
                expression { params.Run_Docker }
            }
            steps {
                echo "Copying JAR file to home directory"
                sh "cp target/*.jar app.jar"
                sh "docker stop ${IMAGE_NAME} || true"
                sh "docker build -t ${IMAGE_NAME}:${IMAGE_TAG} ."
                sh "docker run -d --name ${IMAGE_NAME} -p 8082:8082 ${IMAGE_NAME}:${IMAGE_TAG}"
            }
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
