pipeline {
    def username = 'Andres'
    agent any

    stages {  
        stage ("Java version") {
            step{
                echo "Hola ${username}"
            }
            tools {
               jdk "Oracle JDK 8"
            }
            steps {
                bat 'java -version'
            }
        }
        stage ("compilar") {
            
            steps {
                echo 'Compilando.'
               
                bat 'javac Grafos.java'
                
                echo 'Compilado.'
                
                
            }
        }
        stage ("Run"){
            steps{
                echo 'Corriendo Grafo.java.'
                bat 'java Grafos'
                echo 'Programa finalizado.'
            }
        }
                     
   }
}
