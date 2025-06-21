![Logo](https://download.logo.wine/logo/Spring_Framework/Spring_Framework-Logo.wine.png)

# Spring Boot Clean Architecture Template

A ready-to-use Spring Boot template following Clean Architecture principles. This template provides a structured foundation for your Java projects with clear separation of concerns.

## Tech Stack

- ![Java](https://img.shields.io/badge/-Java-007396?logo=java&logoColor=white)
- ![Spring Boot](https://img.shields.io/badge/-Spring%20Boot-6DB33F?logo=spring-boot&logoColor=white)
- ![PostgreSQL](https://img.shields.io/badge/-PostgreSQL-336791?logo=postgresql&logoColor=white)
- ![Docker](https://img.shields.io/badge/-Docker-2496ED?logo=docker&logoColor=white)
- ![Docker Compose](https://img.shields.io/badge/-Docker%20Compose-2496ED?logo=docker&logoColor=white)

## Project Structure

```
src
└── main
├── java
│ └── com
│ └── example
│ ├── ms_users_java
│ │ ├── MsUsersJavaApplication.java
│ │
│ ├── domain
│ │ └── user
│ │ ├── model
│ │ ├── repository
│ │ └── service
│ │
│ ├── infrastructure
│ │ ├── config
│ │ └── persistence
│ │ ├── entity
│ │ ├── mapper
│ │ └── repository
│ │
│ ├── application
│ │ └── user
│ │ ├── controller
│ │ └── dto
│ │
│ └── shared
│
└── resources
├── application.properties
└── ...
```

## Prerequisites

- Docker installed on your machine

## Getting Started

1. Clone this repository:
   ```bash
   git clone https://github.com/your-username/springboot-clean-architecture-template.git
2. Navigate to the project directory:
   ```bash
   cd springboot-clean-architecture-template
   ```
3. Build and run the application using Make commands:
   ```bash
   make build
   ```
4. Access the application at `http://localhost:8080`.

## Makefile Commands
- `make build`: Build the application.
- `make start`: Start the application.
- `make down`: Stop the application.
- `make clean`: Stop and remove containers, networks, volumes, and orphaned containers.
- `make help`: Display help information for available commands.

## Author
- [@jorge-andrade00](https://github.com/Jorge-Andrade00)