package edu.ccrm.io;

import edu.ccrm.domain.Student;
import java.nio.file.*;
import java.util.*;
import java.io.*;

public class ImportExportService {
    // NIO.2 import example (stub)
    public List<Student> importStudents(String filePath) throws IOException {
        List<Student> result = new ArrayList<>();
        Files.lines(Paths.get(filePath)).forEach(line -> {
            String[] fields = line.split(",");
            if (fields.length >= 4) {
                result.add(new Student(fields[0], fields[1], fields[2], fields[3]));
            }
        });
        return result;
    }
}