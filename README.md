#Football Standings App

This is a full-stack football standings app built using React for the frontend and Spring Boot for the backend. The app fetches football team standings from a public API and displays them on the UI. The project is fully Dockerized, with CI/CD pipeline setup using Jenkins.

----------------------------------------------------------------------------------------------------------------------------------------------------

#Project Structure
	frontend/: Contains the React app for displaying football standings.

	backend/: Contains the Spring Boot application that serves the football standings data.

	docker-compose.yaml: Docker Compose configuration to run the frontend and backend in separate containers.

	Jenkinsfile: Jenkins pipeline configuration to build, test, and deploy the Dockerized app.
----------------------------------------------------------------------------------------------------------------------------------------------------

#Prerequisites
Before running the project, ensure you have the following installed:

	Docker: To build and run Docker containers.

	Docker Compose: To run multi-container Docker applications.

	Jenkins: For continuous integration and deployment (optional).

	Node.js (for React): Only required for local development or custom builds.

	Java (for Spring Boot): Required for backend development.
----------------------------------------------------------------------------------------------------------------------------------------------------
#Running the Project Locally

##To run the project locally using Docker, follow these steps:

	Clone the repository:

	git clone https://github.com/tejass23-sar/football-app/
	cd football-app
	Build and run the containers using Docker Compose:
	before building docker, make sure that you change localhost with IP of Docker instance, at first it is pointing localhost
		docker-compose up --build
		
	This will:

		Build the frontend and backend Docker images.

	Start the containers in detached mode.

	Access the application:

		The frontend will be available at http://localhost:3000.

		The backend API will be available at http://localhost:8080.
		

##Steps to Run the Project Locally Without Docker:

		Prerequisites
		Make sure you have the following software installed on your local machine:

		Node.js (for the frontend React app): Install Node.js
		Java (for the backend Spring Boot app): Install Java
		Maven (for Spring Boot backend): Install Maven
		Git (to clone the repository): Install Git

		1. Clone the Repository
		    Clone the project repository to your local machine:
			git clone https://github.com/tejass23-sar/football-app/
			cd football-app
			
		2. Set Up the Backend (Spring Boot)
		   Navigate to the backend directory:

			cd backend/football-standings
			Build the backend project:

		3.If you're using Maven, run:
			mvn clean install
			This will compile the backend, run tests, and create a runnable JAR file.

		4.Run the backend application:

			To run the Spring Boot application locally, use the following command:
			mvn spring-boot:run
			
			This will start the backend server on http://localhost:8080.

		5. Set Up the Frontend (React)
					Navigate to the frontend directory:
					cd ../frontend/football-standings
					Install frontend dependencies:

					Make sure you have Node.js installed. Run the following command to install the necessary dependencies for the frontend:

					npm install
					Set the backend URL for React:

					Create an .env file in the frontend directory and add the following:

					REACT_APP_BACKEND_URL=http://localhost:8080
					Run the React app:

					After installing the dependencies, run the React development server:

					npm start
					This will start the React development server on http://localhost:3000.

		6. Access the Application
					The React frontend will be available at http://localhost:3000.

					The Spring Boot backend will be available at http://localhost:8080/swagger-ui.html.

-----------------------------------------------------------------------------------------------------------------------------------------------------
#Docker Compose Configuration

The project uses Docker Compose to manage both the frontend and backend services.

#docker-compose.yaml
update REACT_APP_BACKEND_URL with the ip of your instance on which docker is running.
In the frontend service, we define the REACT_APP_BACKEND_URL environment variable to point to the backend service using Docker's internal network.

.env File in React
To make React pick the correct backend URL, create an .env file in the frontend directory with the following content:

REACT_APP_BACKEND_URL=http://localhost:8080  # Or the appropriate backend URL
----------------------------------------------------------------------------------------------------------------------------------------------------

#Jenkins Pipeline

	Jenkinsfile :This pipeline automates the build, test, and deploy process for the Dockerized project. It uses Jenkins to:

	Build the frontend and backend Docker images.

	Run tests for both frontend (React) and backend (Spring Boot).

	Push the Docker images to a registry (e.g., Docker Hub, AWS ECR).

	Deploy the app using Docker Compose.


#How to Trigger the Jenkins Pipeline

	Manual Trigger: You can manually trigger the Jenkins pipeline from the Jenkins UI.

	Git Hook: Alternatively, configure a webhook to automatically trigger the pipeline when there are changes in your repository.

----------------------------------------------------------------------------------------------------------------------------------------------------
#Additional Notes:
	Frontend Environment: The React app relies on the REACT_APP_BACKEND_URL environment variable to point to the backend service. By default, it is set to http://localhost:8080, but it can be changed using Docker Compose or the .env file for local development.

	Backend: The backend service (Spring Boot) exposes the API on port 8080.

	Docker Compose: The Docker Compose file ensures that the React and Spring Boot containers are orchestrated and run together.


#Conclusion:

This project provides an end-to-end solution for displaying football standings, including a Dockerized setup and CI/CD pipeline using Jenkins. Follow the steps to build, test, and deploy your app with ease.

