pipeline {
     environment { 

        registry = "ceceyphoenix/cyrine" 

        registryCredential = 'ceceyphoenix' 

        dockerImage = '' 

    }
    agent any
    tools {
        maven 'M2_HOME'
    }
    stages {
        stage('GIT') {
            steps {
                git branch: 'cyrine-chouchane',
                url: 'https://github.com/Phenix0103/5INFINI2-G1-Projet2.git'
            }
        }
        stage('MVN CLEAN') {
            steps {
                sh 'mvn clean';
            }
        }
        stage('MVN COMPILE') {
            steps {
                sh 'mvn compile';
            }
        }
        stage('MVN SONARQUBE') {
            steps {
                sh 'mvn sonar:sonar -Dsonar.login=admin -Dsonar.password=cyrine -Dmaven.test.skip=true';
            }
        }
       stage('MOCKITO'){
            steps {
                 sh 'mvn test';
            }
        }
         stage('NEXUS'){
            steps {
                 sh 'mvn deploy';
            }
        }
   stage('Building image') { 
            steps { 
                script { 
                    dockerImage = docker.build registry + ":$BUILD_NUMBER" 
                }
            } 
        }
         stage('Deploy image') { 
            steps { 
                script { 
                    try {
                        docker.withRegistry( '', registryCredential ) { 
                            dockerImage.push() 
                        }
                    } catch (err) {
                        echo "Erreur lors du déploiement de l'image Docker : ${err}"
                        currentBuild.result = 'FAILURE'
                        error "Échec du déploiement de l'image Docker"
                        mail(
                            to: 'votre@email.com',
                            subject: 'Erreur dans le pipeline Jenkins',
                            body: "Une erreur s'est produite dans le pipeline Jenkins.\nDétails de l'erreur : ${err}"
                        )
                    }
                } 
            }
        } 
        stage('docker-compose') {
            steps {
                sh 'docker compose up -d'
            }
        }
          stage('Grafana') {
            steps {
                sh 'docker compose up -d'
            }
        }
         
    }
}
