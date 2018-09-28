# Jenkins Build steps
1. Run tests simultaneous
    1. Unit tests
    1. Static code analysis
1. Build jar artifact. Use version from build.gradle
1. Push jar artifact to Artifactory
1. Build Docker image using latest jar artifact. This can be snapshot or release
1. Push Docker image to Docker registry
1. Run tests on Docker image
    1. Integration tests
1. 