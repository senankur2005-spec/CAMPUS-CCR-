package edu.ccrm.cli;

import edu.ccrm.domain.Course;
import edu.ccrm.domain.CourseCode;
import edu.ccrm.domain.Department;
import edu.ccrm.domain.Grade;
import edu.ccrm.domain.Semester;
import edu.ccrm.domain.Student;
import edu.ccrm.io.ImportExportService;
import edu.ccrm.service.CourseService;
import edu.ccrm.service.StudentService;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.function.Predicate;


public class CLIMenu {
    private final Scanner scanner;
    private final StudentService studentService;
    private final CourseService courseService;
    private final ImportExportService ioService;

    public CLIMenu() {
        this.scanner = new Scanner(System.in);
        this.studentService = new StudentService();
        this.courseService = new CourseService();
        this.ioService = new ImportExportService();
        initializeSampleData();
    }

    public void start() {
        System.out.println("=== Campus Course & Records Manager ===");
        
        mainLoop: while (true) {
            printMainMenu();
            int choice = getIntInput("Choose an option: ");
            
            switch (choice) {
                case 1 :
                 manageStudents();
                break;
                case 2 :
                 manageCourses();
                break;
                case 3 :
                 manageEnrollments();
                break;
                case 4 :
                 manageGrades();
                break;
                case 5 :
                 importExportData();
                break;
                case 6 :
                 backupOperations();
                break;
                case 7 :
                 generateReports();
                break;
                case 8 :
                 System.out.println("Exiting CCRM. Goodbye!");
                 break mainLoop;
                default : System.out.println("Invalid option! Try again.");
            }
        }
    }

    private void printMainMenu() {
        System.out.println("\n=== MAIN MENU ===");
        System.out.println("1. Manage Students");
        System.out.println("2. Manage Courses");
        System.out.println("3. Manage Enrollments");
        System.out.println("4. Manage Grades");
        System.out.println("5. Import/Export Data");
        System.out.println("6. Backup Operations");
        System.out.println("7. Generate Reports");
        System.out.println("8. Exit");
    }

    private void manageStudents() {
        studentMenu: while (true) {
            System.out.println("\n=== STUDENT MANAGEMENT ===");
            System.out.println("1. Add Student");
            System.out.println("2. List All Students");
            System.out.println("3. Search Students");
            System.out.println("4. Update Student");
            System.out.println("5. Back to Main Menu");
            
            int choice = getIntInput("Choose an option: ");
            
            switch (choice) {
                case 1 : addStudent();
                break;
                case 2 : listStudents();
                break;
                case 3 : searchStudents();
                break;
                case 4 : updateStudent();
                break;
                case 5 : break studentMenu;
                default : System.out.println("Invalid option!");
            }
        }
    }

    private void addStudent() {
        try {
            System.out.println("\n--- Add New Student ---");
            String id = getStringInput("Student ID: ");
            String regNo = getStringInput("Registration Number: ");
            String fullName = getStringInput("Full Name: ");
            String email = getStringInput("Email: ");
            
            System.out.println("Available Departments: " + Arrays.toString(Department.values()));
            Department department = Department.valueOf(getStringInput("Department: ").toUpperCase());

            Student student = new Student.Builder(id, regNo)
                .fullName(fullName)
                .email(email)
                .department(department)
                .build();

            studentService.addStudent(student);
            System.out.println("Student added successfully!");
            
        } catch (Exception e) {
            System.out.println("Error adding student: " + e.getMessage());
        }
    }

    private void listStudents() {
        List<Student> students = studentService.findAll();
        if (students.isEmpty()) {
            System.out.println("No students found.");
            return;
        }

        System.out.println("\n--- All Students ---");
        students.forEach(student -> System.out.println(student.getDisplayInfo()));
    }

    private void searchStudents() {
        System.out.println("\n--- Search Students ---");
        String searchTerm = getStringInput("Enter search term (name or email): ").toLowerCase();
        
        Predicate<Student> searchPredicate = student -> 
            student.getFullName().toLowerCase().contains(searchTerm) ||
            student.getEmail().toLowerCase().contains(searchTerm);

        List<Student> results = studentService.search(searchPredicate);
        
        if (results.isEmpty()) {
            System.out.println("No matching students found.");
        } else {
            results.forEach(System.out::println);
        }
    }

    private void backupOperations() {
        try {
            System.out.println("\n=== BACKUP OPERATIONS ===");
            Path backupDir = ioService.createBackup();
            System.out.println("Backup created: " + backupDir.getFileName());
            
            long size = ioService.calculateBackupSize(backupDir);
            System.out.println("Backup size: " + size + " bytes");
            
        } catch (Exception e) {
            System.out.println("Backup failed: " + e.getMessage());
        }
    }

    private int getIntInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number!");
            }
        }
    }

    private String getStringInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    private void initializeSampleData() {
        try {
            // For Computer Science
            Course cs101 = new Course.Builder(new CourseCode("CS", 101))
                .title("Introduction to Programming")
                .credits(3)
                .department(Department.COMPUTER_SCIENCE)
                .build();

            // For Electrical Engineering
            Course ee101 = new Course.Builder(new CourseCode("EE", 101))
                .title("Circuit Analysis")
                .credits(4)
                .department(Department.ELECTRICAL_ENGINEERING)
                .build();

            // For Mechanical Engineering
            Course me101 = new Course.Builder(new CourseCode("ME", 101))
                .title("Engineering Mechanics")
                .credits(4)
                .department(Department.MECHANICAL_ENGINEERING)
                .build();

            // For Chemical Engineering
            Course ch101 = new Course.Builder(new CourseCode("CH", 101))
                .title("Chemical Process Principles")
                .credits(3)
                .department(Department.CHEMICAL_ENGINEERING)
                .build();

            // For Biotechnology
            Course bt101 = new Course.Builder(new CourseCode("BT", 101))
                .title("Introduction to Biotechnology")
                .credits(3)
                .department(Department.BIO_TECHNOLOGY)
                .build();

            // Adding all of our courses and saving it in the program
            courseService.addCourse(cs101);
            courseService.addCourse(ee101);
            courseService.addCourse(me101);
            courseService.addCourse(ch101);
            courseService.addCourse(bt101);

            // Create sample students with enrollments and grades
            Student csStudent = new Student.Builder("CS001", "24BCE10335")
                .fullName("Aditya Das")
                .email("aditya.24bce10335@vitbhopal.ac.in")
                .department(Department.COMPUTER_SCIENCE)
                .build();

            Student eeStudent = new Student.Builder("EE001", "24BCE11005")
                .fullName("Arpit Kumar")
                .email("arpit.24bce11005@vitbhopal.ac.in")
                .department(Department.ELECTRICAL_ENGINEERING)
                .build();

            // Creating the semester instances
            Semester fall2025 = new Semester("FALL", 2025);

            // Enrolling students in courses and assign grades
            csStudent.enrollInCourse(cs101, fall2025);
            csStudent.enrollInCourse(ee101, fall2025);
            csStudent.recordGrade(cs101, Grade.S);
            csStudent.recordGrade(ee101, Grade.A);

            eeStudent.enrollInCourse(ee101, fall2025);
            eeStudent.enrollInCourse(me101, fall2025);
            eeStudent.recordGrade(ee101, Grade.A);
            eeStudent.recordGrade(me101, Grade.B);

            // Adding students to the service
            studentService.addStudent(csStudent);
            studentService.addStudent(eeStudent);

        } catch (Exception e) {
            System.err.println("Error initializing sample data: " + e.getMessage());
        }
    }

    private void manageCourses() {
        courseMenu: while (true) {
            System.out.println("\n=== COURSE MANAGEMENT ===");
            System.out.println("1. Add Course");
            System.out.println("2. List All Courses");
            System.out.println("3. Back to Main Menu");
            int choice = getIntInput("Choose an option: ");
            switch (choice) {
                case 1 : addCourse();
                break;
                case 2 : listCourses();
                break;
                case 3 : break courseMenu;
                default : System.out.println("Invalid option!");
            }
        }
    }

    private void addCourse() {
        try {
            System.out.println("\n--- Add New Course ---");
            String codePrefix = getStringInput("Course Code Prefix (e.g., CS): ");
            int codeNumber = getIntInput("Course Code Number (e.g., 101): ");
            String title = getStringInput("Course Title: ");
            int credits = getIntInput("Credits: ");
            System.out.println("Available Departments: " + Arrays.toString(Department.values()));
            Department department = Department.valueOf(getStringInput("Department: ").toUpperCase());
            Course course = new Course.Builder(new CourseCode(codePrefix, codeNumber))
                .title(title)
                .credits(credits)
                .department(department)
                .build();
            courseService.addCourse(course);
            System.out.println("Course added successfully!");
        } catch (Exception e) {
            System.out.println("Error adding course: " + e.getMessage());
        }
    }

    private void listCourses() {
        List<Course> courses = courseService.findAll();
        if (courses.isEmpty()) {
            System.out.println("No courses found.");
            return;
        }
        System.out.println("\n--- All Courses ---");
        courses.forEach(course -> System.out.println(course));
    }

    private void manageEnrollments() {
        enrollmentMenu: while (true) {
            System.out.println("\n=== ENROLLMENT MANAGEMENT ===");
            System.out.println("1. Enroll Student in Course");
            System.out.println("2. Unenroll Student from Course");
            System.out.println("3. Back to Main Menu");
            int choice = getIntInput("Choose an option: ");
            switch (choice) {
                case 1 : enrollStudentInCourse();
                break;
                case 2 : unenrollStudentFromCourse();
                break;
                case 3 : break enrollmentMenu;
                default : System.out.println("Invalid option!");
            }
        }
    }

    private void enrollStudentInCourse() {
        try {
            System.out.println("\n--- Enroll Student in Course ---");
            String regNo = getStringInput("Student Registration Number: ");
            String courseCode = getStringInput("Course Code (e.g., CS101): ");
            String semesterStr = getStringInput("Semester (e.g., FALL2025): ");
            Student student = studentService.findByRegNo(regNo);
            Course course = courseService.findByCode(courseCode);
            Semester semester = Semester.valueOf(semesterStr);
            if (student == null || course == null) {
                System.out.println("Student or course not found.");
                return;
            }
            student.enrollInCourse(course, semester);
            System.out.println("Enrollment successful!");
        } catch (Exception e) {
            System.out.println("Error enrolling student: " + e.getMessage());
        }
    }

    private void unenrollStudentFromCourse() {
        try {
            System.out.println("\n--- Unenroll Student from Course ---");
            String regNo = getStringInput("Student Registration Number: ");
            String courseCode = getStringInput("Course Code (e.g., CS101): ");
            Student student = studentService.findByRegNo(regNo);
            Course course = courseService.findByCode(courseCode);
            if (student == null || course == null) {
                System.out.println("Student or course not found.");
                return;
            }
            student.unenrollFromCourse(course);
            System.out.println("Unenrollment successful!");
        } catch (Exception e) {
            System.out.println("Error unenrolling student: " + e.getMessage());
        }
    }

    private void manageGrades() {
        gradeMenu: while (true) {
            System.out.println("\n=== GRADE MANAGEMENT ===");
            System.out.println("1. Record Grade");
            System.out.println("2. Back to Main Menu");
            int choice = getIntInput("Choose an option: ");
            switch (choice) {
                case 1 : recordGradeForStudent();
                break;
                case 2 :break gradeMenu;
                default : System.out.println("Invalid option!");
            }
        }
    }

    private void recordGradeForStudent() {
        try {
            System.out.println("\n--- Record Grade for Student ---");
            String regNo = getStringInput("Student Registration Number: ");
            String courseCode = getStringInput("Course Code (e.g., CS101): ");
            String gradeValue = getStringInput("Grade (e.g., S, A, B, C): ");
            Student student = studentService.findByRegNo(regNo);
            Course course = courseService.findByCode(courseCode);
            Grade grade = Grade.valueOf(gradeValue.toUpperCase());
            if (student == null || course == null) {
                System.out.println("Student or course not found.");
                return;
            }
            student.recordGrade(course, grade);
            System.out.println("Grade recorded successfully!");
        } catch (Exception e) {
            System.out.println("Error recording grade: " + e.getMessage());
        }
    }

    private void importExportData() {
        importExportMenu: while (true) {
            System.out.println("\n=== IMPORT/EXPORT DATA ===");
            System.out.println("1. Import Students from CSV");
            System.out.println("2. Export Students to CSV");
            System.out.println("3. Back to Main Menu");
            int choice = getIntInput("Choose an option: ");
            switch (choice) {
                case 1 : importStudentsFromCSV();
                break;
                case 2 : exportStudentsToCSV();
                break;
                case 3 : break importExportMenu;
                default : System.out.println("Invalid option!");
            }
        }
    }

    private void importStudentsFromCSV() {
        try {
            System.out.println("\n--- Import Students from CSV ---");
            String pathStr = getStringInput("CSV file path: ");
            Path path = Paths.get(pathStr);
            List<Student> students = ioService.importStudentsFromCSV(path);
            for (Student s : students) {
                studentService.addStudent(s);
            }
            System.out.println("Imported " + students.size() + " students.");
        } catch (Exception e) {
            System.out.println("Error importing students: " + e.getMessage());
        }
    }

    private void exportStudentsToCSV() {
        try {
            System.out.println("\n--- Export Students to CSV ---");
            String pathStr = getStringInput("CSV file path: ");
            Path path = Paths.get(pathStr);
            List<Student> students = studentService.findAll();
            ioService.exportStudentsToCSV(students, path);
            System.out.println("Exported " + students.size() + " students.");
        } catch (Exception e) {
            System.out.println("Error exporting students: " + e.getMessage());
        }
    }

    private void updateStudent() {
        try {
            System.out.println("\n--- Update Student ---");
            String regNo = getStringInput("Registration Number: ");
            Student student = studentService.findByRegNo(regNo);
            if (student == null) {
                System.out.println("Student not found.");
                return;
            }
            String newName = getStringInput("New Full Name (leave blank to keep current): ");
            String newEmail = getStringInput("New Email (leave blank to keep current): ");
            if (newName != null && !newName.trim().isEmpty()) {
                student.setFullName(newName);
            }
            if (newEmail != null && !newEmail.trim().isEmpty()) {
                student.setEmail(newEmail);
            }
            System.out.println("Student updated (fields changed if provided).");
        } catch (Exception e) {
            System.out.println("Error updating student: " + e.getMessage());
        }
    }

    private void generateReports() {
        System.out.println("\n=== REPORT GENERATION ===");
        List<Student> students = studentService.findAll();
        if (students.isEmpty()) {
            System.out.println("No students found.");
            return;
        }
        System.out.println("\n--- GPA Report ---");
        for (Student s : students) {
            System.out.printf("%s (%s): GPA = %.2f\n", s.getFullName(), s.getRegNo(), s.calculateGPA());
        }
    }
}