package edu.ccrm.domain;

public abstract class Person {
    protected String id;
    protected String fullName;
    protected String email;

    public Person(String id, String fullName, String email) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
    }

    public abstract String getProfile();
}