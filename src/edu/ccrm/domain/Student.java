package edu.ccrm.domain;

import java.util.*;
import edu.ccrm.exception.DuplicateEnrollmentException;
import edu.ccrm.exception.MaxCreditLimitExceededException;

public class Student extends Person {
    private final String regNo;
    private final Map<Course, Enrollment> enrolledCourses;
    private final Department department;
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    // Static nested class for Student builder
    public static class Builder {
        private final String id;
        private final String regNo;
        private String fullName;
        private String email;
        private Department department;

        public Builder(String id, String regNo) {
            this.id = id;
            this.regNo = regNo;
        }

        public Builder fullName(String fullName) {
            this.fullName = fullName;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder department(Department department) {
            this.department = department;
            return this;
        }

        public Student build() {
            return new Student(this);
        }
    }

    private Student(Builder builder) {
        super(builder.id, builder.fullName, builder.email);
        this.regNo = builder.regNo;
        this.department = builder.department;
        this.enrolledCourses = new HashMap<>();
    }

    public void enrollInCourse(Course course, Semester semester) 
        throws DuplicateEnrollmentException, MaxCreditLimitExceededException {
        
        if (enrolledCourses.containsKey(course)) {
            throw new DuplicateEnrollmentException(
                "Student " + regNo + " is already enrolled in course " + course.getCode());
        }

        if (getCurrentSemesterCredits(semester) + course.getCredits() > 18) {
            throw new MaxCreditLimitExceededException(
                "Credit limit exceeded for semester " + semester);
        }

        Enrollment enrollment = new Enrollment(this, course, semester);
        enrolledCourses.put(course, enrollment);
    }

    public void unenrollFromCourse(Course course) {
        enrolledCourses.remove(course);
    }

    public void recordGrade(Course course, Grade grade) {
        Enrollment enrollment = enrolledCourses.get(course);
        if (enrollment != null) {
            enrollment.recordGrade(grade);
        }
    }

    public double calculateGPA() {
        if (enrolledCourses.isEmpty()) return 0.0;

        double totalPoints = 0.0;
        int totalCredits = 0;

        for (Enrollment enrollment : enrolledCourses.values()) {
            if (enrollment.getGrade() != null) {
                totalPoints += enrollment.getGrade().getPoints() * enrollment.getCourse().getCredits();
                totalCredits += enrollment.getCourse().getCredits();
            }
        }

        return totalCredits > 0 ? totalPoints / totalCredits : 0.0;
    }

    private int getCurrentSemesterCredits(Semester semester) {
        return enrolledCourses.values().stream()
            .filter(e -> e.getSemester() == semester && e.getGrade() == null)
            .mapToInt(e -> e.getCourse().getCredits())
            .sum();
    }

    @Override
    public String getDisplayInfo() {
        return String.format("Student: %s (%s) - %s - GPA: %.2f", 
            fullName, regNo, department, calculateGPA());
    }

    @Override
    public void validate() throws IllegalArgumentException {
        if (regNo == null || regNo.trim().isEmpty()) {
            throw new IllegalArgumentException("Registration number is required");
        }
        // Additional validation logic
    }

    // Getters
    public String getRegNo() { return regNo; }
    public Department getDepartment() { return department; }
    public Map<Course, Enrollment> getEnrolledCourses() { 
        return Collections.unmodifiableMap(enrolledCourses); 
    }

    @Override
    public String toString() {
        return String.format("Student{id='%s', regNo='%s', name='%s', department=%s, GPA=%.2f}", 
            id, regNo, fullName, department, calculateGPA());
    }
}