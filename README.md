# TicketHub - Event Driven Microservices

TicketHub is a scalable, distributed ticket booking system (BookMyShow clone) built with **Spring Boot 3**, **Apache Kafka**, and **Redis**. It implements the **Saga Design Pattern** for distributed transactions and handles high concurrency using Distributed Locking.

## Key Features
* **Microservices Architecture:** Deployed 7 distinct services using Spring Cloud.
* **Event-Driven Sagas:** Orchestrated booking & payment flows using Kafka.
* **Concurrency Handling:** Solved the "Double Booking" problem using **Redis Distributed Locks**.
* **Gateway & Security:** Centralized API Gateway with JWT Authentication.
* **Service Discovery:** Dynamic scaling with Netflix Eureka.
* **Resilience:** Circuit Breakers and Retry mechanisms.

## Tech Stack
* **Backend:** Java 17, Spring Boot 3.2
* **Messaging:** Apache Kafka, Zookeeper
* **Database:** PostgreSQL (Per-service DB pattern), Redis (Caching & Locking)
* **Infrastructure:** Docker, Docker Compose
* **Testing:** JUnit 5, Mockito, JMeter (Concurrency)

## Architecture
The system consists of the following microservices:
1.  **API Gateway:** Entry point, routing, and SSL termination.
2.  **User Service:** JWT Identity management.
3.  **Catalog Service:** Manages movies, theatres, and shows.
4.  **Booking Service:** Handles ticket reservation and Sagas.
5.  **Payment Service:** Processes mock payments asynchronously.
6.  **Notification Service:** Sends email alerts via SMTP.
7.  **Eureka Server:** Service Registry.

```mermaid
graph TD
    User([User]) -->|HTTP| Gateway[API Gateway]
    
    subgraph Infrastructure
        Eureka[Eureka Server]
        Kafka{Apache Kafka}
        Redis[(Redis Cache)]
    end
    
    Gateway -->|Auth| UserSvc[User Service]
    Gateway -->|Data| CatalogSvc[Catalog Service]
    Gateway -->|Order| BookingSvc[Booking Service]
    
    BookingSvc -->|Lock| Redis
    BookingSvc -->|Event| Kafka
    
    Kafka -->|Consume| PaymentSvc[Payment Service]
    PaymentSvc -->|Response| Kafka
    
    Kafka -->|Consume| NotifSvc[Notification Service]
    
    UserSvc --> DB1[(User DB)]
    CatalogSvc --> DB2[(Catalog DB)]
    BookingSvc --> DB3[(Booking DB)]
```

## How to Run
### Prerequisites
* Docker & Docker Compose
* Java 17+ (Optional, if running locally)

### Quick Start (Docker)
1.  Clone the repository:
    ```bash
    git clone [https://github.com/your-username/ticket-hub.git](https://github.com/your-username/ticket-hub.git)
    cd ticket-hub
    ```
2.  Build and run all services:
    ```bash
    docker-compose up --build -d
    ```
3.  Access the services:
    * **Swagger UI:** [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)
    * **Eureka Dashboard:** [http://localhost:8761](http://localhost:8761)

## API Endpoints

### Auth & User
| Method | Endpoint | Description |
| :--- | :--- | :--- |
| **POST** | `/auth/register` | Register a new User |
| **POST** | `/auth/login` | Authenticate & get JWT |
| **POST** | `/admin/register` | Register a new Admin (Requires Admin JWT) |
| **GET** | `/users/status` | User Service Health Check |

### Catalog (Movies & Theatres)
| Method | Endpoint | Description |
| :--- | :--- | :--- |
| **GET** | `/movies` | Get all Movies |
| **GET** | `/movies/{id}` | Get specific Movie details |
| **POST** | `/movies` | Add a new Movie (Admin) |
| **GET** | `/theatres` | Get all Theatres |
| **POST** | `/theatres` | Add a new Theatre (Admin) |
| **POST** | `/theatres/{id}/halls` | Add a Hall to a Theatre |
| **PUT** | `/halls/{id}/seats` | Configure Seat Layout |

### Booking & Shows
| Method | Endpoint | Description |
| :--- | :--- | :--- |
| **GET** | `/shows` | Get all scheduled Shows |
| **POST** | `/shows` | Schedule a Movie Show (Admin) |
| **POST** | `/bookings` | **Book Ticket** (Starts Saga Transaction) |
| **GET** | `/bookings` | Booking Service Health Check |
