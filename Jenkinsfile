pipeline {
    agent {
        label 'docker'
    }
    triggers {
        pollSCM('*/5 * * * *')
    }
    stages {
        stage('Compile') {
            steps {
                sh './gradlew clean build'
            }
        }
        stage('Unit Test') {
            steps {
                sh './gradlew test'
            }
        }
    }
}