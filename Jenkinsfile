throttle(['throttleDocker']){
    node('docker') {
        stage('Setup'){
            checkout scm
        }
        stage('Compile') {
            gradle('clean', 'build')
        }
        stage('Unit Tests') {
            gradle('test')
        }
    }
}