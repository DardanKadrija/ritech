pipeline {
    agent any
    
    tools {
        maven 'Maven-3.8'  // Configure Maven tool name in Jenkins Global Tool Configuration
        jdk 'JDK-11'       // Configure JDK tool name in Jenkins Global Tool Configuration
    }
    
    options {
        buildDiscarder(logRotator(numToKeepStr: '10'))
        timeout(time: 30, unit: 'MINUTES')
        timestamps()
        ansiColor('xterm')
    }
    
    environment {
        // Set headless mode for CI execution
        HEADLESS = 'true'
        // Browser selection (chrome, firefox, edge)
        BROWSER = 'chrome'
        // Base URL for tests
        BASE_URL = 'https://the-internet.herokuapp.com'
    }
    
    stages {
        stage('Checkout') {
            steps {
                echo 'Checking out source code...'
                checkout scm
            }
        }
        
        stage('Build') {
            steps {
                echo 'Building project and installing dependencies...'
                sh 'mvn clean install -DskipTests'
            }
        }
        
        stage('Update Config for CI') {
            steps {
                echo 'Updating configuration for CI environment...'
                script {
                    // Update config.properties for headless execution
                    sh '''
                        sed -i "s/headless=.*/headless=${HEADLESS}/" src/main/resources/config.properties || true
                        sed -i "s/browser=.*/browser=${BROWSER}/" src/main/resources/config.properties || true
                        sed -i "s|base.url=.*|base.url=${BASE_URL}|" src/main/resources/config.properties || true
                    '''
                }
            }
        }
        
        stage('Run Tests') {
            steps {
                echo 'Running Selenium UI tests...'
                sh 'mvn test'
            }
            post {
                always {
                    echo 'Test execution completed. Collecting reports...'
                }
            }
        }
        
        stage('Publish Reports') {
            steps {
                echo 'Publishing test reports...'
                script {
                    // Publish Cucumber HTML Report
                    if (fileExists('target/cucumber-reports/index.html')) {
                        publishHTML([
                            reportDir: 'target/cucumber-reports',
                            reportFiles: 'index.html',
                            reportName: 'Cucumber HTML Report',
                            keepAll: true,
                            allowMissing: false,
                            alwaysLinkToLastBuild: true
                        ])
                    }
                    
                    // Publish TestNG HTML Report
                    if (fileExists('test-output/index.html')) {
                        publishHTML([
                            reportDir: 'test-output',
                            reportFiles: 'index.html',
                            reportName: 'TestNG HTML Report',
                            keepAll: true,
                            allowMissing: false,
                            alwaysLinkToLastBuild: true
                        ])
                    }
                    
                    // Publish Cucumber JSON Report (for other plugins)
                    if (fileExists('target/cucumber-reports/Cucumber.json')) {
                        archiveArtifacts artifacts: 'target/cucumber-reports/Cucumber.json', allowEmptyArchive: true
                    }
                    
                    // Publish Cucumber XML Report (JUnit format for test results)
                    if (fileExists('target/cucumber-reports/Cucumber.xml')) {
                        junit 'target/cucumber-reports/Cucumber.xml'
                    }
                    
                    // Publish TestNG XML Report
                    if (fileExists('test-output/testng-results.xml')) {
                        junit 'test-output/testng-results.xml'
                    }
                }
            }
        }
        
        stage('Archive Artifacts') {
            steps {
                echo 'Archiving test artifacts...'
                script {
                    // Archive all reports
                    archiveArtifacts artifacts: 'target/cucumber-reports/**/*', allowEmptyArchive: true
                    archiveArtifacts artifacts: 'test-output/**/*', allowEmptyArchive: true
                    archiveArtifacts artifacts: 'target/surefire-reports/**/*', allowEmptyArchive: true
                    
                    // Archive logs if they exist
                    if (fileExists('logs/')) {
                        archiveArtifacts artifacts: 'logs/**/*', allowEmptyArchive: true
                    }
                }
            }
        }
    }
    
    post {
        always {
            echo 'Pipeline execution completed.'
            // Clean up workspace if needed
            // cleanWs()
        }
        success {
            echo '✅ All tests passed successfully!'
            // Optional: Send success notification
            // emailext (
            //     subject: "✅ Build Success: ${env.JOB_NAME} - ${env.BUILD_NUMBER}",
            //     body: "Build ${env.BUILD_NUMBER} completed successfully.\n\nView results: ${env.BUILD_URL}",
            //     to: "${env.CHANGE_AUTHOR_EMAIL}"
            // )
        }
        failure {
            echo '❌ Build failed! Check the test reports for details.'
            // Optional: Send failure notification
            // emailext (
            //     subject: "❌ Build Failed: ${env.JOB_NAME} - ${env.BUILD_NUMBER}",
            //     body: "Build ${env.BUILD_NUMBER} failed.\n\nView results: ${env.BUILD_URL}",
            //     to: "${env.CHANGE_AUTHOR_EMAIL}"
            // )
        }
        unstable {
            echo '⚠️ Build is unstable. Some tests may have failed.'
        }
    }
}
