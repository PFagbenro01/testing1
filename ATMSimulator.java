import java.util.Scanner;

public class ATMSimulator {
    private static final int CORRECT_PIN = 1234;
    private static final int MAX_PIN_ATTEMPTS = 3;
    private static final double STARTING_BALANCE = 5000.00;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double balance = STARTING_BALANCE;

        if (!verifyPin(scanner)) {
            System.out.println("Too many incorrect attempts. Your account is locked.");
            scanner.close();
            return;
        }

        boolean running = true;
        while (running) {
            displayMenu();
            int choice = readInteger(scanner, "Choose an option: ");

            switch (choice) {
                case 1:
                    System.out.printf("Current balance: $%.2f%n", balance);
                    break;
                case 2:
                    double deposit = readAmount(scanner, "Enter deposit amount: ");
                    if (deposit > 0) {
                        balance += deposit;
                        System.out.printf("Deposit successful. New balance: $%.2f%n", balance);
                    } else {
                        System.out.println("Deposit must be greater than $0.00.");
                    }
                    break;
                case 3:
                    double withdrawal = readAmount(scanner, "Enter withdrawal amount: ");
                    if (withdrawal <= 0) {
                        System.out.println("Withdrawal must be greater than $0.00.");
                    } else if (withdrawal > balance) {
                        System.out.println("Withdrawal denied: insufficient funds.");
                    } else {
                        balance -= withdrawal;
                        System.out.printf("Withdrawal successful. New balance: $%.2f%n", balance);
                    }
                    break;
                case 4:
                    running = false;
                    System.out.println("Thank you for using the ATM.");
                    break;
                default:
                    System.out.println("Invalid option. Choose a number from 1 to 4.");
            }
        }

        scanner.close();
    }

    private static boolean verifyPin(Scanner scanner) {
        for (int attempt = 1; attempt <= MAX_PIN_ATTEMPTS; attempt++) {
            int pin = readInteger(scanner, "Enter your four-digit PIN: ");
            if (pin == CORRECT_PIN) {
                System.out.println("PIN accepted.");
                return true;
            }
            System.out.println("Incorrect PIN. Attempts remaining: " + (MAX_PIN_ATTEMPTS - attempt));
        }
        return false;
    }

    private static void displayMenu() {
        System.out.println("\n--- ATM Menu ---");
        System.out.println("1. Check balance");
        System.out.println("2. Deposit");
        System.out.println("3. Withdraw");
        System.out.println("4. Exit");
    }

    private static int readInteger(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            if (scanner.hasNextInt()) {
                return scanner.nextInt();
            }
            scanner.next();
            System.out.println("Please enter a whole number.");
        }
    }

    private static double readAmount(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            if (scanner.hasNextDouble()) {
                double amount = scanner.nextDouble();
                if (Double.isFinite(amount)) {
                    return amount;
                }
                System.out.println("Please enter a finite amount.");
                continue;
            }
            scanner.next();
            System.out.println("Please enter a valid amount.");
        }
    }
}