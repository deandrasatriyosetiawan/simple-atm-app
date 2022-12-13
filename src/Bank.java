import java.util.*;

public class Bank {
  // The name of the bank
  private String name;
  // The list of users in this bank
  private ArrayList<User> users;
  // The list of checking accounts in this bank
  private ArrayList<CheckingAccount> checkingAccounts;
  // The list of savings accounts in this bank
  private ArrayList<SavingsAccount> savingsAccounts;

  // Generate a new user identity number for a user
  public String getNewUserId() {
  }

  // Generate a new account identity number for an account
  public String getNewAccountId() {
  }

  // Generate a new bank card identity number for a user's card
  public String getNewBankCardId() {
  }

  // Add a user in this bank
  public User addUser(String fullName, String pin) {
    // Create a new User object and add it to our list of users
    User newUser = new User(fullName, pin, this);
    this.users.add(newUser);
    // Create a checking account for the user
    // and add it to our list of checking accounts
    CheckingAccount newCheckingAccount = new CheckingAccount(accountId, pin, newUser);
    // Create a savings account for the user
    // and add it to our list of savings accounts
    SavingsAccount newSavingsAccount = new SavingsAccount(accountId, pin, newUser);
  }

}
