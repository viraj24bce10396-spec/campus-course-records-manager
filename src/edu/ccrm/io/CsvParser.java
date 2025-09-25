package edu.ccrm.io;

import java.nio.file.*;
import java.io.IOException;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Generic CSV parser for domain objects.
 * Demonstrates functional interfaces and lambdas.
 */
public class CsvParser<T> {

    /**
     * Parses a CSV file given a mapping function that converts String[] to T.
     * Skips header line.
     *
     * @param filePath Path to CSV file
     * @param mapper   Function to map CSV row (String[]) to object T
     * @return List of T objects
     * @throws IOException if file cannot be read
     */
    public List<T> parse(String filePath, Function<String[], T> mapper) throws IOException {
        try (var lines = Files.lines(Paths.get(filePath))) {
            return lines
                .skip(1) // skip header
                .map(line -> line.split(",", -1)) // split with all columns
                .map(mapper)
                .collect(Collectors.toList());
        }
    }

    /**
     * Parses CSV from a List of lines (without header).
     */
    public List<T> parseLines(List<String> lines, Function<String[], T> mapper) {
        return lines.stream()
            .map(line -> line.split(",", -1))
            .map(mapper)
            .collect(Collectors.toList());
    }
}