node {

    stage('java 8') {
         env.JAVA_HOME="${tool 'jdk8'}"
         env.PATH="${env.JAVA_HOME}/bin:${env.PATH}"
         sh 'java -version'
    }

    stage('Checkout code') {
        checkout scm
    }

    stage('Test') {
        try {
            sh "./gradlew clean build"
        } finally {
            junit '**/build/test-results/**/*.xml'
            archiveArtifacts '**/build/cucumber/**/*.*'
        }
    }

}