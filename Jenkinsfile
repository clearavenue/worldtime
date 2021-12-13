node {
  stage ('Build') {
    withMaven {
      sh "mvn -B -e -T 1C clean compile -DskipTests"
    } 
  }
  stage('JUnit') {
    steps {
      withMaven {
        sh "mvn -B -e -T 1C test"
        junit 'target/surefire-reports/**/*.xml'
       }
     }
  }  
}