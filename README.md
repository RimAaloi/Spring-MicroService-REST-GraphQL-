# Bank Account Service

A Spring Boot application that provides REST and GraphQL APIs for managing bank accounts and customers.

## Technologies Used

- **Spring Boot**: Framework for building Java applications
- **Spring Data JPA**: For database access and ORM
- **H2 Database**: In-memory database for development and testing
- **GraphQL**: For flexible API queries
- **Lombok**: To reduce boilerplate code
- **Spring Web**: For building RESTful APIs

## Project Structure

- **Entities**: Data models representing bank accounts and customers
- **Repositories**: Data access layer for entities
- **Services**: Business logic layer
- **Controllers**: API endpoints (REST and GraphQL)
- **DTOs**: Data Transfer Objects for API requests and responses

## Setup Instructions

1. Clone the repository
2. Make sure you have Java 17+ and Maven installed
3. Run the application using Maven:
   ```
   mvn spring-boot:run
   ```
4. The application will start on port 8082

## API Documentation

### REST API

The REST API is available at the base path `/api/bankAccounts`

#### Endpoints

- **GET /api/bankAccounts**: List all bank accounts
- **GET /api/bankAccounts/{id}**: Get a specific bank account
- **POST /api/bankAccounts**: Create a new bank account
- **PUT /api/bankAccounts/{id}**: Update an existing bank account
- **DELETE /api/bankAccounts/{id}**: Delete a bank account

### GraphQL API

The GraphQL API is available at `/graphql`. A GraphiQL interface is enabled for testing at `/graphiql`.

#### Queries

```graphql
# Get all bank accounts
query {
  bankAccounts {
    id
    balance
    currency
    type
    createdAt
    customer {
      id
      name
    }
  }
}

# Get a specific bank account
query {
  account(id: "account-id") {
    id
    balance
    currency
    type
    createdAt
    customer {
      id
      name
    }
  }
}

# Get all customers
query {
  customers {
    id
    name
    bankAccounts {
      id
      balance
      currency
      type
    }
  }
}
```

#### Mutations

```graphql
# Create a new bank account
mutation {
  createAccount(account: {
    balance: 1000.0,
    currency: "MAD",
    type: CURRENT_ACCOUNT,
    customerId: "customer-id"
  }) {
    id
    balance
    currency
    type
  }
}

# Update an existing bank account
mutation {
  updateAccount(
    id: "account-id",
    account: {
      balance: 2000.0,
      currency: "MAD",
      type: SAVING_ACCOUNT,
      customerId: "customer-id"
    }
  ) {
    id
    balance
    currency
    type
  }
}

# Delete a bank account
mutation {
  deleteAccount(id: "account-id")
}
```

## Features

- Create, read, update, and delete bank accounts
- Associate bank accounts with customers
- Support for different account types (CURRENT_ACCOUNT, SAVING_ACCOUNT)
- Dual API support (REST and GraphQL)
- In-memory H2 database with console access

## H2 Database Console

The H2 database console is available at `/h2-console` with the following configuration:
- JDBC URL: `jdbc:h2:mem:bankdaccountb`
- Username: `sa`
- Password: (leave empty)