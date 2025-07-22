# 🎟️ EventBite Backend

[![Java](https://img.shields.io/badge/Java-17-orange.svg)](https://openjdk.org/projects/jdk/17/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![Docker](https://img.shields.io/badge/Docker-Ready-blue.svg)](https://www.docker.com/)
[![PostgreSQL](https://img.shields.io/badge/Database-PostgreSQL-336791.svg)](https://www.postgresql.org/)

> A robust Spring Boot-based REST API for comprehensive event management, featuring secure authentication, cloud-based persistence, and production-ready deployment capabilities.

## ✨ Features

### 🔒 **Security & Authentication**
- JWT-based stateless authentication
- BCrypt password hashing for enhanced security
- Spring Security integration for robust access control

### 🗄️ **Database & Persistence**
- Cloud-managed Supabase PostgreSQL database
- JPA/Hibernate for elegant entity modeling
- Optimized database queries and relationships

### 🚀 **Deployment & Infrastructure**
- Fully containerized with Docker
- Automated CI/CD pipeline via Google Cloud Run
- Health checks and monitoring built-in

<!-- ### 📊 **Observability**
- Structured logging with SLF4J + Logback
- Prometheus metrics integration
- Grafana dashboards for performance monitoring -->

---

## 🏗️ Tech Stack

<table>
<tr>
<td><strong>Backend Framework</strong></td>
<td>Spring Boot, Spring Security</td>
</tr>
<tr>
<td><strong>Language</strong></td>
<td>Java 17</td>
</tr>
<tr>
<td><strong>Database</strong></td>
<td>PostgreSQL (Supabase)</td>
</tr>
<tr>
<td><strong>ORM</strong></td>
<td>Hibernate (JPA)</td>
</tr>
<tr>
<td><strong>Authentication</strong></td>
<td>JWT (JSON Web Tokens)</td>
</tr>
<tr>
<td><strong>Containerization</strong></td>
<td>Docker</td>
</tr>
<tr>
<td><strong>Deployment</strong></td>
<td>Google Cloud Run</td>
</tr>
  
<!-- <tr>
<td><strong>Monitoring</strong></td>
<td>Prometheus + Grafana</td>
</tr>  -->

</table>

---

## 📁 Project Architecture

```
eventbite-backend/
├── src/
│   ├── main/
│   │   ├── java/com/eventbite/eventbite_backend/
│   │   │   ├── Config/           # Security COnfigurations
│   │   │   ├── Controller/       # REST API Controllers
│   │   │   ├── DTO/              # Data Transfer Classes
│   │   │   ├── Entity/           # JPA Entities & Data Models
│   │   │   ├── Exception/        # Custom exceptions and Mappers
│   │   │   ├── Filter/           # JWT Filter setup
│   │   │   ├── Repository/       # Data Access Layer (JPA)
│   │   │   └── Service/          # Business Logic Layer
│   │   └── resources/
│   │       └── application.properties
│   └── test/                     # Unit & Integration Tests
├── dockerfile
└── README.md
```

---

## 🚀 Quick Start

### Prerequisites
- Java 17 or higher
- Maven 3.6+
- Docker (optional)
- PostgreSQL database (or Supabase account)

### 1. Clone the Repository
```bash
git clone https://github.com/yourusername/eventbite-backend.git
cd eventbite-backend
```

### 2. Configure Environment Variables
Create an `application.properties` file or set environment variables:

```properties
# Database Configuration
spring.datasource.url=${DB_URL:jdbc:postgresql://localhost:5432/eventbite}
spring.datasource.username=${DB_USERNAME:your_username}
spring.datasource.password=${DB_PASSWORD:your_password}

# Server Configuration
server.port=${PORT:8080}
```

### 3. Run the Application

#### Using Maven:
```bash
mvn clean install
mvn spring-boot:run
```

#### Using Docker:
```bash
docker build -t eventbite-backend .
docker run -p 8080:8080 eventbite-backend
```

### 4. Verify Installation
Visit `http://localhost:8080/health` to confirm the application is running.

---

## 🌐 API Documentation

### Authentication Endpoints
| Method | Endpoint | Description | Auth Required |
|--------|----------|-------------|---------------|
| `POST` | `/api/auth/register` | Register new user | ❌ |
| `POST` | `/api/auth/login` | User authentication | ❌ |

### Event Management
| Method | Endpoint | Description | Auth Required |
|--------|----------|-------------|---------------|
| `GET` | `/api/events` | Fetch public events | ❌ |
| `POST` | `/api/events/create` | Create new event | ✅ |
| `GET` | `/api/events/{id}` | Get event details | ❌ |
| `PUT` | `/api/events/{id}` | Update event | ✅ |
| `DELETE` | `/api/events/{id}` | Delete event | ✅ |

### Registration Endpoints
| Method | Endpoint | Description | Auth Required |
|--------|----------|-------------|---------------|
| `POST` | `/api/register/user` | Register user to event | ✅ |
| `POST` | `/api/register/guest` | Guest registration (email only) | ❌ |

### Example Request
```bash
# Register a new user
curl -X POST http://localhost:8080/api/auth/register \
  -H "Content-Type: application/json" \
  -d '{"username": "john_doe", "email": "john@example.com", "password": "securePassword123"}'

# Login and get JWT token
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{"email": "john@example.com", "password": "securePassword123"}'
```

---

## 🐳 Docker Deployment

### Dockerfile
```dockerfile
FROM maven:3.9.4-eclipse-temurin-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

FROM eclipse-temurin:17
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
```

<!-- ### Docker Compose (Development)
```yaml
version: '3.8'
services:
  app:
    build: .
    ports:
      - "8080:8080"
    environment:
      - DB_URL=jdbc:postgresql://db:5432/eventbite
      - DB_USERNAME=eventbite_user
      - DB_PASSWORD=your_password
    depends_on:
      - db
      
  db:
    image: postgres:15
    environment:
      - POSTGRES_DB=eventbite
      - POSTGRES_USER=eventbite_user
      - POSTGRES_PASSWORD=your_password
    ports:
      - "5432:5432"
``` -->

---

## ⚙️ Production Deployment (Google Cloud Run)

### Environment Variables
Set the following in your Render dashboard:
- `DB_URL`: Your Supabase PostgreSQL connection string
- `DB_USERNAME`: Database username
- `DB_PASSWORD`: Database password
- `PORT`: Application port (defaults to 10000)

### Health Check Endpoint
Render automatically monitors: `/health`

### CI/CD Pipeline
Automatic deployments trigger on pushes to the `deploy` branch.

---

## 🧪 Testing

### Run Unit Tests
```bash
mvn test
```

### Run Integration Tests
```bash
mvn verify
```

### Test Coverage
```bash
mvn jacoco:report
```

<!--
## 📊 Monitoring & Observability

### Metrics Endpoint
- **Prometheus**: `/actuator/prometheus`
- **Health Check**: `/actuator/health`
- **Application Info**: `/actuator/info`

### Logging
Structured JSON logging is configured for production environments with appropriate log levels and formatting.

### Grafana Dashboard
Pre-configured dashboard templates available for monitoring:
- Application performance metrics
- Database connection health
- Request/response statistics
- Error tracking and alerting
-->
---

## 🔮 Roadmap

### 🎯 Upcoming Features
- [ ] **React Frontend**
- [ ] **Admin Dashboard** - Comprehensive analytics and user management
- [ ] **Email Notifications** - Event reminders and updates
- [ ] **Event Categories & Tags** - Enhanced event organization
- [ ] **Payment Integration** - Payment support for paid events
<!-- - [ ] **Mobile Push Notifications** - Firebase integration
- [ ] **Advanced Search** - Elasticsearch integration -->

---

<div align="center">


</div>
