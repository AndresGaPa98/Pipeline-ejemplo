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
     
     post{
      failure{
       mail bcc: '', body: 'Hubo un error.', cc: '', from: 'andresgarcia7960@gmail.com', replyTo: '', subject: 'Resultado del programa', to: 'andresgarciapacheco7@gmail.com'
       }
      success{
       mail bcc: '', body: 'El programa ha sido compilado y ejecutado de manera exitosa', cc: '', from: 'andresgarcia7960@gmail.com', replyTo: '', subject: 'Resultado del programa', to: 'andresgarciapacheco7@gmail.com'
      }
     }
                     
   }
}
