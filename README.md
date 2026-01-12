# Hotel Room Allocation System

A Spring Boot REST API for optimizing hotel room allocation based on guest willingness to pay.

## Business Rules

- **Premium Rooms**: For guests paying EUR 100+
- **Economy Rooms**: For guests paying below EUR 100
- **Smart Upgrade**: Economy guests can be upgraded to Premium rooms if Premium rooms are empty and Economy rooms are full
- **Overbooking**: Only highest-paying guests get rooms when demand exceeds supply

## API Endpoint

**POST** `/occupancy`

### Request Body
```json
{
  "premiumRooms": 7,
  "economyRooms": 5,
  "potentialGuests": [23, 45, 155, 374, 22, 99.99, 100, 101, 115, 209]
}
```

### Response
```json
{
  "usagePremium": 6,
  "revenuePremium": 1054,
  "usageEconomy": 4,
  "revenueEconomy": 189.99
}
```

## Running the Application

### Prerequisites
- Java 21 (Eclipse Temurin JDK)
- Maven (or use the included Maven Wrapper)

### Build and Run
```bash
# Using Maven Wrapper (recommended)
./mvnw spring-boot:run

# Or build and run JAR
./mvnw clean package
java -jar target/room-allocation-1.0.0.jar
```

### Using run.sh script
```bash
chmod +x run.sh
./run.sh
```

The application will start on port 8080.

## Testing

Run tests with:
```bash
./mvnw test
```

## Test Cases

1. **3 Premium, 3 Economy rooms**: Usage Premium: 3 (EUR 738), Usage Economy: 3 (EUR 167.99)
2. **7 Premium, 5 Economy rooms**: Usage Premium: 6 (EUR 1054), Usage Economy: 4 (EUR 189.99)
3. **2 Premium, 7 Economy rooms**: Usage Premium: 2 (EUR 583), Usage Economy: 4 (EUR 189.99)