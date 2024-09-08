# Employees Management System

The Employees Management System is a comprehensive platform designed to manage and 
organize employees details efficiently. It caters to organizations, stores, and individuals by providing tools for storing, tracking,
and maintaining details of employees. 
The system offers features like adding employee, deleting employee, updating employee details, and searching capabilities, 
making it easier to manage large collections and enhance user experience.

## Features

- **Adding Employee**: Easily add employee to the database.
- **Updating Employee**: Update employee details.
- **Delete Employee**: Permanent delete employee details from DB.
- **Retrieve All Employees**: Retrieve all available employees paginated as per requesters' request.
- **Retrieve specific employee**: Retrieve specific employees by their IDs.
- **Secured API access**: To avoid leakage of employees' details, Only authorized requests will receive success responses.

## Folder Structure

The folders are arranged in such a way that they align with spring's Model-View-Controller (MVC) design pattern

- **api**: This folder keeps all controllers, Which receives HttpRequest and forward them to their appropriate services.
- **service**: This folder keeps all files containing the business logic of the specific request.
- **dao**: This folder contains all classes/interfaces (Data Access Objects) which have direct interaction with datasource.
- **security**: Security related configurations reside in this folder.
- **entity**: All JPA persisted objects reside in this folder i.e. Employee.
- **exception**: All custom exceptions and exception handler reside in this folder.
- **constants**: Constants data are stored in this folder.
- **config**: Other configurations reside in this folder i.e. OpenApi Configurations.
- **dto**: This folder stores Data Transfer Objects to transfer data between clients and DB.

## Installation

### Prerequisites

To have this project up and running, following pieces of software should be installed on your machine:

- Java Development Kit (JDK) 11 or higher
- Maven
- Docker Desktop (with postgres image)

### Backend Setup

1. **Clone the repository:**
   ```sh
   git clone https://github.com/Kayange123/ems.git
   cd ems

2. **Configure the database:**
Update the database configuration in src/main/resources/application.example.yml:
   ```ruby
   DATABASE_URL: jdbc:postgresql://localhost:5434/employees-management-db
   DATABASE_USERNAME: username
   DATABASE_PASSWORD: password
   ```

3. **Run the backend:**
   ```sh
   mvn clean install
   mvn spring-boot:run
   ```

The backend server should now be running on `http://localhost:8080`.

## Dependencies Used

> Spring Starter packages

1. spring-boot-starter-data-jpa - Jakarta Persistence Api To persist data into relational databases
2. spring-boot-starter-validation - To Validate data across the system
3. spring-boot-starter-security - To secure the APIs
4. springdoc-openapi-starter-webmvc-ui - To enable OpenAPI Swagger integration
5. postgresql - PostgreSQL Database Driver
6. spring-boot-starter-web - To expose RESTFull APIs
7. lombok - To reduce boilerplate codes
8. spring-boot-starter-test - Testing library which includes assertj-core, awaitility, mockito-core etc

> Other packages

1. swagger-annotations - Annotations for OpenApi Documentation with swagger.