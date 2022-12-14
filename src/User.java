import java.util.*;

public class User {
  // The full name of the user
  private String fullName;
  // The first name of the user
  private String firstName;
  // The user id number of the user
  private String userId;
  // The list of user's bank card id number
  private ArrayList<String> bankCardIdList;
  // The checking account of the user
  private ArrayList<CheckingAccount> checkingAccounts;
  // The savings account of the user
  private ArrayList<SavingsAccount> savingsAccounts;

  // Set a user's fullname, user's id, user's bank card id,
  // create an empty lists of checking accounts and saving accounts,
  // and also print an information that a new user has been created
  public User(String fullName, String bankCardId, Bank bank) {
    this.checkingAccounts = new ArrayList<CheckingAccount>();
    this.savingsAccounts = new ArrayList<SavingsAccount>();
    this.bankCardIdList = new ArrayList<String>();
    this.userId = bank.getNewUserId();
    this.fullName = fullName;
    this.setFirstName(fullName);
    this.bankCardIdList.add(bankCardId);
    // System.out.println("New user " + fullName + " has been created");
    // System.out.println("with user ID\t: " + this.userId);
    // System.out.println("with bank card ID\t: " + bankCardId);
    this.printUserInfo();
  }

  // Print an information that a new user has been created
  public void printUserInfo() {
    String bankCardId = this.bankCardIdList.get(bankCardIdList.size() - 1);
    System.out.println("New user " + this.fullName + " has been created");
    System.out.println("with user ID\t: " + this.userId);
    System.out.println("with bank card ID\t: " + bankCardId);
  }

  // Get the user id number
  public String getUserId() {
    return this.userId;
  }

  // Set the user's first name
  public void setFirstName(String fullName) {
    fullName = fullName.trim();
    this.firstName = "";
    for (int index = 0; index < fullName.length(); index++) {
      if (fullName.charAt(index) == ' ')
        break;
      this.firstName += fullName.charAt(index);
    }
  }

  // Get the user's first name
  public String getFirstName() {
    return this.firstName;
  }

  // Add a new checking account and savings account of the user
  public void addAccount(CheckingAccount newCheckingAccount, SavingsAccount newSavingsAccount) {
    this.checkingAccounts.add(newCheckingAccount);
    this.savingsAccounts.add(newSavingsAccount);
  }

  // Add a new bank card id number to the list of user's bank card id number
  public void addBankCardId(String newBankCardId) {
    this.bankCardIdList.add(newBankCardId);
  }

  // Print summary for the account of this user
  // public void printAccountSummary(CheckingAccount checkingAccount) {
  // System.out.print("\n\n" + this.getFirstName() + "'s ");
  // System.out.println(checkingAccount.getSummaryLine() + "\n");
  // }

}
