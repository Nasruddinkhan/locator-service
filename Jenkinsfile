// Declarative //
pipeline {
    agent any
    parameters {
        string(name: 'HOST', defaultValue: 'https://console-openshift-console.apps.sandbox-m2.ll9k.p1.openshiftapps.com')
        text(name: 'BIOGRAPHY', defaultValue: '', description: 'Enter some information about the person')
        booleanParam(name: 'SKIP_TESTS', defaultValue: false, description: 'Do you want to skip the test')
        choice(name: 'NAMESPACE', choices: ['nasruddinkhan786-dev'], description:'openshift namespace')
        choice(name: 'DOCKER_REGISTRY', choices:['https://hub.docker.com'], description: 'docker registry')
        choice(name: 'SONAR', choices:['http://localhost:9000'], description: 'SonarQube URLS')
        string(name: 'SONAR_TOKEN', defaultValue: 'f547ea1989c34b5b223573728a349730d78e40af')
    }
    options {
        skipStagesAfterUnstable()
        disableConcurrentBuilds()
    }
    stages {
        stage('Clean') {
            steps {
                echo "Clean"
                bat "mvn clean post-clean -Dbuild.number=${BUILD_NUMBER}"
            }
        }
        stage('Unit Test') {
            steps {
                echo 'Testing..'
                bat "mvn test -Dmaven.test.skip=${SKIP_TESTS} -Dbuild.number=${BUILD_NUMBER} -Popenshift"
            }
         }
        stage('Package stage') {
            steps {
                echo 'Package....'
                bat "mvn package -Dskip.surefire.tests -Dmaven.test.skip=${params.SKIP_TESTS} -Dbuild.number=${BUILD_NUMBER} -Popenshift"
            }
        }
        stage('SonarQube') {
            steps {
                echo "Code Quality"
                withSonarQubeEnv("SonarQube") {
                    echo "Code Quality SonarQube"
                    script {
                        if(params.SKIP_TESTS){
                            echo "Test case are skip $SKIP_TESTS, so not showing the changes from sonar"
                        }else{
                            bat "mvn sonar:sonar -Dsonar.host.url=${SONAR} -Dbuild.number=${BUILD_NUMBER} -Dsonar.login=${SONAR_TOKEN} -Popenshift"
                        }
                    }
                }
            }
        }
        stage('Docker Build') {
            agent any
            steps {
                echo 'docker image cmd ${DOCKER_REGISTRY}'
                bat 'mvn help:evaluate -Dexpression=jkube.generator.name -q -DforceStdout -Ddocker.registry=${DOCKER_REGISTRY} -Djkube.namespace=${NAMESPACE} -Dbuild.number=${BUILD_NUMBER}'
                script{
                    imageName = bat(script: 'mvn help:evaluate -Dexpression=jkube.generator.name -q -DforceStdout -Ddocker.registry=${DOCKER_REGISTRY} -Djkube.namespace=${NAMESPACE} -Dbuild.number=${BUILD_NUMBER}', returnStdout: true)
                }
//                 def imageName = bat script: 'mvn help:evaluate -Dexpression=jkube.generator.name -q -DforceStdout -Ddocker.registry=${DOCKER_REGISTRY} -Djkube.namespace=${NAMESPACE} -Dbuild.number=${BUILD_NUMBER}', returnStdout: true
//                 echo 'docker image cmd ${imageName}'
//                 bat 'docker build -t ${imageName} .'
            // bat 'docker build -t nasruddin/locator-service:latest .'
                echo '${imageName}'
                bat 'docker build -t ${imageName} .'
            }
        }
    }
}
// pipeline {
//   agent any
//   tools{
//     maven: 'Maven'
//   }
//   parameters{
//      string(name: 'HOST', defaultValue: 'https://console-openshift-console.apps.sandbox-m2.ll9k.p1.openshiftapps.com',
//      description: 'Ingress host')
//      choice(name: 'NAMESPACE', choice: ['nasruddinkhan786-dev'], description:'openshift namespace')
//      choice(name: 'DOCKER_REGISTRY', choice:['https://hub.docker.com/', description: 'docker registry'])
//      booleanParam(name: 'SKIP_TESTS', defaultValue: false, description: 'Do you want to skip the test')
//      choice(name: 'SONAR', choice:['http://localhost:9000'], description: 'SonarQube URLS')
//      string(name: 'SONAR_TOKEN', defaultValue: 'f547ea1989c34b5b223573728a349730d78e40af', description='SONAR TOKEN')
//   }
//   environment {
//     EMAIL_RECIPIENTS = 'nasruddinkhan44@gmaiil.com'
//   }
//   options {
//     skipStagesAfterUnstable()
//     disableConcurrentBuilds()
//   }
//   stages {
//     stage('Clean') {
//       steps {
//         echo 'Clean..'
//         bat "mvn clean post-clean -Dbuild.number=${BUILD_NUMBER}"
//       }
//     }
//     stage('Unit Test') {
//       steps {
//         echo 'Testing..'
//         bat "mvn test -Dmaven.test.skip=${SKIP_TESTS} -Dbuild.number=${BUILD_NUMBER} -Popenshift"
//
//       }
//     }
//     stage('Package stage') {
//       steps {
//         echo 'Deploying....'
//         bat "mvn package -Dskip.surefire.tests -Dmaven.test.skip=${SKIP_TESTS} -Dbuild.number=${BUILD_NUMBER} -Popenshift"
//       }
//     }
// //     stage('result') {
// //       steps {
// //         input('Do you want capture result....?')
// //         junit "target/surefire-reports/**/*.xml"
// //         archive 'target/*.jar'
// //       }
// //     }
//     stage('SonarQube') {
//       steps {
//         echo "Code Quality"
//         withSonarQubeEnv("SonarQube") {
//          echo "Code Quality SonarQube"
//          if(params.SKIP_TESTS){
//             echo "Test case are skip $SKIP_TESTS, so not showing the changes from sonar"
//          }else{
//             bat "mvn sonar:sonar -Dsonar.host.url=${SONAR} -Dbuild.number=${BUILD_NUMBER} -Dsonar.login=${SONAR_TOKEN} -Popenshift"
//          }
//         }
//       }
//     }
//   stage('Docker Build') {
//       agent any
//       steps {
//         def imageName = bat script: 'mvn help:evaluate -Dexpression=jkube.generator.name -q -DforceStdout -Ddocker.registry=${DOCKER_REGISTRY} -Djkube.namespace=${NAMESPACE} -Dbuild.number=${BUILD_NUMBER}', returnStdout: true
//         echo 'docker image cmd ${imageName}'
//         bat 'docker build -t ${imageName} .'
//        // bat 'docker build -t nasruddin/locator-service:latest .'
//       }
//     }
//   }
// }