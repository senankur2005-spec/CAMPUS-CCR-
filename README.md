# Campus Course Repository Manager (CCR)

A Java-based CLI application for managing student records, course enrollments, and academic performance efficiently.

---

## Features

### Student Management

* Add, update, and view student details
* Search students by name or email
* View student GPA

### Course Management

* Create and manage courses
* Track course credits and departments

### Enrollment System

* Enroll and unenroll students
* Track enrollments by semester
* Enforce credit limits (max 18 credits)

### Grade Management

* Assign and update grades
* Automatic GPA calculation

### Data Management

* Import/export data via CSV files
* Backup system support

---

## Technologies Used

* Java (JDK 8 or higher)
* Object-Oriented Programming (OOP)
* Collections Framework
* File Handling (CSV)
* Command Line Interface (CLI)

---

## Project Structure

```
CCRM/
├── src/
│   └── edu/ccrm/
│       ├── cli/
│       ├── domain/
│       ├── service/
│       ├── io/
│       └── exception/
├── data/
├── backups/
├── test-data/
├── screenshots/
```

---

## Getting Started

### Prerequisites

* Java 8 or higher installed

### Run the Project

```
cd CCRM
javac src/edu/ccrm/Main.java
java edu.ccrm.Main
```

---

## Usage

* Manage Students
* Manage Courses
* Enroll Students
* Assign Grades
* Import/Export Data

---

## Sample Data

* Courses: CS101, EE101, ME101, CH101, BT101
* Students with sample enrollments and grades

---

## Rules & Validation

* Unique registration number required
* Maximum 18 credits per semester
* Valid grades only (S, A, B, C, D, E, F)
* No duplicate enrollments

---

## Error Handling

* Input validation for user entries
* Custom exceptions for business rules
* User-friendly error messages

---

## Future Enhancements

* Add GUI (JavaFX / Swing)
* Integrate MySQL database
* Implement login/authentication system
* Develop web-based version

---

## Screenshots

Screenshots are available in the `screenshots/` folder.

---

## Author

**Ankur Sen**

---

## Conclusion

This project demonstrates practical implementation of Java, OOP concepts, and academic data management in a structured and modular way.
