# Library Management System 📚

![Test Status](https://github.com/thilina01/library-management-system/actions/workflows/test.yml/badge.svg)
![Build Status](https://github.com/thilina01/library-management-system/actions/workflows/docker-build-push.yml/badge.svg)
![Docker Pulls](https://img.shields.io/docker/pulls/thilina01/library-management-system)
![Docker Image Size](https://img.shields.io/docker/image-size/thilina01/library-management-system)

## 📖 Overview

The **Library Management System** is a comprehensive RESTful API for managing books and borrowers in a library. Key features include:

- **Borrower and Book Registration**: Register new borrowers and books effortlessly.
- **Book Management**: Borrow and return books with ease.
- **Comprehensive Logging**: Detailed logging with SLF4J and Logback.
- **Containerization**: Docker support for easy deployment.
- **Kubernetes Integration**: Deployment with MySQL and Kubernetes.
- **CI/CD**: Automated builds and deployments with GitHub Actions.

## 🚀 Features

- **Java 17**: Utilizes the latest Java version with Maven for dependencies.
- **Spring Boot**: Simplifies configuration and setup.
- **CI/CD**: GitHub Actions automates Docker builds and pushes to Docker Hub.
- **API Documentation**: Interactive API documentation with Swagger.

## 🛠️ Getting Started

### Prerequisites

Ensure you have the following setup:
- Java 17
- Docker
- Maven
- GitHub account

### Build and Run Locally

1. **Clone the repository**:
    ```sh
    git clone https://github.com/Innocentsax/Library_Managment_System.git
    cd library-management-system
    ```

2. **Build the project using Maven**:
    ```sh
    mvn clean install
    ```

3. **Run the application**:
    ```sh
    java -jar target/library-managment-system-0.0.1-SNAPSHOT.jar
    ```


## 📜 Logging

This project uses SLF4J with Logback for logging. The log configuration can be found in `src/main/resources/logback.xml`.

### Logback Configuration

The Logback configuration specifies the logging pattern, log file location, and log rotation policy. You can customize the configuration to suit your needs.

### Accessing Logs

Logs are written to both the console and a log file located at `logs/library-management-system.log`. The log file is rotated daily, and log files older than 30 days are automatically deleted.

### Logging Levels

The logging levels used in the project are:
- `INFO`: General application information.
- `DEBUG`: Detailed information used for debugging purposes.
- `ERROR`: Error messages indicating that something went wrong.

You can adjust the logging levels in the Logback configuration file to control the verbosity of the logs.

### Viewing Logs

To view the logs, you can either:
- Check the console output when running the application.
- Open the log file located at `logs/library-management-system.log`.

## 🚀 Testing

### Running Unit Tests

Unit tests are implemented to ensure the functionality and reliability of the Library Management System. The tests can be run using Maven, and the results will be generated in a report format.

**Running Tests:**

To run the unit tests, execute the following command in the root directory of your project:

```bash
mvn test
```

### Test Status Badges

You can monitor the status of your tests using badges that show the build status and test coverage. These badges will be updated automatically with each commit to the repository.

### Postman Collection

A Postman collection is available to facilitate testing and exploring the Library Management System API. This collection includes various endpoints for managing borrowers and books. You can import this collection into Postman to quickly set up your API requests.

**Postman Collection Information:**
- **Name**: Library Management System
- **Description**: Postman collection for the Library Management System API

**Endpoints Included:**
1. **Register a New Borrower**
    - **Method**: POST
    - **URL**: `{{base_url}}/api/borrowers`
    - **Headers**:
        - `Content-Type: application/json`
    - **Body**:
      ```json
      {
        "email": "john.doe@example.com",
        "name": "John Doe"
      }
      ```

2. **Register a New Book**
    - **Method**: POST
    - **URL**: `{{base_url}}/api/books`
    - **Headers**:
        - `Content-Type: application/json`
    - **Body**:
      ```json
      {
        "isbn": "1234567890",
        "title": "Sample Book",
        "author": "Sample Author"
      }
      ```

3. **List All Books**
    - **Method**: GET
    - **URL**: `{{base_url}}/api/books`

4. **Borrow a Book**
    - **Method**: POST
    - **URL**: `{{base_url}}/api/borrowers/{{borrower_id}}/borrow/{{book_id}}`
    - **Headers**:
        - `Content-Type: application/json`
    - **Body**:
      ```json
      {
        "borrowerId": {{borrower_id}},
        "bookId": {{book_id}}
      }
      ```

5. **Return a Book**
    - **Method**: POST
    - **URL**: `{{base_url}}/api/borrowers/{{borrower_id}}/return/{{book_id}}`
    - **Headers**:
        - `Content-Type: application/json`

6. **Verify Borrower Details**
    - **Method**: GET
    - **URL**: `{{base_url}}/api/borrowers/{{borrower_id}}`

7. **Verify Book Details**
    - **Method**: GET
    - **URL**: `{{base_url}}/api/books/{{book_id}}`

**Variables Included:**
- **base_url**: `http://localhost:8080`
- **borrower_id**: `1`
- **book_id**: `1`


### API Documentation with Swagger

Swagger is integrated into the project to provide interactive API documentation. To access the Swagger UI:

1. **Run the application** .
2. Open your browser and navigate to:
    ```sh
    http://localhost:8080/swagger-ui.html
    ```

This will open the Swagger UI where you can interact with the API endpoints and see the detailed API documentation.

### API Endpoints

- **Register a new borrower**:
    ```http
    POST /api/borrowers
    {
      "name": "John Doe",
      "email": "john.doe@example.com"
    }
    ```

- **Register a new book**:
    ```http
    POST /api/books
    {
      "isbn": "1234567890",
      "title": "Test Book",
      "author": "Test Author"
    }
    ```

- **Get a list of all books**:
    ```http
    GET /api/books
    ```

- **Borrow a book**:
    ```http
    POST /api/borrowers/{borrowerId}/borrow/{bookId}
    ```

- **Return a borrowed book**:
    ```http
    POST /api/borrowers/{borrowerId}/return/{bookId}
    ```

- **Get borrower details**:
    ```http
    GET /api/borrowers/{borrowerId}
    ```

- **Get book details**:
    ```http
    GET /api/books/{bookId}
    ```

## 🤝 Thanks



