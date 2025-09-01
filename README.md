# Microservices Dynamic Discovery Project

This project demonstrates a simple microservices architecture using **Spring Cloud Netflix Eureka** for service registration and discovery. It consists of a **Eureka Server**, a **Product Service**, and an **Order Service**. The services leverage dynamic service discovery and inter-service REST communication, and are fully dockerized for easy deployment.

---

## Table of Contents

- [Project Overview](#project-overview)
- [Architecture](#architecture)
- [Features](#features)
- [Technical Stack](#technical-stack)
- [Setup & Run](#setup--run)
- [API Endpoints](#api-endpoints)
- [Dockerization](#dockerization)

---

## Project Overview

This project demonstrates:
- Centralized service registry using Eureka Server.
- RESTful CRUD Product management.
- Order management with dynamic product validation.
- Dynamic service discovery (no hardcoded URLs).
- Containerized deployment with Docker and Docker Compose.

---

## Architecture

```
+-----------------+        REST         +------------------+         REST        +------------------+
|                 |<------------------>|                  |<------------------->|                  |
|  Order Service  |    (dynamic via    |   Eureka Server  |    (dynamic via     |  Product Service |
| (Eureka Client) |     Eureka)        |  (Eureka Server) |      Eureka)        | (Eureka Client)  |
+-----------------+                    +------------------+                    +------------------+
```
- **Order Service** and **Product Service** are Eureka clients.
- Both services register themselves with Eureka Server on startup.
- **Order Service** discovers **Product Service** dynamically via Eureka for inter-service REST calls.

---

## Features

### 1. Eureka Server Setup
- Central registry for all microservices.
- Enables dynamic registration and discovery.

### 2. Product Service
- CRUD REST APIs for managing products (Create, Read, Update, Delete).
- Registers with Eureka Server at startup.

### 3. Order Service
- Manages orders containing product ID and quantity.
- Validates existence of products by calling Product Service (via Eureka discovery).
- Registers with Eureka Server at startup.

### 4. Inter-Service REST Calls
- Order Service fetches product data during order creation by calling Product Service.

---

## Technical Stack

- **Java 21**
- **Spring Boot**
- **Spring Cloud Netflix Eureka** (Server & Client)
- **Spring Web**
- **RestTemplate** for REST calls
- **Docker & Docker Compose**

---

## Setup & Run

### Prerequisites

- Java 21
- Gradle
- Docker & Docker Compose (optional, for containerized setup)

### 1. Clone the Repository

```bash
git clone https://github.com/Aqsin211/microservices-dynamic-discovery.git
cd microservices-dynamic-discovery
```

### 2. Build All Services

Build each service using gradle (from their respective directories):

```bash
cd eureka-server
./gradlew build

cd ../product-service
./gradlew build

cd ../order-service
./gradlew build
```

### 3. Run Locally (Without Docker)

Start Eureka Server:

```bash
cd eureka-server
./gradlew bootRun
```

Start Product Service (in new terminal):

```bash
cd product-service
./gradlew bootRun
```

Start Order Service (in another terminal):

```bash
cd order-service
./gradlew bootRun
```

### 4. Access Eureka Dashboard

Visit: [http://localhost:8761](http://localhost:8761)

---

## API Endpoints

### Product Service

| Method | Endpoint           | Description                   |
|--------|--------------------|-------------------------------|
| GET    | /products          | List all products             |
| GET    | /products/{id}     | Get product by ID             |
| POST   | /products          | Create a new product          |
| PUT    | /products/{id}     | Update product                |
| DELETE | /products/{id}     | Delete product                |
| POST   | /products/order    | Place an order for a product  |

### Order Service

| Method | Endpoint         | Description                          |
|--------|------------------|--------------------------------------|
| GET    | /orders         | List all orders                      |
| GET    | /orders/{id}    | Get order by ID                      |
| POST   | /orders         | Create a new order (validates product) |
| DELETE | /orders/{id}    | Delete an order                      |

> **Note:** Order Service calls Product Service REST endpoints via Eureka discovery for product validation.

---

## Dockerization

Both Eureka Server and microservices are dockerized.

### Run with Docker Compose

A sample `docker-compose.yml` is provided:

```bash
docker-compose up --build
```

- Eureka Server: [http://localhost:8761](http://localhost:8761)
- Product Service: [http://localhost:8080](http://localhost:8080)
- Order Service: [http://localhost:8081](http://localhost:8081)
