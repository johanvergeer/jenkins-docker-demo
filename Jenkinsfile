pipeline {
    agent {
        label 'docker'
    }
    triggers {
        pollSCM('*/1 * * * *')
    }
    stages {
        stage('SonarQube analysis') {
            steps {
                withSonarQubeEnv('Sonarqube1') {
                    sh './gradlew sonarqube'
                }
            }
        }
        stage("Quality Gate") {
            steps {
                waitForQualityGate abortPipeline: true
            }
        }
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
        stage('Deploy to staging'){
            steps {
                sshPublisher(
                    publishers: [
                        sshPublisherDesc(
                            configName: 'Staging',
                            transfers: [
                                sshTransfer(
                                    cleanRemote: false,
                                    excludes: '',
                                    execCommand: 'java -jar /var/www/staging/build/libs/jenkins-docker-demo-0.0.1-SNAPSHOT.jar &',
                                    execTimeout: 120000,
                                    flatten: false,
                                    makeEmptyDirs: false,
                                    noDefaultExcludes: false,
                                    patternSeparator: '[, ]+',
                                    remoteDirectory: '',
                                    remoteDirectorySDF: false,
                                    removePrefix: '',
                                    sourceFiles: '**/*.jar')
                            ],
                            usePromotionTimestamp: false,
                            useWorkspaceInPromotion: false,
                            verbose: false
                        )
                    ]
                )
            }
        }
        stage('Postman test') {
            steps {
                sh 'newman run https://www.getpostman.com/collections/a6a6b1153b86166326ca --global-var url="http://188.166.10.76:8090" --timeout-request 30000'
            }
        }
    }
}