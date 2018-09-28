throttle(['throttleDocker']){
    node('docker') {
        stage('Setup'){
            checkout scm
        }
        stage('Compile') {
            steps {
                gradle {
                    tasks('clean')
                    tasks('build')
                }
            }
        }
        stage('Unit Tests') {
            gradle{
                tasks('test')
            }
        }
    }
}