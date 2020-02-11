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
                bat 'javac TextPrompt.java'
                bat 'javac VistaCompilador.java'
                
                echo 'Compilado.'
                bat 'java VistaCompilador'
                echo 'El programa ha finalizado de forma exitosa.'
            }
        }
                     
   }
}
