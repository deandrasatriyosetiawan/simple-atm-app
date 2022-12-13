import java.util.*;

public class User {
  // The full name of the user
  private String fullName;
  // The first name of the user
  private String firstName;
  // The user identity number of the user
  private String userId;
  // The bank card identity number of the user
  private ArrayList<String> bankCardIdLists;
  // The checking account of the user
  private ArrayList<CheckingAccount> checkingAccounts;
  // The savings account of the user
  private ArrayList<SavingsAccount> savingsAccounts;

  // Initialize values to the attribute of the user
  public User(String fullName, String pin, Bank bank) {
    // Set user's full name
    this.fullName = fullName;
    // Get a new user identity number for the user
    this.userId = Bank.getNewUserId;
    // Get a bank new card identity number for the user
    // and add it to user's list of bank card identity number
    String bankCardId = Bank.getNewBankCardId;
    this.bankCardIdLists.add(bankCardId);
    // Create empty list of user's checking accounts
    this.checkingAccounts = new ArrayList<CheckingAccount>();
    // Create empty list of user's savings accounts
    this.savingsAccounts = new ArrayList<SavingsAccount>();
    // Print the log message
    System.out.printf("New user " + fullName + " with ID " + this.userId + " created");
  }

}
