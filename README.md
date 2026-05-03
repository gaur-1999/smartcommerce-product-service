# SmartCommerce Product Service

Microservices-based e-commerce backend — Product management service.

## Tech Stack
- Java 17
- Spring Boot 4.x
- Spring Security
- JWT Authentication
- MySQL
- Docker

## Features
- Add, Update, Delete Products
- Category wise filtering
- Product Search by name
- JWT Token verification
- Only creator can update/delete their products
- Global Exception Handling

## API Endpoints

| Method | URL | Description | Auth |
|--------|-----|-------------|------|
| POST | /api/products | Add new product | Yes |
| GET | /api/products | Get all products | No |
| GET | /api/products/{id} | Get product by ID | No |
| GET | /api/products/search?name= | Search products | No |
| GET | /api/products/category/{category} | Filter by category | No |
| PUT | /api/products/{id} | Update product | Yes |
| DELETE | /api/products/{id} | Delete product | Yes |

## Categories
ELECTRONICS, CLOTHING, FOOD, BOOKS, OTHER

## Setup

1. Clone the repo
2. Create MySQL database: `smartcommerce_products`
3. Copy `application.properties.example` to `application.properties`
4. Add your MySQL password
5. Run the application — Port 8082

## Related Services
- [User Service](https://github.com/gaur-1999/smartcommerce-user-service)
- [Order Service](https://github.com/gaur-1999/smartcommerce-order-service)
- [Notification Service](https://github.com/gaur-1999/smartcommerce-notification-service)
