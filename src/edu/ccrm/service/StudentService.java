package edu.ccrm.service;

import edu.ccrm.domain.Course;
import edu.ccrm.domain.Semester;
import edu.ccrm.domain.Student;
import edu.ccrm.exception.DuplicateEnrollmentException;
import edu.ccrm.exception.MaxCreditLimitExceededException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class StudentService implements Searchable<Student> {
    private final Map<String, Student> students;

    public StudentService() {
        this.students = new HashMap<>();
    }

    public void addStudent(Student student) {
        students.put(student.getId(), student);
    }

    public Student findByRegNo(String regNo) {
        for (Student student : students.values()) {
            if (student.getRegNo().equals(regNo)) {
                return student;
            }
        }
        return null;
    }

    public void enrollStudent(String studentId, Course course, Semester semester) 
        throws DuplicateEnrollmentException, MaxCreditLimitExceededException {
        
        Student student = students.get(studentId);
        if (student != null) {
            student.enrollInCourse(course, semester);
        }
    }

    // Using Streams for search operations
    @Override
    public List<Student> search(Predicate<Student> predicate) {
        return students.values().stream()
            .filter(predicate)
            .collect(Collectors.toList());
    }

    public List<Student> getTopStudents(int count) {
        return students.values().stream()
            .filter(Student::isActive)
            .sorted((s1, s2) -> Double.compare(s2.calculateGPA(), s1.calculateGPA()))
            .limit(count)
            .collect(Collectors.toList());
    }

    public Map<Double, Long> getGPADistribution() {
        return students.values().stream()
            .collect(Collectors.groupingBy(
                student -> Math.floor(student.calculateGPA()),
                Collectors.counting()
            ));
    }

    @Override
    public List<Student> findAll() {
        return new ArrayList<>(students.values());
    }

    @Override
    public Student findById(String id) {
        return students.get(id);
    }
}