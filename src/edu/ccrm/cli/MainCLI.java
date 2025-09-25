package edu.ccrm.cli;

import edu.ccrm.config.AppConfig;
import edu.ccrm.domain.*;
import edu.ccrm.service.*;
import edu.ccrm.io.*;

import java.util.*;
import java.util.UUID;
import java.io.IOException;

public class MainCLI {
    private final Scanner sc = new Scanner(System.in);
    private final StudentService studentService = new StudentService();
    private final CourseService courseService = new CourseService();
    private final EnrollmentService enrollmentService = new EnrollmentService();
    private final TranscriptService transcriptService = new TranscriptService();
    private final ImportExportService importExportService = new ImportExportService();
    private final BackupService backupService = new BackupService();

    public void start() {
        System.out.println("Campus Course & Records Manager");
        AppConfig config = AppConfig.getInstance();
        boolean running = true;
        MAIN_MENU: while (running) {
            System.out.println("\n1. Manage Students");
            System.out.println("2. Manage Courses");
            System.out.println("3. Enrollments");
            System.out.println("4. Grades/Transcripts");
            System.out.println("5. Import/Export");
            System.out.println("6. Backup");
            System.out.println("7. Reports");
            System.out.println("8. Exit");
            System.out.print("Select option: ");
            int ch = readInt();
            switch (ch) {
                case 1: manageStudents(); break;
                case 2: manageCourses(); break;
                case 3: manageEnrollments(); break;
                case 4: manageTranscripts(); break;
                case 5: importExportMenu(); break;
                case 6: backupMenu(); break;
                case 7: reportsMenu(); break;
                case 8: break MAIN_MENU;
                default: System.out.println("Invalid choice!");
            }
        }
        System.out.println("Exiting CCRM...");
    }

    /** Student Management Submenu */
    private void manageStudents() {
        boolean studentMenu = true;
        while (studentMenu) {
            System.out.println("\n--- Student Management ---");
            System.out.println("1. Add Student");
            System.out.println("2. List Students");
            System.out.println("3. Deactivate Student");
            System.out.println("4. Back");
            System.out.print("Select option: ");
            int choice = readInt();
            switch (choice) {
                case 1:
                    System.out.print("Enter Name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter Email: ");
                    String email = sc.nextLine();
                    String regNo = "REG" + (int)(Math.random() * 10000);
                    Student s = new Student(UUID.randomUUID().toString(), regNo, name, email);
                    studentService.addStudent(s);
                    System.out.println("Student added! RegNo: " + regNo);
                    break;
                case 2:
                    System.out.println("Students:");
                    for (Student st : studentService.listStudents())
                        System.out.println(st.getProfile());
                    break;
                case 3:
                    System.out.print("Enter RegNo to deactivate: ");
                    String r = sc.nextLine();
                    studentService.deactivateStudent(r);
                    System.out.println("Student " + r + " deactivated.");
                    break;
                case 4:
                    studentMenu = false;
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    /** Course Management Submenu */
    private void manageCourses() {
        boolean courseMenu = true;
        while (courseMenu) {
            System.out.println("\n--- Course Management ---");
            System.out.println("1. Add Course");
            System.out.println("2. List Courses");
            System.out.println("3. Deactivate Course");
            System.out.println("4. Back");
            System.out.print("Select option: ");
            int choice = readInt();
            switch (choice) {
                case 1:
                    System.out.print("Enter Course Code: ");
                    String code = sc.nextLine();
                    System.out.print("Enter Title: ");
                    String title = sc.nextLine();
                    System.out.print("Enter Credits: ");
                    int credits = readInt();
                    System.out.print("Enter Department: ");
                    String dept = sc.nextLine();
                    System.out.print("Enter Semester (SPRING/SUMMER/FALL): ");
                    String semStr = sc.nextLine().toUpperCase();
                    Semester semester = Semester.valueOf(semStr);
                    // For demo, instructor will be default
                    Instructor instructor = new Instructor(UUID.randomUUID().toString(), "Dr. Default", "dr@institute.com", dept);
                    Course c = new Course(new CourseCode(code), title, credits, instructor, semester, dept);
                    courseService.addCourse(c);
                    System.out.println("Course added!");
                    break;
                case 2:
                    System.out.println("Courses:");
                    for (Course c2 : courseService.listCourses())
                        System.out.println(c2);
                    break;
                case 3:
                    System.out.print("Enter Course Code to deactivate: ");
                    String cc = sc.nextLine();
                    Course c3 = courseService.findByCode(cc);
                    if (c3 != null) {
                        c3.deactivate();
                        System.out.println("Course " + cc + " deactivated.");
                    } else {
                        System.out.println("Course not found.");
                    }
                    break;
                case 4:
                    courseMenu = false;
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    /** Enrollment Submenu */
    private void manageEnrollments() {
        boolean enrollMenu = true;
        while (enrollMenu) {
            System.out.println("\n--- Enrollment Management ---");
            System.out.println("1. Enroll Student");
            System.out.println("2. List Enrollments");
            System.out.println("3. Back");
            System.out.print("Select option: ");
            int choice = readInt();
            switch (choice) {
                case 1:
                    System.out.print("Enter Student RegNo: ");
                    String regNo = sc.nextLine();
                    System.out.print("Enter Course Code: ");
                    String code = sc.nextLine();
                    Student s = studentService.findByRegNo(regNo);
                    Course c = courseService.findByCode(code);
                    if (s == null || c == null) {
                        System.out.println("Student or Course not found.");
                        break;
                    }
                    try {
                        enrollmentService.enroll(s, c);
                        System.out.println("Enrolled successfully!");
                    } catch (DuplicateEnrollmentException | MaxCreditLimitExceededException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
                case 2:
                    System.out.println("Enrollments:");
                    for (Enrollment e : enrollmentService.getEnrollments())
                        System.out.println(e);
                    break;
                case 3:
                    enrollMenu = false;
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    /** Transcript/Grades Submenu */
    private void manageTranscripts() {
        boolean transcriptMenu = true;
        while (transcriptMenu) {
            System.out.println("\n--- Transcript/Grades ---");
            System.out.println("1. Record Grade");
            System.out.println("2. Print Transcript");
            System.out.println("3. Back");
            System.out.print("Select option: ");
            int choice = readInt();
            switch (choice) {
                case 1:
                    System.out.print("Enter Student RegNo: ");
                    String regNo = sc.nextLine();
                    System.out.print("Enter Course Code: ");
                    String code = sc.nextLine();
                    System.out.print("Enter Marks: ");
                    double marks = readDouble();
                    System.out.print("Enter Grade (S/A/B/C/D/E/F): ");
                    String gradeStr = sc.nextLine().toUpperCase();
                    Grade grade = Grade.valueOf(gradeStr);
                    for (Enrollment e : enrollmentService.getEnrollments()) {
                        if (e.getStudent().getRegNo().equals(regNo) && e.getCourse().getCode().getValue().equals(code)) {
                            e.setMarks(marks);
                            e.setGrade(grade);
                            System.out.println("Grade assigned.");
                        }
                    }
                    break;
                case 2:
                    System.out.print("Enter Student RegNo: ");
                    String regNo2 = sc.nextLine();
                    Student s2 = studentService.findByRegNo(regNo2);
                    if (s2 == null) {
                        System.out.println("Student not found.");
                        break;
                    }
                    System.out.println(transcriptService.printTranscript(s2, enrollmentService.getEnrollments()));
                    break;
                case 3:
                    transcriptMenu = false;
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    /** Import/Export Submenu */
    private void importExportMenu() {
        boolean menu = true;
        while (menu) {
            System.out.println("\n--- Import/Export ---");
            System.out.println("1. Import Students");
            System.out.println("2. Export Students");
            System.out.println("3. Back");
            System.out.print("Select option: ");
            int choice = readInt();
            switch (choice) {
                case 1:
                    System.out.print("Enter CSV path (e.g., test-data/students.csv): ");
                    String path = sc.nextLine();
                    try {
                        List<Student> imported = importExportService.importStudents(path);
                        for (Student s : imported) studentService.addStudent(s);
                        System.out.println("Imported " + imported.size() + " students.");
                    } catch (IOException ex) {
                        System.out.println("Error importing: " + ex.getMessage());
                    }
                    break;
                case 2:
                    // Implement export logic using importExportService
                    System.out.println("Export not implemented.");
                    break;
                case 3:
                    menu = false;
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    /** Backup Submenu */
    private void backupMenu() {
        System.out.println("\n--- Backup ---");
        System.out.print("Enter data directory to backup (default: ./data): ");
        String dir = sc.nextLine();
        if (dir.isEmpty()) dir = "./data";
        try {
            backupService.backupData(dir);
            System.out.println("Backup complete.");
        } catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }

    /** Reports Submenu */
    private void reportsMenu() {
        System.out.println("\n--- Reports ---");
        // Example: Top students by GPA
        Map<Student, Double> gpas = new HashMap<>();
        for (Student s : studentService.listStudents()) {
            double gpa = transcriptService.computeGPA(s, enrollmentService.getEnrollments());
            gpas.put(s, gpa);
        }
        List<Student> sorted = new ArrayList<>(gpas.keySet());
        sorted.sort((s1, s2) -> Double.compare(gpas.get(s2), gpas.get(s1)));
        System.out.println("Top Students by GPA:");
        int rank = 1;
        for (Student s : sorted)
            System.out.printf("%d. %s - GPA: %.2f\n", rank++, s.getProfile(), gpas.get(s));
    }

    /** Utility: Read int (with error handling) */
    private int readInt() {
        while (true) {
            try { return Integer.parseInt(sc.nextLine()); }
            catch (Exception e) { System.out.print("Enter a valid number: "); }
        }
    }
    /** Utility: Read double (with error handling) */
    private double readDouble() {
        while (true) {
            try { return Double.parseDouble(sc.nextLine()); }
            catch (Exception e) { System.out.print("Enter a valid decimal number: "); }
        }
    }

    public static void main(String[] args) {
        new MainCLI().start();
    }
}