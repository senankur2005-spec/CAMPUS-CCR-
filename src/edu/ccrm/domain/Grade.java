package edu.ccrm.domain;

public enum Grade {
    S(10.0, "Outstanding"),
    A(9.0, "Excellent"),
    B(8.0, "Very Good"),
    C(7.0, "Good"),
    D(6.0, "Average"),
    E(5.0, "Pass"),
    F(0.0, "Fail");

    private final double points;
    private final String description;

    Grade(double points, String description) {
        this.points = points;
        this.description = description;
    }

    public double getPoints() { return points; }
    public String getDescription() { return description; }

    public static Grade fromPoints(double points) {
        for (Grade grade : values()) {
            if (points >= grade.points) return grade;
        }
        return F;
    }
}