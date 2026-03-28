package edu.ccrm.domain;

import java.util.Objects;

public class Course {
    private final CourseCode code;
    private String title;
    private int credits;
    private Instructor instructor;
    private Department department;
    private boolean active;

    public static class Builder {
        private final CourseCode code;
        private String title;
        private int credits = 3;
        private Instructor instructor;
        private Department department;

        public Builder(CourseCode code) {
            this.code = code;
        }

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder credits(int credits) {
            this.credits = credits;
            return this;
        }

        public Builder instructor(Instructor instructor) {
            this.instructor = instructor;
            return this;
        }

        public Builder department(Department department) {
            this.department = department;
            return this;
        }

        public Course build() {
            Course course = new Course(this);
            course.validate();
            return course;
        }
    }

    private Course(Builder builder) {
        this.code = builder.code;
        this.title = builder.title;
        this.credits = builder.credits;
        this.instructor = builder.instructor;
        this.department = builder.department;
        this.active = true;
    }

    private void validate() {
        Objects.requireNonNull(code, "Course code is required");
        Objects.requireNonNull(title, "Course title is required");
        if (credits <= 0 || credits > 6) {
            throw new IllegalArgumentException("Credits must be between 1 and 6");
        }
    }

    // Getters and setters
    public CourseCode getCode() { return code; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public int getCredits() { return credits; }
    public void setCredits(int credits) { this.credits = credits; }
    public Instructor getInstructor() { return instructor; }
    public void setInstructor(Instructor instructor) { this.instructor = instructor; }
    public Department getDepartment() { return department; }
    public void setDepartment(Department department) { this.department = department; }
    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }

    @Override
    public String toString() {
        return String.format("Course{code=%s, title='%s', credits=%d, instructor=%s}", 
            code, title, credits, instructor != null ? instructor.getFullName() : "None");
    }
}