# Queue Management Application

This project is a simple CRUD application for managing queues and push notifications using Java and Spring Boot. It provides endpoints for creating new queues, pushing messages to queues, and pulling messages from queues. Authentication is required to use the service.

## Project Structure

```
queue-management-app
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com
│   │   │       └── example
│   │   │           ├── QueueManagementApplication.java
│   │   │           ├── controller
│   │   │           │   ├── QueueController.java
│   │   │           │   └── AuthController.java
│   │   │           ├── service
│   │   │           │   └── QueueService.java
│   │   │           ├── model
│   │   │           │   ├── Queue.java
│   │   │           │   └── LoginRequest.java
│   │   │           └── security
│   │   │               ├── JwtUtil.java
│   │   │               └── SecurityConfig.java
│   │   └── resources
│   │       ├── application.properties
│   │       └── data.sql
├── pom.xml
└── README.md
```

## Setup Instructions

1. **Clone the repository:**
   ```
   git clone <repository-url>
   cd queue-management-app
   ```

2. **Build the project:**
   ```
   mvn clean install
   ```

3. **Run the application:**
   ```
   mvn spring-boot:run
   ```

4. **Access the API:**
   The application will be running on `http://localhost:8080`. You can use tools like Postman or curl to interact with the API.

## Authentication

All endpoints (except `/auth/login`) require authentication. You must first log in to obtain a JWT token.

### Login
- **POST** `/auth/login`
- **Request Body:**
  ```json
  {
    "username": "yourUsername",
    "password": "yourPassword"
  }
  ```
- **Response:**
  ```json
  {
    "token": "your-jwt-token"
  }
  ```

Use the token in the `Authorization` header for all subsequent requests:
```
Authorization: Bearer your-jwt-token
```

## API Endpoints

### Create a New Queue
- **POST** `/queues`
- **Request Body:**
  ```json
  {
    "name": "queueName"
  }
  ```
- **Response:**
  ```json
  {
    "id": 1,
    "name": "queueName",
    "owner": "yourUsername"
  }
  ```

### Push a Message to a Queue
- **POST** `/queues/{queueId}/messages`
- **Request Body:**
  ```json
  {
    "message": "Your message here"
  }
  ```
- **Response:**
  ```json
  {
    "id": 1,
    "queueId": 1,
    "message": "Your message here"
  }
  ```

### Pull Messages from a Queue
- **GET** `/queues/{queueId}/messages`
- **Response:**
  ```json
  [
    {
      "id": 1,
      "queueId": 1,
      "message": "Your message here"
    }
  ]
  ```

### Delete a Queue
- **DELETE** `/queues/{queueId}`
- **Note:** Only the owner of the queue can delete it.
- **Response:**
  ```json
  {
    "message": "Queue deleted successfully"
  }
  ```

## Database Initialization

The `data.sql` file contains SQL statements to initialize the database with sample data. You can modify this file to add your own initial data.

## Dependencies

This project uses Maven for dependency management. The `pom.xml` file includes necessary dependencies for Spring Boot, Spring Security, and JWT.

## License

This project is licensed under the MIT License. See the LICENSE file for more details.