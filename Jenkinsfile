pipeline {
    agent any // Assumes Docker, JDK, and Maven are available on the agent

    environment {
        DOCKER_REGISTRY_URL = 'https://registry.hub.docker.com'
        DOCKER_IMAGE_NAME = 'waramos/mywallet'
        APP_VERSION = "1.0.1"
        DOCKER_CREDENTIALS_ID = 'doc-hubs-cred'
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
                    try {
                        def pom = readMavenPom file: 'pom.xml'
                        env.APP_VERSION = pom.version
                        echo "Application version from pom.xml: ${env.APP_VERSION}"
                    } catch (Exception e) {
                        echo "Could not read version from pom.xml, using default: ${env.APP_VERSION}. Error: ${e.getMessage()}"
                    }
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
                    def imageNameWithTag = "${env.DOCKER_IMAGE_NAME}:${env.APP_VERSION}"
                    echo "Building Docker image: ${imageNameWithTag}"
                    sh "docker build -t ${imageNameWithTag} ."
                }
            }
        }

        stage('Push Docker Image') {
            when {
                expression { env.DOCKER_CREDENTIALS_ID != '' }
            }
            steps {
                script {
                    def imageNameWithTag = "${env.DOCKER_IMAGE_NAME}:${env.APP_VERSION}"
                    echo "Pushing Docker image to Docker Hub: ${imageNameWithTag}"
                    withCredentials([string(credentialsId: env.DOCKER_CREDENTIALS_ID, variable: 'DOCKER_REGISTRY_PASSWORD')]) {
                        sh "echo $DOCKER_REGISTRY_PASSWORD | docker login -u waramos --password-stdin"
                        sh "docker push ${imageNameWithTag}"
                    }
                }
            }
        }

        stage('Image Pushed') {
            steps {
                echo "Docker image ${env.DOCKER_IMAGE_NAME}:${env.APP_VERSION} pushed to Docker Hub."
                echo "Pipeline finished. Image is ready for use."
            }
        }
    }

    post {
        always {
            echo 'Pipeline finished.'
        }
    }
}