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
            System.out.printf("\nWelcome to %s\n\n", bank.getName());
            System.out.print("Enter user ID : ");
            userId = scanner.nextLine();
            System.out.print("Enter bank card ID : ");
            bankCardId = scanner.nextLine();
            System.out.print("Enter PIN : ");
            pin = scanner.nextLine();

            authenticatedAccount = bank.login(userId, bankCardId, pin);
            if (authenticatedAccount == null) {
                System.out.println("\nIncorrect user ID/PIN combination or " +
                        "bank card ID\nis not registered. Please try again.\n");
            }
        } while (authenticatedAccount == null);
        return authenticatedAccount;
    }

    // Display the main menu of the simple ATM
    public static void mainMenu(Account currentAccount, Scanner scanner, Bank bank) {
        int choice;
        do {
            currentAccount.printAccountSummary();
            System.out.printf("\nWelcome %s, what do you want to do?\n",
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
                System.out.println("\nInvalid choice. Please choose 1-8.");
        } while (choice < 1 || choice > 8);

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
                scanner.nextLine();
                break;
        }

        if (choice != 8) {
            scanner.nextLine();
            SimpleATM.mainMenu(currentAccount, scanner, bank);
        }
    }

    // Lead the user to return to main menu
    // public static void returnToMainMenu(Account currentAccount, Scanner scanner,
    // Bank bank) {
    // scanner.nextLine();
    // String input, newLine = System.getProperty("line.separator");
    // do {
    // System.out.print("\nPlease click enter to return to main menu.");
    // input = scanner.nextLine();
    // if (input.compareTo(newLine) == 0) {
    // scanner.nextLine();
    // SimpleATM.mainMenu(currentAccount, scanner, bank);
    // }
    // } while (input.compareTo(newLine) != 0);
    // }

    // Change the PIN of the bank card id
    public static void changePin(Account currentAccount, Scanner scanner, Bank bank) {
        boolean isThePinCorrect;
        int lengthPin;
        String newPin;
        scanner.nextLine();
        do {
            System.out.print("\nEnter the old pin : ");
            String oldPin = scanner.nextLine();
            lengthPin = oldPin.length();
            isThePinCorrect = currentAccount.validatePin(oldPin);
            if (!isThePinCorrect)
                System.out.println("Incorrect PIN combination. Please try again.");
        } while (!isThePinCorrect);

        do {
            System.out.print("Enter a new pin : ");
            newPin = scanner.nextLine();
            lengthPin = newPin.length();
            if (lengthPin == 0) {
                System.out.println("\nPIN can't be empty. Please re-enter the new PIN.");
                isThePinCorrect = false;
            } else if ((lengthPin > 0 && lengthPin < 6) || lengthPin > 6) {
                System.out.println("\nPIN must be 6 digits. Please re-enter the new PIN.");
                isThePinCorrect = false;
            } else
                isThePinCorrect = true;
        } while (!isThePinCorrect);

        do {
            System.out.print("Re-enter the new pin : ");
            String newReenterPin = scanner.nextLine();
            lengthPin = newReenterPin.length();
            if (lengthPin == 0) {
                System.out.println("\nPIN can't be empty. Please re-enter the new PIN.");
                continue;
            } else if ((lengthPin > 0 && lengthPin < 6) || lengthPin > 6) {
                System.out.println("\nPIN must be 6 digits. Please re-enter the new PIN.");
                continue;
            } else {
                if (newReenterPin.compareTo(newPin) == 0) {
                    isThePinCorrect = true;
                    System.out.println("\nPIN has been changed.");
                } else {
                    isThePinCorrect = false;
                    System.out.println("\nPIN must be the same as previously entered.\nPlease re-enter the new PIN.");
                }
            }
        } while (!isThePinCorrect);

        currentAccount.setPin(newPin);
        Account account = bank.getAccount(currentAccount);
        account.setPin(newPin);
        // SimpleATM.returnToMainMenu(currentAccount, scanner, bank);
        SimpleATM.mainMenu(currentAccount, scanner, bank);

    }

    // Show the balance inquiry of the account
    public static void showBalanceInquiry(Account currentAccount, Scanner scanner, Bank bank) {
        currentAccount.printBalanceInquiry();
        // SimpleATM.returnToMainMenu(currentAccount, scanner, bank);
        SimpleATM.mainMenu(currentAccount, scanner, bank);
    }

    // Withdrawal process from the account's balance
    public static void withdrawalFunds(Account currentAccount, Scanner scanner, Bank bank) {
        double balance, amount;
        String typeOfTransaction = "Withdrawal";
        do {
            balance = currentAccount.getBalance();
            System.out.printf("\nEnter the amount to withdraw (max $%.2f) : $", balance);
            amount = scanner.nextDouble();
            if (amount < 0) {
                System.out.println("Amount must be greater than zero.");
            } else if (amount > balance) {
                System.out.printf("Amount must not be greater than balance of $%.2f.\n", balance);
            }
        } while (amount < 0 || amount > balance);
        scanner.nextLine();
        System.out.print("Enter a note : ");
        String note = scanner.nextLine();
        amount *= -1;
        currentAccount.addTransaction(amount, typeOfTransaction, note);
        // SimpleATM.returnToMainMenu(currentAccount, scanner, bank);
        SimpleATM.mainMenu(currentAccount, scanner, bank);
    }

    // Deposit process to the account's balance
    public static void depositFunds(Account currentAccount, Scanner scanner, Bank bank) {
        double amount;
        String typeOfTransaction = "Deposit";
        do {
            System.out.print("\nEnter the amount to deposit : $");
            amount = scanner.nextDouble();
            if (amount < 0) {
                System.out.println("Amount must be greater than zero.");
            }
        } while (amount < 0);
        scanner.nextLine();
        System.out.printf("Enter a note : ");
        String note = scanner.nextLine();
        currentAccount.addTransaction(amount, typeOfTransaction, note);
        // SimpleATM.returnToMainMenu(currentAccount, scanner, bank);
        SimpleATM.mainMenu(currentAccount, scanner, bank);
    }

    // Transfer process from the account's balance
    public static void transferFunds(Account currentAccount, Scanner scanner, Bank bank) {
        double balance, amount;
        String typeOfTransaction = "Transfer";
        String className = currentAccount.getClass().getName();
        if (className.compareTo("CheckingAccount") == 0) {
            do {
                balance = currentAccount.getBalance();
                System.out.printf("\nEnter the amount to transfer (max $%.2f) : $", balance);
                amount = scanner.nextDouble();
                if (amount < 0) {
                    System.out.println("Amount must be greater than zero.");
                } else if (amount > balance) {
                    System.out.printf("Amount must not be greater than balance of $%.2f.\n", balance);
                }
            } while (amount < 0 || amount > balance);
            scanner.nextLine();
            System.out.print("Enter a note : ");
            String note = scanner.nextLine();
            Account savingsAccount = bank.getAccount(currentAccount);
            savingsAccount.addTransaction(amount, typeOfTransaction, note);
            amount *= -1;
            currentAccount.addTransaction(amount, typeOfTransaction, note);
        } else if (className.compareTo("SavingsAccount") == 0) {
            System.out.println("\nSavings account can't transfer to a checking account.");
        }
        // SimpleATM.returnToMainMenu(currentAccount, scanner, bank);
        SimpleATM.mainMenu(currentAccount, scanner, bank);
    }

    // Show the transaction history of the account
    public static void showTransactionHistory(Account currentAccount, Scanner scanner, Bank bank) {
        currentAccount.printTransactionHistory();
        // SimpleATM.returnToMainMenu(currentAccount, scanner, bank);
        SimpleATM.mainMenu(currentAccount, scanner, bank);
    }

    // Switch from checking account to savings account and vice versa
    public static void switchAccount(Account currentAccount, Scanner scanner, Bank bank) {
        currentAccount = bank.getAccount(currentAccount);
        String className = currentAccount.getClass().getName();
        if (className.compareTo("CheckingAccount") == 0) {
            System.out.println("\nSwitched to checking account.");
        } else if (className.compareTo("SavingsAccount") == 0) {
            System.out.println("\nSwitched to savings account.");
        }
        // SimpleATM.returnToMainMenu(currentAccount, scanner, bank);
        SimpleATM.mainMenu(currentAccount, scanner, bank);
    }
}
