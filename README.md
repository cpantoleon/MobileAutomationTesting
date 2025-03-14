# Mobile Automation Testing with Appium and Selenium

## Table of Contents
- [Overview](#overview)
- [Technologies Used](#technologies-used)
- [Project Structure](#project-structure)
- [Prerequisites](#prerequisites)
- [Java and Maven Setup on Windows](#java-and-maven-setup-on-windows)
  - [Java Setup on Windows](#java-setup-on-windows)
  - [Maven Setup on Windows](#maven-setup-on-windows)
  - [Summary](#summary)
- [Installation & Setup](#installation--setup)
  - [1. Clone the Repository](#1-clone-the-repository)
  - [2. Install Dependencies](#2-install-dependencies)
  - [3. Install Appium](#3-install-appium)
  - [4. Set Up an Android Emulator](#4-set-up-an-android-emulator)
  - [5. Run Tests Locally](#5-run-tests-locally)
  - [Installing Docker on Windows (Using WSL)](#installing-docker-on-windows-using-wsl)
- [Jenkins Configuration](#jenkins-configuration)
- [Running Tests in Dockerized Emulator (via Jenkins Pipeline)](#running-tests-in-dockerized-emulator-via-jenkins-pipeline)
- [Logs & Reports](#logs--reports)
- [Cleanup](#cleanup)

## Overview
This project is a **mobile automation testing framework** that leverages **Appium**, **Selenium**, **Serenity BDD**, and **Cucumber** to automate mobile applications. The testing framework is built using **Maven** and supports **JUnit 5** for test execution.

Additionally, a **Jenkins pipeline** is included to automate the setup of a **Dockerized Android emulator**, install dependencies, deploy the application, and run tests.

## Technologies Used
- **Java 17**
- **Maven** (Build Tool)
- **Appium** (Mobile Testing Framework)
- **Selenium** (WebDriver for automation)
- **Cucumber** (Behavior-Driven Development)
- **JUnit 5** (Testing Framework)
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

# Java and Maven Setup on Windows

## Java Setup on Windows

### Official Java Installation (Oracle JDK)
- [Download Oracle JDK](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html)

### OpenJDK Installation on Windows
- [OpenJDK Downloads](https://adoptopenjdk.net/)

### Setting the JAVA_HOME Environment Variable
- [How to Set JAVA_HOME on Windows](https://www.baeldung.com/java-home-on-windows-mac-os-x-linux)
    
---

## Maven Setup on Windows

### Official Maven Installation Guide
- [Maven Installation Guide for Windows](https://maven.apache.org/install.html)
 
### Configure Maven
- [Configuration of Maven](https://www.qamadness.com/knowledge-base/how-to-install-maven-and-configure-environment-variables/)
    
### Configuring settings.xml in Maven
- [Maven settings.xml Documentation](https://maven.apache.org/settings.html)

---

## Summary

- **Java Setup**: Install Java JDK and set JAVA_HOME.
- **Maven Setup**: Install Maven and configure MAVEN_HOME and PATH.
- **settings.xml Configuration**: Configure your Maven settings.xml file for repositories, proxies, and profiles.

By following these links, you will be able to properly configure Java, Maven, and the settings.xml file on Windows. If you encounter any issues, these resources should also help you with troubleshooting the common setup problems.

## Installation & Setup
### 1. Clone the Repository
```sh
git clone https://github.com/cpantoleon/MobileAutomationTesting.git
cd MobileAutomationTesting
```

### 2. Install Dependencies
Install the necessary project dependencies using Maven:
```sh
mvn clean install
```

### 3. Install Appium
You need **Appium** installed to run the mobile tests. You can install it globally via npm:

**Install Node.js (if not already installed)**
Download and install Node.js from [nodejs.org](https://nodejs.org/en).

**Install Appium**
After installing Node.js, install **Appium** globally using the following command:
```sh
npm install -g appium
```
**Start the Appium Server with Custom Configuration**
To run Appium with the specific address, port, and base path as required, use this command:
```sh
appium --address 0.0.0.0 --port 4723 --base-path /wd/hub
```
This command starts the Appium server and makes it listen on `0.0.0.0` (accessible from any IP address), at port `4723`, with a custom base path of `/wd/hub`.

### 4. Set Up an Android Emulator
You can run tests using either an Android Emulator or a real device. Here's how to set up an Android Emulator:

#### Install Android Studio
1. Download and install **Android Studio** from [here](https://developer.android.com/studio).
2. Open **Android Studio** and go to **Configure** → **SDK Manager**.
3. In the **SDK Tools** tab, check the box for **Android Emulator** and **Android SDK** (if not already installed).
4. Click **Apply** to install the necessary components.

#### Install the APK on the Emulator

After setting up the **Android Emulator**, you can install the **APK** onto it. Here's how:

1. **Build the APK**: 
   - Ensure you have the APK file that you want to install on the emulator. If you don't have it, you may need to build the app first.

2. **Start the Emulator**: 
   - Ensure your **Android Emulator** is running.

3. **Install the APK**:
   - Open a terminal window and use the following command to install the APK onto the running emulator:

   ```sh
   adb -s <emulator_name> install <path_to_your_apk>.apk
   ```

#### Create an Emulator
1. Open **AVD Manager** (Android Virtual Device Manager) from **Android Studio**.
2. Click **Create Virtual Device** and choose a device model (e.g., **Pixel 4**).
3. Select a **System Image** (e.g., **Android 11**) and click **Next**.
4. Configure the emulator settings and click **Finish**.

#### Start the Emulator
Start the emulator either through **Android Studio** or using the **AVD Manager**.

Alternatively, you can start the emulator using the following command from the terminal:

```sh
emulator -avd <name_of_your_avd>
```

### 5. Run Tests Locally
After setting up **Appium** and the **Android Emulator**, you can run your tests locally. Use the following Maven command to run the tests:

```sh
mvn clean test -Dcucumber.plugin=pretty
```

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
                        -v "${WORKSPACE_PATH}:/workspace" ^
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
                        def apkFileName = APK_PATH.split('/')[-1]
                        def result = bat(returnStatus: true, script: "docker exec android-container adb install /workspace/${apkFileName}")
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

## Running Tests in Dockerized Emulator (via Jenkins Pipeline)
1. **Ensure Docker is running.**
2. **Run Jenkins Pipeline** to automatically:
   - Start an **Android emulator in Docker**
   - Install the required **JDK and Maven**
   - Deploy the **APK** to the emulator
   - Start the **Appium server**
   - Execute the **Cucumber tests**

## Logs & Reports
### Serenity Reports
After running tests, Serenity generates an HTML report.
Find reports in:
```
target/test.html
```

## Cleanup
After tests, the Jenkins pipeline ensures:
- **Appium server is stopped.**
- **Docker container is removed.**
