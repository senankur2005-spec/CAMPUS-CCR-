package edu.ccrm.domain;

public class Instructor {
    private final String id;
    private final String fullName;
    private final String department;

    public Instructor(String id, String fullName, String department) {
        this.id = id;
        this.fullName = fullName;
        this.department = department;
    }

    public String getId() { return id; }
    public String getFullName() { return fullName; }
    public String getDepartment() { return department; }

    @Override
    public String toString() {
        return fullName + " (" + department + ")";
    }

}
