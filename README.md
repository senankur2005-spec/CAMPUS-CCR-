# Campus Course & Records Manager (CCRM)

A Java-based academic management system designed for managing student records, course enrollments, and academic performance tracking.

## Java Evolution & Environment

### Evolution of Java
- Java 1.0 (1996): First release
- Java 1.2-1.4: Collections framework, Assert keyword, Regular expressions
- Java 5: Generics, Enhanced for-loop, Autoboxing
- Java 6-7: JDBC 4.0, Try-with-resources, Diamond operator
- Java 8: Lambda expressions, Stream API, Optional, New Date/Time API
- Current features used: Streams, NIO.2, Lambda expressions, Date/Time API

### Java Editions Comparison

| Feature | Java ME | Java SE | Java EE |
|---------|---------|----------|----------|
| Target | Mobile/Embedded | Desktop/Core | Enterprise |
| Memory | Minimal | Standard | Large |
| Features | Basic subset | Complete core | Enterprise extensions |
| Use Case | IoT devices | Standalone apps | Web/Server apps |

### JDK vs JRE vs JVM
- **JDK (Java Development Kit)**
  - Complete development kit
  - Includes compiler (javac)
  - Contains JRE and development tools
  - Required for Java development

- **JRE (Java Runtime Environment)**
  - Runtime environment only
  - Includes Java libraries
  - Contains JVM
  - Required to run Java applications

- **JVM (Java Virtual Machine)**
  - Executes Java bytecode
  - Platform-specific implementation
  - Memory management & garbage collection
  - Security sandbox

## Features

### Student Management
- Add and update student information
- Track student details (ID, registration number, name, email, department)
- Search students by name or email
- View student academic records and GPA

### Course Management
- Create and manage course offerings
- Support for multiple departments
  - Computer Science
  - Electrical Engineering
  - Mechanical Engineering
  - Chemical Engineering
  - Bio Technology
- Course credits system (1-6 credits per course)
- Track course details (code, title, credits, department)

### Enrollment System
- Enroll students in courses
- Track enrollments by semester
- Enforce credit limit per semester (max 18 credits)
- Prevent duplicate enrollments
- Support for unenrollment

### Grade Management
- Record and update student grades
- Grading Scale:
  - S (10.0) - Outstanding
  - A (9.0) - Excellent
  - B (8.0) - Very Good
  - C (7.0) - Good
  - D (6.0) - Average
  - E (5.0) - Pass
  - F (0.0) - Fail
- Automatic GPA calculation

### Data Management
- Import/Export student data via CSV
- Backup functionality for system data
- Data validation and error handling

## Technical Details

### Architecture
- Object-Oriented Design with Builder Pattern
- Service Layer Architecture
- Domain-Driven Design principles

### Key Technologies
- Java 8 Compatible
- NIO.2 for file operations
- Stream API for data processing
- Exception handling for robust error management

### Design Patterns
- Builder Pattern for object creation
- Singleton Pattern for services
- Command Pattern for menu operations

## Project Structure

```
CCRM/
├── src/
│   └── edu/ccrm/
│       ├── cli/          # Command-line interface and menu system
│       ├── domain/       # Domain models (Student, Course, etc.)
│       ├── service/      # Business logic and services
│       ├── io/           # Data import/export operations
│       └── exception/    # Custom exceptions
├── data/                 # Data storage
├── backups/              # Backup storage
├── test-data/           # Test data files
└── screenshots/         # Application screenshots
```

## Getting Started

1. Ensure you have Java 8 or higher installed
2. Clone the repository
3. Compile and run:
   ```bash
   cd CCRM
   javac src/edu/ccrm/Main.java
   java edu.ccrm.Main
   ```

## Usage Guide

### Main Menu Options:
1. **Manage Students**
   - Add new students
   - List all students
   - Search students
   - Update student information

2. **Manage Courses**
   - Add new courses
   - List all courses
   - View course details

3. **Manage Enrollments**
   - Enroll students in courses
   - Unenroll students from courses
   - View enrollments

4. **Manage Grades**
   - Record grades
   - Update grades
   - View grade reports

5. **Import/Export Data**
   - Import student data from CSV
   - Export student data to CSV

6. **Backup Operations**
   - Create system backups
   - Calculate backup sizes

7. **Generate Reports**
   - View GPA reports
   - Student performance analysis

### Sample Data
The system includes sample data for demonstration:
- Courses: CS101, EE101, ME101, CH101, BT101
- Students with sample enrollments and grades
- Various departments and grade entries

## Data Validation and Rules

- Registration numbers must be unique
- Course codes follow department prefix pattern (e.g., CS101)
- Maximum 18 credits per semester
- No duplicate course enrollments
- Valid grade values (S, A, B, C, D, E, F)
- Course credits must be between 1 and 6

## Error Handling

- Input validation for all user inputs
- Custom exceptions for business rules
- Graceful error recovery
- User-friendly error messages

## Syllabus Topic Mapping

| Topic | Implementation Location | Description |
|-------|------------------------|-------------|
| Classes & Objects | `domain/*.java` | Domain models using OOP |
| Inheritance | `Person.java`, `Student.java` | Student inherits from Person |
| Interfaces | `service/Searchable.java` | Service interfaces |
| Exception Handling | `exception/*.java` | Custom exceptions |
| Collections | `StudentService.java` | HashMap for student storage |
| Generics | `service/Searchable.java` | Generic type parameters |
| Stream API | `StudentService.java` | Student filtering and search |
| File I/O | `io/ImportExportService.java` | CSV import/export |
| Date & Time API | `domain/Student.java` | Timestamp handling |
| Assertions | Throughout codebase | Precondition checking |

## Assertions

To enable assertions when running the program:
```bash
java -ea edu.ccrm.Main
```

Assertions are used for:
- Input validation
- State invariants
- Precondition checking
- Internal logic verification

## Installation Guide

### Windows Installation

1. Download JDK 8 from Oracle website
2. Run installer and follow prompts
3. Add JAVA_HOME environment variable
4. Add %JAVA_HOME%\bin to PATH
5. Verify with `java -version`

### Eclipse Setup

1. Download Eclipse IDE
2. Import project:
   - File → Import
   - General → Existing Projects
   - Select CCRM folder
   - Finish
3. Configure build path:
   - Right-click project
   - Build Path → Configure
   - Add JDK 8 library

### Screenshots
Screenshots are available in the `screenshots/` folder:
- JDK installation verification and Eclipse project setup
  
  ![Eclipse Installer](screenshots/eclipse%20installer.png)
  
  ![Eclipse Installer](screenshots/eclipse%20foundation%20software%20user%20agreement%20versions.png)
  
- Program execution samples

  ![Eclipse Installer](screenshots/1.jpeg)

  ![Eclipse Installer](screenshots/2.jpeg)

  ![Eclipse Installer](screenshots/3.jpeg)

  ![Eclipse Installer](screenshots/4.jpeg)

- File system structure

  ![Eclipse Installer](screenshots/File%20location.jpeg)

  ![Eclipse Installer](screenshots/Package%20Structure.jpeg)

## Future Enhancements

- Web-based user interface
- Database integration
- Advanced reporting features
- Faculty management
- Course prerequisite system
- Academic calendar integration
