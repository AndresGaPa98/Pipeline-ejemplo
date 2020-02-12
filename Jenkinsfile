 def username = 'Andres'
pipeline {
    
    agent any

    stages {  
        stage ("Java version") {
            
            tools {
               jdk "Oracle JDK 8"
            }
            steps {
               
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
     stage("Enviando Resultados"){
           steps{
            emailtext (
                         subject: "'${username}'. Resultado del programa",
                         body: "El programa se ha compilado y ejecutado de manera exitosa",
                         to: "andresgarciapacheco7@gmail.com",
                         from: "andresgarcia7960@gmail.com"
)
           }
     }
                     
   }
}
