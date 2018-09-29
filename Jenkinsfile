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
        stage('build && SonarQube analysis') {
            steps {
                withSonarQubeEnv('jenkins-docker-demo') {
                    sh './gradlew --info sonarqube'
                    // Optionally use a Maven environment you've configured already
                    //withMaven(maven:'Maven 3.5') {
                    //    sh 'mvn clean package sonar:sonar'
                    //}
                }
            }
        }
        stage("Quality Gate") {
            steps {
                timeout(time: 1, unit: 'HOURS') {
                    // Parameter indicates whether to set pipeline to UNSTABLE if Quality Gate fails
                    // true = set pipeline to UNSTABLE, false = don't
                    // Requires SonarQube Scanner for Jenkins 2.7+
                    waitForQualityGate abortPipeline: true
                }
            }
        }
    }
}