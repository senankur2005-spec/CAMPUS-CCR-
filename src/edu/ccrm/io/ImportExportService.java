package edu.ccrm.io;

import edu.ccrm.domain.*;
import edu.ccrm.config.AppConfig;

import java.nio.file.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ImportExportService {
    private final AppConfig config;

    public ImportExportService() {
        this.config = AppConfig.getInstance();
    }

    public List<Student> importStudentsFromCSV(Path filePath) throws Exception {
        try (Stream<String> lines = Files.lines(filePath)) {
            return lines.skip(1) // Skip header
                .map(this::parseStudentFromCSV)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
        }
    }

    private Student parseStudentFromCSV(String line) {
        try {
            String[] fields = line.split(",");
            if (fields.length >= 4) {
                return new Student.Builder(fields[0], fields[1])
                    .fullName(fields[2])
                    .email(fields[3])
                    .department(Department.valueOf(fields[4]))
                    .build();
            }
        } catch (Exception e) {
            System.err.println("Error parsing student: " + e.getMessage());
        }
        return null;
    }

    public void exportStudentsToCSV(List<Student> students, Path filePath) throws Exception {
        List<String> lines = new ArrayList<>();
        lines.add("ID,RegNo,FullName,Email,Department,GPA");
        
        lines.addAll(students.stream()
            .map(s -> String.format("%s,%s,%s,%s,%s,%.2f", 
                s.getId(), s.getRegNo(), s.getFullName(), s.getEmail(), 
                s.getDepartment(), s.calculateGPA()))
            .collect(Collectors.toList()));

        Files.write(filePath, lines, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
    }

    public Path createBackup() throws Exception {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
        Path backupDir = config.getBackupDirectory().resolve(timestamp);
        Files.createDirectories(backupDir);

        // Copy all data files to backup directory
        try (Stream<Path> files = Files.list(config.getDataDirectory())) {
            files.forEach(file -> {
                try {
                    Files.copy(file, backupDir.resolve(file.getFileName()));
                } catch (Exception e) {
                    System.err.println("Failed to backup: " + file.getFileName());
                }
            });
        }

        return backupDir;
    }

    // Recursive method to calculate backup directory size
    public long calculateBackupSize(Path directory) throws Exception {
        assert directory != null : "Directory cannot be null";
        
        if (!Files.exists(directory)) return 0;

        try (Stream<Path> paths = Files.walk(directory)) {
            return paths.filter(Files::isRegularFile)
                .mapToLong(this::getFileSize)
                .sum();
        }
    }

    private long getFileSize(Path file) {
        try {
            return Files.size(file);
        } catch (Exception e) {
            return 0;
        }
    }
}