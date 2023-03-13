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
            body: 'Test trigger push build hook'
        }
    }
}