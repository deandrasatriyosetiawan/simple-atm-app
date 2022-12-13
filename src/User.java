import java.util.*;

public class User {
  // The full name of the user
  private String fullName;
  // The first name of the user
  private String firstName;
  // The user identity number of the user
  private String userId;
  // The bank card identity number of the user
  private ArrayList<String> bankCardIdList;
  // The checking account of the user
  private ArrayList<CheckingAccount> checkingAccounts;
  // The savings account of the user
  private ArrayList<SavingsAccount> savingsAccounts;

  // Initialize values to the attribute of the user
  public User(String fullName, String pin, Bank bank) {
    this.fullName = fullName;
    this.userId = bank.getNewUserId();
    String bankCardId = bank.getNewBankCardId();
    bank.addBankCardId(bankCardId);
    this.checkingAccounts = new ArrayList<CheckingAccount>();
    this.savingsAccounts = new ArrayList<SavingsAccount>();
    System.out.printf("New user " + fullName + " with ID " + this.userId + " created");
  }

  // Getting the user identity number
  public String getUserId() {
    return userId;
  }

}
