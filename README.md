# File Upload Application

A full-stack web application enabling users to securely upload and manage profile images on Amazon S3. Built with Spring Boot backend and React.js frontend, featuring robust cloud integration and secure file handling.

## Features

### Authentication & User Management
- **User Registration & Login**: Secure authentication system with JWT tokens
- **Customer CRUD Operations**: Create, read, update, and delete customer profiles
- **Role-based Access Control**: Secure endpoints with proper authorization

### File Upload System
- **Drag & Drop Interface**: Modern React dropzone for intuitive file uploads
- **Profile Image Management**: Upload and update customer profile pictures
- **Multiple File Formats**: Support for various image formats (JPG, PNG, etc.)
- **Real-time Upload**: Instant feedback and progress tracking

### Cloud Integration
- **Amazon S3 Storage**: Secure cloud storage for uploaded files
- **UUID-based Naming**: Unique file identifiers for security
- **Scalable Architecture**: Built for production cloud deployment

### Database & Persistence
- **PostgreSQL Database**: Robust data storage with proper relationships
- **Profile Image Tracking**: Database integration for image metadata
- **Migration System**: Automated database schema management

## Technology Stack

### Backend
- **Java 17** - Core application language
- **Spring Boot 3** - Modern application framework
- **Spring Security** - Authentication and authorization
- **Spring Data JPA** - Database operations
- **PostgreSQL** - Primary database
- **AWS SDK** - S3 integration
- **JWT** - Token-based authentication

### Frontend
- **React 18** - Modern UI framework
- **Chakra UI** - Beautiful component library
- **React Dropzone** - File upload interface
- **Formik + Yup** - Form handling and validation
- **Axios** - HTTP client for API communication

### Infrastructure
- **Docker** - Containerization
- **Docker Compose** - Local development environment
- **Maven** - Backend dependency management
- **npm** - Frontend package management

## Getting Started

### Prerequisites
- Java 17 or higher
- Node.js 16 or higher
- Docker and Docker Compose
- PostgreSQL database
- AWS S3 bucket and credentials

### Backend Setup
```bash
cd backend
mvn clean install
mvn spring-boot:run
```

### Frontend Setup
```bash
cd frontend/react
npm install
npm start
```

### Database Setup
```bash
# Using Docker Compose
docker-compose up -d db

# Or connect to existing PostgreSQL instance
# Update application.yml with your database credentials
```

### S3 Configuration
1. Create an S3 bucket for profile images
2. Configure AWS credentials in `application.yml`
3. Set appropriate bucket permissions

## API Endpoints

### Authentication
- `POST /api/v1/auth/login` - User login
- `POST /api/v1/customers` - User registration

### Customer Management
- `GET /api/v1/customers` - Get all customers
- `GET /api/v1/customers/{id}` - Get customer by ID
- `PUT /api/v1/customers/{id}` - Update customer
- `DELETE /api/v1/customers/{id}` - Delete customer

### File Upload
- `POST /api/v1/customers/{id}/profile-image` - Upload profile image
- `GET /api/v1/customers/{id}/profile-image` - Retrieve profile image

## Architecture

```
┌─────────────────┐    ┌─────────────────┐    ┌─────────────────┐
│   React Frontend │    │  Spring Boot    │    │   PostgreSQL    │
│                 │    │   Backend       │    │   Database      │
│  - Dropzone     │◄──►│  - REST APIs    │◄──►│  - Customer     │
│  - Forms        │    │  - Security     │    │  - Profile      │
│  - UI Components│    │  - S3 Service   │    │  - Images       │
└─────────────────┘    └─────────────────┘    └─────────────────┘
                                │
                                ▼
                       ┌─────────────────┐
                       │   Amazon S3     │
                       │                 │
                       │  - File Storage │
                       │  - CDN          │
                       └─────────────────┘
```

## Configuration

### Environment Variables
```yaml
# Backend (application.yml)
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/customer
    username: ${DB_USERNAME}
    password: ${DB_PASSWORD}

aws:
  s3:
    bucket: ${S3_BUCKET_NAME}
    region: ${AWS_REGION}
    access-key: ${AWS_ACCESS_KEY}
    secret-key: ${AWS_SECRET_KEY}

# Frontend (.env)
VITE_API_BASE_URL=http://localhost:8080
```

## Testing

### Backend Tests
```bash
cd backend
mvn test
```

### Frontend Tests
```bash
cd frontend/react
npm test
```

## Deployment

### Docker Deployment
```bash
# Build and run with Docker Compose
docker-compose up --build
```

### AWS Deployment
- Deploy backend to AWS Elastic Beanstalk
- Deploy frontend to AWS S3 + CloudFront
- Use AWS RDS for PostgreSQL
- Configure S3 bucket for production

## Database Schema

### Customer Table
```sql
CREATE TABLE customer (
    id BIGSERIAL PRIMARY KEY,
    name TEXT NOT NULL,
    email TEXT NOT NULL UNIQUE,
    password TEXT NOT NULL,
    age INT NOT NULL,
    gender TEXT NOT NULL,
    profile_image_id VARCHAR(36) UNIQUE
);
```

## Security Features

- **JWT Authentication**: Secure token-based authentication
- **Password Encryption**: BCrypt password hashing
- **CORS Configuration**: Proper cross-origin resource sharing
- **Input Validation**: Comprehensive form validation
- **SQL Injection Prevention**: Parameterized queries

## Use Cases

- **E-commerce Platforms**: Customer profile management
- **Social Networks**: User avatar uploads
- **Business Applications**: Employee profile pictures
- **Educational Platforms**: Student profile management
- **Healthcare Systems**: Patient photo management

## Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request


## Acknowledgments

- Built with Spring Boot and React.js
- File upload powered by AWS S3
- UI components from Chakra UI
- File handling with React Dropzone


