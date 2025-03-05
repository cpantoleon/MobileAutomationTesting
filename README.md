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
|-- Jenkinsfile  # Jenkins Pipeline Configuration
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
   - Provide the **Git repository URL**.
   - Ensure the **Branch** is set to `main`.
   - Click **Save** and trigger a build.

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

## Contribution
Feel free to contribute by:
- Fixing bugs.
- Adding new test cases.
- Improving automation scripts.

## License
This project is open-source under the **MIT License**.

---

Enjoy automated mobile testing with **Appium + Selenium + Serenity + Jenkins**! 🚀

