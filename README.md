# Campus Course & Records Manager (CCRM)

## Project Overview
A Java console application to manage students, courses, enrollments, grades, import/export, and backups for a campus.

## How to Run
- Requires JDK 17+ (tested with JDK 21)
- Compile:  
  `javac -d bin @sources.txt`
- Run:  
  `java -cp bin edu.ccrm.cli.MainCLI`

## Evolution of Java
- 1995: Java 1.0 released (write once, run anywhere)
- 2004: Generics introduced
- 2014: Lambdas and streams (Java 8)
- 2018: Local variable type inference (`var`)
- 2021+: Records, pattern matching, preview features

## Java ME vs SE vs EE
- **ME** (Micro Edition): For mobile/embedded devices (limited APIs)
- **SE** (Standard Edition): Core Java (desktop/server apps)
- **EE** (Enterprise Edition): Adds web, distributed, and enterprise APIs (Servlets, EJBs)

## JDK / JRE / JVM Explanation
- **JDK**: Java Development Kit (compiler + tools + JRE)
- **JRE**: Java Runtime Environment (JVM + core libraries)
- **JVM**: Java Virtual Machine (runs bytecode, platform-independent)

## Windows Install Steps
1. Download JDK from [Oracle](https://www.oracle.com/java/technologies/downloads/)
2. Install and set `JAVA_HOME`
3. Verify: `java -version`
   ![jdk-version](screenshots/jdk-version.png)

## Eclipse Setup Steps
1. Download [Eclipse IDE](https://www.eclipse.org/downloads/)
2. Import project, set JDK
   ![eclipse-project-setup](screenshots/eclipse-project-setup.png)
3. Run MainCLI
   ![eclipse-run](screenshots/eclipse-run.png)

## Mapping Table

| Syllabus Topic           | Class/File/Method            |
|------------------------- |-----------------------------|
| OOP (class/object)       | Student.java, Course.java   |
| Inheritance              | Person.java, Instructor.java|
| Enum                     | Grade.java, Semester.java   |
| Exception handling       | EnrollmentService.java      |
| File I/O                 | ImportExportService.java    |
| Collections              | StudentService.java         |
| Assertions               | MainCLI.java (sample usage) |

## Enabling Assertions
- Add `-ea` to Java run command:
  `java -ea -cp bin edu.ccrm.cli.MainCLI`

## Screenshots
- JDK installation: ![jdk-version](screenshots/jdk-version.png)
- Eclipse setup: ![eclipse-project-setup](screenshots/eclipse-project-setup.png)
- Program menu: ![program-menu](screenshots/program-menu.png)
- Exports/Backups: See `exports/` folder

## Optional Demo Video
- [YouTube/Drive Demo](your-demo-link-here)
