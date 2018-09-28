throttle(['throttleDocker']){
    node('docker') {
        stage('Setup'){
            checkout scm
        }
        stage('Compile') {
            gradlew('clean', 'build')
        }
        stage('Unit Tests') {
            gradlew('test')
        }
    }
}