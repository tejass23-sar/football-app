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
          sh 'mvn clean install'
        }
      }
    }

    stage('Unit Tests') {
      steps {
        dir("${env.BACKEND_DIR}") {
          sh 'echo "mvn test" '
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
