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
        stage ("compilar") {
            
            steps {
                echo 'Compilando.'
               
                bat 'javac Example.java'
                
                echo 'Compilado.'
                bat 'java Example'
                echo 'El programa ha finalizado de forma exitosa.'
                mail bcc: '', body: 'Todo salio bien.', cc: '', from: 'andresgarcia7960@gmail.com', replyTo: '', subject: 'Result', to: 'andresgarciapacheco7@gmail.com'
            }
        }
        
                     
   }
}
