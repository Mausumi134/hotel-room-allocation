# 🏨 Hotel Room Allocation System

A smart room allocation optimization tool for hotels that automatically allocates premium and economy rooms to guests based on their willingness to pay, with intelligent upgrade logic.

## 📋 Table of Contents

- [Overview](#overview)
- [Features](#features)
- [Business Rules](#business-rules)
- [Technology Stack](#technology-stack)
- [Getting Started](#getting-started)
- [API Documentation](#api-documentation)
- [Testing](#testing)
- [Project Structure](#project-structure)

## 🎯 Overview

This system helps hotels optimize room allocation by:
- Automatically categorizing guests into Premium (≥€100) and Economy (<€100) segments
- Allocating rooms to maximize revenue
- Implementing smart upgrade logic when beneficial
- Handling overbooking scenarios efficiently

## ✨ Features

- **RESTful API** - Simple POST endpoint for room allocation
- **Smart Upgrade Logic** - Automatically upgrades economy guests to premium rooms when optimal
- **Revenue Optimization** - Prioritizes highest-paying guests
- **Interactive Documentation** - Swagger UI for easy API testing
- **Comprehensive Testing** - 10 test cases covering all scenarios
- **Docker Ready** - Configured for containerized deployment

## 📐 Business Rules

### Room Types
- **Premium Rooms** - For high-paying guests (≥ EUR 100)
- **Economy Rooms** - For budget guests (< EUR 100)

### Allocation Logic
1. **Premium guests (≥ EUR 100)** - ONLY allocated to Premium rooms
2. **Economy guests (< EUR 100)** - Allocated to Economy rooms by default
3. **Smart Upgrade** - If Premium rooms are empty AND Economy rooms are full, highest-paying Economy guests get upgraded to Premium rooms
4. **Overbooking** - When more guests than rooms, only highest-paying guests get accommodated

## 🛠️ Technology Stack

- **Java 17** - Programming language
- **Spring Boot 3.3.5** - Web framework
- **Maven** - Build tool
- **JUnit 5** - Testing framework
- **Swagger/OpenAPI 3** - API documentation
- **Docker** - Containerization (eclipse-temurin:21-jdk-jammy)

## 🚀 Getting Started

### Prerequisites

- Java 17 or higher
- Maven (included via wrapper)

### Build and Run

```bash
# Make the script executable
chmod +x run.sh

# Build and start the application
./run.sh
```

The application will start on **port 8080** by default.

### Alternative: Manual Build

```bash
# Build the project
./mvnw clean package

# Run the application
java -jar target/room-allocation-1.0.0.jar
```

### Run on Different Port

```bash
java -jar target/room-allocation-1.0.0.jar --server.port=8083
```

## 📚 API Documentation

### Swagger UI (Interactive)

Once the application is running, access the interactive API documentation:

```
http://localhost:8080/swagger-ui.html
```

### OpenAPI Specification

```
http://localhost:8080/api-docs
```

### API Endpoint

**POST** `/occupancy`

**Request Body:**
```json
{
  "premiumRooms": 7,
  "economyRooms": 5,
  "potentialGuests": [23, 45, 155, 374, 22, 99.99, 100, 101, 115, 209]
}
```

**Response:**
```json
{
  "usagePremium": 6,
  "revenuePremium": 1054.0,
  "usageEconomy": 4,
  "revenueEconomy": 189.99
}
```

### Example with cURL

```bash
curl -X POST http://localhost:8080/occupancy \
  -H "Content-Type: application/json" \
  -d '{
    "premiumRooms": 7,
    "economyRooms": 5,
    "potentialGuests": [23, 45, 155, 374, 22, 99.99, 100, 101, 115, 209]
  }'
```

## 🧪 Testing

### Run All Tests

```bash
./mvnw test
```

### Test Coverage

The project includes **10 comprehensive test cases**:

1. **Test Case 1** - 3 Premium, 3 Economy rooms
2. **Test Case 2** - 7 Premium, 5 Economy rooms (main requirement)
3. **Test Case 3** - 2 Premium, 7 Economy rooms
4. **Smart Upgrade Test** - Economy guests upgrade to premium
5. **No Upgrade Test** - When economy rooms aren't full
6. **Boundary Test** - 100 EUR edge case
7. **Edge Case** - No guests
8. **Edge Case** - No rooms available
9. **API Validation** - Controller endpoint tests
10. **Invalid Input** - Negative room numbers

### Test Results

```
Tests run: 10, Failures: 0, Errors: 0, Skipped: 0
✅ BUILD SUCCESS
```

## 📁 Project Structure

```
hotel-room-allocation/
├── src/
│   ├── main/
│   │   ├── java/com/hotel/
│   │   │   ├── HotelRoomAllocationApplication.java  # Main application
│   │   │   ├── config/
│   │   │   │   └── OpenApiConfig.java              # Swagger configuration
│   │   │   ├── controller/
│   │   │   │   └── OccupancyController.java        # REST API endpoint
│   │   │   ├── dto/
│   │   │   │   ├── OccupancyRequest.java           # Request model
│   │   │   │   └── OccupancyResponse.java          # Response model
│   │   │   └── service/
│   │   │       └── RoomAllocationService.java      # Business logic
│   │   └── resources/
│   │       └── application.properties              # Configuration
│   └── test/
│       └── java/com/hotel/
│           ├── controller/
│           │   └── OccupancyControllerTest.java    # API tests
│           └── service/
│               └── RoomAllocationServiceTest.java  # Business logic tests
├── pom.xml                                         # Maven configuration
├── run.sh                                          # Startup script
└── README.md                                       # This file
```

## 🎯 Test Scenarios

### Scenario 1: Basic Allocation
- **Input:** 3 Premium rooms, 3 Economy rooms
- **Guests:** [23, 45, 155, 374, 22, 99.99, 100, 101, 115, 209]
- **Result:** 3 Premium used (€738), 3 Economy used (€167.99)

### Scenario 2: Main Requirement
- **Input:** 7 Premium rooms, 5 Economy rooms
- **Guests:** [23, 45, 155, 374, 22, 99.99, 100, 101, 115, 209]
- **Result:** 6 Premium used (€1054), 4 Economy used (€189.99)

### Scenario 3: Smart Upgrade
- **Input:** 2 Premium rooms, 7 Economy rooms
- **Guests:** [23, 45, 155, 374, 22, 99.99, 100, 101, 115, 209]
- **Result:** 2 Premium used (€583), 4 Economy used (€189.99)

## 🐳 Docker Deployment

The application is configured to run in Docker container:

```bash
# The run.sh script will be executed in the container
# Container: eclipse-temurin:21-jdk-jammy (Ubuntu 22.04)
./run.sh
```

**Note:** Do not use docker or docker-compose inside the run.sh script.

## 🔧 Configuration

### Application Properties

```properties
server.port=8080
spring.application.name=hotel-room-allocation

# Swagger Configuration
springdoc.api-docs.path=/api-docs
springdoc.swagger-ui.path=/swagger-ui.html
```

## 📊 Algorithm Overview

1. **Sort guests** by payment amount (highest first)
2. **Categorize** into Premium (≥€100) and Economy (<€100)
3. **Allocate Premium guests** to Premium rooms
4. **Allocate Economy guests** to Economy rooms
5. **Smart Upgrade:** If Premium rooms remain empty AND Economy rooms are full:
   - Upgrade highest-paying Economy guests to Premium rooms
6. **Reject** remaining guests if no rooms available

## 🤝 Contributing

This is a technical assessment project. For production use, consider:
- Adding authentication/authorization
- Implementing database persistence
- Adding logging and monitoring
- Implementing rate limiting
- Adding more validation rules

## 📄 License

This project is created as a technical assessment.

## 👤 Author

Hotel Management System

## 🔗 Links

- [Swagger UI](http://localhost:8080/swagger-ui.html) - Interactive API documentation
- [OpenAPI Spec](http://localhost:8080/api-docs) - API specification in JSON format

---

**Built with ❤️ using Spring Boot**
