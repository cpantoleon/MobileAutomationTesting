# Mobile Automation Testing with Appium and Selenium

## Table of Contents
- [Overview](#overview)
- [Technologies Used](#technologies-used)
- [Project Structure](#project-structure)
- [Prerequisites](#prerequisites)
- [Java and Maven Setup on Windows](#java-and-maven-setup-on-windows)
  - [Java Setup on Windows](#java-setup-on-windows)
  - [Maven Setup on Windows](#maven-setup-on-windows)
  - [Environment Variable Configuration](#environment-variable-configuration)
  - [Verification Steps](#verification-steps)
  - [Troubleshooting](#troubleshooting)
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

## Java and Maven Setup on Windows

### Java Setup on Windows

#### 1. Download and Install Java 17
Choose one of the following options:

**Option 1: Oracle JDK 17**
- Download from [Oracle JDK 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
- Run the installer and follow the on-screen instructions
- Note the installation path (typically `C:\Program Files\Java\jdk-17`)

**Option 2: OpenJDK 17**
- Download from [Eclipse Temurin (Adoptium)](https://adoptium.net/temurin/releases/?version=17)
- Run the installer and follow the on-screen instructions
- Note the installation path (typically `C:\Program Files\Eclipse Adoptium\jdk-17.x.x.x-hotspot`)

#### 2. Set JAVA_HOME Environment Variable
1. Press `Win + R`, type `sysdm.cpl`, and press **Enter**
2. Go to the **Advanced** tab and click **Environment Variables**
3. Under **System Variables**, click **New**
4. Set Variable name as `JAVA_HOME`
5. Set Variable value as your Java installation path (e.g., `C:\Program Files\Java\jdk-17`)
6. Click **OK**

---

### Maven Setup on Windows

#### 1. Download and Install Maven
1. Download the latest Maven binary from [Maven's official website](https://maven.apache.org/download.cgi) (choose the Binary zip archive)
2. Extract the downloaded zip file to a location of your choice (e.g., `C:\Program Files\Apache\maven`)

#### 2. Set Maven Environment Variables
1. Press `Win + R`, type `sysdm.cpl`, and press **Enter**
2. Go to the **Advanced** tab and click **Environment Variables**
3. Under **System Variables**, click **New**
4. Set Variable name as `MAVEN_HOME`
5. Set Variable value as your Maven installation path (e.g., `C:\Program Files\Apache\maven`)
6. Click **OK**

#### 3. Configure Maven settings.xml (Optional)
The `settings.xml` file allows you to customize Maven's behavior:

1. Navigate to `%MAVEN_HOME%\conf` or create a `.m2` directory in your user home directory
2. Create or edit `settings.xml` with the following template:

```xml
<settings xmlns="http://maven.apache.org/SETTINGS/1.0.0"
          xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
          xsi:schemaLocation="http://maven.apache.org/SETTINGS/1.0.0
                              http://maven.apache.org/xsd/settings-1.0.0.xsd">
  <!-- Optional: Proxy settings if you're behind a corporate firewall -->
  <!--
  <proxies>
    <proxy>
      <id>optional</id>
      <active>true</active>
      <protocol>http</protocol>
      <host>proxy.company.com</host>
      <port>8080</port>
      <nonProxyHosts>localhost|127.0.0.1</nonProxyHosts>
    </proxy>
  </proxies>
  -->
  
  <!-- Optional: Mirror settings for faster downloads -->
  <mirrors>
    <mirror>
      <id>maven-default-http-blocker</id>
      <mirrorOf>external:http:*</mirrorOf>
      <name>Pseudo repository to mirror external repositories initially using HTTP.</name>
      <url>http://0.0.0.0/</url>
      <blocked>true</blocked>
    </mirror>
  </mirrors>
</settings>
```

---

### Environment Variable Configuration

#### Update PATH Variable
1. Press `Win + R`, type `sysdm.cpl`, and press **Enter**
2. Go to the **Advanced** tab and click **Environment Variables**
3. Under **System Variables**, find the `Path` variable and click **Edit**
4. Click **New** and add the following paths:
   ```
   %JAVA_HOME%\bin
   %MAVEN_HOME%\bin
   ```
5. Click **OK** to save all changes

---

### Verification Steps

After setting up Java and Maven, open a **new** Command Prompt window and run the following commands to verify the installation:

#### 1. Verify Java Installation
```sh
java -version
```
Expected output (example):
```
openjdk version "17.0.6" 2023-01-17
OpenJDK Runtime Environment Temurin-17.0.6+10 (build 17.0.6+10)
OpenJDK 64-Bit Server VM Temurin-17.0.6+10 (build 17.0.6+10, mixed mode, sharing)
```

#### 2. Verify JAVA_HOME
```sh
echo %JAVA_HOME%
```
Expected output (example):
```
C:\Program Files\Java\jdk-17
```

#### 3. Verify Maven Installation
```sh
mvn -version
```
Expected output (example):
```
Apache Maven 3.9.2 (c9616018c7a021c1c39be70fb2843d6f5f9b8a1c)
Maven home: C:\Program Files\Apache\maven
Java version: 17.0.6, vendor: Eclipse Adoptium, runtime: C:\Program Files\Eclipse Adoptium\jdk-17.0.6.10-hotspot
Default locale: en_US, platform encoding: Cp1252
OS name: "windows 11", version: "10.0", arch: "amd64", family: "windows"
```

#### 4. Verify MAVEN_HOME
```sh
echo %MAVEN_HOME%
```
Expected output (example):
```
C:\Program Files\Apache\maven
```

---

### Troubleshooting

If you encounter issues with Java or Maven setup, try the following:

#### Java Not Found or Wrong Version
1. Ensure `JAVA_HOME` points to the correct Java 17 installation directory
2. Verify that `%JAVA_HOME%\bin` is in your `PATH`
3. If using multiple Java versions, ensure Java 17 appears first in your `PATH`

#### Maven Not Found
1. Ensure `MAVEN_HOME` points to the correct Maven installation directory
2. Verify that `%MAVEN_HOME%\bin` is in your `PATH`
3. Try restarting your Command Prompt

#### Command Prompt Doesn't Recognize Environment Variables
1. Restart your Command Prompt after making changes to environment variables
2. If issues persist, restart your computer

#### Maven Build Fails
1. Ensure your `settings.xml` is correctly configured
2. Check your network connection (especially if using a corporate proxy)
3. Run `mvn clean` before attempting to build again

---

With these steps, you should have Java 17 and Maven correctly installed and configured on Windows. 🚀

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

### 4. Set Up an Android Emulator
You can run tests using either an Android Emulator or a real device. Here's how to set up an Android Emulator:

#### Install Android Studio
1. Download and install **Android Studio** from [here](https://developer.android.com/studio).
2. Open **Android Studio** and go to **Configure** → **SDK Manager**.
3. In the **SDK Tools** tab, check the box for **Android Emulator** and **Android SDK** (if not already installed).
4. Click **Apply** to install the necessary components.

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

### 5. Run Tests Locally
```sh
mvn clean test -Dcucumber.plugin=pretty
```

## Installing Docker on Windows (Using WSL)
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
