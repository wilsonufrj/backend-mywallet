pipeline {
    agent any // Assumes Docker, JDK, and Maven are available on the agent

    environment {
        DOCKER_REGISTRY_URL = 'https://registry.hub.docker.com' // Explicitly set to empty for Docker Hub
        DOCKER_IMAGE_NAME = 'waramos/mywallet' // Updated for Docker Hub username/repository
        APP_VERSION = "1.0.0" // Default version, can be dynamic
        DOCKER_CREDENTIALS_ID = 'doc-hubs-cred' // Jenkins credentials ID for Docker registry
    }

    tools {
        maven 'MAVEN_HOME' // Specify your Maven tool name configured in Jenkins Global Tool Configuration, or remove if mvn is in PATH
    }

    stages {
        stage('Checkout SCM') {
            steps {
                checkout scm
            }
        }

        stage('Determine App Version') {
            steps {
                script {
                    // Attempt to read version from pom.xml, fallback to default
                    try {
                        def pom = readMavenPom file: 'pom.xml'
                        env.APP_VERSION = pom.version
                        echo "Application version from pom.xml: ${env.APP_VERSION}"
                    } catch (Exception e) {
                        echo "Could not read version from pom.xml, using default: ${env.APP_VERSION}. Error: ${e.getMessage()}"
                    }
                    // Sanitize APP_VERSION for Docker tagging (replace '+' with '-')
                    env.APP_VERSION = env.APP_VERSION.replace('+', '-')
                }
            }
        }

        stage('Build Application') {
            steps {
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
                    // Image name for local build, Docker Hub format (username/imagename:tag)
                    def imageNameWithTag = "${env.DOCKER_IMAGE_NAME}:${env.APP_VERSION}"
                    echo "Building Docker image: ${imageNameWithTag}"
                    sh "docker build -t ${imageNameWithTag} ."
                }
            }
        }

        stage('Push Docker Image') {
            // Updated when condition: DOCKER_CREDENTIALS_ID must be set.
            // DOCKER_REGISTRY_URL is now always empty for Docker Hub.
            when {
                expression { env.DOCKER_CREDENTIALS_ID != '' }
            }
            steps {
                script {
                    // Image name for push, Docker Hub format (username/imagename:tag)
                    def imageNameWithTag = "${env.DOCKER_IMAGE_NAME}:${env.APP_VERSION}"
                    echo "Pushing Docker image to Docker Hub: ${imageNameWithTag}"
                    withCredentials([string(credentialsId: env.DOCKER_CREDENTIALS_ID, variable: 'DOCKER_REGISTRY_PASSWORD')]) {
                        // Docker login for Docker Hub
                        sh "echo $DOCKER_REGISTRY_PASSWORD | docker login -u waramos --password-stdin"
                        sh "docker push ${imageNameWithTag}"
                    }
                }
            }
        }

        stage('Image Pushed') { // Renamed and simplified stage
            // Removed Kubernetes 'when' condition
            steps {
                echo "Docker image ${env.DOCKER_IMAGE_NAME}:${env.APP_VERSION} pushed to Docker Hub."
                echo "Pipeline finished. Image is ready for use."
            }
        }
    }

    post {
        always {
            echo 'Pipeline finished.'
            // Example: cleanWs() // Cleans up the workspace
        }
    }
}