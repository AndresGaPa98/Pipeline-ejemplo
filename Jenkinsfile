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
                bat 'javac Example.java'
                echo 'Compilado.'
                bat 'java Example'
            }
        }
                     
   }
}
