# Wiki Administrator Application - Docker Setup

## Overview
This is a Spring Boot application for Wiki Administrator login system, dockerized with SQLite database.

## Features
- User registration and login
- SQLite database for data persistence
- BCrypt password encryption
- Responsive web interface
- Docker containerization

## Quick Start

### Prerequisites
- Docker
- Docker Compose

### Running the Application

1. **Clone and navigate to the project directory**
   ```bash
   cd /path/to/your/project
   ```

2. **Create data directory for SQLite database**
   ```bash
   mkdir -p data
   ```

3. **Build and run with Docker Compose**
   ```bash
   docker-compose up --build
   ```

4. **Access the application**
   - Open your browser and go to: http://localhost:8080
   - You'll be redirected to the login page

### Test Accounts
The application comes with pre-loaded test accounts:
- Username: `admin`, Password: `admin123`
- Username: `john`, Password: `password123`
- Username: `sarah`, Password: `securepass456`
- Username: `mike`, Password: `mypassword789`

### Registration
You can also create new accounts by clicking "Register here" on the login page.

## Docker Commands

### Build the application
```bash
docker-compose build
```

### Run in background
```bash
docker-compose up -d
```

### View logs
```bash
docker-compose logs -f
```

### Stop the application
```bash
docker-compose down
```

### Clean up (remove containers and volumes)
```bash
docker-compose down -v
```

## Database
- SQLite database file is stored in `./data/wiki_admin.db`
- Database is automatically created on first run
- Data persists between container restarts

## Development
- Application runs on port 8080
- Hot reload is disabled in production mode
- For development, you can run the Spring Boot app directly with `./mvnw spring-boot:run`

## Troubleshooting

### Port already in use
If port 8080 is already in use, modify the port mapping in `docker-compose.yml`:
```yaml
ports:
  - "8081:8080"  # Change 8081 to any available port
```

### Database issues
If you encounter database issues:
1. Stop the application: `docker-compose down`
2. Remove the data directory: `rm -rf data`
3. Restart: `docker-compose up --build`

### Build issues
If the build fails:
1. Clean Docker cache: `docker system prune -a`
2. Rebuild: `docker-compose build --no-cache`
