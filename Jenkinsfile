pipeline {
    agent any

    stages {  
        stage ("Java version") {
            tools {
               jdk "Oracle JDK 8"
            }
            steps {
                bat 'java -version'
            }
        }
        stage ("compile") {
            tools {
               jdk "Oracle JDK 8"
            }
            steps {
                bat 'javav Example.java'
            }
        }
                     
   }
}
