pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                echo 'My first Jenkinsfile'
            }
        }
    }
    post {
        always {
            mail to: 'blackparadise0407@gmail.com',
            subject: 'Build asset service',
            body: 'Test 2 trigger push build hook'
        }
    }
}