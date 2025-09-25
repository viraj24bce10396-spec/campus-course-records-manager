package edu.ccrm.domain;

import java.time.LocalDate;
import java.util.*;

public class Student extends Person {
    private String regNo;
    private Status status;
    private List<Course> enrolledCourses;
    private LocalDate createdAt;

    public enum Status { ACTIVE, DEACTIVATED }

    public Student(String id, String regNo, String fullName, String email) {
        super(id, fullName, email);
        this.regNo = regNo;
        this.status = Status.ACTIVE;
        this.enrolledCourses = new ArrayList<>();
        this.createdAt = LocalDate.now();
    }

    @Override
    public String getProfile() {
        return String.format("Student: %s (%s) [%s]", fullName, regNo, status);
    }

    public String getRegNo() {
        return regNo;
    }

    public void setRegNo(String regNo) {
        this.regNo = regNo;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public List<Course> getEnrolledCourses() {
        return enrolledCourses;
    }

    public void setEnrolledCourses(List<Course> enrolledCourses) {
        this.enrolledCourses = enrolledCourses;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    /** Deactivate student (for business logic) */
    public void deactivate() {
        this.status = Status.DEACTIVATED;
    }

    /** Enroll student in a course */
    public void enroll(Course course) {
        if (!enrolledCourses.contains(course)) {
            enrolledCourses.add(course);
        }
    }

    /** Unenroll student from a course */
    public void unenroll(Course course) {
        enrolledCourses.remove(course);
    }

    /** Print a transcript of enrolled courses */
    public String printTranscript() {
        StringBuilder sb = new StringBuilder(getProfile());
        sb.append("\nEnrolled Courses:\n");
        for (Course c : enrolledCourses) {
            sb.append(c.toString()).append("\n");
        }
        return sb.toString();
    }
}
