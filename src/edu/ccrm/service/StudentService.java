package edu.ccrm.service;

import edu.ccrm.domain.Student;
import java.util.*;

public class StudentService implements Persistable {
    private final Map<String, Student> students = new HashMap<>();

    public void addStudent(Student s) {
        if (students.containsKey(s.getRegNo()))
            throw new IllegalArgumentException("Student with regNo already exists.");
        students.put(s.getRegNo(), s);
    }

    public List<Student> listStudents() {
        return new ArrayList<>(students.values());
    }

    public Student findByRegNo(String regNo) {
        return students.get(regNo);
    }

    public void deactivateStudent(String regNo) {
        Student s = students.get(regNo);
        if (s != null) {
            s.deactivate();
        } else {
            System.out.println("Student with RegNo " + regNo + " not found.");
        }
    }

    @Override
    public void save() { /* ... */ }
    @Override
    public void load() { /* ... */ }
}