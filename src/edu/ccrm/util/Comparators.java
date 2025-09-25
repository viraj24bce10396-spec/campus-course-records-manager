package edu.ccrm.util;

import edu.ccrm.domain.*;
import java.util.Comparator;

public class Comparators {
    public static Comparator<Student> byGPA(Map<Student, Double> gpas) {
        return (s1, s2) -> Double.compare(gpas.getOrDefault(s2, 0.0), gpas.getOrDefault(s1, 0.0));
    }
}