package edu.ccrm.domain;

public class Semester {
    private final String name;
    private final int year;

    public Semester(String name, int year) {
        this.name = name;
        this.year = year;
    }

    public String getName() { return name; }
    public int getYear() { return year; }

    public static Semester valueOf(String str) {
        // Accept formats like FALL2025, SPRING2026, etc.
        str = str.trim().toUpperCase();
        if (str.matches("[A-Z]+\\d{4}")) {
            String name = str.replaceAll("\\d{4}$", "");
            int year = Integer.parseInt(str.replaceAll("^[A-Z]+", ""));
            return new Semester(name, year);
        }
        throw new IllegalArgumentException("Invalid semester format: " + str);
    }

    @Override
    public String toString() {
        return name + " " + year;
    }
}
