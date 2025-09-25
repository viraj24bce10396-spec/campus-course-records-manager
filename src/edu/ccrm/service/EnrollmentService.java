package edu.ccrm.service;

import edu.ccrm.domain.*;
import java.util.*;

public class EnrollmentService implements Persistable {
    private final List<Enrollment> enrollments = new ArrayList<>();
    private static final int MAX_CREDITS_PER_SEMESTER = 30;

    public void enroll(Student student, Course course) throws DuplicateEnrollmentException, MaxCreditLimitExceededException {
        for (Enrollment e : enrollments) {
            if (e.getStudent().equals(student) && e.getCourse().equals(course))
                throw new DuplicateEnrollmentException("Already enrolled!");
        }
        int credits = enrollments.stream()
            .filter(e -> e.getStudent().equals(student) && e.getCourse().getSemester() == course.getSemester())
            .mapToInt(e -> e.getCourse().getCredits()).sum();
        if (credits + course.getCredits() > MAX_CREDITS_PER_SEMESTER)
            throw new MaxCreditLimitExceededException("Max credits exceeded!");
        enrollments.add(new Enrollment(student, course));
        student.enroll(course);
    }

    public List<Enrollment> getEnrollments() { return enrollments; }

    @Override
    public void save() { /* ... */ }
    @Override
    public void load() { /* ... */ }
}