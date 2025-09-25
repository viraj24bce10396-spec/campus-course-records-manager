package edu.ccrm.service;

import edu.ccrm.domain.*;
import java.util.*;

public class TranscriptService {
    public double computeGPA(Student student, List<Enrollment> enrollments) {
        int totalPoints = 0, totalCredits = 0;
        for (Enrollment e : enrollments) {
            if (e.getStudent().equals(student) && e.getGrade() != null) {
                totalPoints += e.getGrade().getPoints() * e.getCourse().getCredits();
                totalCredits += e.getCourse().getCredits();
            }
        }
        return totalCredits == 0 ? 0.0 : ((double) totalPoints) / totalCredits;
    }

    public String printTranscript(Student student, List<Enrollment> enrollments) {
        StringBuilder sb = new StringBuilder();
        sb.append(student.getProfile()).append("\nCourses:\n");
        for (Enrollment e : enrollments) {
            if (e.getStudent().equals(student)) sb.append(e).append("\n"); // polymorphic toString
        }
        sb.append("GPA: ").append(computeGPA(student, enrollments));
        return sb.toString();
    }
}