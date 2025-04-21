# Management Applications

This repository contains two Spring Boot applications designed for managing topics and queues, along with a shared module for common resources.

## Project Structure

```
management-apps
├── topic-management-app
│   ├── A CRUD application for managing topics and subscriptions.
│   └── Runs on port 8080.
├── queue-management-app
│   ├── A CRUD application for managing queues and push notifications.
│   └── Runs on port 8081.
├── shared
│   └── Contains shared resources used by both applications.
├── start-services.sh
│   └── Script to start both services.
└── pom.xml
    └── Parent POM for managing dependencies and modules.
```

## Applications Overview

### Topic Management Application
- Provides endpoints for creating, deleting, publishing, and subscribing to topics.
- Database: H2 in-memory database.
- Port: `8080`.

### Queue Management Application
- Provides endpoints for creating queues, pushing messages, and pulling messages.
- Includes authentication and authorization using JWT.
- Database: H2 in-memory database.
- Port: `8081`.

### Shared Module
- Contains common resources such as the `User` model.

## Prerequisites
- Java 17 or higher.
- Maven 3.8 or higher.

## Setup Instructions

1. **Clone the repository:**
   ```bash
   git clone <repository-url>
   cd management-apps
   ```

2. **Build the project:**
   ```bash
   mvn clean install
   ```

3. **Start the services:**
   ```bash
   ./start-services.sh
   ```

4. **Access the APIs:**
   - Topic Management: `http://localhost:8080`
   - Queue Management: `http://localhost:8081`

## API Endpoints

### Topic Management Application

| Endpoint                          | Method   | Description                              | Request Body                          |
|-----------------------------------|----------|------------------------------------------|---------------------------------------|
| `/topics`                         | POST     | Create a new topic                      | `{ "name": "topicName" }`            |
| `/topics/{topicId}`               | DELETE   | Delete a topic                          | None                                  |
| `/topics/{topicId}/publish`       | POST     | Publish a message to a topic            | `{ "message": "Your message here" }` |
| `/topics/{topicId}/subscribe`     | GET      | Subscribe to a topic                    | None                                  |
| `/topics`                         | GET      | Get all topics                          | None                                  |

### Queue Management Application

| Endpoint                          | Method   | Description                              | Request Body                          |
|-----------------------------------|----------|------------------------------------------|---------------------------------------|
| `/auth/login`                     | POST     | Login and obtain a JWT token            | `{ "username": "yourUsername", "password": "yourPassword" }` |
| `/auth/register`                  | POST     | Register a new user                     | `{ "username": "yourUsername", "password": "yourPassword" }` |
| `/queues`                         | POST     | Create a new queue                      | `{ "name": "queueName" }`            |
| `/queues/{queueName}`             | DELETE   | Delete a queue                          | None                                  |
| `/queues/{queueName}/messages`    | POST     | Push a message to a queue               | `{ "message": "Your message here" }` |
| `/queues/{queueName}/messages`    | GET      | Pull messages from a queue              | None                                  |
| `/queues`                         | GET      | Get all queues                          | None                                  |
## License
This project is licensed under the MIT License. See the LICENSE file for more details.  