pipeline{
    agent any
    tools{
        maven "maven"

    }
    stages{
        stage("Build JAR File"){
            steps{
                checkout scmGit(branches: [[name: '*/main']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/CristobalTorresU/Tingeso-Evaluacion-1']])
                dir("AutoFixPlatform"){
                    sh "mvn clean install"
                }
            }
        }
        stage("Test"){
            steps{
                dir("AutoFixPlatform"){
                    sh "mvn test"
                }
            }
        }        
        stage("Build and Push Docker Image"){
            steps{
                dir("AutoFixPlatform"){
                    script{
                         withDockerRegistry(credentialsId: 'docker-credentials'){
                            sh "docker build -t dilget/autofix-backend ."
                            sh "docker push dilget/autofix-backend"
                        }
                    }                    
                }
            }
        }
    }
}