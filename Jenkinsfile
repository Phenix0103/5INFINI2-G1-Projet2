pipeline {
     environment { 

        registry = "ceceyphoenix/projetdevops" 

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
                url: 'https://github.com/azizjaziri48/5INFINI2-G1-Projet2.git'
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
         stage('Docker PUSH image') {
            steps {
                //sh 'docker login -u lindabelhadj -p dock@hello123!!Lin'
                //sh 'docker push lindabelhadj/kaddem:1-0'
                echo "Deploying the image..."
                sh 'docker login -u ceceyphoenix -p Princesseflora1 docker.io'
                sh 'docker push ${DOCKER_REGISTRY}/${DOCKER_IMAGE_NAME}:${DOCKER_IMAGE_TAG} '
            }
        }
        stage('docker-compose') {
            steps {
                sh 'docker compose up -d'
            }
        }
         /* stage('Grafana') {
            steps {
                sh 'docker compose up -d'
            }
        }*/
         
    }
}
