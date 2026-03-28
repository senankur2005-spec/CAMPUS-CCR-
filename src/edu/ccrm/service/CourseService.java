package edu.ccrm.service;

import edu.ccrm.domain.Course;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CourseService {
    private final Map<String, Course> courses;

    public CourseService() {
        this.courses = new HashMap<>();
    }

    public void addCourse(Course course) {
        if (course == null || course.getCode() == null) {
            throw new IllegalArgumentException("Course or CourseCode cannot be null");
        }
        courses.put(course.getCode().toString(), course);
    }

    public List<Course> findAll() {
        return new ArrayList<>(courses.values());
    }

    public Course findByCode(String code) {
        String searchCode = code.toUpperCase();
        if (searchCode.matches("[A-Z]+\\d+")) {
            String prefix = searchCode.replaceAll("\\d+$", "");
            int number = Integer.parseInt(searchCode.substring(prefix.length()));
            searchCode = String.format("%s%04d", prefix, number);
        }
        return courses.get(searchCode);
    }

    public void updateCourse(String code, Course updatedCourse) {
        if (courses.containsKey(code)) {
            courses.put(code, updatedCourse);
        } else {
            throw new IllegalArgumentException("Course not found: " + code);
        }
    }

    public void deleteCourse(String code) {
        if (courses.containsKey(code)) {
            courses.remove(code);
        } else {
            throw new IllegalArgumentException("Course not found: " + code);
        }
    }
}