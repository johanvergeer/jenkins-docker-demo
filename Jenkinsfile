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
        stage('Deploy') {
            steps {
                sh ‘ssh johan@188.166.10.76 rm -rf /var/www/staging/dist/’
                sh 'ssh johan@188.166.10.76 mkdir -p /var/www/staging'
                sh 'scp build/libs/**/*.jar johan@188.166.10.76:/var/www/staging/dist/'
            }
        }
    }
}