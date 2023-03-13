pipeline {
    agent {
        docker {
            image 'maven:3.9.0-eclipse-temurin-17-alpine'
            args '-v $HOME/.m2:/root/.m2 --network jenkins'
            reuseNode true
        }
    }

    stages {
        stage('Build') {
            steps {
                sh 'mvn -version'
                sh 'java -version'
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