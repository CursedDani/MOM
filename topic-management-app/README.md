# Topic Management Application

This project is a simple CRUD application for managing topics and messages using Java and Spring Boot. It provides endpoints for creating, deleting, publishing, and subscribing to topics.

## Project Structure

```
topic-management-app
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com
│   │   │       └── example
│   │   │           ├── TopicManagementApplication.java
│   │   │           ├── controller
│   │   │           │   └── TopicController.java
│   │   │           ├── service
│   │   │           │   └── TopicService.java
│   │   │           ├── model
│   │   │           │   └── Topic.java
│   │   │           └── repository
│   │   │               └── TopicRepository.java
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
   cd topic-management-app
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

- **Create a new topic**
  - **POST** `/topics`
  - Request Body: `{ "name": "topicName" }`

- **Delete a topic**
  - **DELETE** `/topics/{topicId}`

- **Publish a message to a topic**
  - **POST** `/topics/{topicId}/publish`
  - Request Body: `{ "message": "Your message here" }`

- **Subscribe to a topic**
  - **GET** `/topics/{topicId}/subscribe`

## Database Initialization

The `data.sql` file contains SQL statements to initialize the database with sample topics. You can modify this file to add your own initial data.

## Dependencies

This project uses Maven for dependency management. The `pom.xml` file includes necessary dependencies for Spring Boot and other libraries.

## License

This project is licensed under the MIT License. See the LICENSE file for more details.