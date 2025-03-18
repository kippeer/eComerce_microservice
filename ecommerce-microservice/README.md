# E-commerce Microservice

A robust e-commerce microservice built with Spring Boot, featuring user authentication, product management, and order processing capabilities.

## Features

- User Management
  - Registration and Authentication
  - JWT-based security
  - Role-based access control
- Product Management
  - CRUD operations for products
  - Stock management
  - Price handling
- Order Processing
  - Order creation and management
  - Order status tracking
  - Multiple status support (PENDING, CONFIRMED, SHIPPED, DELIVERED, CANCELLED)

## Technology Stack

- Java 11
- Spring Boot 2.7.0
- Spring Security
- Spring Data JPA
- PostgreSQL
- Redis (for caching)
- Swagger/OpenAPI (API documentation)
- JUnit 5 & Mockito (testing)
- Docker & Docker Compose

## Prerequisites

- Java 11 or higher
- Maven 3.6+
- Docker and Docker Compose
- PostgreSQL 13+
- Redis 6+

## Getting Started

1. Clone the repository:
   ```bash
   git clone https://github.com/yourusername/ecommerce-microservice.git
   cd ecommerce-microservice
   ```

2. Start the required services using Docker Compose:
   ```bash
   cd docker
   docker-compose up -d
   ```

3. Build the application:
   ```bash
   mvn clean install
   ```

4. Run the application:
   ```bash
   mvn spring-boot:run
   ```

The application will be available at `http://localhost:8080`

## API Documentation

Once the application is running, you can access the Swagger UI at:
```
http://localhost:8080/swagger-ui/
```

## API Endpoints

### Users
- POST `/api/users/register` - Register a new user
- POST `/api/users/login` - Authenticate user and get JWT token
- GET `/api/users/profile` - Get current user profile

### Products
- GET `/api/products` - List all products
- GET `/api/products/{id}` - Get product by ID
- POST `/api/products` - Create a new product
- PUT `/api/products/{id}` - Update a product
- DELETE `/api/products/{id}` - Delete a product

### Orders
- GET `/api/orders` - List all orders
- GET `/api/orders/{id}` - Get order by ID
- POST `/api/orders` - Create a new order
- PUT `/api/orders/{id}/status` - Update order status

## Database Configuration

The application uses PostgreSQL as its primary database. Configuration can be found in `application.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/ecommerce
spring.datasource.username=postgres
spring.datasource.password=postgres
```

## Testing

The project includes comprehensive unit tests for all service layers. To run the tests:

```bash
mvn test
```

## Docker Support

The application can be containerized using the provided Dockerfile. To build and run the Docker image:

```bash
docker build -t ecommerce-microservice .
docker run -p 8080:8080 ecommerce-microservice
```

## Contributing

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## License

This project is licensed under the MIT License - see the LICENSE file for details.