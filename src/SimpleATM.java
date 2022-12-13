import java.util.*;

public class SimpleATM {
    public static void main(String[] args) throws Exception {
        // Scanner object for getting input from keyboard
        Scanner scanner = new Scanner(System.in);
        // Initialize the name of our bank
        Bank bank = new Bank("Pochita Bank");
        // Add a user to our bank
        User user = bank.addUser("Anya Forger", "12345");
        // The interface of the ATM
        while (true) {
            // The reference for users who will log in
            User currentUser;
            // Validating user who will log in
            currentUser = ATM.validateUser(bank, scanner);
            // Serving users who have successfully logged in
            ATM.mainMenu(currentUser, scanner);
        }
    }

    public static User validateUser() {

    }

    // Display the main menu of the simple ATM
    public static void mainMenu(User currentUser, ) {
        System.out.println("[1] Change PIN");
        System.out.println("[2] Balance Inquiry");
        System.out.println("[3] Withdrawal");
        System.out.println("[4] Deposit");
        System.out.println("[5] Transfer");
        System.out.println("[6] Transaction History");
        System.out.println("[7] Switch Account");
        System.out.println("[8] Exit");
    }
}
