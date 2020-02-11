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
            
            steps {
                echo 'Compilando.'
                bat 'java clean compile'
                echo 'Compilado.'
            }
        }
                     
   }
}
