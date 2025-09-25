package edu.ccrm.util;

import java.nio.file.*;
import java.io.IOException;

public class RecursionUtil {
    // Recursively compute total size of files in a dir
    public static long computeTotalSize(Path dir) throws IOException {
        if (!Files.isDirectory(dir)) return 0;
        long size = 0;
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir)) {
            for (Path entry : stream) {
                if (Files.isDirectory(entry)) {
                    size += computeTotalSize(entry);
                } else {
                    size += Files.size(entry);
                }
            }
        }
        return size;
    }
}