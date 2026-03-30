# 📦 MisEnvios API

Backend service for tracking and managing shipments.

This project was developed as a personal learning project while studying **Software Engineering at UTN (Argentina)**, with the goal of learning **Java and its backend frameworks**, particularly **Spring Boot**, while building a real REST API.

The application allows users to register, authenticate and manage shipment tracking information.

At the moment, shipment tracking works only with **e-commerce tracking codes from Correo Argentino and Andreani**.

---

## 🚀 Tech Stack

- **Java**
- **Spring Boot**
- **PostgreSQL**
- **Docker**
- **REST API**

---

## ✨ Features

- User registration and authentication
- Shipment tracking management
- REST API architecture
- Data persistence using PostgreSQL
- Containerized backend with Docker

---

## 📁 Project Structure

```
misenvios-api
│
├── src/main/java
│   ├── controller
│   ├── service
│   ├── repository
│   ├── model
│   ├── dto
│   └── config
│
├── src/main/resources
│   └── application.properties
│
├── Dockerfile
├── pom.xml
└── README.md
```

---

# 📦 Supported Tracking Codes

Currently the system supports **e-commerce shipment codes** from:

- **Correo Argentino**
- **Andreani**

Examples of valid tracking codes:

### Correo Argentino (e-commerce)

```
00005526919092E105T0701
```

### Andreani

```
360002897729630
```

These codes can be added to the system to track shipments.

---

## ⚙️ Running the Project

### Clone the repository

```
git clone https://github.com/your-username/misenvios-api.git
cd misenvios-api
```

---

### Run with Maven

```
./mvnw spring-boot:run
```

or

```
mvn spring-boot:run
```

---

### Run with Docker

Build the container:

```
docker build -t misenvios-api .
```

Run the container:

```
docker run -p 8080:8080 misenvios-api
```

---

# 🔗 API Endpoints

## Authentication

### Register user

```
POST /auth/register
```

Creates a new user account.

Example request body:

```
{
  "firstName": "John",
  "lastName": "Doe",
  "email": "john@email.com",
  "password": "password",
  "address": "Street 123"
}
```

---

### Login

```
POST /auth/login
```

Authenticates the user and returns a JWT token used to access protected endpoints.

Example request body:

```
{
  "email": "john@email.com",
  "password": "password"
}
```

---

## Shipments

### Get all shipments

```
GET /shipments
```

Returns all shipments associated with the authenticated user.

---

### Create shipment

```
POST /shipments
```

Creates a new shipment to track.

Example request body:

```
{
  "trackingCode": "360002897729630",
  "name": "Package from Andreani"
}
```

---

### Get shipment by ID

```
GET /shipments/{id}
```

Returns the information of a specific shipment.

---

### Delete shipment

```
DELETE /shipments/{id}
```

Deletes a shipment from the user's tracking list.
