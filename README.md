# Alan Huynh | s1557984 | alan@protoflow.com.au

# Spring Boot MVC Application - BIT235

This is a Spring Boot MVC application created following the course guidelines for BIT235 Object Oriented Programming.

## Project Structure

```
mvc-app/
├── src/
│   ├── main/
│   │   ├── java/com/example/mvcapp/
│   │   │   ├── MvcAppApplication.java          # Main Spring Boot application
│   │   │   ├── controller/
│   │   │   │   ├── HelloController.java        # Thymeleaf integration example
│   │   │   │   └── QuotesController.java       # HTML/CSS content example
│   │   │   ├── model/
│   │   │   │   └── Customer.java               # JPA Entity with encryption
│   │   │   └── util/
│   │   │       └── EncryptionConverter.java    # Database field encryption
│   │   └── resources/
│   │       ├── static/
│   │       │   └── index.html                  # Static homepage
│   │       ├── templates/
│   │       │   ├── hello.html                  # Thymeleaf template
│   │       │   └── quotes.html                 # Historical quotes page
│   │       └── application.properties          # Configuration
│   └── pom.xml                                 # Maven dependencies
```

## Features

- **Spring Boot 3.x** with Jakarta EE namespace
- **Thymeleaf** templating engine
- **Spring Data JPA** with H2 database (development)
- **MySQL** support (production)
- **Auto-reload** with Spring Boot DevTools
- **Field encryption** for sensitive database fields
- **Modern CSS** styling with gradients and responsive design

## Running the Application

1. **Prerequisites:**
   - Java 17 or later
   - Maven 3.8.x or newer

2. **Check your environment:**
   ```bash
   java --version
   mvn --version
   ```

3. **Run the application:**
   ```bash
   mvn spring-boot:run
   ```

4. **Access the application:**
   - Homepage: http://localhost:8080/
   - Hello page: http://localhost:8080/hello
   - Historical quotes: http://localhost:8080/history
   - H2 Console: http://localhost:8080/h2-console

## Key Annotations Used

- `@SpringBootApplication` - Marks the main application class
- `@Controller` - Marks classes as Spring MVC controllers
- `@GetMapping` - Maps HTTP GET requests to handler methods
- `@Entity` - Marks classes as JPA entities
- `@Id` - Marks primary key fields
- `@GeneratedValue` - Configures auto-generation of primary keys
- `@Convert` - Applies custom converters for field encryption

## Database Configuration

The application uses H2 in-memory database for development. To switch to MySQL for production, uncomment the MySQL configuration in `application.properties` and ensure MySQL is installed and running.

## Encryption

Sensitive fields like email and password in the `Customer` entity are automatically encrypted when stored in the database using the `EncryptionConverter` class.

## Auto-Reload

The application includes Spring Boot DevTools for automatic recompilation and restart when source files change. Simply save your files and refresh the browser after a few seconds.
