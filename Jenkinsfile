pipeline {
    agent {
        label 'docker'
    }
    triggers {
        pollSCM('*/5 * * * *')
    }
    stages {
        stage('Build & Unit test') {
            steps {
                sh './gradlew clean build'
            }
            post {
                always {
                    junit '**/test-results/test/**.xml'
                }
            }
        }
        stage('SonarQube analysis') {
            steps {
                sh './gradlew sonarqube'
            }
        }
    }
}