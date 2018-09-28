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
                gradlew('clean', 'build')
            }
        }
    }
}