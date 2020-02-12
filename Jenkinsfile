pipeline {
    
    agent any

    stages {  
        stage ("Java version") {
            
            tools {
               jdk "Oracle JDK 8"
            }
            steps {
                def username = 'Andres'
                echo "Hola ${username}"
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
