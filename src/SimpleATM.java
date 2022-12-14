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
            SimpleATM.mainMenu(currentAccount, scanner, bank);
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
    public static void mainMenu(Account currentAccount, Scanner scanner, Bank bank) {
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
            System.out.println();

            if (choice < 1 || choice > 8)
                System.out.println("Invalid choice. Please choose 1-8.");
        } while (choice < 1 || choice > 8);
        // if (choice == 8) {
        // System.exit(0);
        // }

        switch (choice) {
            case 1:
                SimpleATM.changePin(currentAccount, scanner, bank);
                break;
            case 2:
                SimpleATM.showBalanceInquiry(currentAccount, scanner, bank);
                break;
            case 3:
                SimpleATM.withdrawalFunds(currentAccount, scanner, bank);
                break;
            case 4:
                SimpleATM.depositFunds(currentAccount, scanner, bank);
                break;
            case 5:
                SimpleATM.transferFunds(currentAccount, scanner, bank);
                break;
            case 6:
                SimpleATM.showTransactionHistory(currentAccount, scanner, bank);
                break;
            case 7:
                SimpleATM.switchAccount(currentAccount, scanner, bank);
                break;
            case 8:
                System.exit(0);
            default:
                SimpleATM.mainMenu(currentAccount, scanner, bank);
        }
    }

    // Lead the user to return to main menu
    public static void returnToMainMenu(Account currentAccount, Scanner scanner, Bank bank) {
        String input;
        do {
            System.out.print("\nPlease click enter to return to main menu.");
            input = scanner.nextLine();
            if (input.compareTo("\n") == 0)
                SimpleATM.mainMenu(currentAccount, scanner, bank);
        } while (input.compareTo("\n") != 0);
    }

    // Change the PIN of the bank card id
    public static void changePin(Account currentAccount, Scanner scanner, Bank bank) {
        boolean isThePinCorrect;
        int lengthPin;
        String newPin;
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
            newPin = scanner.nextLine();
            lengthPin = newPin.length();
            if (lengthPin == 0) {
                System.out.println("PIN can't be empty. Please re-enter the new PIN.");
                isThePinCorrect = false;
            } else if ((lengthPin > 0 && lengthPin < 6) || lengthPin > 6) {
                System.out.println("PIN must be 6 digits. Please re-enter the new PIN.");
                isThePinCorrect = false;
            } else
                isThePinCorrect = true;
        } while (!isThePinCorrect);

        do {
            System.out.print("Re-enter the new pin\t: ");
            String newReenterPin = scanner.nextLine();
            lengthPin = newReenterPin.length();
            if (lengthPin == 0) {
                System.out.println("PIN can't be empty. Please re-enter the new PIN.");
                continue;
            } else if ((lengthPin > 0 && lengthPin < 6) || lengthPin > 6) {
                System.out.println("PIN must be 6 digits. Please re-enter the new PIN.");
                continue;
            } else {
                if (newReenterPin.compareTo(newPin) == 0) {
                    isThePinCorrect = true;
                    System.out.println("PIN has been changed.");
                } else {
                    isThePinCorrect = false;
                    System.out.println("PIN must be the same as previously entered.");
                }
            }
        } while (!isThePinCorrect);

        currentAccount.setPin(newPin);
        SimpleATM.returnToMainMenu(currentAccount, scanner, bank);

    }

    // Show the balance inquiry of the account
    public static void showBalanceInquiry(Account currentAccount, Scanner scanner, Bank bank) {
        currentAccount.printBalanceInquiry();
        SimpleATM.returnToMainMenu(currentAccount, scanner, bank);
    }

    // Withdrawal process from the account's balance
    public static void withdrawalFunds(Account currentAccount, Scanner scanner, Bank bank) {
        double balance, amount;
        do {
            balance = currentAccount.getBalance();
            System.out.printf("Enter the amount to withdraw (max $%.2f) : $", balance);
            amount = scanner.nextDouble();
            if (amount < 0) {
                System.out.println("Amount must be greater than zero.");
            } else if (amount > balance) {
                System.out.printf("Amount must not be greater than balance of $%.2f.\n", balance);
            }
        } while (amount < 0 || amount > balance);
        System.out.print("Enter a note : ");
        String note = scanner.nextLine();
        amount *= -1;
        currentAccount.addTransaction(amount, note);
        SimpleATM.returnToMainMenu(currentAccount, scanner, bank);
    }

    // Deposit process to the account's balance
    public static void depositFunds(Account currentAccount, Scanner scanner, Bank bank) {
        double amount;
        do {
            System.out.print("Enter the amount to deposit : $");
            amount = scanner.nextDouble();
            if (amount < 0) {
                System.out.println("Amount must be greater than zero.");
            }
        } while (amount < 0);
        System.out.printf("Enter a note : ");
        String note = scanner.nextLine();
        currentAccount.addTransaction(amount, note);
        SimpleATM.returnToMainMenu(currentAccount, scanner, bank);
    }

    // Transfer process from the account's balance
    public static void transferFunds(Account currentAccount, Scanner scanner, Bank bank) {
        double balance, amount;
        String className = currentAccount.getClass().getName();
        if (className.compareTo("CheckingAccount") == 0) {
            do {
                balance = currentAccount.getBalance();
                System.out.printf("Enter the amount to transfer (max $%.2f) : $", balance);
                amount = scanner.nextDouble();
                if (amount < 0) {
                    System.out.println("Amount must be greater than zero.");
                } else if (amount > balance) {
                    System.out.printf("Amount must not be greater than balance of $%.2f.\n", balance);
                }
                System.out.print("Enter a note : ");
                String note = scanner.nextLine();
                amount *= -1;
                currentAccount.addTransaction(amount, note);
            } while (amount < 0 || amount > balance);
        } else if (className.compareTo("SavingsAccount") == 0) {
            System.out.println("Savings account can't transfer to a checking account.");
        }
        SimpleATM.returnToMainMenu(currentAccount, scanner, bank);
    }

    // Show the transaction history of the account
    public static void showTransactionHistory(Account currentAccount, Scanner scanner, Bank bank) {
        currentAccount.printTransactionHistory();
        SimpleATM.returnToMainMenu(currentAccount, scanner, bank);
    }

    // Switch from checking account to savings account and vice versa
    public static void switchAccount(Account currentAccount, Scanner scanner, Bank bank) {
        currentAccount = bank.getAccount(currentAccount);
        SimpleATM.returnToMainMenu(currentAccount, scanner, bank);
    }
}
