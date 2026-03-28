# CCRM Usage Guide

## Running the Program

1. Enable assertions when running:
```bash
java -ea edu.ccrm.Main
```

2. Run with debug information:
```bash
java -Ddebug=true edu.ccrm.Main
```

## Sample Data Files

### Student Import CSV Format
Located in `test-data/students.csv`:
```
ID,RegNo,FullName,Email,Department
CS001,24BCE10335,Aditya Das,aditya.das@university.edu,COMPUTER_SCIENCE
EE001,24BCE10234,Tejaswa Sharma,tejaswa.sharma@university.edu,ELECTRICAL_ENGINEERING
```

### Course Import CSV Format
Located in `test-data/courses.csv`:
```
Code,Title,Credits,Department
CS101,Introduction to Programming,3,COMPUTER_SCIENCE
EE101,Circuit Analysis,4,ELECTRICAL_ENGINEERING
```

## Common Operations

### Adding a Student
1. Select option 1 (Manage Students)
2. Select option 1 (Add Student)
3. Enter details as prompted:
   - Student ID (e.g., CS002)
   - Registration Number (e.g., 24BCE10335)
   - Full Name
   - Email
   - Department

### Recording Grades
1. Select option 4 (Manage Grades)
2. Select option 1 (Record Grade)
3. Enter:
   - Student Registration Number
   - Course Code
   - Grade (S/A/B/C/D/E/F)

### Backup Operations
1. Select option 6 (Backup Operations)
2. Backup will be created in `backups/` directory
3. Size calculation will be displayed
