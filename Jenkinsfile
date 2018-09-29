pipeline {
    agent {
        label 'docker'
    }
    triggers {
        pollSCM('*/1 * * * *')
    }
    stages {
        stage('Unit test & Build') {
            steps {
                sh './gradlew clean build'
            }
            post {
                always {
                    junit '**/test-results/test/**.xml'
                    archiveArtifacts artifacts: 'build/libs/**/*.jar', fingerprint: true
                }
            }
        }
        stage('SonarQube analysis') {
            steps {
                sh './gradlew sonarqube'
            }
        }
        stage('Postman test') {
            steps {
                sh 'newman run https://www.getpostman.com/collections/a6a6b1153b86166326ca --global-var url="http://188.166.10.76:8090" --timeout-request 30000'
            }
        }
    }
}