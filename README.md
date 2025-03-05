# Mobile Automation Testing with Appium, Selenium, and Serenity

## Overview
This project is a **mobile automation testing framework** that leverages **Appium**, **Selenium**, **Serenity BDD**, and **Cucumber** to automate mobile applications. The testing framework is built using **Maven** and supports **JUnit 5** for test execution.

Additionally, a **Jenkins pipeline** is included to automate the setup of a **Dockerized Android emulator**, install dependencies, deploy the application, and run tests.

## Technologies Used
- **Java 17**
- **Maven** (Build Tool)
- **Appium** (Mobile Testing Framework)
- **Selenium** (WebDriver for automation)
- **Serenity BDD** (Reporting & BDD Framework)
- **Cucumber** (Behavior-Driven Development)
- **JUnit 5** (Testing Framework)
- **Spring Boot** (Dependency Injection)
- **Google Guava** (Utility Library)
- **SLF4J** (Logging Framework)
- **SnakeYAML** (YAML Parsing)
- **Docker** (Containerization for Android Emulator)
- **Jenkins** (CI/CD Automation)

## Project Structure
```
|-- src/test/java/project/test/swag
|   |-- stepDef  # Step Definitions for Cucumber Scenarios
|   |-- runners  # Cucumber Test Runners
|-- pom.xml  # Maven Configuration with Dependencies
```

## Prerequisites
### Install the following before running tests:
1. **Java 17**
2. **Maven**
3. **Appium Server**
4. **Android Emulator or Real Device**
5. **Docker** (For running Android emulator inside a container)
6. **Jenkins** (If running tests via CI/CD pipeline)

### Installing Docker on Windows (Using WSL)
If you are using **Windows**, install and update **Windows Subsystem for Linux (WSL)** before installing Docker:
```sh
wsl --install
wsl --update
```
Then, proceed with Docker installation from [Docker's official site](https://www.docker.com/).

## Jenkins Configuration
To set up Jenkins for running the pipeline:
1. Install **Jenkins** from [Jenkins Official Site](https://www.jenkins.io/download/).
2. Install required **Jenkins Plugins**:
   - **Pipeline**
   - **Git**
   - **Docker Pipeline**
3. Create a new **Pipeline Job**:
   - Go to Jenkins Dashboard → New Item → Pipeline.
   - In **Pipeline Definition**, select **Pipeline script from SCM**.
   - Provide the **Git repository URL** (e.g., `https://github.com/cpantoleon/MobileAutomationTesting.git`).
   - Ensure the **Branch** is set to `main`.
   - Click **Save** and trigger a build.
4. **Add Pipeline Script**:
   - Select **Pipeline script**.
   - In the text box, paste the following pipeline script that defines how the tests should be run, including steps to launch the Dockerized emulator, install dependencies, deploy the APK, and trigger tests.
```sh
pipeline {
    agent any

    environment {
        EMULATOR_DEVICE = "Samsung Galaxy S10"
        APK_PATH = "${WORKSPACE}/Android.SauceLabs.Mobile.Sample.app.2.7.1.apk"
        // Convert Windows workspace path to Unix-friendly format
        WORKSPACE_PATH = pwd().replace("\\", "/")
    }

    stages {
        stage('Checkout Maven Project') {
            steps {
                git url: 'https://github.com/cpantoleon/MobileAutomationTesting.git', branch: 'main'
            }
        }
        
        stage('Start Fresh Docker Container') {
            steps {
                script {
                    echo "Removing any existing container..."
                    bat 'docker rm -f android-container || echo No container to remove'

                    echo "Starting a new container..."
                    bat """
                        docker run -d -p 6080:6080 -p 4723:4723 ^
                        -e EMULATOR_DEVICE="${EMULATOR_DEVICE}" -e WEB_VNC=true --device /dev/kvm ^
                        -v "${WORKSPACE_PATH}:/workspace" -v "C:/apk-files:/apk-files" ^
                        --name android-container budtmo/docker-android:emulator_14.0
                    """
                }
            }
        }
        
        stage('Wait for Emulator to Start') {
            steps {
                script {
                    echo "Waiting for emulator to start (2 minutes)..."
                    bat 'ping -n 10 127.0.0.1 >nul'
                }
            }
        }
        
        stage('Verify ADB Connection') {
            steps {
                script {
                    echo "Checking if emulator is detected by ADB..."
                    bat 'docker exec android-container adb devices'
                }
            }
        }
        
        stage('Install JDK 17 and Maven') {
            steps {
                script {
                    echo "Installing JDK 17 and Maven in the container..."
                    bat """
                        docker exec -u 0 android-container bash -c "apt-get update"
                        docker exec -u 0 android-container bash -c "apt-get install -y openjdk-17-jdk maven"
                    """
                }
            }
        }
        
        stage('Verify Java and Maven Installation') {
            steps {
                script {
                    echo "Verifying JDK 17 and Maven installation..."
                    bat 'docker exec android-container java -version'
                    bat 'docker exec android-container mvn -v'
                }
            }
        }
        
        stage('Install APK via ADB Inside Container (With Retry)') {
            steps {
                script {
                    def maxAttempts = 5
                    def attempt = 1
                    def success = false

                    while (attempt <= maxAttempts && !success) {
                        echo "Attempt ${attempt}: Installing APK..."
                        def result = bat(returnStatus: true, script: 'docker exec android-container adb install /apk-files/Android.apk')
                        if (result == 0) {
                            echo "APK installed successfully!"
                            success = true
                        } else {
                            echo "APK installation failed. Retrying in 5 seconds..."
                            sleep(5)
                            attempt++
                        }
                    }
                    if (!success) {
                        error("APK installation failed after ${maxAttempts} attempts. Stopping pipeline.")
                    }
                }
            }
        }
        
        stage('Setup & Start Appium') {
            steps {
                script {
                    echo "Starting Appium server..."
                    bat 'docker exec -d android-container appium --address 0.0.0.0 --port 4723 --base-path /wd/hub'
                }
            }
        }
        
        stage('Run Maven Tests with Profile') {
            steps {
                script {
                    echo "Running Maven tests from /workspace with JDK 17 settings..."
                    bat '''
                        docker exec android-container bash -c "export JAVA_HOME=/usr/lib/jvm/java-17-openjdk-amd64 && export PATH=/usr/lib/jvm/java-17-openjdk-amd64/bin:$PATH && cd /workspace && mvn clean test -Dcucumber.plugin=pretty -Dandroid.maven.plugin.skip=true"
                        docker exec android-container ls -l /workspace/src/test/java/project/test/swag/stepDef
                        docker exec android-container ls -l /workspace/src/test/java/project/test/swag/runners
                    '''
                }
            }
        }
    }
    
    post {
        always {
            script {
                echo "Stopping Appium server..."
                try {
                    bat 'docker exec android-container pkill -f appium || echo No Appium server running'
                } catch (Exception e) {
                    echo "Appium server not found. Continuing cleanup."
                }
                echo "Cleaning up Docker container..."
                bat 'docker rm -f android-container || echo No container to remove'
            }
        }
    }
}
```

## Installation & Setup
### 1. Clone the Repository
```sh
git clone https://github.com/cpantoleon/MobileAutomationTesting.git
cd MobileAutomationTesting
```

### 2. Install Dependencies
```sh
mvn clean install
```

### 3. Run Tests Locally
```sh
mvn clean test -Dcucumber.plugin=pretty
```

## Running Tests in Dockerized Emulator (via Jenkins Pipeline)
1. **Ensure Docker is running.**
2. **Run Jenkins Pipeline** to automatically:
   - Start an **Android emulator in Docker**
   - Install the required **JDK and Maven**
   - Deploy the **APK** to the emulator
   - Start the **Appium server**
   - Execute the **Cucumber tests**

### Trigger the Jenkins Pipeline
- Configure Jenkins to pull the repository.
- Run the pipeline script defined in `Jenkinsfile`.

## Logs & Reports
### Serenity Reports
After running tests, Serenity generates an HTML report.
Find reports in:
```
target/site/serenity/index.html
```

## Cleanup
After tests, the Jenkins pipeline ensures:
- **Appium server is stopped.**
- **Docker container is removed.**
