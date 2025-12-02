# User API

A RESTful API built with Spring Boot for managing user data.

## Features

* User registration
* User retrieval

## Stack
* Language: Java 25
* Framework: Spring Boot 4
* Database: H2

## Prerequisites
* JDK 25
* Apache Maven 4

## Build and run
1. Clone the repository
```
git clone https://github.com/thales-b/user-api
```
2. Compile
```
mvn clean install
```
3. Run the application
```
mvn spring-boot:run
```

## API endpoints
### 1. User retrieval
* **path**: `/api/users/{username}`
* **response**:
* `200 OK` (user found)
* `404 Not Found` (user not found)

### 2. User registration
* **path**: ̀/api/users/register`
* **body**: The user to register
* **response**:
* `200 OK` (registration successful)
* `400 Bad Request` (malformed user)
* `409 Conflict` (user already registered)

Examples can be found in the `User API.postman_collection.json` file at the root of the project.
