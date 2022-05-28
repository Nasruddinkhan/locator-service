// Declarative //
pipeline {
  agent any
  environment {
    EMAIL_RECIPIENTS = 'nasruddinkhan44@gmaiil.com'
  }
  parameters{
      booleanParam(name: 'SKIP_TESTS', defaultValue: false, description: 'Do you want to skip the test')
      string(name: 'SONAR', defaultValue:'http://localhost:9000', description: 'SonarQube URLS')
      string(name: 'SONAR_TOKEN', defaultValue: 'f547ea1989c34b5b223573728a349730d78e40af',description: 'Do you want to skip the test')
    }
  stages {
    stage('Clean') {
      steps {
        echo 'Clean..'
        bat "mvn clean post-clean -Dbuild.number=${BUILD_NUMBER}"
      }
    }
    stage('Test') {
      steps {
        echo 'Testing..'
        bat "mvn test -Dmaven.test.skip=${SKIP_TESTS} -Dbuild.number=${BUILD_NUMBER} -Popenshift"
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
               if(params.SKIP_TESTS){
                  echo "Test case are skip $SKIP_TESTS, so not showing the changes from sonar"
               }else{
               withSonarQubeEnv("SonarQube") {
                  bat "mvn sonar:sonar -Dsonar.host.url=${SONAR} -Dbuild.number=${BUILD_NUMBER} -Dsonar.login=${SONAR_TOKEN} -Popenshift"
                }
               }
              }
    }
  stage('Docker Build') {
      agent any
      steps {
        bat 'mvn help:evaluate -Dexpression=jkube.generator.name -q -DforceStdout -Ddocker.registry=https://hub.docker.com -Djkube.namespace=nasruddinkhan -Dbuild.number=${BUILD_NUMBER}'
        //bat 'docker build -t nasruddin/locator-service:latest .'
      }
    }
  }
}