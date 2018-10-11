pipeline {
    agent {
        label 'docker'
    }
    triggers {
        pollSCM('*/1 * * * *')
    }
    options {
        throttle(categories: ['throttleDocker'])
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
        stage('Deploy to test'){
            steps {
                sshPublisher(
                    publishers: [
                        sshPublisherDesc(
                            configName: 'Test',
                            transfers: [
                                sshTransfer(
                                    cleanRemote: false,
                                    excludes: '',
                                    execCommand: '/var/www/test/start.sh',
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
                timeout(5) {
                    waitUntil {
                       script {
                         def r = sh script: 'wget -q http://188.166.10.76:8100 -O /dev/null', returnStatus: true
                         return (r == 0);
                       }
                    }
                }
            }
        }
        stage('Postman test') {
            steps {
                sh 'newman run https://www.getpostman.com/collections/a6a6b1153b86166326ca --global-var url="http://188.166.10.76:8100" --timeout-request 30000'
            }
        }
        stage('Send slack message') {
            steps {
                slackSend("The application is ready to be deployed to acceptance. Please perform tests and click Yes or Abort in the Jenkins pipeline.")
            }
        }
        stage('Approve deploy to acceptance'){
            input {
                message "Deploy to acct?"
                ok "Yes"
            }
            steps {
                sshPublisher(
                    publishers: [
                        sshPublisherDesc(
                            configName: 'Acceptance',
                            transfers: [
                                sshTransfer(
                                    cleanRemote: false,
                                    excludes: '',
                                    execCommand: '/var/www/acceptance/start.sh',
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
    }
}