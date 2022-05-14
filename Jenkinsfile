// Declarative //
pipeline {
  agent any
  environment {
    EMAIL_RECIPIENTS = 'nasruddinkhan44@gmaiil.com'
  }
  parameters{
      booleanParam(name: 'SKIP_TESTS', defaultValue: false, description: 'Do you want to skip the test')
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
        bat "mvn package -Dskip.surefire.tests -Dmaven.test.skip=${SKIP_TESTS} -Dbuild.number=${BUILD_NUMBER} -Popenshift"
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
         echo "Code Quality SonarQube"
          bat "mvn sonar:sonar -Dsonar.login=f547ea1989c34b5b223573728a349730d78e40af  -Dsonar.coverage.jacoco.xmlReportPaths=target/surefire-reports/**/*.xml"
        }
      }
    }
  stage('Docker Build') {
      agent any
      steps {
        bat 'mvn help:evaluate -Dexpression=jkube.generator.name -q -DforceStdout -Ddocker.registry=https://hub.docker.com -Djkube.namespace=nasruddinkhan786-dev -Dbuild.number=${BUILD_NUMBER}'
        //bat 'docker build -t nasruddin/locator-service:latest .'
      }
    }
  }
}