package edu.ccrm.domain;

/** Immutable value class for course code. */
public final class CourseCode {
    private final String value;

    public CourseCode(String value) {
        if (value == null || value.isEmpty()) {
            throw new IllegalArgumentException("Course code cannot be empty");
        }
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() { return value; }
}