package edu.ccrm.domain;

import java.time.LocalDate;

public class Enrollment {
    private final Student student;
    private final Course course;
    private Grade grade;
    private double marks;
    private final LocalDate enrolledAt;

    public Enrollment(Student student, Course course) {
        this.student = student;
        this.course = course;
        this.enrolledAt = LocalDate.now();
    }

    public Student getStudent() { return student; }
    public Course getCourse() { return course; }
    public Grade getGrade() { return grade; }
    public void setGrade(Grade grade) { this.grade = grade; }
    public double getMarks() { return marks; }
    public void setMarks(double marks) { this.marks = marks; }
    public LocalDate getEnrolledAt() { return enrolledAt; }

    @Override
    public String toString() {
        return String.format("Enrollment: %s in %s [%s, %.2f]", student.getProfile(), course.getTitle(), grade, marks);
    }
}