# Green Shadow Agriculture - Crop Monitoring System

## Overview
**Green Shadow (Pvt) Ltd** is a mid-scale farm based in Matale, Sri Lanka, specializing in root crops and cereals. The company has expanded its land coverage and is transitioning to large-scale production. To streamline operations, a comprehensive crop monitoring system has been developed. The system focuses on managing fields, crops, staff, monitoring logs, vehicles, and equipment.

### Key Features:
- **Field Management**: Manage allocated land for cultivation.
- **Crop Management**: Handle crop types, growth stages, and observations.
- **Staff Management**: Manage human resources and staff assignments.
- **Monitoring Logs**: Track activities and observations related to crops.
- **Vehicle Management**: Allocate and manage vehicles used in operations.
- **Equipment Management**: Track agricultural equipment and its usage.
- **User Access Control**: Manage access levels (Manager, Administrative, Scientist).
- **CRUD Operations**: Perform Create, Read, Update, Delete operations on fields, crops, staff, vehicles, and equipment.

## Business Process
1. **User Access**: Users can log into the system as Manager, Administrative, or Scientist with different access permissions.
2. **CRUD Operations**: Managers can manage crop data, staff, vehicles, and other assets, while Admin and Scientist roles have limited permissions.
3. **Data Analysis**:
    - **Relational Analysis**: Evaluate relationships between resources.
    - **Spatial & Temporal Analysis**: Analyze data based on time and location.
4. **Permissions & Access Limitations**:
    - **Manager**: Full access.
    - **Administrative**: Cannot modify crop or field data.
    - **Scientist**: Cannot modify staff, vehicle, or equipment data.

## System Services
- **Field Service**: Manages fields allocated for cultivation.
- **Crop Service**: Handles crop data.
- **Staff Service**: Manages human resources.
- **Monitoring Log Service**: Records crop-related activities and observations.
- **Vehicle Service**: Manages vehicles.
- **Equipment Service**: Oversees agricultural equipment.
- **Authentication Service**: Handles user login and access control.

## Tech Stack
- **Back End**: Spring Boot, Spring Data JPA, Spring Security, Spring Validation, MySQL, JWT
- **Front End**: HTML, CSS, JS, jQuery, AJAX
- **Database**: MySQL

## Guidelines
1. **Validation**: Both client and server-side validation must be implemented.
2. **Sorting**: Entities should support sorting by name, designation, gender, vehicle type, land size, and status.
3. **UI/UX**: Emphasis on clean design and good user experience.
4. **Password Security**: Credentials must be stored securely (encrypted).
5. **Authentication & Authorization**: Implement Spring Security with OAuth 2.0.
6. **Exception Handling**: Handle all exceptions with custom messages.
7. **Logging**: Log important activities and exceptions.

## Configuration

```properties
# Application Configuration
spring.application.name=GreenShadowCropMonitor_BackEnd
spring.profiles.active=dev
server.servlet.context-path=/CropMonitorSystem

# File Upload
spring.servlet.multipart.enabled=true
spring.servlet.multipart.max-file-size=10MB
spring.servlet.multipart.max-request-size=10MB

# Logging
logging.level.org.springframework.web=DEBUG
logging.level.org.springframework.web.multipart=DEBUG
server.port=8080

# Database Configuration
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.url=jdbc:mysql://localhost:3306/Crop_Monitor?createDatabaseIfNotExist=true
spring.datasource.username=root
spring.datasource.password=Pasindu@1234
spring.jpa.database-platform=org.hibernate.dialect.MySQL8Dialect
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

# JWT Configuration
spring.jwtKey=5297e8283409819a965da98ccae71bc567370a465121a7717757293d495476a7726a1f9687c3eae8ea5a0ccbd70d01e5272004c50d7z1b0e70f88ab9a28a3ed1e8a2424441ce7e8235b43b4


