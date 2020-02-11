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
                bat 'java Example compile'
                echo 'Compilado.'
            }
        }
                     
   }
}
