import java.util.Scanner;

public class SchoolGradingSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the student's name: ");
        String studentName = scanner.nextLine().trim();
        while (studentName.isEmpty()) {
            System.out.print("Name cannot be empty. Enter the student's name: ");
            studentName = scanner.nextLine().trim();
        }

        double grade = readGrade(scanner, "Enter the student's grade (0-100): ");
        String letterGrade;

        if (grade >= 90) {
            letterGrade = "A";
        } else if (grade >= 80) {
            letterGrade = "B";
        } else if (grade >= 70) {
            letterGrade = "C";
        } else if (grade >= 60) {
            letterGrade = "D";
        } else {
            letterGrade = "F";
        }

        System.out.println("\nStudent: " + studentName);
        System.out.printf("Numeric grade: %.2f%n", grade);
        System.out.println("Letter grade: " + letterGrade);
        System.out.println(grade >= 60 ? "Status: Passing" : "Status: Failing");

        scanner.close();
    }

    private static double readGrade(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            if (scanner.hasNextDouble()) {
                double grade = scanner.nextDouble();
                if (grade >= 0 && grade <= 100) {
                    return grade;
                }
            } else {
                scanner.next();
            }
            System.out.println("Please enter a number from 0 to 100.");
        }
    }
}