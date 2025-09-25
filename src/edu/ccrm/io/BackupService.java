package edu.ccrm.io;

import java.nio.file.*;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class BackupService {
    public void backupData(String sourceDir) throws IOException {
        String timestamp = DateTimeFormatter.ofPattern("yyyyMMddHHmmss").format(LocalDateTime.now());
        Path backupDir = Paths.get(sourceDir, "backup_" + timestamp);
        Files.createDirectories(backupDir);
        Files.walk(Paths.get(sourceDir)).forEach(path -> {
            try {
                if (Files.isRegularFile(path)) {
                    Path dest = backupDir.resolve(Paths.get(sourceDir).relativize(path));
                    Files.copy(path, dest, StandardCopyOption.REPLACE_EXISTING);
                }
            } catch (IOException e) { e.printStackTrace(); }
        });
    }
}