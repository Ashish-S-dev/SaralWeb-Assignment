# Priority Queue REST API

A Spring Boot based REST API implementation of a **Priority Queue**, providing operations such as insertion, peek, extract-min, extract-max, update, delete, and checking whether the queue is empty.

The application uses **PostgreSQL** for persistent data storage and exposes RESTful APIs that can be tested using tools such as Postman.

---

## 🚀 Problem Overview

The objective of this project is to implement a Priority Queue as a backend REST service.

The application supports:

* Inserting elements with a priority
* Retrieving the element with the highest priority
* Extracting the minimum-priority element
* Extracting the maximum-priority element
* Updating an existing element
* Deleting an element
* Checking whether the queue is empty

For elements having the same priority, the **smaller ID is considered first**, providing deterministic ordering.

---

## 🛠️ Technology Stack

| Technology          | Purpose                         |
| ------------------- | ------------------------------- |
| **Java**            | Core programming language       |
| **Spring Boot**     | Backend application framework   |
| **Spring Web**      | REST API development            |
| **Spring Data JPA** | Database interaction            |
| **Hibernate / JPA** | ORM and entity management       |
| **PostgreSQL**      | Persistent data storage         |
| **Maven**           | Dependency management and build |
| **Postman**         | API testing                     |
| **Git/GitHub**      | Version control                 |

---

## 🏗️ Application Architecture

The application follows a layered architecture:

```text
Client / Postman
       ↓
Controller Layer
       ↓
Service Layer
       ↓
Repository Layer
       ↓
JPA / Hibernate
       ↓
PostgreSQL Database
```

### Controller Layer

Handles HTTP requests and exposes REST endpoints.

### Service Layer

Contains the business logic for Priority Queue operations.

### Repository Layer

Uses Spring Data JPA to perform database operations.

### Database Layer

PostgreSQL is used to persist Priority Queue elements.

---

# ⚙️ Setup & Installation

## Prerequisites

Before running the application, make sure the following are installed:

* **JDK 17 or later**
* **Maven 3.8+**
* **PostgreSQL**
* **Git**
* **Eclipse / IntelliJ IDEA / VS Code** (any Java IDE)
* **Postman** (optional, for API testing)

You should also have a PostgreSQL database available and accessible from the application.

---

## 🔑 Database Configuration

This application uses **PostgreSQL** as the database.

Before running the application on another system, update the JDBC configuration according to your local PostgreSQL setup.

Example:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/<database-name>
spring.datasource.username=<username>
spring.datasource.password=${DB_PASSWORD}
```

### Environment Variable

For security, the database password can be supplied through an environment variable instead of hard-coding it in `application.properties`.

For example:

```text
DB_PASSWORD=your_database_password
```

If using Eclipse:

**Run Configurations → Environment → New**

Then add:

```text
Name: DB_PASSWORD
Value: your_database_password
```

> **Important:** Do not commit database passwords or other sensitive credentials to GitHub.

---

## ▶️ Running the Application

### 1. Clone the repository

```bash
git clone <repository-url>
```

### 2. Open the project in your IDE

Import the project as a **Maven Project**.

### 3. Configure PostgreSQL

Update the JDBC URL, username, database name, and password/environment variable according to your system.

### 4. Build the project

```bash
mvn clean install
```

### 5. Run the Spring Boot application

Run the main Spring Boot application class.

By default, the application will start on:

```text
http://localhost:8080
```

---

# 📡 REST API Documentation

Base URL:

```text
http://localhost:8080/api/pq
```

---

## 1. Insert an Element

**POST**

```text
/api/pq/queue
```

Adds a new element to the Priority Queue.

### Request Body

```json
{
  "priority": 5
}
```

The server generates/maintains the corresponding ID according to the entity configuration.

### Example

```text
POST http://localhost:8080/api/pq/queue
```

---

## 2. Peek

**GET**

```text
/api/pq/peek
```

Returns the element currently at the top of the Priority Queue without removing it.

### Example

```text
GET http://localhost:8080/api/pq/peek
```

The queue prioritizes the element according to its priority and ID ordering.

---

## 3. Extract Minimum

**GET**

```text
/api/pq/extract-min
```

Removes and returns the element having the **minimum priority value**.

If multiple elements have the same priority, the element with the **smaller ID** is selected.

### Example

```text
GET http://localhost:8080/api/pq/extract-min
```

Example:

```text
ID    Priority
1     10
2      5
3      5
```

`extract-min` returns:

```text
ID = 2
Priority = 5
```

---

## 4. Extract Maximum

**GET**

```text
/api/pq/extract-max
```

Removes and returns the element having the **maximum priority value**.

### Example

```text
GET http://localhost:8080/api/pq/extract-max
```

---

## 5. Update an Element

**PUT**

```text
/api/pq/{id}
```

Updates an existing Priority Queue element using its ID.

### Example

```text
PUT http://localhost:8080/api/pq/2
```

### Request Body

```json
{
  "priority": 10
}
```

Here, `2` represents the ID of the element to be updated.

---

## 6. Delete an Element

**DELETE**

```text
/api/pq/{id}
```

Deletes an element from the Priority Queue using its ID.

### Example

```text
DELETE http://localhost:8080/api/pq/2
```

### Response

```text
Item deleted successfully
```

---

## 7. Check if Queue is Empty

**GET**

```text
/api/pq/is-empty
```

Checks whether the Priority Queue currently contains any elements.

### Example

```text
GET http://localhost:8080/api/pq/is-empty
```

### Response

```json
true
```

or

```json
false
```

---

# 📋 API Summary

| Operation   | Method   | Endpoint              | Description                          |
| ----------- | -------- | --------------------- | ------------------------------------ |
| Insert      | `POST`   | `/api/pq/queue`       | Adds an element                      |
| Peek        | `GET`    | `/api/pq/peek`        | Returns top element without removing |
| Extract Min | `GET`    | `/api/pq/extract-min` | Removes minimum-priority element     |
| Extract Max | `GET`    | `/api/pq/extract-max` | Removes maximum-priority element     |
| Update      | `PUT`    | `/api/pq/{id}`        | Updates an element                   |
| Delete      | `DELETE` | `/api/pq/{id}`        | Deletes an element                   |
| Is Empty    | `GET`    | `/api/pq/is-empty`    | Checks whether queue is empty        |

---

# 🧠 Priority Handling

The application maintains priority-based ordering.

For example:

```text
ID    Priority
1       10
2        5
3        5
4        8
```

For **minimum extraction**, the order is:

```text
Priority 5 → ID 2
Priority 5 → ID 3
Priority 8 → ID 4
Priority 10 → ID 1
```

When priorities are equal, the **smaller ID gets precedence**.

This ensures deterministic behavior when multiple elements have the same priority.

---

# 🗄️ Database

PostgreSQL is used for persistent storage.

The application uses:

* JPA entities for database mapping
* Hibernate as the ORM implementation
* Spring Data JPA repositories for database operations
* Derived query methods where applicable

For example, retrieving the minimum-priority element with the smallest ID can be handled through repository-level ordering.

---

# 🧪 API Testing

The APIs can be tested using **Postman**.

Recommended testing sequence:

```text
1. Insert multiple elements
        ↓
2. Check /peek
        ↓
3. Check /is-empty
        ↓
4. Test /extract-min
        ↓
5. Test /extract-max
        ↓
6. Update an element
        ↓
7. Delete an element
        ↓
8. Check /is-empty again
```

It is recommended to test duplicate priorities as well to verify the ID-based tie-breaking behavior.

---

# 🔐 Configuration & Security Notes

* Database credentials should not be hard-coded in source code.
* Use environment variables for sensitive configuration.
* Update the JDBC URL when running the application with a different PostgreSQL instance.
* Ensure PostgreSQL is running and the configured database is accessible before starting the application.

---

# 📌 Important Notes for Evaluators

If you are running this project on a different system:

1. Install **JDK 17+**, Maven, and PostgreSQL.
2. Create/configure a PostgreSQL database.
3. Update the application's **JDBC URL** and database username.
4. Provide the database password through the configured environment variable.
5. Import the project as a Maven project.
6. Run the Spring Boot application.
7. Use Postman or another REST client to test the APIs.

The application is designed to expose the Priority Queue functionality through REST APIs while using PostgreSQL for persistent storage.

---

## 👨‍💻 Project Structure

```text
src
└── main
    └── java
        └── com.saralweb.assignment.SaralWeb
            ├── controller
            ├── service
            ├── repository
            ├── model
            └── SaralWebApplication.java
```

---

## 📄 License

This project was developed as part of the **SaralWeb Assignment**.
