package edu.ccrm.service;

import edu.ccrm.domain.*;
import java.util.*;

public class CourseService implements Persistable {
    private final Map<String, Course> courses = new HashMap<>();

    public void addCourse(Course c) {
        courses.put(c.getCode().getValue(), c);
    }

    public List<Course> listCourses() {
        return new ArrayList<>(courses.values());
    }

    public Course findByCode(String code) {
        return courses.get(code);
    }

    public void save() {
        // Implement file save logic here if needed
    }

    public void load() {
        // Implement file load logic here if needed
    }
}