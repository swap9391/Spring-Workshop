# Spring-Workshop

A demonstration project built with Spring Boot that showcases the implementation of a RESTful API for a simple water tracking application. This workshop covers core Spring concepts including 

## Core Concepts Demonstrated

-   **Spring Boot**: For rapid application development and simplified configuration.
-   **Spring MVC**: Used to build the RESTful API endpoints with annotations like `@RestController` and `@RequestMapping`.
-   **Dependency Injection (DI)**: The core of the Spring Framework, used to manage components and their dependencies (e.g., injecting the repository into the controller).
-   **Spring Data JPA**: Simplifies the data access layer by providing repository support for JPA.
-   **Spring Security**: Secures the API endpoints with authentication mechanisms.
-   **JUnit & Mockito**: For writing comprehensive unit tests for different application layers.


## Table of Contents

- [Features](#features)
- [Technologies & Tools](#technologies--tools)
- [API Endpoints](#api-endpoints)
- [Getting Started](#getting-started)
  - [Prerequisites](#prerequisites)
  - [Running the Application](#running-the-application)
- [Project Structure](#project-structure)

## Features

-   **User Management**: Basic user entity (`Users`) integrated with Spring Security.
-   **Authentication**: Secure endpoints using Spring Security. User details are managed via `CustomUserDetails`.
-   **Water Log CRUD**: Full CRUD (Create, Read, Update, Delete) functionality for daily water logs.
-   **RESTful API**: Well-defined endpoints to manage water tracking data.
-   **Data Persistence**: Uses Spring Data JPA with an in-memory H2 database for easy setup and testing.
-   **Unit Testing**: Includes unit tests for models and other layers using JUnit 5.

## Technologies & Tools

-   **Framework**: Spring Boot 3.5.4
-   **Language**: Java 17
-   **Build Tool**: Maven
-   **Data Persistence**:
    -   Spring Data JPA
    -   In-Memory Database: H2
-   **API**:
    -   Spring Web (MVC)
    -   Spring Data REST
-   **Security**:
    -   Spring Security
-   **Testing**:
    -   JUnit 5
    -   Mockito

## API Endpoints

The following are the primary endpoints exposed by the application.

| Method   | Endpoint                                   | Description                                                                                      |
| :------- | :----------------------------------------- | :----------------------------------------------------------------------------------------------- |
| `POST`   | `/api/water-tracker/add-water-log`         | Adds a new water log entry for the authenticated user. Updates if a log for the date already exists. |
| `GET`    | `/api/water-tracker/fetch-all-water-log`   | Retrieves all water log entries from the database.                                               |
| `GET`    | `/api/water-tracker/fetch-water-log/{id}`  | Retrieves a specific water log entry by its ID.                                                  |
| `DELETE` | `/api/water-tracker/remove-water-log/{id}` | Deletes a specific water log entry by its ID.                                                    |

## Getting Started

Follow these instructions to get the project up and running on your local machine.

### Prerequisites

-   Java Development Kit (JDK) 17 or later.
-   Apache Maven.

### Running the Application

1.  **Clone the repository** and navigate into the directory.
2.  **Run the application using Maven:**
    ```sh
    mvn spring-boot:run
    ```
    The application will start on `http://localhost:8080`.

## Project Structure

```
src/main/java/com/example/springworkshop
├── controller       // REST API controllers
├── dto              // Data Transfer Objects
├── model            // JPA entity classes
├── repository       // Spring Data JPA repositories
└── securities       // Spring Security configuration
```
