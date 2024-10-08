
import java.util.ArrayList;
import java.util.Scanner;

class Student {
    private String name;
    private String studentID;
    private int[] grades;
    private int totalMarks;
    private int averagePercentage;
    private String grade;

    // Constructor
    public Student(String name, String studentID, int[] grades) {
        this.name = name;
        this.studentID = studentID;
        this.grades = grades;
        this.calculateResults();
    }

    // Calculate total marks, average percentage, and grade
    private void calculateResults() {
        totalMarks = 0;
        for (int grade : grades) {
            totalMarks += grade;
        }
        averagePercentage = totalMarks / grades.length;

        if (averagePercentage >= 90) {
            grade = "o+";
        } else if (averagePercentage >= 80) {
            grade = "A+";
        } else if (averagePercentage >= 70) {
            grade = "A";
        } else if (averagePercentage >= 60) {
            grade = "B+";
        } else if (averagePercentage >= 50) {
            grade = "B";
        } else if (averagePercentage >= 40) {
            grade = "C+";
        } else if (averagePercentage >= 30) {
            grade = "C";
        } else {
            grade = "F";
        }
    }

    // Display student results
    public void displayResults() {
        System.out.println("Results for " + name + ":");
        System.out.println("Total Marks: " + totalMarks);
        System.out.println("Average Percentage: " + averagePercentage + "%");
        System.out.println("Grade: " + grade);
        System.out.println("Status: " + (hasPassed() ? "Passed" : "Failed"));
    }

    // Determine if the student has passed
    public boolean hasPassed() {
        return averagePercentage >= 40;
    }

    // Getters
    public int getAveragePercentage() {
        return averagePercentage;
    }
}

class Classroom {
    private ArrayList<Student> students;
    private int numSubjects;

    // Constructor
    public Classroom(int numSubjects) {
        this.students = new ArrayList<>();
        this.numSubjects = numSubjects;
    }

    // Input multiple students
    public void inputStudents() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of students: ");
        int numStudents = scanner.nextInt();

        for (int i = 0; i < numStudents; i++) {
            System.out.println("Enter details for student " + (i + 1) + ":");

            System.out.print("Name: ");
            String name = scanner.next();

            System.out.print("Student ID: ");
            String studentID = scanner.next();

            int[] grades = new int[numSubjects];
            System.out.println("Enter marks for " + numSubjects + " subjects (out of 100):");
            for (int j = 0; j < numSubjects; j++) {
                int marks = scanner.nextInt();
                while (marks < 0 || marks > 100) {
                    System.out.println("Invalid marks. Please enter marks between 0 and 100.");
                    marks = scanner.nextInt();
                }
                grades[j] = marks;
            }

            students.add(new Student(name, studentID, grades));
        }
    }

    // Calculate the class average
    public double calculateClassAverage() {
        int totalPercentage = 0;
        for (Student student : students) {
            totalPercentage += student.getAveragePercentage();
        }
        return (double) totalPercentage / students.size();
    }

    // Display all student results
    public void displayAllResults() {
        for (Student student : students) {
            student.displayResults();
            System.out.println();
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of subjects for each student: ");
        int numSubjects = scanner.nextInt();

        Classroom classroom = new Classroom(numSubjects);

        classroom.inputStudents();
        classroom.displayAllResults();

        System.out.println("Class Average Percentage: " + classroom.calculateClassAverage() + "%");
    }
}

