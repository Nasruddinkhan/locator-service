// Declarative //
pipeline {
  agent any
  environment {
    EMAIL_RECIPIENTS = 'nasruddinkhan44@gmaiil.com'
  }
  stages {
    stage('Clean') {
      steps {
        echo 'Clean..'
        bat "mvn clean"

      }
    }
    stage('Test') {
      steps {
        echo 'Testing..'
        bat "mvn test"
      }
    }
    stage('package stage') {
      steps {
        echo 'Deploying....'
        bat "mvn package"
      }
    }
    stage('result') {
      steps {
        input('Do you want capture result....?')
        junit "target/surefire-reports/**/*.xml"
        archive 'target/*.jar'
      }
    }
    stage('Code Quality') {
      steps {
        echo "Code Quality"
        withSonarQubeEnv("SonarQube") {
          bat "mvn sonar:sonar"
        }
      }
    }

  }
}