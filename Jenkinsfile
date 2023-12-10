pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                // Check out the code from your version control system (e.g., Git)
                checkout scm
            }
        }

        stage('Build') {
            steps {
                // Use the Gradle Wrapper to ensure a specific Gradle version is used
                bat './gradlew clean build'
            }
        }
    }

    post {
        success {
            // This block will be executed if the build is successful
            echo 'Build successful! Deploy or perform additional steps here.'
        }

        failure {
            // This block will be executed if the build fails
            echo 'Build failed! Take necessary actions for failure.'
        }
    }
}
