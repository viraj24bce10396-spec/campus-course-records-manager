package edu.ccrm.domain;

public class Instructor extends Person {
    private String department;

    public Instructor(String id, String fullName, String email, String department) {
        super(id, fullName, email);
        this.department = department;
    }

    @Override
    public String getProfile() {
        return String.format("Instructor: %s [%s]", fullName, department);
    }

    public String getDepartment() { return department; }

    public String getFullName() { return fullName; }
}