# Queue Management Application

This project is a simple CRUD application for managing queues and push notifications using Java and Spring Boot. It provides endpoints for creating new queues, pushing messages to queues, and pulling messages from queues.

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
│   │   │           │   └── QueueController.java
│   │   │           ├── service
│   │   │           │   └── QueueService.java
│   │   │           └── model
│   │   │               └── Queue.java
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

## API Endpoints

- **Create a new queue**
  - **POST** `/queues`
  - Request Body: `{ "name": "queueName" }`

- **Push a message to a queue**
  - **POST** `/queues/{queueId}/messages`
  - Request Body: `{ "message": "Your message here" }`

- **Pull messages from a queue**
  - **GET** `/queues/{queueId}/messages`

## Database Initialization

The `data.sql` file contains SQL statements to initialize the database with sample data. You can modify this file to add your own initial data.

## Dependencies

This project uses Maven for dependency management. The `pom.xml` file includes necessary dependencies for Spring Boot and other libraries.

## License

This project is licensed under the MIT License. See the LICENSE file for more details.