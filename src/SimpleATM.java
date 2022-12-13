import java.util.*;

public class SimpleATM {
    public static void main(String[] args) throws Exception {
        // Scanner object for getting input from keyboard
        Scanner scanner = new Scanner(System.in);
        // Initialize the name of our bank
        Bank bank = new Bank("Pochita Bank");
        // Add a user to our bank
        bank.addUser("Anya Forger", "123456");
        // The reference for users who will log in
        User currentUser;
        // The interface of the ATM
        while (true) {
            // Validate user who will log in
            currentUser = SimpleATM.loginMenu(bank, scanner);
            // Serve users who have successfully logged in
            SimpleATM.mainMenu(currentUser, scanner);
        }
    }

    // Display the login menu of the simple ATM
    public static User loginMenu(Bank bank, Scanner scanner) {
        String userId, pin;
        User authenticatedUser;
        while (true) {
            System.out.printf("\n\nWelcome to %s\n\n", bank.getName());
            System.out.print("Enter user ID : ");
            userId = scanner.nextLine();
            System.out.print("Enter PIN : ");
            pin = scanner.nextLine();

            authenticatedUser = bank.userLogin(userId, pin);
            if (authenticatedUser == null) {
                System.out.println("Incorrect user ID or PIN combination. Please try again.");
                continue;
            }
            break;
        }
        return authenticatedUser;
    }

    // Display the main menu of the simple ATM
    public static void mainMenu(User currentUser, Scanner scanner) {
        currentUser.printAccountSummary();
        int choice;
        do {
            System.out.println("Welcome " + currentUser.getFirstName() + ", what do you want to do?");
            System.out.println("[1] Change PIN");
            System.out.println("[2] Balance Inquiry");
            System.out.println("[3] Withdrawal");
            System.out.println("[4] Deposit");
            System.out.println("[5] Transfer");
            System.out.println("[6] Transaction History");
            System.out.println("[7] Switch Account");
            System.out.println("[8] Exit");

            if (choice < 1 || choice > 9)
                System.out.println("Invalid choice. Please choose 1-8.");
        } while (choice < 1 || choice > 5);

        switch (choice) {
            case 1:
                SimpleATM.changePin();
                break;
            case 2:
                SimpleATM.showBalanceInquiry();
                break;
            case 3:
                SimpleATM.withdrawalFunds();
                break;
            case 4:
                SimpleATM.depositFunds();
                break;
            case 5:
                SimpleATM.transferFunds();
                break;
            case 6:
                SimpleATM.showTransactionHistory();
                break;
            case 7:
                SimpleATM.switchAccount();
                break;
            case 8:
                System.exit(0);
            default:
                SimpleATM.mainMenu(currentUser, scanner);
        }
    }
}
