pipeline {
    agent {
        label 'docker'
    }
    triggers {
        pollSCM('*/5 * * * *')
    }
    stages {
        stage('Build') {
            steps {
                sh './gradlew clean build'
            }
        }
        stage('Unit Test') {
            steps {
                sh './gradlew test'
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