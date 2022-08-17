//일단 lecture-domain 부분을 빌드해보자(2022-08-16)
pipeline {
    agent {
//        any
//        tools {
//          maven 'MavenTest'
//        }
        docker {
            image 'maven:3.8.1-adoptopenjdk-11' 
            args '-v /root/.m2:/root/.m2' 
            reuseNode true
        }
    }
    stages {
        stage('Build') { 
            steps {
                echo "(myinno)maven *********************************"
                sh 'mvn -f lecture-domain/pom.xml  clean package -Dskiptests=true'

                echo "(myinno)Docker Build **************************"
                sh """
                  docker build -t lecture-domain-docker  lecture-domain/Dockerfile
                """
            }
        }
    }
} 