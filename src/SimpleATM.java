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
        // User currentUser;
        // The reference for users who will log in as an owner of the account
        Account currentAccount;
        // The interface of the ATM
        while (true) {
            // Validate user who will log in
            currentAccount = SimpleATM.loginMenu(bank, scanner);
            // Serve users who have successfully logged in
            SimpleATM.mainMenu(currentAccount, scanner);
        }
    }

    // Display the login menu of the simple ATM
    public static Account loginMenu(Bank bank, Scanner scanner) {
        String userId, bankCardId, pin;
        Account authenticatedAccount;
        do {
            System.out.printf("\n\nWelcome to %s\n\n", bank.getName());
            System.out.print("Enter user ID : ");
            userId = scanner.nextLine();
            System.out.print("Enter bank card ID : ");
            bankCardId = scanner.nextLine();
            System.out.print("Enter PIN : ");
            pin = scanner.nextLine();

            authenticatedAccount = bank.login(userId, bankCardId, pin);
            if (authenticatedAccount == null) {
                System.out.println("Incorrect user ID/PIN combination or " +
                        "bank card ID\nis not registered. Please try again.");
            }
        } while (authenticatedAccount == null);
        return authenticatedAccount;
    }

    // Display the main menu of the simple ATM
    public static void mainMenu(Account currentAccount, Scanner scanner) {
        currentAccount.printAccountSummary();
        int choice;
        do {
            System.out.printf("Welcome %s, what do you want to do?\n",
                    currentAccount.getHolder().getFirstName());
            System.out.println("[1] Change PIN");
            System.out.println("[2] Balance Inquiry");
            System.out.println("[3] Withdrawal");
            System.out.println("[4] Deposit");
            System.out.println("[5] Transfer");
            System.out.println("[6] Transaction History");
            System.out.println("[7] Switch Account");
            System.out.println("[8] Exit");
            System.out.println();
            System.out.print("Enter choice : ");
            choice = scanner.nextInt();

            if (choice < 1 || choice > 8)
                System.out.println("Invalid choice. Please choose 1-8.");
        } while (choice < 1 || choice > 8);
        // if (choice == 8) {
        // System.exit(0);
        // }

        switch (choice) {
            case 1:
                SimpleATM.changePin(currentAccount, scanner);
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
                SimpleATM.mainMenu(currentAccount, scanner);
        }
    }

    // Change the PIN of the bank card id
    public static void changePin(Account currentAccount, Scanner scanner) {
        boolean isThePinCorrect;
        int lengthPin;
        do {
            System.out.print("Enter the old pin\t: ");
            String oldPin = scanner.nextLine();
            lengthPin = oldPin.length();
            isThePinCorrect = currentAccount.validatePin(oldPin);
            if (!isThePinCorrect)
                System.out.println("Incorrect PIN combination. Please try again.");
        } while (!isThePinCorrect);
        do {
            System.out.print("Enter a new pin\t: ");
            String newPin = scanner.nextLine();
            lengthPin = newPin.length();
            if (lengthPin == 0) {
                System.out.println("PIN can't be empty. Please re-enter the new PIN.");
                continue;
            } else if ((lengthPin > 0 && lengthPin < 6) || lengthPin > 6) {
                System.out.println("PIN must be 6 digits. Please re-enter the new PIN.");
                continue;
            }
            System.out.print("Re-enter the new pin\t: ");
            newPin = scanner.nextLine();
            currentAccount.setPin(newPin);
        } while (!isThePinCorrect);
        do {
            System.out.print("Enter a new pin\t: ");
            String newPin = scanner.nextLine();
            lengthPin = newPin.length();
            if (lengthPin == 0) {
                System.out.println("PIN can't be empty. Please re-enter the new PIN.");
                continue;
            } else if ((lengthPin > 0 && lengthPin < 6) || lengthPin > 6) {
                System.out.println("PIN must be 6 digits. Please re-enter the new PIN.");
                continue;
            }
        } while();
    }
}
