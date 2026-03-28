package edu.ccrm.domain;

public class Enrollment {
    private final Student student;
    private final Course course;
    private final Semester semester;
    private Grade grade;

    public Enrollment(Student student, Course course, Semester semester) {
        this.student = student;
        this.course = course;
        this.semester = semester;
        this.grade = null;
    }

    public Student getStudent() {
        return student;
    }

    public Course getCourse() {
        return course;
    }

    public Semester getSemester() {
        return semester;
    }

    public Grade getGrade() {
        return grade;
    }

    public void recordGrade(Grade grade) {
        this.grade = grade;
    }
}
