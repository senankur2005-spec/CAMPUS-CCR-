package edu.ccrm.domain;

import java.util.Objects;

public final class CourseCode {
    private final String departmentCode;
    private final int courseNumber;
    private final String fullCode;

    public CourseCode(String departmentCode, int courseNumber) {
        this.departmentCode = Objects.requireNonNull(departmentCode).toUpperCase();
        this.courseNumber = courseNumber;
        this.fullCode = String.format("%s%04d", departmentCode, courseNumber);
    }

    public String getDepartmentCode() { return departmentCode; }
    public int getCourseNumber() { return courseNumber; }
    public String getFullCode() { return fullCode; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CourseCode that = (CourseCode) o;
        return courseNumber == that.courseNumber && 
               Objects.equals(departmentCode, that.departmentCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(departmentCode, courseNumber);
    }

    @Override
    public String toString() {
        return fullCode;
    }
}