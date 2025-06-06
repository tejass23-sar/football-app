pipeline {
  agent any

  environment {
    FRONTEND_DIR = 'frontend/football-standings'
    BACKEND_DIR = 'backend/football-standings'
  }


  stages {
    stage('Checkout') {
      steps {
        git branch: 'main', url: 'https://github.com/tejass23-sar/football-app.git'
      }
    }

    stage('Build Frontend') {
      steps {
        dir("${env.FRONTEND_DIR}") {
          sh 'npm install'
          sh 'npm run build'
        }
      }
    }
    
    stage('Build Backend') {
      steps {
        dir("${env.BACKEND_DIR}") {
          sh 'mvn clean install '
        }
      }
    }
    stage('Run Docker Compose down before deployment') {
      steps {
        script {
          // Stop the existing docker comose instances 
          sh 'docker-compose  down'
        }
      }
    }
    stage('Run Docker Compose Up') {
      steps {
        script {
          // Start the containers in the background using docker-compose up
          sh 'docker-compose  up -d'
        }
      }
    }
    

  }

  post {
    success {
      echo '✅ CI Build Success!'
    }
    failure {
      echo '❌ CI Build Failed!'
    }
  }
}
