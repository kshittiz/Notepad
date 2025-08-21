pipeline {
    agent any

    tools {
        maven 'Maven 3.9.11'  // Replace with your Maven installation name in Jenkins
        jdk 'JDK 21'      // Replace with your JDK installation name in Jenkins
    }

    stages {
        stage('Build') {
            steps {
                sh 'mvn clean install'
            }
        }
    }
}
