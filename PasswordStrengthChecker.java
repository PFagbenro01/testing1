import java.util.Scanner;

public class PasswordStrengthChecker {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter a password to check (do not use a real password): ");
        String password = scanner.nextLine();

        boolean longEnough = password.length() >= 12;
        boolean hasUppercase = false;
        boolean hasLowercase = false;
        boolean hasNumber = false;
        boolean hasSpecialCharacter = false;
        boolean hasWhitespace = false;

        for (int index = 0; index < password.length(); index++) {
            char character = password.charAt(index);
            if (Character.isUpperCase(character)) {
                hasUppercase = true;
            }
            if (Character.isLowerCase(character)) {
                hasLowercase = true;
            }
            if (Character.isDigit(character)) {
                hasNumber = true;
            }
            if (Character.isWhitespace(character)) {
                hasWhitespace = true;
            }
            if (!Character.isLetterOrDigit(character) && !Character.isWhitespace(character)) {
                hasSpecialCharacter = true;
            }
        }

        boolean passesAllRequirements = longEnough && hasUppercase && hasLowercase
                && hasNumber && hasSpecialCharacter && !hasWhitespace;
        int passedRequirements = countPassedRequirements(longEnough, hasUppercase, hasLowercase,
                hasNumber, hasSpecialCharacter, !hasWhitespace);

        System.out.println("\nPassword requirements:");
        printResult("At least 12 characters", longEnough);
        printResult("At least one uppercase letter", hasUppercase);
        printResult("At least one lowercase letter", hasLowercase);
        printResult("At least one number", hasNumber);
        printResult("At least one special character", hasSpecialCharacter);
        printResult("No whitespace", !hasWhitespace);

        String strength;
        if (passesAllRequirements) {
            strength = "STRONG";
        } else if (passedRequirements >= 4) {
            strength = "MODERATE";
        } else {
            strength = "WEAK";
        }
        System.out.println("\nPassword strength: " + strength);

        scanner.close();
    }

    private static void printResult(String requirement, boolean passed) {
        System.out.println((passed ? "PASS: " : "FAIL: ") + requirement);
    }

    private static int countPassedRequirements(boolean... requirements) {
        int count = 0;
        for (boolean requirement : requirements) {
            if (requirement) {
                count++;
            }
        }
        return count;
    }
}